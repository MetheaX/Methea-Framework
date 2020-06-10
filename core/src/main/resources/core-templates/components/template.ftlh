<#macro main title>
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
        <title>Methea | ${title}</title>

        <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
        <link rel="stylesheet" href="${contextPath}/resources/vendor/select2/select2.min.css">
        <link rel="stylesheet" href="${contextPath}/resources/vendor/select2/select2-bootstrap.min.css">
        <link rel="stylesheet" href="${contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    </head>
    <body class="c-app">
    <script src="${contextPath}/resources/axios/axios.min.js"></script>
    <script src="${contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
    <script src="${contextPath}/resources/vendor/jquery/jquery.twbsPagination.min.js"></script>
    <script src="${contextPath}/resources/vendor/sweetalert/sweetalert.min.js"></script>
    <script src="${contextPath}/resources/vendor/select2/select2.min.js"></script>
    <script>
        $(document).ready(function () {
            $.fn.select2.defaults.set( "theme", "bootstrap" );
        });
    </script>
    <div class="c-sidebar c-sidebar-dark c-sidebar-fixed c-sidebar-lg-show" id="sidebar">
        <div>
            <h3 class="c-sidebar-brand-full"><a class="c-sidebar-nav-link" href="${contextPath}">Methea Framework</a>
            </h3>
        </div>
        <ul class="c-sidebar-nav">
            <li class="c-sidebar-nav-item c-sidebar-nav-dropdown"><a
                        class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle" href="#"><i class="fa fa-cog fa-lg"></i>&nbsp;&nbsp;Configurations</a>
                <ul class="c-sidebar-nav-dropdown-items">
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/accounts">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-building-o"></i>&nbsp;&nbsp;Accounts</a></li>
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/groups">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-group"></i>&nbsp;&nbsp;Groups</a></li>
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/users">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-user-o"></i>&nbsp;&nbsp;Users</a></li>
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/roles">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-id-badge"></i>&nbsp;&nbsp;Roles</a></li>
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/uris">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-server"></i>&nbsp;&nbsp;URLs</a></li>
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/roleURIs">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-unlock"></i>&nbsp;&nbsp;Role URI Mapping</a></li>
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/permissions">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-lock"></i>&nbsp;&nbsp;User Permissions</a></li>
                </ul>
            </li>
            <li class="c-sidebar-nav-item c-sidebar-nav-dropdown">
                <a class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle" href="#"><i class="fa fa-cloud fa-lg"></i>&nbsp;&nbsp;Web-Services Configs</a>
                <ul class="c-sidebar-nav-dropdown-items">
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/system-certificate">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-id-card"></i>&nbsp;&nbsp;System Certificate</a></li>
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/apibases">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-paper-plane-o"></i>&nbsp;&nbsp;API Base URLs</a></li>
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/webservice-clients">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-user-secret" ></i>&nbsp;&nbsp;Clients</a></li>
                </ul>
            </li>
            <li class="c-sidebar-nav-item c-sidebar-nav-dropdown">
                <a class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle" href="#"><i class="fa fa-plus-square fa-lg"></i>&nbsp;&nbsp;Addons</a>
                <ul class="c-sidebar-nav-dropdown-items">
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/datatable">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-table fa-lg"></i>&nbsp;&nbsp;Datable Configuration</a></li>
                    <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="${contextPath}/app/clear-caches">
                            <span class="c-sidebar-nav-icon"></span><i class="fa fa-recycle"></i>&nbsp;&nbsp;Clear System Cache</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="c-wrapper c-fixed-components">
        <header class="c-header c-header-light c-header-fixed c-header-with-subheader">
            <button class="c-header-toggler c-class-toggler mfs-3 d-md-down-none" type="button" data-target="#sidebar"
                    data-class="c-sidebar-lg-show" responsive="true">
                <i class="fa fa-bars fa-2x"></i>
            </button>
            <ul class="c-header-nav ml-auto mr-4">
                <li class="c-header-nav-item dropdown"><a class="c-header-nav-link" data-toggle="dropdown" href="#"
                                                          role="button" aria-haspopup="true" aria-expanded="false">
                        <div class="c-avatar"><img class="c-avatar-img"
                                                   src="${contextPath}/resources/images/faces/dk-profile.png"
                                                   alt="Profile Image"></div>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right pt-0">
                        <div class="dropdown-header bg-light py-2"><strong>Settings</strong></div>
                        <a class="dropdown-item" href="#"><i class="fa fa-user-circle"></i>&nbsp;&nbsp;Profile</a>
                        <a class="dropdown-item" href="#"><i class="fa fa-cog"></i>&nbsp;&nbsp;Settings</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${contextPath}/logout"><i class="fa fa-sign-out"></i>&nbsp;&nbsp;Logout</a>
                    </div>
                </li>
            </ul>
        </header>
        <div class="c-body">
            <main class="c-main">
                <div class="container-fluid">
                    <div class="fade-in">
                        <#nested>
                    </div>
                </div>
            </main>
            <footer class="c-footer">
                <div><a href="${contextPath}">Methea Framework</a> © 2020 Methea Labs.</div>
                <div class="ml-auto">Powered by&nbsp;<a href="${contextPath}">Methea Framework</a></div>
            </footer>
        </div>
    </div>
    <script src="${contextPath}/resources/vendor/coreui/coreui.bundle.min.js"></script>
    <script src="${contextPath}/resources/vendor/coreui/utils/coreui-utils.js"></script>
    </body>
    </html>
</#macro>