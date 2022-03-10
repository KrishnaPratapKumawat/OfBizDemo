<div class="screenlet">
    <div class="screenlet-title-bar">
        <ul>
            <li class="h3">${uiLabelMap.CreateProductionRun}</li>
        </ul>
        <br/>
    </div>
    <div class="screenlet-body">
        <form name="CreateProductionRunFromByEvent" method="post" action="<@ofbizUrl>createProductionRunEvent</@ofbizUrl>">
            <table width="100%">
                <tr>
                    <td width="15%" align="right"><span class="label" >${uiLabelMap.ProductId}</span></td>
                    <td width="2%"></td>
                    <td width="83%"><@htmlTemplate.lookupField value="${productId!}" formName="CreateProductionRunFromByEvent" name="productId" id="productId" fieldFormName="LookupProduct"/></td>

                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label" >${uiLabelMap.Quantity!}</span></td>
                    <td width="2%"></td>
                    <td width="83%"><input type="text" name="pRQuantity" value="${pRQuantity!}"/></td>
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label" >${uiLabelMap.StartDate}</span></td>
                    <td width="2%"></td>
                    <td width="83%"><@htmlTemplate.renderDateTimeField name="startDate" event="" action="" className="" alert="" title="Format: yyyy-MM-dd HH:mm:ss.SSS" value="${parameters.startDate!}" size="" maxlength="50" id="fromDate_1" dateType="date" shortDateInput=false timeDropdownParamName="" defaultDateTimeString="" localizedIconTitle="" timeDropdown="" timeHourName="" classString="" hour1="" hour2="" timeMinutesName="" minutes="" isTwelveHour="" ampmName="" amSelected="" pmSelected="" compositeType="" formName=""/></td>
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label">${uiLabelMap.FacilityId}</span></td>
                    <td width="2%"></td>
                    <td width="83%">
                        <select name="facilityId">
                            <#list facilities as facility>
                            <option value="${facility.facilityId!}">${facility.facilityName}[${facility.facilityId}]</option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label" >${uiLabelMap.RoutingId}</span></td>
                    <td width="2%"></td>
                    <td width="83%"><@htmlTemplate.lookupField value="" formName="CreateProductionRunFromByEvent" name="routingtId" id="routingId" fieldFormName="LookupRouting"/></td>
                    <!--                    <td width="83%"><input type="text"/></td>-->
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label" >${uiLabelMap.WorkEffortName}</span></td>
                    <td width="2%"></td>
                    <td width="83%"><input type="text" name="workEffortName"/></td>
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label" >${uiLabelMap.Description}</span></td>
                    <td width="2%"></td>
                    <td width="83%"><input type="text" name="description"/></td>
                </tr>
                <tr>
                    <td width="15%"></td>
                    <td width="2%"></td>
                    <td width="83%" align="left"><input type="submit" value="${uiLabelMap.CreateButton}"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>