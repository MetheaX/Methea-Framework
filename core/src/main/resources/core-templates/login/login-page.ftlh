<#compress>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Methea | Login</title>

        <link rel="icon" type="image/png" href="${contextPath}/resources/images/icons/favicon.ico"/>
        <link rel="stylesheet" type="text/css"
              href="${contextPath }/resources/vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css"
              href="${contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css"
              href="${contextPath}/resources/vendor/animate/animate.css">
        <link rel="stylesheet" type="text/css"
              href="${contextPath}/resources/vendor/css-hamburgers/hamburgers.min.css">
        <link rel="stylesheet" type="text/css"
              href="${contextPath}/resources/vendor/animsition/css/animsition.min.css">
        <link rel="stylesheet" type="text/css"
              href="${contextPath}/resources/vendor/select2/select2.min.css">
        <link rel="stylesheet" type="text/css"
              href="${contextPath}/resources/vendor/daterangepicker/daterangepicker.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/util.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main.css">
    </head>
    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <form class="login100-form validate-form p-l-55 p-r-55 p-t-178"  action="login" method="post">
                            <span class="login100-form-title">
                                Sign In
                            </span>
                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter username">
                            <input class="input100" type="text" name="username" value="admin" placeholder="Username"/>
                            <span class="focus-input100"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Please enter password">
                            <input class="input100" type="password" name="password" value="admin" placeholder="Password"/>
                            <span class="focus-input100"></span>
                        </div>
                        <div class="text-right p-t-13 p-b-23">
                                <span class="txt1">
                                    Forgot
                                </span>
                            <a href="#" class="txt2">
                                Username / Password?
                            </a>
                        </div>
                        <div class="container-login100-form-btn">
                            <button type="submit" class="login100-form-btn">
                                Sign in
                            </button>
                        </div>
                        <div class="flex-col-c p-t-170 p-b-40">
                                <span class="txt1 p-b-9">
                                    Don’t have an account?
                                </span>
                            <a href="#" class="txt3">
                                Sign up now
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="${contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="${contextPath}/resources/vendor/animsition/js/animsition.min.js"></script>
        <script src="${contextPath}/resources/vendor/bootstrap/js/popper.js"></script>
        <script src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="${contextPath}/resources/vendor/select2/select2.min.js"></script>
        <script src="${contextPath}/resources/vendor/daterangepicker/moment.min.js"></script>
        <script src="${contextPath}/resources/vendor/daterangepicker/daterangepicker.js"></script>
        <script src="${contextPath}/resources/vendor/countdowntime/countdowntime.js"></script>
        <script src="${contextPath}/resources/js/main.js"></script>
    </body>
</html>
</#compress>