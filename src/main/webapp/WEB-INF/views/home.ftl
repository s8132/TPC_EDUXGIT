<#import "/spring.ftl" as spring>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="/image/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/image/favicon.ico" type="image/x-icon">

    <title>edux-git</title>

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-row-equal-height.css" rel="stylesheet">
    <link href="/flag/css/flag-icon.min.css" rel="stylesheet">
    <link href="/css/login.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <div>
            <img src="/image/logo.png" style="margin: 0 auto; width: 300px; display: block; padding: 30px;">
            <form class="form form-login" action="/security/login" method="post">
                <div class="panel panel-primary" style="max-width: 400px; margin: 0 auto;">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <@spring.message "login.form.title"/>
                            <a href="?lang=en" class="pull-right">
                                <span class="flag-icon flag-icon-gb"></span>
                            </a>
                            <a href="?lang=pl" class="pull-right" style="padding-right: 5px;">
                                <span class="flag-icon flag-icon-pl"></span>
                            </a>
                        </h4>
                    </div>
                    <div class="panel-body">
                        <#if error>
                            <div class="alert alert-danger" role="alert">
                                <p><@spring.message "login.error.alert"/></p>
                            </div>
                        </#if>
                        <div class="form-group" style="margin-bottom: -1px">
                            <div class="input-group">
                                <div class="input-group-addon" style="border-bottom-left-radius: 0">
                                    <span class="glyphicon glyphicon-user"></span>
                                </div>
                                <input type="text" class="form-control" name="eusername" placeholder="<@spring.message "login.form.user.label"/>" style="border-bottom-right-radius: 0; height: 40px;">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"  style="border-top-left-radius: 0">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </div>
                                <input type="password" class="form-control" name="epassword" placeholder="<@spring.message "login.form.password.label"/> " style="border-top-right-radius: 0; height: 40px;">
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <button class="btn btn-lg btn-primary btn-block" type="submit"><@spring.message "login.button.text"/></button>
                    </div>
                </div>
            </form>
            <#--<form class="form form-login" style="max-width: 400px; margin: 0 auto;">
                <div class="row">
                    <div class="row-same-height">
                        <div class="col-xs-8 col-xs-height col-middle">
                            <h3 class="form-signin-heading"><@spring.message "login.form.title"/></h3>
                        </div>
                        <div class="col-xs-4 col-xs-height col-bottom">
                            <a href="?lang=en" class="btn pull-right">
                                <span class="flag-icon flag-icon-gb"></span>
                            </a>
                            <a href="?lang=pl" class="btn pull-right">
                                <span class="flag-icon flag-icon-pl"></span>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="form-group" style="margin-bottom: -1px">
                    <div class="input-group">
                        <div class="input-group-addon" style="border-bottom-left-radius: 0">
                            <span class="glyphicon glyphicon-user"></span>
                        </div>
                        <input type="text" class="form-control" placeholder="<@spring.message "login.form.user.label"/>" style="border-bottom-right-radius: 0; height: 40px;">
                    </div>
                </div>
                <div class="form-group" style="margin-bottom: 30px;">
                    <div class="input-group">
                        <div class="input-group-addon"  style="border-top-left-radius: 0">
                            <span class="glyphicon glyphicon-lock"></span>
                        </div>
                        <input type="password" class="form-control" placeholder="<@spring.message "login.form.password.label"/> " style="border-top-right-radius: 0; height: 40px;">
                    </div>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><@spring.message "login.button.text"/></button>
            </form>-->
        </div>
    </div>
    <footer class="footer">
        <div class="container">
            <p class="text-muted">
                Projekt TPC &copy; Marcin Michalik s8132, e-mail:
                <a href="mailto:s8132@pjwstk.edu.pl">s8132@pjwstk.edu.pl</a>
            </p>
        </div>
    </footer>
</body>
</html>
