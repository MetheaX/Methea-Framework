<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/select/methea-standard-select.ftlh" as mselect>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="Map Role URIs">
        <@mtable.table thead=tableHead tdata=roleURIs tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/roleURIs" id="tbl-role-uris">
        </@mtable.table>
        <@mmodal.modal className="modal-tbl-role-uris-add" labelTitle="Add New Mapping">
            <@mform.form actionUrl="${contextPath}/app/roleURIs/save" resetUrl="/app/roleURIs">
                <@mselect.select labelName="Role Name" selectId="labelRoleName" selectName="roleId"
                values=mDropdown.roleDropdown required="true" errors=errors></@mselect.select>
                <@mselect.select labelName="URI Name" selectId="labelURIName" selectName="uriId"
                values=mDropdown.uriDropdown required="true" errors=errors></@mselect.select>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-role-uris-edit" labelTitle="Edit Mapping">
            <@mform.form actionUrl="${contextPath}/app/roleURIs/modify" resetUrl="/app/roleURIs">
                <@mhiddeninput.input inputType="text" inputId="labelEditGroupId" inputName="id"
                inputValue="${binder.id}" required="true"></@mhiddeninput.input>
                <@mselect.select labelName="Role Name" selectId="labelEditRoleId" selectName="roleId"
                values=mDropdown.roleDropdown required="true" errors=errors></@mselect.select>
                <@mselect.select labelName="URI Name" selectId="labelEditURIId" selectName="uriId"
                values=mDropdown.uriDropdown required="true" errors=errors></@mselect.select>
            </@mform.form>
        </@mmodal.modal>

        <@mpagination.pagination page=pagination url="/app/roleURIs"></@mpagination.pagination>
    </@template.main>
    <script>
        $(document).ready(function () {
            const $modalEdit = $('.modal-tbl-role-uris-edit');
            $('.btn-tbl-role-uris-data-edit').click(function () {
                const $row = $(this).closest("tr");
                const $id = $row.find('td:eq(0)').text();
                axios.get('${contextPath}' + '/app/roleURIs/rest?id=' + $id).then(function (response) {
                    if (200 === response.data.status) {
                        $modalEdit.find('input[name="id"]').val(response.data.roleURIs.id);
                        $modalEdit.find('select[name="roleId"]').val(response.data.roleURIs.roleId).trigger('change');
                        $modalEdit.find('select[name="uriId"]').val(response.data.roleURIs.uriId).trigger('change');
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            });
        });
    </script>
</#compress>