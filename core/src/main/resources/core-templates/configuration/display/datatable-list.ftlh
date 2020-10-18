<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/select/methea-standard-select.ftlh" as mselect>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="Datatable Configuration">
        <@mtable.table thead=tableHead tdata=datatable tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/datatable" id="tbl-datatable">
        </@mtable.table>

        <@mmodal.modal className="modal-tbl-datatable-add" labelTitle="Config New Datatable">
            <@mform.form actionUrl="${contextPath}/app/datatable/save" resetUrl="/app/datatable">
                <@minput.input labelName="Datatable Name" inputType="text" inputId="labelDatatableName"
                inputPlaceHolder="Datatable Name" inputName="viewName" inputValue="${binder.viewName}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Column Label" inputType="text" inputId="labelColumnLabelName"
                inputPlaceHolder="Column Label" inputName="labelColumnHead" inputValue="${binder.labelColumnHead}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Attribute Name" inputType="text" inputId="labelAttributeName"
                inputPlaceHolder="Attribute Name" inputName="columnKey" inputValue="${binder.columnKey}" required="true" errors=errors></@minput.input>
                <@mselect.select labelName="Allow Filter" selectId="labelAllowFilter" selectName="allowFilter"
                values=mDropdown.yesNoDropdown required="true" errors=errors multiple=""></@mselect.select>
                <@minput.input labelName="Column Seq" inputType="number" inputId="labelColumnSeq"
                inputPlaceHolder="Column Seq" inputName="sequence" inputValue="${binder.sequence}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-datatable-edit" labelTitle="Edit Datatable Config">
            <@mform.form actionUrl="${contextPath}/app/datatable/modify" resetUrl="/app/datatable">
                <@mhiddeninput.input inputType="text" inputId="labelEditDatatableId" inputName="id"
                inputValue="${binder.id}" required="true"></@mhiddeninput.input>
                <@minput.input labelName="Datatable Name" inputType="text" inputId="labelEditDatatableName"
                inputPlaceHolder="Datatable Name" inputName="viewName" inputValue="${binder.viewName}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Column Label" inputType="text" inputId="labelEditColumnLabelName"
                inputPlaceHolder="Column Label" inputName="labelColumnHead" inputValue="${binder.labelColumnHead}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Attribute Name" inputType="text" inputId="labelEditAttributeName"
                inputPlaceHolder="Attribute Name" inputName="columnKey" inputValue="${binder.columnKey}" required="true" errors=errors></@minput.input>
                <@mselect.select labelName="Allow Filter" selectId="labelEditAllowFilter" selectName="allowFilter"
                values=mDropdown.yesNoDropdown required="true" errors=errors multiple=""></@mselect.select>
                <@minput.input labelName="Column Seq" inputType="number" inputId="labelEditColumnSeq"
                inputPlaceHolder="Column Seq" inputName="sequence" inputValue="${binder.sequence}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mpagination.pagination page=pagination url="/app/datatable"></@mpagination.pagination>

        <script>
            $(document).ready(function () {
                const $modalEdit = $('.modal-tbl-datatable-edit');
                $('.btn-tbl-datatable-data-edit').click(function () {
                    const $row = $(this).closest("tr");
                    const $id = $row.find('td:eq(0)').text();
                    axios.get('${contextPath}' + '/app/datatable/rest?id=' + $id).then(function (response) {
                        if (response.data.status === 200) {
                            $modalEdit.find('input[name="id"]').val(response.data.datatable.id);
                            $modalEdit.find('input[name="viewName"]').val(response.data.datatable.viewName);
                            $modalEdit.find('input[name="labelColumnHead"]').val(response.data.datatable.labelColumnHead);
                            $modalEdit.find('input[name="columnKey"]').val(response.data.datatable.columnKey);
                            $modalEdit.find('select[name="allowFilter"]').val(response.data.datatable.allowFilter).trigger('change');
                            $modalEdit.find('input[name="sequence"]').val(response.data.datatable.sequence);
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            });
        </script>
    </@template.main>
</#compress>