<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<#macro template type='admin'>
    <#assign colorClassNavbar = "navbar-inverse"/>
    <#if type='admin'>
        <#assign colorClassNavbar = "navbar-inverse"/>
    </#if>

    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
            <meta name="description" content="">
            <meta name="author" content="">
            <link rel="icon" href="/image/favicon.ico">

            <title>Dashboard Template for Bootstrap</title>

            <!-- Bootstrap core CSS -->
            <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

            <!-- Custom styles for this template -->
            <link href="/css/template.css" rel="stylesheet">
        </head>
        <body>
            <nav class="navbar ${colorClassNavbar} navbar-fixed-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/">
                            <img alt="logo" src="/image/logo-white.png" height="20">
                        </a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                    <span class="glyphicon glyphicon-user"></span>
                                    <@security.authentication property="principal.userProfile.firstName" /> <@security.authentication property="principal.userProfile.lastName" />
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" role="menu">
                                    <li class="divider"></li>
                                    <li><a href="#">Wyloguj</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-3 col-md-2 sidebar">
                        <@security.authorize access="hasRole('ROLE_ADMIN')">
                            <ul class="nav nav-sidebar">
                                <li><a href="#">Użytkownicy</a></li>
                                <li><a href="#">Przedmioty</a></li>
                            </ul>
                        </@security.authorize>
                    </div>
                    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                        <#nested />
                    </div>
                </div>
            </div>

            <!-- Bootstrap core JavaScript
            ================================================== -->
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
            <script src="/bootstrap/js/bootstrap.min.js"></script>
        </body>
    </html>
</#macro>