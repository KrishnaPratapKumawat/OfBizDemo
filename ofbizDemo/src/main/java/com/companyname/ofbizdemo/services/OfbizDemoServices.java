package com.companyname.ofbizdemo.services;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.manufacturing.jobshopmgt.ProductionRun;
import org.apache.ofbiz.service.*;


public class OfbizDemoServices {

    public static final String module = OfbizDemoServices.class.getName();
    public static final String resource = "ofbizDemoUiLabels.xml";

    public static Map<String, Object> createOfbizDemo(DispatchContext dctx, Map<String, ? extends Object> context) {
        Debug.log("+++++++++++++++++++++++++++++++");
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        try {
            GenericValue ofbizDemo = delegator.makeValue("OfbizDemo");
            // Auto generating next sequence of ofbizDemoId primary key
            ofbizDemo.setNextSeqId();
            // Setting up all non primary key field values from context map
            ofbizDemo.setNonPKFields(context);
            // Creating record in database for OfbizDemo entity for prepared value
            ofbizDemo = delegator.create(ofbizDemo);
            result.put("ofbizDemoId", ofbizDemo.getString("ofbizDemoId"));
            Debug.log("==========This is my first Java Service implementation in Apache OFBiz. OfbizDemo record created successfully with ofbizDemoId: " + ofbizDemo.getString("ofbizDemoId"));
        } catch (GenericEntityException e) {
            Debug.logError(e, module);
            return ServiceUtil.returnError("Error in creating record in OfbizDemo entity ........" + module);
        }
        return result;
    }

    public static Map<String, Object> createProductionRunJava(DispatchContext dctx,
                                                              Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        LocalDispatcher dispatcher = dctx.getDispatcher();

        try {
            Map<String, Object> createProductionRunMap = new HashMap<>();
            createProductionRunMap = dctx.getModelService("createProductionRun").makeValid(context,
                    ModelService.IN_PARAM);
            Map<String, Object> resultsMap = dispatcher.runSync("createProductionRun",
                    createProductionRunMap);
            if (ServiceUtil.isError(resultsMap)) {
                return ServiceUtil.returnError(ServiceUtil.getErrorMessage(resultsMap));
            }
            String productionRunId = (String) resultsMap.get("productionRunId");
            result.put("productionRunId", productionRunId);
        } catch (GenericServiceException e) {
            Debug.log(e, module);
            return ServiceUtil.returnError("Error in creating record in WorkEffort entity ........" +
                    module);
        }
        return result;
    }
//    =============================================UpdateProductionRun==================================================

    public static Map<String, Object> updateProductionRun(DispatchContext ctx, Map<String, ? extends Object> context) {
        Debug.log("=========================================================");
        Delegator delegator = ctx.getDelegator();
        LocalDispatcher dispatcher = ctx.getDispatcher();
        Locale locale = (Locale) context.get("locale");
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        String productionRunId = (String) context.get("productionRunId");

        if (UtilValidate.isNotEmpty(productionRunId)) {
            ProductionRun productionRun = new ProductionRun(productionRunId, delegator, dispatcher);
            if (productionRun.exist()) {

                if (!"PRUN_CREATED".equals(productionRun.getGenericValue().getString("currentStatusId")) &&
                        !"PRUN_SCHEDULED".equals(productionRun.getGenericValue().getString("currentStatusId"))) {
                    return ServiceUtil.returnError(UtilProperties.getMessage(resource, "ProductionRunPrinted", locale));
                }

                BigDecimal quantity = (BigDecimal) context.get("quantity");
                if (quantity != null && quantity.compareTo(productionRun.getQuantity()) != 0) {
                    productionRun.setQuantity(quantity);
                }

                Timestamp estimatedStartDate = (Timestamp) context.get("estimatedStartDate");
                if (estimatedStartDate != null && !estimatedStartDate.equals(productionRun.getEstimatedStartDate())) {
                    productionRun.setEstimatedStartDate(estimatedStartDate);
                }

                String workEffortName = (String) context.get("workEffortName");
                if (workEffortName != null) {
                    productionRun.setProductionRunName(workEffortName);
                }

                String description = (String) context.get("description");
                if (description != null) {
                    productionRun.setDescription(description);
                }

                String facilityId = (String) context.get("facilityId");
                if (facilityId != null) {
                    productionRun.getGenericValue().set("facilityId", facilityId);
                }

                boolean updateEstimatedOrderDates = productionRun.isUpdateCompletionDate();
                if (productionRun.store()) {
                    if (updateEstimatedOrderDates && "PRUN_SCHEDULED".equals(productionRun.getGenericValue().getString("currentStatusId"))) {
                        try {
                            Map<String, Object> result = dispatcher.runSync("setEstimatedDeliveryDates",
                                    UtilMisc.toMap("userLogin", userLogin));
                            if (ServiceUtil.isError(result)) {
                                return ServiceUtil.returnError(ServiceUtil.getErrorMessage(result));
                            }
                        } catch (GenericServiceException e) {
                            Debug.logError(e, "Problem calling the setEstimatedDeliveryDates service", module);
                            return ServiceUtil.returnError(UtilProperties.getMessage(resource, "ProductionRunNotUpdated", locale));
                        }
                    }
                    return ServiceUtil.returnSuccess();
                } else {
                    Debug.logError("productionRun.store() fail for productionRunId =" + productionRunId, module);
                    return ServiceUtil.returnError(UtilProperties.getMessage(resource, "ProductionRunNotUpdated", locale));
                }
            }
            else {
                Debug.logError("no productionRun for productionRunId =" + productionRunId, module);
                return ServiceUtil.returnError(UtilProperties.getMessage(resource, "ProductionRunNotUpdated", locale));
            }
        }
        else {
            Debug.logError("service updateProductionRun call with productionRunId empty", module);
            return ServiceUtil.returnError(UtilProperties.getMessage(resource, "ProductionRunNotUpdated", locale));
        }
    }
}