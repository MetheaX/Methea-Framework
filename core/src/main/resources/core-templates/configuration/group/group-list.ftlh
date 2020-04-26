<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/select/methea-standard-select.ftlh" as mselect>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
<@template.main title="Groups">
    <div class="table-responsive">
        <@mtable.table thead=tableHead tdata=groups tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/groups" id="tbl-groups">
        </@mtable.table>
    </div>

    <@mmodal.modal className="modal-tbl-groups-add" labelTitle="Add New Group">
        <@mform.form actionUrl="${contextPath}/app/groups/save">
            <@minput.input labelName="Group Name" inputType="text" inputId="labelGroupName"
            inputPlaceHolder="Group Name" inputName="groupName" inputValue="${binder.groupName}" required="true" errors=errors></@minput.input>
            <@mselect.select labelName="Account Name" selectId="labelUserAccountName" selectName="accountId"
            values=mDropdown.accountDropdown required="true"></@mselect.select>
            <@minput.input labelName="Remarks" inputType="text" inputId="labelRemarks"
            inputPlaceHolder="Remarks" inputName="remarks" inputValue="${binder.remarks}" required="true" errors=errors></@minput.input>
        </@mform.form>
    </@mmodal.modal>

    <@mmodal.modal className="modal-tbl-groups-edit" labelTitle="Edit Group">
        <@mform.form actionUrl="${contextPath}/app/groups/modify">
            <@mhiddeninput.input inputType="text" inputId="labelEditGroupId" inputName="id"
            inputValue="" required="true"></@mhiddeninput.input>
            <@minput.input labelName="Group Name" inputType="text" inputId="labelEditGroupName"
            inputPlaceHolder="Group Name" inputName="groupName" inputValue="SYS_ADMIN" required="true" errors=errors></@minput.input>
            <@mselect.select labelName="Account Name" selectId="labelEditUserAccountName" selectName="accountId"
            values=mDropdown.accountDropdown required="true"></@mselect.select>
            <@minput.input labelName="Remarks" inputType="text" inputId="labelEditRemarks"
            inputPlaceHolder="Remarks" inputName="remarks" inputValue="" required="true" errors=errors></@minput.input>
        </@mform.form>
    </@mmodal.modal>
    <@mpagination.pagination page=pagination url="/app/groups"></@mpagination.pagination>
</@template.main>
<script>
    $(document).ready(function () {
        const $modalEdit = $('.modal-tbl-groups-edit');
        const $modalAdd = $('.modal-tbl-groups-add');
        $('.btn-tbl-groups-data-edit').click(function () {
            const $row = $(this).closest("tr");
            const $id = $row.find('td:eq(0)').text();
            axios.get('${contextPath}' + '/app/groups/rest?id=' + $id).then(function (response) {
                if (response.data.status === 200) {
                    $modalEdit.find('input[name="id"]').val(response.data.groups.id);
                    $modalEdit.find('input[name="groupName"]').val(response.data.groups.groupName);
                    $modalEdit.find('select[name="accountId"]').val(response.data.groups.accountId).trigger('change');
                    $modalEdit.find('input[name="remarks"]').val(response.data.groups.remarks);
                }
            }).catch(function (error) {
                console.log(error);
            });
        });
        if(${hasErrors?c} && ${isNew?c}){
            $modalAdd.modal('show');
        }else if(${hasErrors?c} && !${isNew?c}){
            $modalEdit.modal('show');
        }
    });
</script>
</#compress>