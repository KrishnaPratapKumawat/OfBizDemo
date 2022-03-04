

import com.companyname.ofbizdemo.services.ProductionRun

productionRunId = parameters.productionRunId

if (productionRunId) {
    ProductionRun productionRun = new ProductionRun(productionRunId, delegator, dispatcher)
    if (productionRun.exist()) {
        productionRunId = productionRun.getGenericValue().workEffortId
        context.productionRunId = productionRunId
        context.productionRun = productionRun.getGenericValue()
        // Prepare production run header data
        productionRunData = [:]
//        productId = productionRunData.productId
//        product = from("Product").where("productId", productId).queryOne()
//        productName = product.internalName
//        println("=========================================================="+productName)
        productionRunData.productionRunId = productionRunId
        productionRunData.productId = productionRun.getProductProduced().productId
        productionRunData.currentStatusId = productionRun.getGenericValue().currentStatusId
        productionRunData.facilityId = productionRun.getGenericValue().facilityId
        productionRunData.workEffortName = productionRun.getProductionRunName()
        productionRunData.description = productionRun.getDescription()
        productionRunData.quantity = productionRun.getQuantity()
        productionRunData.estimatedStartDate = productionRun.getEstimatedStartDate()
        productionRunData.estimatedCompletionDate = productionRun.getEstimatedCompletionDate()
       // productionRunData.productName = productionRun.getinternalName()
//        manufacturer = from("WorkEffortPartyAssignment").where("workEffortId", productionRunId, "roleTypeId", "MANUFACTURER").filterByDate().queryFirst()
//        if (manufacturer){
//            productionRunData.manufacturerId = manufacturer.partyId
//        }
        context.productionRunData = productionRunData









    }
}
