<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/select/methea-standard-select.ftlh" as mselect>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="Menu Configuration">
        <@mtable.table thead=tableHead tdata=menu tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/menu" id="tbl-menu">
        </@mtable.table>

        <@mmodal.modal className="modal-tbl-menu-add" labelTitle="Add New Menu">
            <@mform.form actionUrl="${contextPath}/app/menu/save" resetUrl="/app/menu">
                <@mselect.select labelName="Parent Menu" selectId="labelParentMenu" selectName="parentId"
                values=mDropdown.menuDropdown required="true" errors=errors></@mselect.select>
                <@minput.input labelName="Menu Label" inputType="text" inputId="labelMenuLabel"
                inputPlaceHolder="Menu Label" inputName="menuLabel" inputValue="${binder.menuLabel}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Menu Icon" inputType="text" inputId="labelMenuIcon"
                inputPlaceHolder="Menu Icon" inputName="menuIcon" inputValue="${binder.menuIcon}" required="true" errors=errors></@minput.input>
                <@mselect.select labelName="URL" selectId="labelURIName" selectName="uriId"
                values=mDropdown.uriDropdown required="true" errors=errors></@mselect.select>
                <@mselect.select labelName="Group Name" selectId="labelDropDownGroupName" selectName="groupId"
                values=mDropdown.groupDropdown required="true" errors=errors></@mselect.select>
                <@minput.input labelName="Index" inputType="number" inputId="labelMenuIndex"
                inputPlaceHolder="Index" inputName="index" inputValue="${binder.index}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-menu-edit" labelTitle="Edit Menu">
            <@mform.form actionUrl="${contextPath}/app/menu/modify" resetUrl="/app/menu">
                <@mhiddeninput.input inputType="text" inputId="labelEditMenuId" inputName="id"
                inputValue="${binder.id}" required="true"></@mhiddeninput.input>
                <@mselect.select labelName="Parent Menu" selectId="labelEditParentMenu" selectName="parentId"
                values=mDropdown.menuDropdown required="true" errors=errors></@mselect.select>
                <@minput.input labelName="Menu Label" inputType="text" inputId="labelEditMenuLabel"
                inputPlaceHolder="Menu Label" inputName="menuLabel" inputValue="${binder.menuLabel}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Menu Icon" inputType="text" inputId="labelEditMenuIcon"
                inputPlaceHolder="Menu Icon" inputName="menuIcon" inputValue="${binder.menuIcon}" required="true" errors=errors></@minput.input>
                <@mselect.select labelName="URL" selectId="labelEditURIName" selectName="uriId"
                values=mDropdown.uriDropdown required="true" errors=errors></@mselect.select>
                <@mselect.select labelName="Group Name" selectId="labelEditDropDownGroupName" selectName="groupId"
                values=mDropdown.groupDropdown required="true" errors=errors></@mselect.select>
                <@minput.input labelName="Index" inputType="number" inputId="labelEditMenuIndex"
                inputPlaceHolder="Index" inputName="index" inputValue="${binder.index}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mpagination.pagination page=pagination url="/app/menu"></@mpagination.pagination>
    </@template.main>
    <script>
        $(document).ready(function () {
            const $modalEdit = $('.modal-tbl-menu-edit');
            $('.btn-tbl-menu-data-edit').click(function () {
                const $row = $(this).closest("tr");
                const $id = $row.find('td:eq(0)').text();
                axios.get('${contextPath}' + '/app/menu/rest?id=' + $id).then(function (response) {
                    if (200 === response.data.status) {
                        $modalEdit.find('input[name="id"]').val(response.data.menu.id);
                        $modalEdit.find('select[name="parentId"]').val(response.data.menu.parentId).trigger('change');
                        $modalEdit.find('input[name="menuLabel"]').val(response.data.menu.menuLabel);
                        $modalEdit.find('input[name="menuIcon"]').val(response.data.menu.menuIcon);
                        $modalEdit.find('select[name="uriId"]').val(response.data.menu.uriId).trigger('change');
                        $modalEdit.find('select[name="groupId"]').val(response.data.menu.groupId).trigger('change');
                        $modalEdit.find('input[name="index"]').val(response.data.menu.index);
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            });
        });
    </script>
</#compress>