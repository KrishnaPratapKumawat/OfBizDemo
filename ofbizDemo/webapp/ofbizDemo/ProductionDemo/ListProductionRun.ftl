
<div class="screenlet">
    <div class="screenlet-title-bar">
        <ul>
            <li class="h3">${uiLabelMap.SearchResult}</li>
        </ul>
    </div>

    <div class="screenlet-body">

        <table width="100%" class="basic-table hover-bar" cellspacing="0">
            <thead>
                <tr class="header-row">
                    <th width="10%">${uiLabelMap.ProductionRunId}</th>
                    <th width="15%">${uiLabelMap.ProductionRunName}</th>
                    <th width="10%">${uiLabelMap.ProductId}</th>
                    <th width="10%">${uiLabelMap.Quantity}</th>
                    <th width="10%">${uiLabelMap.Status}</th>
                    <th width="10%">${uiLabelMap.StartDate}</th>
                    <th width="15%">${uiLabelMap.Description}</th>
                    <th width="10%">${uiLabelMap.FacilityId}</th>
                </tr>
            </thead>
            <tbody >
            <#list productionRunList as productionRun>
            <tr>
                <td width="10%"><a href="<@ofbizUrl controlPath='/manufacturing/control'>EditProductionRun?productionRunId=${productionRun.workEffortId!}</@ofbizUrl>" class="smallSubmit">${productionRun.workEffortId!}</a></td>
                <td width="15%">${productionRun.workEffortName!}</td>
                <td width="10%"><a href="<@ofbizUrl controlPath='/catalog/control'>EditProduct?productId=${productionRun.productId!}</@ofbizUrl>" class="smallSubmit">${productionRun.productId!}</a></td>
                <td width="10%">${productionRun.estimatedQuantity!}</td>
                <td width="10%">${productionRun.statusDescription}</td>
                <td width="10%">${productionRun.estimatedStartDate!}</td>
                <td width="15%">${productionRun.description!}</td>
                <td width="15%">${productionRun.facilityId!}</td>
            </tr>
            </#list>
        </tbody>
        </table>
    </div>
</div>