import org.apache.ofbiz.entity.GenericEntityException
import org.apache.ofbiz.entity.GenericValue
import org.apache.ofbiz.service.ServiceUtil

def createProductionRunGroovy() {

    Map result = success()
    createProductionRunMap = [:]

    GenericValue userLogin = context.userLogin
    String productId = context.productId
    String pRQuantity = context.pRQuantity
    String startDate = context.startDate
    String facilityId = context.facilityId
    String routingId = context.routingId
    String workEffortName = context.workEffortName
    String description = context.description


    try {
        createProductionRunMap.userLogin = userLogin
        createProductionRunMap.productId = productId
        createProductionRunMap.pRQuantity = pRQuantity
        createProductionRunMap.startDate = startDate
        createProductionRunMap.facilityId = facilityId
        createProductionRunMap.routingId = routingId
        createProductionRunMap.workEffortName = workEffortName
        createProductionRunMap.description = description
        Map serviceResult = dispatcher.runSync("createProductionRun", createProductionRunMap)
        if (ServiceUtil.isError(serviceResult)) {
            println(ServiceUtil.getErrorMessage(serviceResult))
            return error(ServiceUtil.getErrorMessage(serviceResult))
        }
        result.productionRunId = serviceResult?.productionRunId
    } catch (GenericEntityException e) {
        println("" + e)
    }
    return result
}