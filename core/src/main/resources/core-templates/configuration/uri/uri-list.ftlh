<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="URIs">
        <div class="table-responsive">
            <@mtable.table thead=tableHead tdata=uris tcolumns=tableColumns filters=tableFilterColumns
            dataFilters=dataFilters urlFilter="/app/uris" id="tbl-uris">
            </@mtable.table>
        </div>

        <@mmodal.modal className="modal-tbl-uris-add" labelTitle="Add New URIs">
            <@mform.form actionUrl="${contextPath}/app/uris/save" resetUrl="/app/uris">
                <@minput.input labelName="URI name" inputType="text" inputId="labelURIName"
                inputPlaceHolder="URI name" inputName="uriName" inputValue="${binder.uriName}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-uris-edit" labelTitle="Edit URIs">
            <@mform.form actionUrl="${contextPath}/app/uris/modify" resetUrl="/app/uris">
                <@mhiddeninput.input inputType="text" inputId="labelEditRoleId" inputName="id"
                inputValue="" required="true"></@mhiddeninput.input>
                <@minput.input labelName="URIs name" inputType="text" inputId="labelEditURIName"
                inputPlaceHolder="URIs name" inputName="uriName" inputValue="${binder.uriName}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>
        <@mpagination.pagination page=pagination url="/app/uris"></@mpagination.pagination>
    </@template.main>
    <script>
        $(document).ready(function () {
            const $modalEdit = $('.modal-tbl-uris-edit');
            $('.btn-tbl-uris-data-edit').click(function () {
                const $row = $(this).closest("tr");
                const $id = $row.find('td:eq(0)').text();
                axios.get('${contextPath}' + '/app/uris/rest?id=' + $id).then(function (response) {
                    if (response.data.status === 200) {
                        $modalEdit.find('input[name="id"]').val(response.data.uris.id);
                        $modalEdit.find('input[name="uriName"]').val(response.data.uris.uriName);
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            });
        });
    </script>
</#compress>