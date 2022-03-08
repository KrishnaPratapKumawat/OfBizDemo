<div>
    <ul>
        <li class="h1">${uiLabelMap.FindProductionRun}</li>
    </ul>
    <a href="<@ofbizUrl>CreateProduction</@ofbizUrl>" target="_blank">
        <button>Create a Production Run</button>
    </a>
</div>
<br/>
<div class="screenlet">
    <div class="screenlet-title-bar">
        <ul>
            <li class="h3">${uiLabelMap.SearchOption}</li>
        </ul>
        <br class="clear"/>
    </div>
    <div class="screenlet-body">
        <div>
            <form name="FindProductionRun" action="<@ofbizUrl>FindProductionRun</@ofbizUrl>" method="post">
                <table width="100%">
                    <tr>
                        <td width="15%" align="right"><span class="label">${uiLabelMap.ProductionRunId}</span></td>
                        <td width="2%"></td>
                        <td width="83%"><input type="text" name="workEffortId" value="${parameters.workEffortId!}"/></td>
                    </tr>
                    <tr>
                        <td width="15%" align="right"><span class="label">${uiLabelMap.Status}</span></td>
                        <td width="2%"></td>
                        <td width="83%">
                            <#list statusItems as statusItem >
                            <input type="checkbox" name="currentStatusId"  value="${statusItem.description!}" <#if parameters.currentStatusId?has_content && statusItem.statusId.contains(parameters.currentStatusId)>checked</#if>/>
                        ${statusItem.description!}
                    </#list>
                    </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right"><span class="label">${uiLabelMap.ProductId}</span></td>
                        <td width="2%"></td>
                        <td width="83%"><@htmlTemplate.lookupField value="${parameters.productId!}" formName="CreateProductionRunFromByJava" name="productId" id="productId" fieldFormName="LookupProduct"/></td>
                    </tr>
                    <tr>
                        <td width="15%" align="right"><span class="label">${uiLabelMap.ProductionRunName}</span></td>
                        <td width="2%"></td>
                        <td width="83%"><input type="text" name="workEffortName" value="${parameters.workEffortName!}"/></td>
                    </tr>
                    <tr>
                        <td width="15%" align="right"><span class="label">${uiLabelMap.StartDate}</span></td>
                        <td width="2%"></td>
                        <td width="83%"><@htmlTemplate.renderDateTimeField name="estimatedStartDate" event="" action="" className="" alert="" title="Format: yyyy-MM-dd HH:mm:ss.SSS" value="${parameters.estimatedStartDate!}" size="" maxlength="50" id="fromDate_1" dateType="date" shortDateInput=false timeDropdownParamName="" defaultDateTimeString="" localizedIconTitle="" timeDropdown="" timeHourName="" classString="" hour1="" hour2="" timeMinutesName="" minutes="" isTwelveHour="" ampmName="" amSelected="" pmSelected="" compositeType="" formName=""/></td>
                    </tr>
                    <tr>
                        <td width="15%" align="right"><span class="label">${uiLabelMap.FacilityId}</span></td>
                        <td width="2%"></td>
                        <td width="83%">
                            <select name="facilityId">
                                <#list facilities as facility>
                                <option value="${facility.facilityId!}" <#if parameters.facilityId?has_content && parameters.facilityId == facility.facilityId>selected</#if>>${facility.facilityId!}</option>
                        </#list>
                        </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                        <td width="2%"></td>
                        <td width="83%">
                            <input type="submit" value="${uiLabelMap.OfbizDemoFind}"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>