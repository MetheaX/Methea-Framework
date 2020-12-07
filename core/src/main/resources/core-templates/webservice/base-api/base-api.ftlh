<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="API Base URLs">
        <@mtable.table thead=tableHead tdata=apibases tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/apibases" id="tbl-apibases">
        </@mtable.table>

        <@mmodal.modal className="modal-tbl-apibases-add" labelTitle="Add New API URLs">
            <@mform.form actionUrl="${contextPath}/app/apibases/save" resetUrl="/app/apibases">
                <@minput.input labelName="API Endpoint" inputType="text" inputId="labelApiBaseUrl"
                inputPlaceHolder="Name" inputName="apiUrl" inputValue="${binder.apiUrl}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-apibases-edit" labelTitle="Edit API URLs">
            <@mform.form actionUrl="${contextPath}/app/apibases/modify" resetUrl="/app/apibases">
                <@mhiddeninput.input inputType="text" inputId="labelEditAPIUrlId" inputName="id"
                inputValue="" required="true"></@mhiddeninput.input>
                <@minput.input labelName="API Endpoint" inputType="text" inputId="labelEditAccountName"
                inputPlaceHolder="API Base URL" inputName="apiUrl" inputValue="${binder.apiUrl}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <script>
            $(document).ready(function () {
                const $modalEdit = $('.modal-tbl-apibases-edit');
                $('.btn-tbl-apibases-data-edit').click(function () {
                    const $row = $(this).closest("tr");
                    const $id = $row.find('td:eq(0)').text();
                    axios.get('${contextPath}' + '/app/apibases/rest?id=' + $id).then(function (response) {
                        if (response.data.status === 200) {
                            $modalEdit.find('input[name="id"]').val(response.data.apibases.id);
                            $modalEdit.find('input[name="apiUrl"]').val(response.data.apibases.apiUrl);
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            });
        </script>

        <@mpagination.pagination page=pagination url="/app/apibases"></@mpagination.pagination>
    </@template.main>
</#compress>