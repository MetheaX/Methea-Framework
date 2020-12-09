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
        <@mtable.table thead=tableHead tdata=users tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/users" id="tbl-users">
            <button class="dropdown-item btn-reset-user-password" data-toggle="modal"
                    data-target=".modal-tbl-users-reset-password"><i class="fa fa-window-restore"></i>&nbsp;&nbsp;Reset
                Password
            </button>
        </@mtable.table>

        <@mmodal.modal className="modal-tbl-users-add" labelTitle="Add New User">
            <@mform.form actionUrl="${contextPath}/app/users/save" resetUrl="/app/users">
                <@mselect.select labelName="Account" selectId="labelDropDownAccountName" selectName="accountId"
                values=mDropdown.accountDropdown required="true" errors=errors></@mselect.select>
                <@mselect.select labelName="Group" selectId="labelDropDownGroupName" selectName="groupId"
                values=mDropdown.groupDropdown required="true" errors=errors></@mselect.select>
                <@minput.input labelName="Username" inputType="text" inputId="labelUsername"
                inputPlaceHolder="Username" inputName="username" inputValue="${binder.username}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Firstname" inputType="text" inputId="labelFirstname"
                inputPlaceHolder="Firstname" inputName="firstName" inputValue="${binder.firstName}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Lastname" inputType="text" inputId="labelLastname"
                inputPlaceHolder="Lastname" inputName="lastName" inputValue="${binder.lastName}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Phone" inputType="text" inputId="labelPhone"
                inputPlaceHolder="Phone" inputName="phone" inputValue="${binder.phone}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Email" inputType="text" inputId="labelEmail"
                inputPlaceHolder="Email" inputName="email" inputValue="${binder.email}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Password" inputType="password" inputId="labelPassword"
                inputPlaceHolder="Password" inputName="password" inputValue="${binder.password}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Confirm Password" inputType="password" inputId="labelConfirmPassword"
                inputPlaceHolder="Confirm Password" inputName="confirmPassword" inputValue="${binder.confirmPassword}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-users-edit" labelTitle="Edit User Information">
            <@mform.form actionUrl="${contextPath}/app/users/modify" resetUrl="/app/users">
                <@mhiddeninput.input inputType="text" inputId="labelEditUserId" inputName="id"
                inputValue="${binder.id}" required="true"></@mhiddeninput.input>
                <@mreadonlyinput.input labelName="Account" inputType="text" inputId="labelEditAccount"
                inputPlaceHolder="Account" inputName="accountName" inputValue="${binder.accountName}" required="true"></@mreadonlyinput.input>
                <@mselect.select labelName="Group" selectId="labelEditDropDownGroupName" selectName="groupId"
                values=mDropdown.groupDropdown required="true" errors=errors></@mselect.select>
                <@mreadonlyinput.input labelName="Username" inputType="text" inputId="labelEditUsername"
                inputPlaceHolder="Username" inputName="username" inputValue="${binder.username}" required="true"></@mreadonlyinput.input>
                <@minput.input labelName="Firstname" inputType="text" inputId="labelEditFirstname"
                inputPlaceHolder="Firstname" inputName="firstName" inputValue="${binder.firstName}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Lastname" inputType="text" inputId="labelEditLastname"
                inputPlaceHolder="Lastname" inputName="lastName" inputValue="${binder.lastName}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Phone" inputType="text" inputId="labelEditPhone"
                inputPlaceHolder="Phone" inputName="phone" inputValue="${binder.phone}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Email" inputType="text" inputId="labelEditEmail"
                inputPlaceHolder="Email" inputName="email" inputValue="${binder.email}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mmodal.modal className="modal-tbl-users-reset-password" labelTitle="Reset User Password">
            <@mform.form actionUrl="${contextPath}/app/users/reset-password" resetUrl="/app/users">
                <@mhiddeninput.input inputType="text" inputId="labelResetUserId" inputName="id"
                inputValue="${binder.id}" required="true"></@mhiddeninput.input>
                <@minput.input labelName="Password" inputType="password" inputId="labelResetPassword"
                inputPlaceHolder="Password" inputName="password" inputValue="${binder.password}" required="true" errors=errors></@minput.input>
                <@minput.input labelName="Confirm Password" inputType="password" inputId="labelResetConfirmPassword"
                inputPlaceHolder="Confirm Password" inputName="confirmPassword" inputValue="${binder.confirmPassword}" required="true" errors=errors></@minput.input>
            </@mform.form>
        </@mmodal.modal>

        <@mpagination.pagination page=pagination url="/app/users"></@mpagination.pagination>
    </@template.main>
    <script>
        $(document).ready(function () {
            const $modalEdit = $('.modal-tbl-users-edit');
            const $modalResetPassword = $('.modal-tbl-users-reset-password');
            const $accountDropdown = $('#labelDropDownAccountName');
            const $groupDropdown = $('#labelDropDownGroupName');
            const $editGroupDropdown = $('#labelEditDropDownGroupName');

            loadGroupDropdownByAccount($accountDropdown, $groupDropdown, false, undefined);

            $accountDropdown.change(function () {
                loadGroupDropdownByAccount($accountDropdown, $groupDropdown, false, undefined);
            });

            $('.btn-tbl-users-data-edit').click(function () {
                const $row = $(this).closest("tr");
                const $id = $row.find('td:eq(0)').text();
                axios.get('${contextPath}' + '/app/users/rest?id=' + $id).then(function (response) {
                    if (200 === response.data.status) {
                        if (loadGroupDropdownByAccount(response.data.users.accountId, $editGroupDropdown, true, response.data.users.groupId)) {
                            $modalEdit.find('input[name="id"]').val(response.data.users.id);
                            $modalEdit.find('input[name="accountName"]').val(response.data.users.accountName);
                            $modalEdit.find('select[name="groupId"]').val(response.data.users.groupId);
                            $modalEdit.find('input[name="username"]').val(response.data.users.username);
                            $modalEdit.find('input[name="firstName"]').val(response.data.users.firstName);
                            $modalEdit.find('input[name="lastName"]').val(response.data.users.lastName);
                            $modalEdit.find('input[name="phone"]').val(response.data.users.phone);
                            $modalEdit.find('input[name="email"]').val(response.data.users.email);
                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            });
            $('.btn-reset-user-password').click(function () {
                const $row = $(this).closest("tr");
                const $id = $row.find('td:eq(0)').text();
                $modalResetPassword.find('input[name="id"]').val($id);
            });

            async function loadGroupDropdownByAccount(accountControl, groupControl, isEdit, selectedId) {
                let id = accountControl;
                if (!isEdit) id = accountControl.find(":selected").val();
                await axios.get('${contextPath}' + '/app/dropdown/accounts/' + id + "/groups").then(function (response) {
                    if (200 === response.data.status) {
                        groupControl.empty();
                        for (let o of response.data.data) {
                            if (selectedId === o.id) {
                                groupControl.append("<option selected value='" + o.id + "'>"
                                    + o.name + "</option>");
                            } else {
                                groupControl.append("<option value='" + o.id + "'>"
                                    + o.name + "</option>");
                            }
                        }
                        return true;
                    }
                    return false;
                }).catch(function (err) {
                    console.log(err);
                });
                return false;
            }
        });
    </script>
</#compress>