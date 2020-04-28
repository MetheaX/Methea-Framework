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
# Date : 4/25/2020
#-->
<#macro input labelName inputType inputId inputPlaceHolder inputName inputValue required>
    <div class="form-group">
        <label for="${inputId}">${labelName}</label>
        <input type="${inputType}" class="form-control" id="${inputId}" placeholder="${inputPlaceHolder}"
               name="${inputName}" value="${inputValue}" required="${required}" readonly>
    </div>
</#macro>