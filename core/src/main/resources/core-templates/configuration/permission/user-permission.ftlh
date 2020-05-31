<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/select/methea-standard-select.ftlh" as mselect>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="Permissions">
        <@mtable.table thead=tableHead tdata=permissions tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/permissions" id="tbl-permissions">
        </@mtable.table>

        <@mmodal.modal className="modal-tbl-permissions-add" labelTitle="Apply Permission">
            <@mform.form actionUrl="${contextPath}/app/permissions/save" resetUrl="/app/permissions">
                <@mselect.select labelName="Username" selectId="labelPermUsername" selectName="userLoginId"
                values=mDropdown.userDropdown required="true" errors=errors></@mselect.select>
                <@mselect.select labelName="Role name" selectId="labelPermRoleName" selectName="roleId"
                values=mDropdown.roleDropdown required="true" errors=errors></@mselect.select>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-permissions-edit" labelTitle="Edit Permission">
            <@mform.form actionUrl="${contextPath}/app/permissions/modify" resetUrl="/app/permissions">
                <@mhiddeninput.input inputType="text" inputId="labelEditPermissionId" inputName="id"
                inputValue="${binder.id}" required="true"></@mhiddeninput.input>
                <@mselect.select labelName="Username" selectId="labelEditPermUsername" selectName="userLoginId"
                values=mDropdown.userDropdown required="true" errors=errors></@mselect.select>
                <@mselect.select labelName="Role name" selectId="labelEditPermRoleName" selectName="roleId"
                values=mDropdown.roleDropdown required="true" errors=errors></@mselect.select>
            </@mform.form>
        </@mmodal.modal>

        <@mpagination.pagination page=pagination url="/app/permissions"></@mpagination.pagination>
    </@template.main>
    <script>
        $(document).ready(function () {
            const $modalEdit = $('.modal-tbl-permissions-edit');
            $('.btn-tbl-permissions-data-edit').click(function () {
                const $row = $(this).closest("tr");
                const $id = $row.find('td:eq(0)').text();
                axios.get('${contextPath}' + '/app/permissions/rest?id=' + $id).then(function (response) {
                    if (response.data.status === 200) {
                        $modalEdit.find('input[name="id"]').val(response.data.permissions.id);
                        $modalEdit.find('select[name="userId"]').val(response.data.permissions.userLoginId).trigger('change');
                        $modalEdit.find('select[name="roleId"]').val(response.data.permissions.roleId).trigger('change');
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            });
        });
    </script>
</#compress>