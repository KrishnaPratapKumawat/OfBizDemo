package com.companyname.ofbizdemo.services;
import java.util.HashMap;
import java.util.Map;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.*;


public class OfbizDemoServices {

    public static final String module = OfbizDemoServices.class.getName();

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
//                Debug.logInfo(ServiceUtil.getErrorMessage(resultsMap), module);
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
}