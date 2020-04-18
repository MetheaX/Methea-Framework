<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
<@template.main title="Accounts">
    <div class="table-responsive">
        <@mtable.table thead=tableHead tdata=accounts tcolumns=tableColumns filters=tableFilterColumns
            dataFilters=dataFilters urlFilter="/app/accounts" id="tbl-accounts">
        </@mtable.table>
    </div>

    <@mmodal.modal className="modal-tbl-accounts-add" labelTitle="Add New Account">
        <@mform.form actionUrl="${contextPath}/app/accounts/save">
            <@minput.input labelName="Name" inputType="text" inputId="labelAccountName"
            inputPlaceHolder="Name" inputName="accountName" inputValue="" required="true"></@minput.input>
            <@minput.input labelName="Email" inputType="email" inputId="labelEmailName"
            inputPlaceHolder="Email" inputName="accountEmail" inputValue="" required="true"></@minput.input>
            <@minput.input labelName="Address" inputType="text" inputId="labelAddress"
            inputPlaceHolder="Address" inputName="accountAddress" inputValue="" required="true"></@minput.input>
        </@mform.form>
    </@mmodal.modal>

    <@mmodal.modal className="modal-tbl-accounts-edit" labelTitle="Edit Account">
        <@mform.form actionUrl="${contextPath}/app/accounts/modify">
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
<script>
    $(document).ready(function () {
        $('.btn-tbl-accounts-data-edit').click(function () {
            const $row = $(this).closest("tr");
            const $id = $row.find('td:eq(0)').text();
            const $modalEdit = $('.modal-tbl-accounts-edit');
            axios.get('${contextPath}' + '/app/accounts/rest?id=' + $id).then(function (response) {
                if (response.data.status === 200) {
                    $modalEdit.find('input[name="id"]').val(response.data.accounts.id);
                    $modalEdit.find('input[name="accountName"]').val(response.data.accounts.accountName);
                    $modalEdit.find('input[name="accountEmail"]').val(response.data.accounts.accountEmail);
                    $modalEdit.find('input[name="accountAddress"]').val(response.data.accounts.accountAddress);
                }
            }).catch(function (error) {
                console.log(error);
            });
        });
    });
</script>
</#compress>