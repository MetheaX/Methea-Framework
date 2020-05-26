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
            <#if "A" == "${certificateStatus}">
                <div class="success-msg">
                    System certificate active.
                </div>
            <#else>
                <div class="error-msg">
                    System certificate inactive.
                </div>
            </#if>
        </div>
        <div class="sub-content-wrapper">
            <a class="btn btn-google btn-disable-certificate">Disable Certificate</a>
            <a class="btn btn-success btn-enable-certificate">Enable Certificate</a>
            <a class="btn btn-facebook" href="${contextPath}/app/system-certificate/re-generate">Re-Generate
                Certificate</a>
        </div>

        <script>
            $(document).ready(function () {
                $('.btn-disable-certificate').click(function () {
                    swal({
                        title: "Are you sure?",
                        text: "Once you disable, you still can enable its back.",
                        icon: "warning",
                        buttons: true,
                        dangerMode: true,
                    }).then((willDisable) => {
                        if (willDisable) {
                            axios.post('${contextPath}' + '/app/system-certificate/rest/deactivate', {
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
                $('.btn-enable-certificate').click(function () {
                    swal({
                        title: "Are you sure?",
                        text: "Once you enable, you still can disable its back.",
                        icon: "warning",
                        buttons: true,
                        dangerMode: true,
                    }).then((willActivate) => {
                        if (willActivate) {
                            axios.post('${contextPath}' + '/app/system-certificate/rest/activate', {
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
    </@template.main>
</#compress>