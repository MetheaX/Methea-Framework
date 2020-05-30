<#compress>
    <!DOCTYPE html>
    <!--
    * CoreUI - Free Bootstrap Admin Template
    * @version v3.2.0
    * @link https://coreui.io
    * Copyright (c) 2020 creativeLabs Łukasz Holeczek
    * Licensed under MIT (https://coreui.io/license)
    -->
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta name="description" content="Methea - Open Source Framework">
        <meta name="author" content="DKSilverX">
        <meta name="keyword"
              content="Methea,Spring,Framework,Boot,Open,Source,Java,FreeMarker,Authentication,Server,Authorize">
        <title>Methea | Login</title>

        <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
        <link rel="stylesheet" href="${contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    </head>
    <body class="c-app flex-row align-items-center">
        <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card-group">
                    <div class="card p-4">
                        <div class="card-body">
                            <h1>Change Password</h1>
                            <p class="text-muted">Update password to secure your account</p>
                            <form action="${contextPath}/profile/change-password/save" method="post">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend"><span class="input-group-text">
                                          <i class="fa fa-user-o"></i></span></div>
                                    <input class="form-control" type="password" name="password" value="" placeholder="New password">
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend"><span class="input-group-text">
                                          <i class="fa fa-lock"></i></span></div>
                                    <input class="form-control" type="password" name="confirmPassword" value="" placeholder="Confirm new password">
                                </div>
                                <#if errors['password'] != "">
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend"><span class="input-group-text" style="color: red;">${errors['password']}</span></div>
                                    </div>
                                <#else>
                                </#if>
                                <div class="row">
                                    <div class="col-6">
                                        <button class="btn btn-primary px-4" type="submit">Submit</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="${contextPath}/resources/vendor/coreui/coreui.bundle.min.js"></script>
    </body>
    </html>
</#compress>