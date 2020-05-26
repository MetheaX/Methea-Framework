<#macro main title>
<!DOCTYPE html>
<html lang="en">
    <head>
        <#-- Required meta tags-->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Methea | ${title}</title>
        <#-- plugins:css-->
        <link rel="stylesheet" href="${contextPath}/resources/vendor/iconfonts/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="${contextPath}/resources/vendor/css/vendor.bundle.base.css">
        <link rel="stylesheet" href="${contextPath}/resources/vendor/css/vendor.bundle.addons.css">
        <link rel="stylesheet" href="${contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
        <#-- endinject -->
        <#-- plugin css for this page -->
        <#-- End plugin css for this page -->
        <#-- inject:css -->
        <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
        <link rel="stylesheet" href="${contextPath}/resources/vendor/select2/select2.min.css">
        <link rel="stylesheet" href="${contextPath}/resources/vendor/select2/select2-bootstrap.min.css">
        <#-- endinject -->
    </head>
    <body>
        <#-- plugins:js -->
        <script src="${contextPath}/resources/vendor/js/vendor.bundle.base.js"></script>
        <script src="${contextPath}/resources/vendor/js/vendor.bundle.addons.js"></script>
        <#-- endinject -->
        <#-- Plugin js for this page-->
        <#-- End plugin js for this page-->
        <#-- inject:js -->
        <script src="${contextPath}/resources/js/off-canvas.js"></script>
        <script src="${contextPath}/resources/js/misc.js"></script>
        <#-- endinject -->
        <#-- Custom js for this page-->
        <script src="${contextPath}/resources/js/dashboard.js"></script>
        <script src="${contextPath}/resources/axios/axios.min.js"></script>
        <script src="${contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="${contextPath}/resources/vendor/jquery/jquery.twbsPagination.min.js"></script>
        <script src="${contextPath}/resources/vendor/sweetalert/sweetalert.min.js"></script>
        <script src="${contextPath}/resources/vendor/select2/select2.min.js"></script>
        <script src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $.fn.select2.defaults.set( "theme", "bootstrap" );
            });
        </script>
        <#-- End custom js for this page-->
        <nav class="navbar default-layout col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
            <div class="text-center navbar-brand-wrapper d-flex align-items-top" style="padding-left: 10px">
                <a class="navbar-brand brand-logo" href="index.html">
                    <img src="${contextPath}/resources/images/methea-logo-extent.svg" alt="logo" />
                </a>
            </div>
            <div class="navbar-menu-wrapper d-flex align-items-center">
                <ul class="navbar-nav navbar-nav-right">
                    <li class="nav-item dropdown">
                        <a class="nav-link count-indicator dropdown-toggle" id="messageDropdown" href="#" data-toggle="dropdown" aria-expanded="false">
                            <i class="mdi mdi-file-document-box"></i>
                            <span class="count">7</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="messageDropdown">
                            <div class="dropdown-item">
                                <p class="mb-0 font-weight-normal float-left">You have 7 unread mails
                                </p>
                                <span class="badge badge-info badge-pill float-right">View all</span>
                            </div>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <img src="${contextPath}/resources/images/faces/face4.jpg" alt="image" class="profile-pic">
                                </div>
                                <div class="preview-item-content flex-grow">
                                    <h6 class="preview-subject ellipsis font-weight-medium text-dark">David Grey
                                        <span class="float-right font-weight-light small-text">1 Minutes ago</span>
                                    </h6>
                                    <p class="font-weight-light small-text">
                                        The meeting is cancelled
                                    </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <img src="${contextPath}/resources/images/faces/face2.jpg" alt="image" class="profile-pic">
                                </div>
                                <div class="preview-item-content flex-grow">
                                    <h6 class="preview-subject ellipsis font-weight-medium text-dark">Tim Cook
                                        <span class="float-right font-weight-light small-text">15 Minutes ago</span>
                                    </h6>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <img src="${contextPath}/resources/images/faces/face3.jpg" alt="image" class="profile-pic">
                                </div>
                                <div class="preview-item-content flex-grow">
                                    <h6 class="preview-subject ellipsis font-weight-medium text-dark"> Johnson
                                        <span class="float-right font-weight-light small-text">18 Minutes ago</span>
                                    </h6>
                                    <p class="font-weight-light small-text">
                                        Upcoming board meeting
                                    </p>
                                </div>
                            </a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link count-indicator dropdown-toggle" id="notificationDropdown" href="#" data-toggle="dropdown">
                            <i class="mdi mdi-bell"></i>
                            <span class="count">4</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="notificationDropdown">
                            <a class="dropdown-item">
                                <p class="mb-0 font-weight-normal float-left">You have 4 new notifications
                                </p>
                                <span class="badge badge-pill badge-warning float-right">View all</span>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <div class="preview-icon bg-success">
                                        <i class="mdi mdi-alert-circle-outline mx-0"></i>
                                    </div>
                                </div>
                                <div class="preview-item-content">
                                    <h6 class="preview-subject font-weight-medium text-dark">Application Error</h6>
                                    <p class="font-weight-light small-text">
                                        Just now
                                    </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <div class="preview-icon bg-warning">
                                        <i class="mdi mdi-comment-text-outline mx-0"></i>
                                    </div>
                                </div>
                                <div class="preview-item-content">
                                    <h6 class="preview-subject font-weight-medium text-dark">Settings</h6>
                                    <p class="font-weight-light small-text">
                                        Private message
                                    </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <div class="preview-icon bg-info">
                                        <i class="mdi mdi-email-outline mx-0"></i>
                                    </div>
                                </div>
                                <div class="preview-item-content">
                                    <h6 class="preview-subject font-weight-medium text-dark">New user registration</h6>
                                    <p class="font-weight-light small-text">
                                        2 days ago
                                    </p>
                                </div>
                            </a>
                        </div>
                    </li>
                    <li class="nav-item dropdown d-none d-xl-inline-block">
                        <a class="nav-link dropdown-toggle" id="UserDropdown" href="#" data-toggle="dropdown" aria-expanded="false">
                            <span class="profile-text">Hello, DKSilverX !</span>
                            <img class="img-xs rounded-circle" src="${contextPath}/resources/images/faces/dk-profile.png" alt="Profile image">
                        </a>
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="UserDropdown">
                            <a class="dropdown-item p-0">
                                <div class="d-flex border-bottom">
                                    <div class="py-3 px-4 d-flex align-items-center justify-content-center">
                                        <i class="mdi mdi-bookmark-plus-outline mr-0 text-gray"></i>
                                    </div>
                                    <div class="py-3 px-4 d-flex align-items-center justify-content-center border-left border-right">
                                        <i class="mdi mdi-account-outline mr-0 text-gray"></i>
                                    </div>
                                    <div class="py-3 px-4 d-flex align-items-center justify-content-center">
                                        <i class="mdi mdi-alarm-check mr-0 text-gray"></i>
                                    </div>
                                </div>
                            </a>
                            <a class="dropdown-item mt-2">
                                Manage Profile
                            </a>
                            <a class="dropdown-item">
                                Change Password
                            </a>
                            <a class="dropdown-item">
                                Check Inbox
                            </a>
                            <a class="dropdown-item" href="${contextPath}/logout">
                                Sign Out
                            </a>
                        </div>
                    </li>
                </ul>
                <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
                    <span class="icon-menu"></span>
                </button>
            </div>
        </nav>
        <div class="container-fluid page-body-wrapper">
            <nav class="sidebar sidebar-offcanvas" id="sidebar">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/app">
                            <i class="menu-icon mdi mdi-television"></i>
                            <span class="menu-title">Dashboard</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="collapse" href="#core-config" aria-expanded="false" aria-controls="ui-basic">
                            <i class="menu-icon mdi mdi-key-variant"></i>
                            <span class="menu-title">Configurations</span>
                            <i class="menu-arrow"></i>
                        </a>
                        <div class="collapse" id="core-config">
                            <ul class="nav flex-column sub-menu">
                                <li class="nav-item">
                                    <a class="nav-link" href="${contextPath}/app/accounts">Accounts</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${contextPath}/app/groups">Groups</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${contextPath}/app/users">Users</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${contextPath}/app/roles">Roles</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${contextPath}/app/uris">URLs</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${contextPath}/app/roleURIs">Role URI Mapping</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${contextPath}/app/permissions">Users Permissions</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="collapse" href="#web-services-config" aria-expanded="false" aria-controls="ui-basic">
                            <i class="menu-icon mdi mdi-server-security"></i>
                            <span class="menu-title">Web-Services Configs</span>
                            <i class="menu-arrow"></i>
                        </a>
                        <div class="collapse" id="web-services-config">
                            <ul class="nav flex-column sub-menu">
                                <li class="nav-item">
                                    <a class="nav-link" href="${contextPath}/app/system-certificate">System Certificate</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Clients</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="collapse" href="#additional-config" aria-expanded="false" aria-controls="ui-basic">
                            <i class="menu-icon mdi mdi-settings"></i>
                            <span class="menu-title">Additional Settings</span>
                            <i class="menu-arrow"></i>
                        </a>
                        <div class="collapse" id="additional-config">
                            <ul class="nav flex-column sub-menu">
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Column Datatable</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${contextPath}/app/clear-caches">Clear System Caches</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
            <div class="main-panel">
                <div class="content-wrapper">
                    <#nested>
                </div>
                <footer class="footer">
                    <div class="container-fluid clearfix">
                        <span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright © 2020
                          <a href="#" target="_blank">Custom By DKSilverX</a>. All rights reserved.</span>
                                    <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted & made with
                          <i class="mdi mdi-heart text-danger"></i>
                        </span>
                    </div>
                </footer>
            </div>
        </div>
    </body>
</html>
</#macro>