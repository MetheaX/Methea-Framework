<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="System Certificate">
        <div class="sub-content-wrapper">
            <a class="btn btn-google">Disable Certificate</a>
            <a class="btn btn-success">Enable Certificate</a>
            <a class="btn btn-facebook" href="${contextPath}/app/system-certificate/re-generate">Re-Generate Certificate</a>
        </div>
        <div class="sub-content-wrapper">
            <div class="row">
                <div class="col-md-6">
                    <label class="label">Private Key</label>
                    <textarea class="form-control" rows="12" readonly>${certificate.privateKey}</textarea>
                </div>
                <div class="col-md-6">
                    <label class="label">Public Key</label>
                    <textarea class="form-control" rows="12" readonly>${certificate.publicKey}</textarea>
                </div>
            </div>
        </div>
    </@template.main>
</#compress>