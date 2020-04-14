<#-- ##### -->
<#-- @inputType #String #Define type of input -->
<#-- @inputPlaceholder #String #Define placeholder of input -->
<#-- @inputValue #String #Define input defualt value -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 4/14/2020
#-->
<#macro input inputType inputPlaceholder  inputValue >
    <input type="${inputType}" placeholder="${inputPlaceholder}" id="${inputPlaceholder}" class="form-control"
           value="${inputValue}">
</#macro>