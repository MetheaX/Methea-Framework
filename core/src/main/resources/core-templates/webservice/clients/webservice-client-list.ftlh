<#import "../../components/template.ftlh" as template>
<#import "../../components/form/methea-simple-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/select/methea-multiple-select.ftlh" as mmselect>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#compress>
    <@template.main title="Webservices-Client">
        <#if clientSecret != "">
            <div class="alert alert-success" role="alert">Please keep your client secret, <span style="color: red">ONE TIME GENERATE ONLY</span>: ${clientSecret}</div>
        </#if>
        <div class="card">
            <div class="card-header">
                <strong>Webservice Clients</strong>
            </div>
            <div class="card-body">
                <@mform.form actionUrl="${contextPath}/app/webservice-clients/create">
                    <@minput.input labelName="Client ID" inputType="text" inputId="lableClientId"
                    inputPlaceHolder="Client ID" inputName="clientId" inputValue="" required="true" errors=errors></@minput.input>
                    <@mmselect.select labelName="Allow API Enpoints" selectId="labelSelectBaseApi" selectName="apiBases"
                    values=mDropdown.apiUrlDropdown required="true" errors=errors></@mmselect.select>
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
                                <button class="btn btn-sm btn-danger btn-revoke-client">Revoke</button>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </@template.main>
    <script>
        $(document).ready(function () {
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
                            if (200 === response.data.status) {
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