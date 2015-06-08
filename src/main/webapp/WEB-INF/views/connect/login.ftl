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
        <div class="jumbotron">
            <h2>Witaj!</h2>
            <p>Do poprawnego działa tej aplikacji wymagane jest połączenie swojego konta w naszej aplikacji z kontem na <a href="https://github.com/">www.github.com</a>. Jeśli jeszcze nie posiadasz swojego konta na github.com to możesz je założyć <a href="https://github.com/join" class="btn btn-success">TUTAJ</a>  </p>
            <p>
                <a href="https://github.com/login/oauth/authorize?client_id=${clientId}&scope=${scope}&redirect_uri=http://stormy-harbor-6689.herokuapp.com/connect/token" class="btn btn-primary btn-lg" href="#" role="button">
                    <span class="glyphicon glyphicon-link"></span>
                    Połącz swoje konto z github.com
                </a>
            </p>
        </div>
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
