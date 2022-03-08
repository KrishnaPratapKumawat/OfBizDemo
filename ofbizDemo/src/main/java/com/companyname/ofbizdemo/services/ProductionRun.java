package com.companyname.ofbizdemo.services;

import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.LocalDispatcher;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class ProductionRun {

        public static final String module = org.apache.ofbiz.manufacturing.jobshopmgt.ProductionRun.class.getName();
        public static final String resource = "ofbizDemoUiLabels";

        protected GenericValue productionRun;
        protected GenericValue productionRunProduct;
        protected GenericValue productProduced;
        protected BigDecimal quantity;
        protected Timestamp estimatedStartDate;
        protected Timestamp estimatedCompletionDate;
        protected String productionRunName;
        protected String description;
        protected GenericValue currentStatus;
        protected List<GenericValue> productionRunComponents;
        protected List<GenericValue> productionRunRoutingTasks;
        protected LocalDispatcher dispatcher;

    }
