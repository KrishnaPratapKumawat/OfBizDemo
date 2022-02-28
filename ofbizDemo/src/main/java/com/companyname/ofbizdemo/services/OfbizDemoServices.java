package com.companyname.ofbizdemo.services;
import java.util.HashMap;
import java.util.Map;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.*;
import org.apache.sis.util.Static;

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
    public static Map<String, Object> createProduction(DispatchContext dctx, Map<String, ? extends Object> context) {
        Debug.log("+++++++++++++++++++++++++++++++");
        Map<String,Object> result = ServiceUtil.returnSuccess();
        GenericValue userLogin = (GenericValue) context.get("userlogin");
        LocalDispatcher localDispatcher = dctx.getDispatcher();
        try{
            Map<String, Object> createProductionRunMap = dctx.getModelService("createProduction").makeValid(context, ModelService.IN_PARAM);
            createProductionRunMap.put("userLogin", userLogin);
            Map<String,Object> resultMap = localDispatcher.runSync("createProduction",createProductionRunMap);
            if (ServiceUtil.isError(resultMap)){
                return ServiceUtil.returnError(ServiceUtil.getErrorMessage(resultMap));
            }
            String productionRunId = (String) resultMap.get("productionRunId");
            result.put("productionRunId",productionRunId);

        }catch (GenericServiceException e) {
            Debug.log(e, module);
            Debug.log("+++++++++++++++++++++++++++++++");
            return ServiceUtil.returnError("Error creating record......"+module);
        }
        return result;

    }
}