import com.companyname.ofbizdemo.services.ProductionRun

productionRunId = parameters.productionRunId ?: parameters.workEffortId
if (productionRunId) {
    ProductionRun productionRun = new ProductionRun(productionRunId, delegator, dispatcher)
    if (productionRun.exist()) {
        productionRunFixedAssetsData = []
        productionRunRoutingTasks = productionRun.getProductionRunRoutingTasks()
        productionRunRoutingTasks.each { prodRunTask ->
            taskFixedAssets = prodRunTask.getRelated("WorkEffortFixedAssetAssign", null, null, false)
            productionRunFixedAssetsData.addAll(taskFixedAssets)
        }
        context.productionRunFixedAssetsData = productionRunFixedAssetsData
    }
}