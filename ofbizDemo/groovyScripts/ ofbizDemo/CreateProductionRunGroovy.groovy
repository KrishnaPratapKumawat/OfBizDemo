import org.apache.ofbiz.entity.GenericEntityException
import org.apache.ofbiz.entity.GenericValue
import org.apache.ofbiz.service.ServiceUtil

def createProductionRunGroovy() {

    Map result = success("Created your data SuccessFully........................")
    createProductionRunMap = [:]

    GenericValue userLogin = parameters.userLogin
    productId = parameters.productId
    pRQuantity = parameters.pRQuantity
    startDate = parameters.startDate
    facilityId = parameters.facilityId
    routingId = parameters.routingId
    workEffortName = parameters.workEffortName
    description = parameters.description

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