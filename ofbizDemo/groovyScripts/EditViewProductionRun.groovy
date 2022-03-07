import com.companyname.ofbizdemo.services.ProductionRun

productionRunId = parameters.productionRunId

if (productionRunId) {
    ProductionRun productionRun = new ProductionRun(productionRunId, delegator, dispatcher)
    if (productionRun.exist()) {
        productionRunId = productionRun.getGenericValue().workEffortId
        //context.productionRunId = productionRunId
        context.productionRun = productionRun.getGenericValue()
        // Prepare production run header data
        productionRunData = [:]
        productionRunData.productionRunId = productionRunId

        productionRunData.productId = productionRun.getProductProduced().productId
        productionRunData.currentStatusId = productionRun.getGenericValue().currentStatusId
        productionRunData.facilityId = productionRun.getGenericValue().facilityId
        productionRunData.workEffortName = productionRun.getProductionRunName()
        productionRunData.description = productionRun.getDescription()
        productionRunData.quantity = productionRun.getQuantity()
        productionRunData.estimatedStartDate = productionRun.getEstimatedStartDate()
        productionRunData.estimatedCompletionDate = productionRun.getEstimatedCompletionDate()
//        product = productionRun.productId
//        product = from("Product").where("productId", productId).queryOne()
//        productName = product.internalName
        context.productionRunData = productionRunData










    }
}
