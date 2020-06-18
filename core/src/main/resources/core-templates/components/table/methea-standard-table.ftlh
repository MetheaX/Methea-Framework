<#-- ##### -->
<#-- @thead #List<String> #Define table head's label -->
<#-- @tdata #List of JSON #Define table data -->
<#-- @tcolumns #List<String> #Define keys of JSON tdata -->
<#-- @id #String #Define table id   -->
<#-- @filters #LinkedHashMap<String,String> #Define filter columns   -->
<#-- @urlFilters #String #Define endpoint filters   -->
<#-- @dataFilters #Object #Cache user filtering   -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 1/12/2020
#-->
<#import "../input/methea-filter-input.ftlh" as filter>
<#macro table id thead tdata tcolumns filters dataFilters urlFilter>
    <table class="table table-responsive-md table-bordered table-striped table-sm">
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
                    <div class="btn-group">
                        <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></button>
                        <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; transform: translate3d(0px, 34px, 0px); top: 0px; left: 0px; will-change: transform;">
                            <button class="dropdown-item" onclick="onFilter()"><i class="fa fa-filter"></i>&nbsp;&nbsp;Search</button>
                            <button class="dropdown-item" onclick="onResetFilter()"><i class="fa fa-retweet"></i>&nbsp;&nbsp;Reset</button>
                            <button class="dropdown-item" data-toggle="modal" data-target=".modal-${id}-add"><i class="fa fa-plus"></i>&nbsp;&nbsp;Add new</button>
                        </div>
                    </div>
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
            <#assign buttonClassName = "btn-danger btn-${id}-disable">
            <#assign iconLabel = "fa-window-close-o">
            <tr>
                <#list tcolumns as col>
                    <#if col == "status">
                        <#if td[col] == "I">
                            <#assign statusClassName = "badge badge-danger">
                            <#assign statusLabel = "Inactive">
                            <#assign buttonLabel = "Enable">
                            <#assign buttonClassName = "btn-warning btn-${id}-enable">
                            <#assign iconLabel = "fa-check-square">
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
                    <div class="btn-group">
                        <button class="btn btn-info btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></button>
                        <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; transform: translate3d(0px, 34px, 0px); top: 0px; left: 0px; will-change: transform;">
                            <button class="dropdown-item ${buttonClassName}"><i class="fa ${iconLabel}"></i>&nbsp;&nbsp;${buttonLabel}</button>
                            <button class="dropdown-item btn-success btn-${id}-data-edit" data-toggle="modal"
                                    data-target=".modal-${id}-edit"><i class="fa fa-edit"></i>&nbsp;&nbsp;Edit</button>
                            <#nested>
                        </div>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <script>
        $(document).ready(function () {
            $('.btn-${id}-disable').click(function () {
                swal({
                    title: "Are you sure?",
                    text: "Once you disable, you still can enable its back.",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                }).then((willDisable) => {
                    if (willDisable) {
                        const $row = $(this).closest("tr");
                        const $id = $row.find('td:eq(0)').text();
                        axios.post('${contextPath}' + '/app/' + '${urlFilter}'.split('/')[2] + '/rest/deactivate', {
                            id: $id
                        }).then(function (response) {
                            switch (response.data.status) {
                                case 200:
                                    window.location.reload();
                                    break;
                                case 500:
                                    swal(response.data.message);
                                    break;
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });
                    } else {
                        swal("Your operation cancel!");
                    }
                });
            });
            $('.btn-${id}-enable').click(function () {
                swal({
                    title: "Are you sure?",
                    text: "Once you enable, you still can disable its back.",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                }).then((willActivate) => {
                    if (willActivate) {
                        const $row = $(this).closest("tr");
                        const $id = $row.find('td:eq(0)').text();
                        axios.post('${contextPath}' + '/app/' + '${urlFilter}'.split('/')[2] + '/rest/activate', {
                            id: $id
                        }).then(function (response) {
                            switch (response.data.status) {
                                case 200:
                                    window.location.reload();
                                    break;
                                case 500:
                                    swal(response.data.message);
                                    break;
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });
                    } else {
                        swal("Your operation cancel!");
                    }
                });
            });

            if(${hasErrors?c}) {
                $('.modal-${popup}').modal('show');
            }
        });

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