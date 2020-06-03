<#-- ##### -->
<#-- @actionUrl #String #Define url of form's data submit to backend -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 02/06/2020
#-->
<#macro form actionUrl>
    <form method="post" action="${actionUrl}">
        <#nested>
        <button type="submit" class="btn btn-success mr-2">Submit</button>
    </form>
</#macro>