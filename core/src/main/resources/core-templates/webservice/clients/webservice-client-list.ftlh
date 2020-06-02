<#import "../../components/template.ftlh" as template>
<#import "../../components/form/methea-simple-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#compress>
    <@template.main title="Webservices-Client">
        <#if clientSecret != "">
            <div class="alert alert-success" role="alert">Please keep your client secret: ${clientSecret}</div>
        </#if>
        <div class="card">
            <div class="card-header">
                <strong>Webservice Clients</strong>
            </div>
            <div class="card-body">
                <@mform.form actionUrl="${contextPath}/app/webservice-clients/create">
                    <@minput.input labelName="Client ID" inputType="text" inputId="lableClientId"
                    inputPlaceHolder="Client ID" inputName="clientId" inputValue="" required="true" errors=errors></@minput.input>
                </@mform.form>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <strong>Clients List</strong>
            </div>
            <div class="card-body">
                <table class="table table-responsive-md table-bordered table-striped table-sm">
                    <thead>
                    <tr>
                        <th>Client ID</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody id="tbl-client-webservices">
                    <#list clients.data as td >
                        <tr>
                            <td>${td.clientId}</td>
                            <td>
                                <button class="btn btn-sm btn-info btn-client-view" data-toggle="modal" data-target="#clientDetail">View</button>
                                &nbsp;&nbsp;<button class="btn btn-sm btn-danger btn-revoke-client">Revoke</button>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="modal fade modal-tbl-client-view" id="clientDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Client Detail</h4>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    </div>
                    <div class="modal-body">
                        <div class="card">
                            <div class="card-header">
                                <strong>Client ID</strong>
                            </div>
                            <div class="card-body">
                                <input type="text" class="form-control" readonly name="detailClientId" value=""/>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header">
                                <strong>Verify Key</strong>
                            </div>
                            <div class="card-body">
                                <textarea class="form-control" readonly name="detailClientVerifyKey"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </@template.main>
    <script>
        $(document).ready(function () {
            const $modalEdit = $('.modal-tbl-client-view');
            $('.btn-client-view').click(function () {
                const $row = $(this).closest("tr");
                const $id = $row.find('td:eq(0)').text();
                axios.get('${contextPath}' + '/app/webservice-clients/view?id=' + $id).then(function (response) {
                    if (response.data.status === 200) {
                        $modalEdit.find('input[name="detailClientId"]').val(response.data.data.clientId);
                        $modalEdit.find('textarea[name="detailClientVerifyKey"]').val(response.data.data.verifyCode);
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            });

            $('.btn-revoke-client').click(function () {
                swal({
                    title: "Are you sure?",
                    text: "Once you revoke, you cannot recovery!",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                }).then((willDisable) => {
                    if (willDisable) {
                        const $row = $(this).closest("tr");
                        const $id = $row.find('td:eq(0)').text();
                        axios.post('${contextPath}' + '/app/webservice-clients/revoke-client', {
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
        });
    </script>
</#compress>