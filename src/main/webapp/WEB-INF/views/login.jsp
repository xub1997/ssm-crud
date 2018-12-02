<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>

<link href="css/login.css" rel="stylesheet"
	type="text/css" />

</head>
<body>
	<div id="body">
		<div id="text">
			<h1>xub博客</h1>
		</div>
		<div id="loginDiv">
			<div class="loginMessage">
				<p>管理员登录</p>
			</div>
			<div id="loginForm">
				<input type="text" id="username" name="username"
					onfocus="clearUsername();" placeholder="请输入用户名" required /><br>
				<br> <input type="password" id="pwd" name="pwd"
					onfocus="clearPwd()" placeholder="请输入密码" required /><br>
				<br> <input type="radio" name="level" value="1">管理员 <input
					type="radio" name="level" value="2" checked>普通用户 <br>
				<button id="loginButton">登录</button>
			</div>
		</div>
		<div id="loginAlert"></div>
	</div>
	<div id="footer">
		<p>Copyright &copy; 2018.xub All rights reserved.xub出品</p>
	</div>
</body>
<script type="text/javascript"
	src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</html>
