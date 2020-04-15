<#-- ##### -->
<#-- @thead #List<String> #Define table head's label -->
<#-- @tdata #List of JSON #Define table data -->
<#-- @tcolumns #List<String> #Define keys of JSON tdata -->
<#-- @id #String #Define table id   -->
<#-- @filters #Map<String,String> #Define filter columns   -->
<#-- @urlFilters #String #Define endpoint filters   -->
<#-- @modalClass #String #Define pop up modal class   -->
<#-- @dataFilters #Object #Cache user filtering   -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 1/12/2020
#-->
<#import "../input/methea-filter-input.ftlh" as filter>
<#macro table thead tdata tcolumns filters dataFilters urlFilter modalClass id>
    <table class="table table-striped">
        <#if thead?has_content>
            <thead>
            <tr>
                <#list filters?keys as key>
                    <#assign dataKey = "${key}"?split("_")[1]>
                    <#if key == "filter_status">
                        <th><@filter.input inputType="text" inputPlaceholder="${filters[key]}" inputId="${key}${id}"
                            inputValue="${dataFilters[dataKey]}"></@filter.input></th>
                    <#else>
                        <th><@filter.input inputType="text" inputPlaceholder="${filters[key]}" inputId="${key}"
                            inputValue="${dataFilters[dataKey]}"></@filter.input></th>
                    </#if>
                </#list>
                <th>
                    <button class="btn btn-facebook" onclick="onFilter()">Search</button>
                    <button class="btn btn-google" onclick="onResetFilter()">Reset</button>
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="${modalClass}">Add New
                        <i class="mdi mdi-plus"></i></button>
                </th>
            </tr>
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
        </#if>
        <tbody id="${id}">
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
                    <button class="btn badge badge-success btn-data-edit" data-toggle="modal" data-target=".modal-edit">
                        Edit
                    </button>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <script>
        let requestURL = "";
        function onFilter() {
            <#list filters?keys as key>
                <#if key == "filter_status">
                    requestURL = requestURL + "${key}".split("_")[1] + "=" + $('#${key}${id}').val() + "&";
                <#else>
                    requestURL = requestURL + "${key}".split("_")[1] + "=" + $('#${key}').val() + "&";
                </#if>
            </#list>
            window.location.href = "${contextPath}${urlFilter}?page=1&size=10&" + requestURL;
        }
        function onResetFilter() {
            window.location.href = "${contextPath}${urlFilter}";
        }
    </script>
</#macro>