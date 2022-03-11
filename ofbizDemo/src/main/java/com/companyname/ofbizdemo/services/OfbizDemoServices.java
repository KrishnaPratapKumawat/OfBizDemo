package com.companyname.ofbizdemo.services;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.*;

public class OfbizDemoServices {
    public static final String module = OfbizDemoServices.class.getName();
    public static final String resource = "ofbizDemoUiLabels.xml";
    public static Map<String, Object> createOfbizDemo(DispatchContext dctx, Map<String, ? extends Object> context) {
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
//    =============================================CreateProductionRun==================================================

    public static Map<String, Object> createProductionRunJava(DispatchContext dctx,
                                                              Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess("Created your data SuccessFully.....................");
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        LocalDispatcher dispatcher = dctx.getDispatcher();
        String productionRunId;
        try {
            Map<String, Object> createProductionRunMap = dctx.getModelService("createProductionRun").makeValid(context,
                    ModelService.IN_PARAM);
            Map<String, Object> resultsMap = dispatcher.runSync("createProductionRun",createProductionRunMap);
            if (ServiceUtil.isSuccess(resultsMap)) {
                productionRunId = (String) resultsMap.get("productionRunId");
                result.put("productionRunId", productionRunId);
            }
        } catch (GenericServiceException e) {
            Debug.log(e, module);
            return ServiceUtil.returnError("Error in creating record........................." +
                    module);
        }

        return result;
    }
//    =============================================UpdateProductionRun==================================================

    public static Map<String, Object> updateProductionRunDemo(DispatchContext ctx, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        LocalDispatcher dispatcher = ctx.getDispatcher();
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        String productionRunId = (String) context.get("productionRunId");
        try {
            Map<String, Object> productionRunData;
            productionRunData = ctx.getModelService("updateProductionRun").makeValid(context,
                    ModelService.IN_PARAM);
            Map<String, Object> resultsMap = dispatcher.runSync("updateProductionRun", productionRunData);
            if (ServiceUtil.isSuccess(resultsMap)) {
                 productionRunId = (String) resultsMap.get("productionRunId");
                result.put("productionRunId", productionRunId);
                return ServiceUtil.returnSuccess("SuccessFully UpdateRecord..........");
            }
        } catch (GenericServiceException e) {
            Debug.log(e, module);
            return ServiceUtil.returnError("Error in UpdateRecord............");
        }
        result.put("productionRunId", productionRunId);
        return result;
    }
}