<div>
    <ul>
        <li class="h1">[ID ${parameters.productionRunId!}]</li>
    </ul>
    <div class="screenlet">
        <div class="screenlet-title-bar">
            <ul>
                <li class="h3">${uiLabelMap.ProductionRunId} ${parameters.productionRunId!}</li>
            </ul>
        </div>
        <form name="EditProductionRun" method="post" action="<@ofbizUrl>updateProductionRunDemo</@ofbizUrl>">
            <table>
                <tr>
                    <td width="15%" align="right"><span class="label">${uiLabelMap.ProductName!}</span></td>
                    <td width="2%"></td>
                    <td width="83%" align=""><span class="label">${productionRunData.productName} [${productionRunData.productId}]</span><input
                            type="hidden" name="${productionRunData.productId!}"/></td>
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label">${uiLabelMap.CurrentStatusId!}</span></td>
                    <td width="2%"></td>
                    <td width="83%" align=""><span class="label"> ${productionRun.currentStatusId!}</span></td>
                </tr>
                <input name="productionRunId" type="hidden" value="${productionRunData.productionRunId!}"/>
                <tr>
                    <td width="15%" align="right"><span class="label">${uiLabelMap.manufacturerID!}</span></td>
                    <td width="2%"></td>
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label">${uiLabelMap.FacilityId!}</span></td>
                    <td width="2%"></td>
                    <td width="83%">
                        <select name="facilityId">
                            <#list facilities as facility>
                            <option value="${facility.facilityId!}"
                            <#if parameters.facilityId?has_content && parameters.facilityId ==
                            facility.facilityId>selected</#if>>${facility.facilityName}[${facility.facilityId}]</option>
                    </#list>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label">${uiLabelMap.Quantity!}</span></td>
                    <td width="2%"></td>
                    <td width="83%"><input type="text" name="quantity" value="${productionRunData.quantity}"></td>
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label">${uiLabelMap.StartDate!}</span></td>
                    <td width="2%"></td>
                    <td width="83%">
                        <@htmlTemplate.renderDateTimeField name="estimatedStartDate" event="" action="" className=""
                        alert=""
                        title="Format: yyyy-MM-dd HH:mm:ss.SSS" value="${productionRunData.estimatedStartDate!}" size=""
                        maxlength="50"
                        id="fromDate_1" dateType="date" shortDateInput=false timeDropdownParamName=""
                        defaultDateTimeString=""
                        localizedIconTitle="" timeDropdown="" timeHourName="" classString="" hour1="" hour2=""
                        timeMinutesName="" minutes="" isTwelveHour="" ampmName="" amSelected="" pmSelected=""
                        compositeType=""
                        formName=""/>
                    </td>
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label">${uiLabelMap.estimatedCompletionDate!}</span>
                    </td>
                    <td width="2%"></td>
                    <td width="83%">${productionRunData.estimatedCompletionDate!}</td>
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label">${uiLabelMap.productionRun!}</span></td>
                    <td width="2%"></td>
                    <td width="83%"><input name="workEffortName" type="text"
                                           value="${productionRunData.workEffortName!}"/></td>
                </tr>
                <tr>
                    <td width="15%" align="right"><span class="label">${uiLabelMap.Description!}</span></td>
                    <td width="2%"></td>
                    <td width="83%"><input type="text" name="description" value="${productionRunData.description!}"/>
                    </td>
                </tr>
                </td>
                <td width="15%" align="center"><input type="submit" value="${uiLabelMap.submitButton!}"/></td>
            </table>
        </form>
    </div>
</div>