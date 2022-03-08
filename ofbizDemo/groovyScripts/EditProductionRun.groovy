import org.apache.ofbiz.com.companyname.ofbizdemo.services.ProductionRun
import org.apache.ofbiz.manufacturing.jobshopmgt.ProductionRun

productionRunId = parameters.productionRunId
if (productionRunId) {
    ProductionRun productionRun = new ProductionRun(productionRunId, delegator, dispatcher)
    if (productionRun.exist()) {
        productionRunId = productionRun.getGenericValue().workEffortId
        context.productionRunId = productionRunId
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

        manufacturer = from("WorkEffortPartyAssignment").where("workEffortId", productionRunId, "roleTypeId", "MANUFACTURER").filterByDate().queryFirst()
        if (manufacturer){
            productionRunData.manufacturerId = manufacturer.partyId
        }
        context.productionRunData = productionRunData

        // Find all the order items to which this production run is linked.
        orderItems = from("WorkOrderItemFulfillment").where("workEffortId", productionRunId).queryList()
        if (orderItems) {
            context.orderItems = orderItems
        }
        //  RoutingTasks list
        context.productionRunRoutingTasks = productionRun.getProductionRunRoutingTasks()
        context.quantity = productionRun.getQuantity() // this is useful to compute the total estimates runtime in the form
        //  Product component/parts list
        context.productionRunComponents = productionRun.getProductionRunComponents()

        // Find all the notes linked to this production run.
        productionRunNoteData = from("WorkEffortNoteAndData").where("workEffortId", productionRunId).queryList()
        if (productionRunNoteData) {
            context.productionRunNoteData = productionRunNoteData
        }
    }
}
