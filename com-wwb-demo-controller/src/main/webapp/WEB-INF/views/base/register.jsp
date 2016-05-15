<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找锡网-注册</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<script language="javascript" src="js/jquery-1.7.2.js"></script>
<script language="javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="js/function.js"></script>
</head>

<body>
<div class="blank50"></div>
<div class="welcome wrap_reg">欢迎加入</div>
<div class="register wrap_reg">
	<form id="FormRegister">
        <label>注册邮箱:</label>
        <input class="inp" dbc2sbc="true" id="Email" name="Email" required="required" type="email" value="">
        <p class="intro" warn="Email" title="输入你的常用邮箱，它会成为你未来的登录账号">输入你的常用邮箱，它会成为你未来的登录账号</p>
        <label>登录密码：</label>
        <input class="inp" id="Password" minlength="6" name="Password" required type="password" />
        <p class="intro" warn="Password" title="登录密码不得少于6个字符，请尽可能复杂">登录密码不得少于6个字符，请尽可能复杂</p>
        <label>确认密码：</label>
        <input class="inp" equal="Password" id="ConfirmPassword" name="ConfirmPassword" required strict="true" type="password" />
        <p class="intro" warn="ConfirmPassword" title="请重新输入一遍密码以确认">请重新输入一遍密码以确认</p>
        <label>用户昵称：</label>
        <input class="inp" dbc2sbc="true" id="UserName" maxlength="16" minlength="2" name="UserName" pattern="[a-zA-Z0-9一-龥]+$" required type="text" value="" />
         <p class="intro" warn="UserName" title="允许英文、中文或数字，不支持任何符号，最多8中文，16英文">允许英文、中文或数字，不支持任何符号，最多8中文，16英文</p>
         <label>性别：</label>
         <p class="intro_sex">
         <input type="radio" name="Sex" class="sex" value="Male" checked="checked" />男
         <input type="radio" name="Sex" class="sex" value="Female"/>女
         </p>
         <label>出生日期：</label>
         <input class="inp" id="Time" onClick="WdatePicker()"/>
         <input type="submit" value="立即加入" class="inp_sub"/>
    </form>
</div>
<div class="blank50">
</div>

</body>
</html>