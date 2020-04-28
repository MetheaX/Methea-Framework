<#-- ##### -->
<#-- @actionUrl #String #Define url of form's data submit to backend -->
<#-- @resetUrl #String #Define url of datatable   -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 2/09/2020
#-->
<#macro form actionUrl resetUrl>
    <form class="forms-sample" method="post" action="${actionUrl}">
        <#nested>
        <button type="submit" class="btn btn-success mr-2">Submit</button>
        <button type="button" class="btn btn-light btn-close-modal" data-dismiss="modal">Cancel</button>
    </form>
    <script>
        $(document).ready(function () {
           $('.btn-close-modal').click(function () {
               window.location.href = "${contextPath}${resetUrl}";
           });
        });
    </script>
</#macro>