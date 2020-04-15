<#-- ##### -->
<#-- @inputType #String #Define type of input -->
<#-- @inputPlaceholder #String #Define placeholder of input -->
<#-- @inputValue #String #Define input defualt value -->
<#-- @id #String #Define input id   -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 4/14/2020
#-->
<#macro input inputType inputPlaceholder inputId  inputValue >
    <input type="${inputType}" placeholder="${inputPlaceholder}" id="${inputId}" class="form-control"
           value="${inputValue}">
</#macro>