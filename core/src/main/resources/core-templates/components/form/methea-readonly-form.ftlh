
<#--
# Author : DKSilverX
# Date : 10/18/2020
#-->
<#macro form>
    <form>
        <fieldset disabled="disabled" style="pointer-events: none;opacity: .8;">
            <#nested>
        </fieldset>
        <button type="button" class="btn btn-light btn-close-modal" data-dismiss="modal">Close</button>
    </form>
</#macro>