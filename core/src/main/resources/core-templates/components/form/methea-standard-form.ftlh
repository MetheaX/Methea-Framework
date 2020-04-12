<#-- ##### -->
<#-- @actionUrl #String #Define url of form's data submit to backend -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 2/09/2020
#-->
<#macro form actionUrl>
    <form class="forms-sample" method="post" action="${actionUrl}">
        <#nested>
        <button type="submit" class="btn btn-success mr-2">Submit</button>
        <button type="button" class="btn btn-light" data-dismiss="modal">Cancel</button>
    </form>
</#macro>