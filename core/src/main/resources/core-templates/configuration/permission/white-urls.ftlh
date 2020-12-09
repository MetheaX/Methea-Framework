<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/form/methea-readonly-form.ftlh" as mrform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/select/methea-standard-select.ftlh" as mselect>
<#import "../../components/select/methea-multiple-select.ftlh" as mmselect>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="Whitelist URLs">
        <@mtable.table thead=tableHead tdata=whiteurls tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/whiteurls" id="tbl-whiteurls">
            <button class="dropdown-item btn-view-whiteurls" data-toggle="modal"
                    data-target=".modal-tbl-whiteurls-view"><i class="fa fa-eye"></i>&nbsp;&nbsp;View
            </button>
        </@mtable.table>

        <@mmodal.modal className="modal-tbl-whiteurls-add" labelTitle="Apply Whitelist URL">
            <@mform.form actionUrl="${contextPath}/app/whiteurls/save" resetUrl="/app/whiteurls">
                <@mselect.select labelName="URI Name" selectId="labelURIName" selectName="uriId"
                values=mDropdown.uriDropdown required="true" errors=errors></@mselect.select>
                <@mmselect.select labelName="Allowed Methods" selectId="labelAllowMethod" selectName="allowedMethod"
                values=mDropdown.httpMethodDropdown required="true" errors=errors></@mmselect.select>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-whiteurls-edit" labelTitle="Edit Whitelist URL">
            <@mform.form actionUrl="${contextPath}/app/whiteurls/modify" resetUrl="/app/whiteurls">
                <@mhiddeninput.input inputType="text" inputId="labelEditWhitelistId" inputName="id"
                inputValue="${binder.id}" required="true"></@mhiddeninput.input>
                <@mselect.select labelName="URI Name" selectId="labelEditURIName" selectName="uriId"
                values=mDropdown.uriDropdown required="true" errors=errors></@mselect.select>
                <@mmselect.select labelName="Allowed Methods" selectId="labelEditAllowMethod" selectName="allowedMethod"
                values=mDropdown.httpMethodDropdown required="true" errors=errors></@mmselect.select>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-whiteurls-view" labelTitle="View Detail">
            <@mrform.form>
                <@mhiddeninput.input inputType="text" inputId="labelReadOnlyWhitelistId" inputName="id"
                inputValue="${binder.id}" required="true"></@mhiddeninput.input>
                <@mselect.select labelName="URI Name" selectId="labelReadOnlyURIName" selectName="uriId"
                values=mDropdown.uriDropdown required="true" errors=errors></@mselect.select>
                <@mmselect.select labelName="Allowed Methods" selectId="labelReadOnlyAllowMethod" selectName="allowedMethod"
                values=mDropdown.httpMethodDropdown required="true" errors=errors></@mmselect.select>
            </@mrform.form>
        </@mmodal.modal>

        <@mpagination.pagination page=pagination url="/app/whiteurls"></@mpagination.pagination>
    </@template.main>
    <script>
        $(document).ready(function () {
            const $modalEdit = $('.modal-tbl-whiteurls-edit');
            const $modalViewDetail = $('.modal-tbl-whiteurls-view');
            $('.btn-tbl-whiteurls-data-edit').click(function () {
                const $row = $(this).closest("tr");
                const $id = $row.find('td:eq(0)').text();
                axios.get('${contextPath}' + '/app/whiteurls/rest/entity?id=' + $id).then(function (response) {
                    if (200 === response.data.status) {
                        $modalEdit.find('input[name="id"]').val(response.data.whiteurls.id);
                        $modalEdit.find('select[name="uriId"]').val(response.data.whiteurls.uriId).trigger('change');
                        $modalEdit.find('select[name="allowedMethod"]').val(response.data.whiteurls.allowedMethod).trigger('change');
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            });
            $('.btn-view-whiteurls').click(function () {
                const $row = $(this).closest("tr");
                const $id = $row.find('td:eq(0)').text();
                axios.get('${contextPath}' + '/app/whiteurls/rest/entity?id=' + $id).then(function (response) {
                    if (200 === response.data.status) {
                        $modalViewDetail.find('input[name="id"]').val(response.data.whiteurls.id);
                        $modalViewDetail.find('select[name="uriId"]').val(response.data.whiteurls.uriId).trigger('change');
                        $modalViewDetail.find('select[name="allowedMethod"]').val(response.data.whiteurls.allowedMethod).trigger('change');
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            });
        });
    </script>
</#compress>