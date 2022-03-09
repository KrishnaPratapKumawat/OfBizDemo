import org.apache.ofbiz.manufacturing.jobshopmgt.ProductionRun

productionRunId = parameters.productionRunId
if (productionRunId) {
    ProductionRun productionRun = new ProductionRun(productionRunId, delegator, dispatcher)
    if (productionRun.exist()) {
        productionRunId = productionRun.getGenericValue().workEffortId
        context.productionRunId = parameters.productionRunId
        context.productionRun = productionRun.getGenericValue()
        productionRunData = [:]
        productionRunData.productionRunId = productionRunId
        productId = productionRun.getProductProduced()?.productId
        productionRunData.productId = productId
        productionRunData.currentStatusId = productionRun.getGenericValue().currentStatusId
        productionRunData.facilityId = productionRun.getGenericValue().facilityId
        productionRunData.workEffortName = productionRun.getProductionRunName()
        productionRunData.description = productionRun.getDescription()
        productionRunData.quantity = productionRun.getQuantity()
        productionRunData.estimatedStartDate = productionRun.getEstimatedStartDate()
        productionRunData.estimatedCompletionDate = productionRun.getEstimatedCompletionDate()
        product = from("Product").where("productId",productId).queryOne()
        productionRunData.productName = product?.productName
          context.productionRunData = productionRunData
    }
}

