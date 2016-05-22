<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>找锡网-登录</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script language="javascript" src="js/jquery-1.7.2.js"></script>
    <script language="javascript" src="js/function.js"></script>
</head>

<body>
<div class="blank100"></div>
<div class="welcome wrap_login">登录</div>
<div class="login wrap_reg">
    <div class="left">
        <form action="loginAction" id="FormLogin" method="post">
            <label>登录邮箱：</label><span>无效的登录邮箱</span>
            <input class="inp" dbc2sbc="true" id="Email" name="email" required value="">
            <label>登录密码：</label><span></span>
            <input class="inp" id="Password" minlength="6" name="Password" required type="password" />
            <input type="submit" value="马上登录" class="inp_sub"/>
        </form>
    </div>
    <div class="right">
        <span>还没有账号?点击注册</span>
        <a href="register.jsp" class="inp_sub">点击注册</a>
    </div>
</div>
<div class="blank50">
</div>

</body>
</html>