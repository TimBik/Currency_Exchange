<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../css/style.css">
    <title>Document</title>
</head>
<body>
<h1>Авторизация</h1>
<#if error??>
    <h1>Ошибка</h1>
</#if>
<div>

    <div class="panel panel-default container-fluid">
        <br>
        <div class="row">
            <div class="col"></div>
            <div class="col-md-3" align="center">
                <span><h1>Login</h1></span>
                <span>And improve yourself</span>
                <br>
                <br>
                <br>
                <form method="post">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                               placeholder="Enter email" name="email">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"
                               name="password">
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberMe">
                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                    </div>
                    <button input type="submit" class="btn btn-primary">Login</button>
                </form>
            </div>
            <div class="col"></div>
        </div>
    </div>
    <br>
</div>
</body>
</html>
