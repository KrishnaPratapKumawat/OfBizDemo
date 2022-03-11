package com.companyname.ofbizdemo.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ServiceUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfbizDemoEvents {

    public static final String module = OfbizDemoEvents.class.getName();

    public static String createOfbizDemoEvent(HttpServletRequest request, HttpServletResponse response) {
        Delegator delegator = (Delegator) request.getAttribute("delegator");
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        GenericValue userLogin = (GenericValue) request.getSession().getAttribute("userLogin");

        String ofbizDemoTypeId = request.getParameter("ofbizDemoTypeId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (UtilValidate.isEmpty(firstName) || UtilValidate.isEmpty(lastName)) {
            String errMsg = "First Name and Last Name are required fields on the form and can't be empty.";
            request.setAttribute("_ERROR_MESSAGE_", errMsg);
            return "error";
        }
        String comments = request.getParameter("comments");

        try {
            Debug.logInfo("=======Creating OfbizDemo record in event using service createOfbizDemoByGroovyService=========", module);
            dispatcher.runSync("createOfbizDemoByGroovyService", UtilMisc.toMap("ofbizDemoTypeId", ofbizDemoTypeId,
                    "firstName", firstName, "lastName", lastName, "comments", comments, "userLogin", userLogin));
        } catch (GenericServiceException e) {
            String errMsg = "Unable to create new records in OfbizDemo entity: " + e.toString();
            request.setAttribute("_ERROR_MESSAGE_", errMsg);
            return "error";
        }
        request.setAttribute("_EVENT_MESSAGE_", "OFBiz Demo created succesfully.");
        return "success";
    }

    /*  Creating Create Production Run  Event */

    public static String createProductionRunEvent(HttpServletRequest request, HttpServletResponse response) {
        Delegator delegator = (Delegator) request.getAttribute("delegator");
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        GenericValue userLogin = (GenericValue) request.getSession().getAttribute("userLogin");
        String productId = request.getParameter("productId");
        int pRQuantity = Integer.parseInt(request.getParameter("pRQuantity"));
        String startDate = request.getParameter("startDate");
        String facilityId = request.getParameter("facilityId");
        String routingId = request.getParameter("routingId");
        String workEffortName = request.getParameter("workEffortName");
        String description = request.getParameter("description");

        List<String> errorMsgList = new ArrayList<>();
        String errorMsg;
        String productionRunId;

        if (UtilValidate.isEmpty(productId)) {
            errorMsg = "Product Id is Missing";
            errorMsgList.add(errorMsg);
        }
        if (UtilValidate.isEmpty(pRQuantity)) {
            errorMsg = "Quantity is Missing";
            errorMsgList.add(errorMsg);
        }
        if (UtilValidate.isEmpty(startDate)) {
            errorMsg = "StartDate is Missing";
            errorMsgList.add(errorMsg);
        }
        if (UtilValidate.isNotEmpty(errorMsgList)) {
            request.setAttribute("_ERROR_MESSAGE_LIST_", errorMsgList);
            return "error";
        }


        try {
            Debug.logInfo("=======Creating Screen record in event using", module);
            Map<String, Object> createProductionRunMap = new HashMap<>();
            createProductionRunMap.put("userLogin", userLogin);
            createProductionRunMap.put("productId", productId);
            createProductionRunMap.put("pRQuantity", pRQuantity);
            createProductionRunMap.put("startDate", startDate);
            createProductionRunMap.put("facilityId", facilityId);
            createProductionRunMap.put("routingId", routingId);
            createProductionRunMap.put("workEffortName", workEffortName);
            createProductionRunMap.put("description", description);
            Map<String, Object> resultsMap = dispatcher.runSync("createProductionRunJavaService", createProductionRunMap);
            if (ServiceUtil.isSuccess(resultsMap)) {
                productionRunId = (String) resultsMap.get("productionRunId");
                request.setAttribute("productionRunId", productionRunId);
            }


        } catch (GenericServiceException e) {
            Debug.logInfo("Error", module, e);
            request.setAttribute("_ERROR_MESSAGE_", e.getMessage());
            return "error";
        }
        request.setAttribute("_EVENT_MESSAGE_", "Created Your Record SuccessFully.....................");
        return "Success";
    }
}