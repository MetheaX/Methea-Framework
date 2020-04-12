<#-- ##### -->
<#-- @thead #List<String> #Define table head's label -->
<#-- @tdata #List of JSON #Define table data -->
<#-- @tcolumns #List<String> #Define keys of JSON tdata -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 1/12/2020
#-->
<#macro table thead tdata tcolumns>
    <table class="table">
        <thead>
        <tr>
            <#list thead as th>
                <#if th == "id">
                    <th hidden>${th}</th>
                <#else>
                    <th>${th}</th>
                </#if>
            </#list>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <#list tdata.data as td>
            <#assign statusClassName = "badge badge-success">
            <#assign statusLabel = "Active">
            <#assign buttonLabel = "Disable">
            <#assign buttonClassName = "badge-danger btn-account-disable">
            <tr>
                <#list tcolumns as col>
                    <#if col == "status">
                        <#if td[col] == "I">
                            <#assign statusClassName = "badge badge-danger">
                            <#assign statusLabel = "Inactive">
                            <#assign buttonLabel = "Enable">
                            <#assign buttonClassName = "badge-warning btn-account-enable">
                        </#if>
                        <td>
                            <label class="${statusClassName}">${statusLabel}</label>
                        </td>
                    <#elseif col == "id">
                        <td hidden class="id">${td[col]}</td>
                    <#else>
                        <td>${td[col]}</td>
                    </#if>
                </#list>
                <td>
                    <button class="btn badge ${buttonClassName}">${buttonLabel}</button>
                    <button class="btn badge badge-success btn-data-edit" data-toggle="modal" data-target=".modal-edit">Edit</button>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</#macro>