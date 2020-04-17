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
<#macro select lableName selectId selectName values required >
    <div class="form-group">
        <label for="${selectId}">${lableName}</label>
        <select id="${selectId}" name="${selectName}" class="form-control" required="${required}" style="width:100% !important;">
            <#list values as value>
                <option value="${value.id}">${value.name}</option>
            </#list>
        </select>
    </div>
</#macro>
