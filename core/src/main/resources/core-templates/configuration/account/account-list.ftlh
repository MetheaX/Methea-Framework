<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<@template.main title="Accounts">
    <div class="table-responsive">
        <div class="float-right">
            <button type="button" class="btn btn-success" data-toggle="modal" data-target=".single-account">Add New
                <i class="mdi mdi-plus"></i></button>
        </div>
        <@mtable.table thead=tableHead tdata=accounts tcolumns=tableColumns id="tbl-accounts">
        </@mtable.table>
    </div>
<#-- Modal Add Account   -->
    <@mmodal.modal className="single-account" labelTitle="Add New Account">
        <@mform.form actionUrl="${contextPath}/app/account/save">
            <@minput.input labelName="Name" inputType="text" inputId="labelAccountName"
            inputPlaceHolder="Name" inputName="accountName" inputValue="" required="true"></@minput.input>
            <@minput.input labelName="Email" inputType="email" inputId="labelEmailName"
            inputPlaceHolder="Email" inputName="accountEmail" inputValue="" required="true"></@minput.input>
            <@minput.input labelName="Address" inputType="text" inputId="labelAddress"
            inputPlaceHolder="Address" inputName="accountAddress" inputValue="" required="true"></@minput.input>
        </@mform.form>
    </@mmodal.modal>
<#-- Modal Edit Account   -->
    <@mmodal.modal className="modal-edit" labelTitle="Edit Account">
        <@mform.form actionUrl="${contextPath}/app/account/modify">
            <@mhiddeninput.input inputType="text" inputId="labelEditAccountId" inputName="id"
            inputValue="" required="true"></@mhiddeninput.input>
            <@minput.input labelName="Name" inputType="text" inputId="labelEditAccountName"
            inputPlaceHolder="Name" inputName="accountName" inputValue="DK Inc" required="true"></@minput.input>
            <@minput.input labelName="Email" inputType="email" inputValue="dk@mail.com" inputId="labelEditEmailName"
            inputPlaceHolder="Email" inputName="accountEmail" required="true"></@minput.input>
            <@minput.input labelName="Address" inputType="text" inputValue="Phnom Penh" inputId="labelEditAddress"
            inputPlaceHolder="Address" inputName="accountAddress" required="true"></@minput.input>
        </@mform.form>
    </@mmodal.modal>
    <@mpagination.pagination page=pagination url="/app/accounts"></@mpagination.pagination>
</@template.main>

<script src="${contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="${contextPath}/resources/vendor/sweetalert/sweetalert.min.js"></script>
<script src="${contextPath}/resources/vendor/jquery/jquery.twbsPagination.min.js"></script>
<script>
    $(document).ready(function () {
        const baseUrl = "${contextPath}";
        // Disable account
        $('.btn-account-disable').click(function () {
            swal({
                title: "Are you sure?",
                text: "Once you disable, you still can enable its back.",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            }).then((willDisable) => {
                if (willDisable) {
                    const $row = $(this).closest("tr");
                    const $id = $row.find('td:eq(0)').text();
                    axios.post(baseUrl + '/app/api/v1/accounts/deactivate', {
                        id: $id
                    }).then(function (response) {
                        if (response.data.status === 200) {
                            window.location.reload();
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                } else {
                    swal("Your operation cancel!");
                }
            });
        });
        // Activate account
        $('.btn-account-enable').click(function () {
            swal({
                title: "Are you sure?",
                text: "Once you enable, you still can disable its back.",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            }).then((willActivate) => {
                if (willActivate) {
                    const $row = $(this).closest("tr");
                    const $id = $row.find('td:eq(0)').text();
                    axios.post(baseUrl + '/app/api/v1/accounts/activate', {
                        id: $id
                    }).then(function (response) {
                        if (response.data.status === 200) {
                            window.location.reload();
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                } else {
                    swal("Your operation cancel!");
                }
            });
        });
        // Bind data to edit form
        $('.btn-data-edit').click(function () {
            const $row = $(this).closest("tr");
            const $id = $row.find('td:eq(0)').text();
            const $modalEdit = $('.modal-edit');
            axios.get(baseUrl + '/app/api/v1/accounts/?id=' + $id).then(function (response) {
                if (response.data.status === 200) {
                    $modalEdit.find('input[name="id"]').val(response.data.account.id);
                    $modalEdit.find('input[name="accountName"]').val(response.data.account.accountName);
                    $modalEdit.find('input[name="accountEmail"]').val(response.data.account.accountEmail);
                    $modalEdit.find('input[name="accountAddress"]').val(response.data.account.accountAddress);
                }
            }).catch(function (error) {
                console.log(error);
            });
        });
    });
</script>