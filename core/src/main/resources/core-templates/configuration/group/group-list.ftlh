<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/select/methea-standard-select.ftlh" as mselect>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<@template.main title="Groups">
    <div class="table-responsive">
        <@mtable.table thead=tableHead tdata=groups tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/groups" id="tbl-groups">
        </@mtable.table>
    </div>
<#-- Modal Add Account   -->
    <@mmodal.modal className="modal-tbl-groups-add" labelTitle="Add New Group">
        <@mform.form actionUrl="${contextPath}/app/group/save">
            <@minput.input labelName="Group Name" inputType="text" inputId="labelGroupName"
            inputPlaceHolder="Group Name" inputName="groupName" inputValue="" required="true"></@minput.input>
            <@mselect.select lableName="Account Name" selectId="labelUserAccountName" selectName="accountId"
            values=mDropdown.accountDropdown required="true"></@mselect.select>
            <@minput.input labelName="Remarks" inputType="text" inputId="labelRemarks"
            inputPlaceHolder="Remarks" inputName="remarks" inputValue="" required="true"></@minput.input>
        </@mform.form>
    </@mmodal.modal>
    <@mpagination.pagination page=pagination url="/app/groups"></@mpagination.pagination>
</@template.main>

<script src="${contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="${contextPath}/resources/vendor/sweetalert/sweetalert.min.js"></script>
<script src="${contextPath}/resources/vendor/select2/select2.min.js"></script>
<script src="${contextPath}/resources/vendor/jquery/jquery.twbsPagination.min.js"></script>
<script>
    $(document).ready(function () {
        $('#labelUserAccountName').select2({
            placeholder: "- Choose account name -",
            allowClear: true
        });
    });
</script>