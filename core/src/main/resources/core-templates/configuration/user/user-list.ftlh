<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/input/methea-readonly-input.ftlh" as mreadonlyinput>
<#import "../../components/select/methea-standard-select.ftlh" as mselect>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="Users">
        <div class="table-responsive">
            <@mtable.table thead=tableHead tdata=users tcolumns=tableColumns filters=tableFilterColumns
            dataFilters=dataFilters urlFilter="/app/users" id="tbl-users">
            </@mtable.table>
        </div>

        <@mmodal.modal className="modal-tbl-users-add" labelTitle="Add New User">
            <@mform.form actionUrl="${contextPath}/app/users/save">
                <@mselect.select labelName="Group Name" selectId="labelDropDownGroupName" selectName="groupId"
                values=mDropdown.groupDropdown required="true" errors=errors></@mselect.select>
                <@minput.input labelName="Username" inputType="text" inputId="labelUsername"
                inputPlaceHolder="Username" inputName="username" inputValue="" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Firstname" inputType="text" inputId="labelFirstname"
                inputPlaceHolder="Firstname" inputName="firstName" inputValue="" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Lastname" inputType="text" inputId="labelLastname"
                inputPlaceHolder="Lastname" inputName="lastName" inputValue="" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Phone" inputType="text" inputId="labelPhone"
                inputPlaceHolder="Phone" inputName="phone" inputValue="" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Email" inputType="text" inputId="labelEmail"
                inputPlaceHolder="Email" inputName="email" inputValue="" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Password" inputType="password" inputId="labelPassword"
                inputPlaceHolder="Password" inputName="password" inputValue="" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Confirm Password" inputType="password" inputId="labelConfirmPassword"
                inputPlaceHolder="Confirm Password" inputName="confirmPassword" inputValue="" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-users-edit" labelTitle="Edit User Information">
            <@mform.form actionUrl="${contextPath}/app/users/modify">
                <@mhiddeninput.input inputType="text" inputId="labelEditUserId" inputName="id"
                inputValue="" required="true"></@mhiddeninput.input>
                <@mselect.select labelName="Group Name" selectId="labelEditDropDownGroupName" selectName="groupId"
                values=mDropdown.groupDropdown required="true" errors=errors></@mselect.select>
                <@mreadonlyinput.input labelName="Username" inputType="text" inputId="labelEditUsername"
                inputPlaceHolder="Username" inputName="username" inputValue="" required="true"></@mreadonlyinput.input>
                <@minput.input labelName="Firstname" inputType="text" inputId="labelEditFirstname"
                inputPlaceHolder="Firstname" inputName="firstName" inputValue="" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Lastname" inputType="text" inputId="labelEditLastname"
                inputPlaceHolder="Lastname" inputName="lastName" inputValue="" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Phone" inputType="text" inputId="labelEditPhone"
                inputPlaceHolder="Phone" inputName="phone" inputValue="" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Email" inputType="text" inputId="labelEditEmail"
                inputPlaceHolder="Email" inputName="email" inputValue="" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mpagination.pagination page=pagination url="/app/users"></@mpagination.pagination>
    </@template.main>
    <script>
        $(document).ready(function () {
            const $modalAdd = $('.modal-tbl-users-add');
            const $modalEdit = $('.modal-tbl-users-edit');
            $('.btn-tbl-users-data-edit').click(function () {
                const $row = $(this).closest("tr");
                const $id = $row.find('td:eq(0)').text();
                axios.get('${contextPath}' + '/app/users/rest?id=' + $id).then(function (response) {
                    if (response.data.status === 200) {
                        $modalEdit.find('input[name="id"]').val(response.data.users.id);
                        $modalEdit.find('select[name="groupId"]').val(response.data.users.groupId).trigger('change');
                        $modalEdit.find('input[name="username"]').val(response.data.users.username);
                        $modalEdit.find('input[name="firstName"]').val(response.data.users.firstName);
                        $modalEdit.find('input[name="lastName"]').val(response.data.users.lastName);
                        $modalEdit.find('input[name="phone"]').val(response.data.users.phone);
                        $modalEdit.find('input[name="email"]').val(response.data.users.email);
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            });
            if(${hasErrors?c} && ${isNew?c}) {
                $modalAdd.modal('show');
            } else if(${hasErrors?c} && !${isNew?c}){
                $modalEdit.modal('show');
            }
        });
    </script>
</#compress>