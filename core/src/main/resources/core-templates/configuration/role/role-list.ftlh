<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="Roles">
        <div class="table-responsive">
            <@mtable.table thead=tableHead tdata=roles tcolumns=tableColumns filters=tableFilterColumns
            dataFilters=dataFilters urlFilter="/app/roles" id="tbl-roles">
            </@mtable.table>
        </div>

        <@mmodal.modal className="modal-tbl-roles-add" labelTitle="Add New Role">
            <@mform.form actionUrl="${contextPath}/app/roles/save" resetUrl="/app/roles">
                <@minput.input labelName="Role name" inputType="text" inputId="labelRoleName"
                inputPlaceHolder="Role name" inputName="name" inputValue="${binder.name}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-roles-edit" labelTitle="Edit Role">
            <@mform.form actionUrl="${contextPath}/app/roles/modify" resetUrl="/app/roles">
                <@mhiddeninput.input inputType="text" inputId="labelEditRoleId" inputName="id"
                inputValue="" required="true"></@mhiddeninput.input>
                <@minput.input labelName="Role name" inputType="text" inputId="labelEditRoleName"
                inputPlaceHolder="Role name" inputName="name" inputValue="${binder.name}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>
        <@mpagination.pagination page=pagination url="/app/roles"></@mpagination.pagination>
    </@template.main>
    <script>
        $(document).ready(function () {
            const $modalEdit = $('.modal-tbl-roles-edit');
            $('.btn-tbl-roles-data-edit').click(function () {
                const $row = $(this).closest("tr");
                const $id = $row.find('td:eq(0)').text();
                axios.get('${contextPath}' + '/app/roles/rest?id=' + $id).then(function (response) {
                    if (response.data.status === 200) {
                        $modalEdit.find('input[name="id"]').val(response.data.roles.id);
                        $modalEdit.find('input[name="name"]').val(response.data.roles.name);
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            });
        });
    </script>
</#compress>