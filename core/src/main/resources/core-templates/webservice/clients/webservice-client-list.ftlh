<#import "../../components/template.ftlh" as template>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#compress>
    <@template.main title="Webservices-Client">
        <#if clientSecret != "">
            <div class="alert alert-success" role="alert">${clientSecret}</div>
        </#if>
        <@mform.form actionUrl="${contextPath}/app/webservice-clients/create" resetUrl="/app/webservice-clients">
            <@minput.input labelName="Client ID" inputType="text" inputId="lableClientId"
            inputPlaceHolder="Client ID" inputName="clientId" inputValue="" required="true" errors=errors></@minput.input>
        </@mform.form>
        <div class="content-center">
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
                            <button class="btn btn-sm btn-info">View</button>
                            &nbsp;&nbsp;<button class="btn btn-sm btn-danger">Revoke</button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </@template.main>
</#compress>