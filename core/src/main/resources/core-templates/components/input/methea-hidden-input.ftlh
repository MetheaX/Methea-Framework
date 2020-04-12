<#-- ##### -->
<#-- @inputType #String #Define type of input -->
<#-- @inputId #String #Define id of input -->
<#-- @inputName #String #Define name of input, basically its refer DTO field -->
<#-- @inputValue #String #Define input defualt value -->
<#-- @required #String #Define input to be optional of mandatory -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 3/08/2020
#-->
<#macro input inputType inputId  inputName inputValue required>
    <div class="form-group">
        <input hidden type="${inputType}" class="form-control" id="${inputId}" name="${inputName}"
               value="${inputValue}" required="${required}">
    </div>
</#macro>