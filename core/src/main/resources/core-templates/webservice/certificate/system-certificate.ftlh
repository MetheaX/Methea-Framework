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
                <div class="alert alert-success" id="certificate-message" role="alert">System certificate active.</div>
            <#else>
                <div class="alert alert-danger" id="certificate-message" role="alert">System certificate inactive.</div>
            </#if>
        </div>
        <div class="sub-content-wrapper">
            <a class="btn btn-ghost-danger btn-disable-certificate">Disable Certificate</a>
            <a class="btn btn-success btn-enable-certificate">Enable Certificate</a>
            <a class="btn btn-github btn-renew-certificate">Re-Generate
                Certificate</a>
        </div>

        <script>
            $(document).ready(function () {
                let message = $('#certificate-message');
                let loader = $('#preloader');
                $('.btn-renew-certificate').click(function () {
                    swal({
                        title: "Are you sure?",
                        text: "Token signed with old certificate will be invalid!",
                        icon: "warning",
                        buttons: true,
                        dangerMode: true,
                    }).then((willDisable) => {
                        if (willDisable) {
                            loader.removeAttr('style');
                            message.text('Please stay on this page! Renewing system certificate.');
                            message.removeClass('alert-success');
                            message.addClass('alert-warning');
                            axios.post('${contextPath}' + '/app/system-certificate/re-generate', {}).then(function (response) {
                                if (200 === response.data.status) {
                                    loader.fadeOut(500);
                                    message.text('System certificate active.');
                                    message.removeClass('alert-warning');
                                    message.addClass('alert-success');
                                    swal(response.data.message);
                                }
                            }).catch(function (error) {
                                console.log(error);
                            });
                        } else {
                            swal("Your operation cancel!");
                        }
                    });
                });

                $('.btn-disable-certificate').click(function () {
                    swal({
                        title: "Are you sure?",
                        text: "Once you disable, you still can enable its back.",
                        icon: "warning",
                        buttons: true,
                        dangerMode: true,
                    }).then((willDisable) => {
                        if (willDisable) {
                            axios.post('${contextPath}' + '/app/system-certificate/rest/deactivate', {}).then(function (response) {
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
                $('.btn-enable-certificate').click(function () {
                    swal({
                        title: "Are you sure?",
                        text: "Once you enable, you still can disable its back.",
                        icon: "warning",
                        buttons: true,
                        dangerMode: true,
                    }).then((willActivate) => {
                        if (willActivate) {
                            axios.post('${contextPath}' + '/app/system-certificate/rest/activate', {}).then(function (response) {
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
    </@template.main>
</#compress>