import org.apache.ofbiz.entity.condition.EntityConditionBuilder

exprBuilder = new EntityConditionBuilder()

condition = exprBuilder.AND() {
    EQUALS(workEffortTypeId: "PROD_ORDER_HEADER")
    if (parameters.workEffortId) {
        EQUALS(workEffortId: parameters.workEffortId)
    }
    if (parameters.productId) {
        EQUALS(productId: parameters.productId)
    }
    if (parameters.currentStatusId) {
        if (parameters.currentStatusId.size() == 1) {
            EQUALS(currentStatusId: parameters.currentStatusId)
        } else {
            IN(currentStatusId: parameters.currentStatusId)
        }
    }
    if (parameters.workEffortName){
        LIKE(workEffortName: parameters.workEffortName + "%")
    }
    if (parameters.facilityId){
        EQUALS(facilityId: parameters.facilityId)
    }
}
workEffortAndGoods = from("WorkEffortAndGoods").where(condition).queryList()
workEfforts = []
workEffortAndGoods.each {workEffortAndGood ->
    workEffort = [:]
    workEffort.putAll(workEffortAndGood)
    statusItem = from("StatusItem").where(statusId: workEffortAndGood.currentStatusId).queryOne();
    workEffort.statusDescription = statusItem.description
    workEfforts.add(workEffort)
}
context.productionRunList = workEfforts
