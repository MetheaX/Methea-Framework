<#-- ##### -->
<#-- @labelName #String #Define label name -->
<#-- @inputType #String #Define type of input -->
<#-- @inputId #String #Define id of input -->
<#-- @inputPlaceHolder #String #Define placeholder of input -->
<#-- @inputName #String #Define name of input, basically its refer DTO field -->
<#-- @inputValue #String #Define input defualt value -->
<#-- @required #String #Define input to be optional of mandatory -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 2/09/2020
#-->
<#macro input labelName inputType inputId inputPlaceHolder inputName inputValue required errors>
    <#assign errorMessage = "">
    <#if errors?has_content>
        <#assign errorMessage = "${errors[inputName]!}">
    </#if>
    <div class="form-group">
        <label for="${inputId}">${labelName}</label> <label style="color: red" >${errorMessage}</label>
        <input type="${inputType}" class="form-control" id="${inputId}" placeholder="${inputPlaceHolder}"
               name="${inputName}" value="${inputValue}" required="${required}">
    </div>
</#macro>