<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<!--[if lt IE 7]>      <html lang="en" ng-app="edu-student" class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html lang="en" ng-app="edu-student" class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html lang="en" ng-app="edu-student" class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en" ng-app="edu-student" class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>edux-git admin</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/edu-student/bower_components/html5-boilerplate/css/normalize.css">
    <link rel="stylesheet" href="/edu-student/bower_components/html5-boilerplate/css/main.css">
    <link rel="stylesheet" href="/edu-student/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/edu-student/bower_components/angular-xeditable/dist/css/xeditable.css">
    <link rel="stylesheet" href="/edu-student/bower_components/angular-ui-router-anim-in-out/css/anim-in-out.css">
    <link rel="stylesheet" href="/edu-student/bower_components/angular-toggle-switch/angular-toggle-switch.css">
    <link rel="stylesheet" href="/edu-student/bower_components/angular-toggle-switch/angular-toggle-switch-bootstrap.css">
    <link rel="stylesheet" href="/edu-student/bower_components/ng-table/dist/ng-table.min.css">
    <link rel="stylesheet" href="/edu-student/bower_components/angular-notify-toaster/toaster.min.css">
    <link rel="stylesheet" href="/edu-student/bower_components/angular-busy/dist/angular-busy.min.css">
    <link rel="stylesheet" href="/edu-student/bower_components/angular-ui-select/dist/select.min.css">
    <link rel="stylesheet" href="/edu-student/bower_components/angular-ui-bootstrap-datetimepicker/datetimepicker.css">
    <link rel="stylesheet" href="/edu-student/app/css/app.css">
    <script src="/edu-student/bower_components/html5-boilerplate/js/vendor/modernizr-2.6.2.min.js"></script>
</head>
<body>
<!--[if lt IE 7]>
<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" ui-sref="home">
                <img alt="logo" src="/image/logo-white.png" height="20">
            </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav" >

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="btn-group" dropdown>
                    <a href="#" dropdown-toggle>
                        Twoje przedmioty <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu" ui-view="menu">

                    </ul>
                </li>
                <li class="btn-group" dropdown>
                    <a href="#" dropdown-toggle>
                        <span class="glyphicon glyphicon-user"></span>
                    <@security.authentication property="principal.userProfile.firstName" /> <@security.authentication property="principal.userProfile.lastName" />
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li class="divider"></li>
                        <li><a href="/security/logout">Wyloguj</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">

    <div class="row" ui-view>
    <#--<div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar" ui-view="menu">
                        <li ui-sref-active="active">
                            <a href="#" ui-sref="users">U¿ytkownicy</a>
                        </li>
                        <li ui-sref-active="active">
                            <a href="#" ui-sref="subjects">Przedmioty</a>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div ui-view="main" class="anim-in-out anim-slide-below-fade" data-anim-speed="500" data-anim-sync="true">

                    </div>
                </div>-->
    </div>
</div>

<toaster-container toaster-options="{'position-class': 'toast-bottom-full-width'}"></toaster-container>
<script src="/js/sock.js"></script>
<script src="/js/stomp.js"></script>
<script src="/edu-student/bower_components/angular/angular.js"></script>
<script src="/edu-student/bower_components/angular-animate/angular-animate.min.js"></script>
<script src="/edu-student/bower_components/angular-resource/angular-resource.min.js"></script>
<script src="/edu-student/bower_components/angular-route/angular-route.js"></script>
<script src="/edu-student/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
<script src="/edu-student/bower_components/angular-ui/build/angular-ui.min.js"></script>
<script src="/edu-student/bower_components/angular-bootstrap/ui-bootstrap.min.js"></script>
<script src="/edu-student/bower_components/angular-toggle-switch/angular-toggle-switch.min.js"></script>
<script src="/edu-student/bower_components/angular-busy/dist/angular-busy.min.js"></script>
<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.12.1.js"></script>
<script src="/edu-student/bower_components/angular-xeditable/dist/js/xeditable.js"></script>
<script src="/edu-student/bower_components/angular-notify-toaster/toaster.js"></script>
<script src="/edu-student/bower_components/ng-table/dist/ng-table.min.js"></script>
<script src="/edu-student/bower_components/angular-ui-router-anim-in-out/anim-in-out.js"></script>
<script src="/edu-student/bower_components/angular-validation-match/dist/angular-input-match.min.js"></script>
<script src="/edu-student/bower_components/angular-ui-select/dist/select.min.js"></script>
<script src="/edu-student/bower_components/angular-breadcrumb/dist/angular-breadcrumb.min.js"></script>
<script src="/edu-student/bower_components/angular-ui-bootstrap-datetimepicker/datetimepicker.js"></script>
<script src="/edu-student/bower_components/angular-input-masks/angular-input-masks.min.js"></script>
<script src="/edu-student/app/controller.js"></script>
<script src="/edu-student/app/service.js"></script>
<#--<script src="/edu-student/app/users/controller.js"></script>
<script src="/edu-student/app/users/service.js"></script>
<script src="/edu-student/app/subjects/controller.js"></script>
<script src="/edu-student/app/subjects/service.js"></script>-->
<script src="/edu-student/app/app.js"></script>
</body>
</html>