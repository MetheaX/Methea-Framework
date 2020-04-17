<#-- ##### -->
<#-- @className #String #Define modal class name -->
<#-- @labelTitle #String #Define modal title -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 2/09/2020
#-->
<#macro modal className labelTitle >
    <div class="modal fade ${className}" role="dialog"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">${labelTitle}</h5>
                </div>
                <div class="modal-body">
                    <#nested>
                </div>
            </div>
        </div>
    </div>
</#macro>