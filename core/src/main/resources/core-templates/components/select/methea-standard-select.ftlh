<#-- ##### -->
<#-- @labelName #String #Define label name -->
<#-- @selectId #String #Define id of select -->
<#-- @selectName #String #Define name of select, basically its refer DTO field -->
<#-- @Values #List<Object> #Define select dropdown list -->
<#-- @required #String #Define select to be optional of mandatory -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 4/17/2020
#-->
<#macro select labelName selectId selectName values required errors>
    <#assign errorMessage = "">
    <#if errors?has_content>
        <#assign errorMessage = "${errors[selectName]!}">
    </#if>
    <div class="form-group">
        <label for="${selectId}">${labelName}</label> <label style="color: red" >${errorMessage}</label>
        <select id="${selectId}" name="${selectName}" class="form-control" required="${required}" style="width:100% !important;">
            <#list values as value>
                <option value="${value.id}">${value.name}</option>
            </#list>
        </select>
    </div>
    <script>
        $(document).ready(function () {
            $('#${selectId}').select2({
                placeholder: "- Choose ${labelName} -",
                allowClear: true
            });
        });
    </script>
</#macro>
