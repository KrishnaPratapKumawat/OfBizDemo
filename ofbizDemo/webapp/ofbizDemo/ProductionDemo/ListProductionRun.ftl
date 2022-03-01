
<div class="screenlet">
    <div class="screenlet-title-bar">
        <ul>
            <li class="h3">${uiLabelMap.SearchResult}</li>
        </ul>
    </div>

    <div class="screenlet-body">

        <table width="100%" class="basic-table hover-bar" cellspacing="0">
            <thead>
            <tr class="head-">
                <td width="20%">${uiLabelMap.ProductionRunId}</td>
                <td width="25%">${uiLabelMap.ProductionRunName}</td>
                <td width="20%">${uiLabelMap.ProductId}</td>
                <td width="20%">${uiLabelMap.Quantity}</td>
                <td width="20%">${uiLabelMap.QuantityUOM}</td>
                <td width="20%">${uiLabelMap.Status}</td>
                <td width="20%">${uiLabelMap.StartDate}</td>
                <td width="25%">${uiLabelMap.Description}</td>
                <td width="20%">${uiLabelMap.SearchResult}</td>
            </tr>
            </thead>
            <tbody >
            <#if productionRunList?has_content>
            <#list productionRunList as productionRun>
            <tr>
                <td width="20%"><a href="<@ofbizUrl controlPath='/manufacturing/control'>EditProductionRun?productionRunId=${productionRun.workEffortId!}</@ofbizUrl>" class="smallSubmit">${productionRun.workEffortId!}</a></td>
                <td width="25%">${productionRun.workEffortName!}</td>
                <td width="20%"><a href="<@ofbizUrl controlPath='/catalog/control'>EditProduct?productId=${productionRun.productId!}</@ofbizUrl>" class="smallSubmit">${productionRun.productId!}</a></td>
                <td width="20%">${productionRun.estimatedQuantity!}</td>
                <td width="20%"></td>
                <td width="20%">${productionRun.statusDescription}</td>
                <td width="20%">${productionRun.estimatedStartDate!}</td>
                <td width="25%">${productionRun.description!}</td>
                <td width="25%"${productionRun.facilityId!}></td>

            </tr>
            </#list>
        </#if>
        </tbody>
        </table>
    </div>
</div>