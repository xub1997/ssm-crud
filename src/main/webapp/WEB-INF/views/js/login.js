
//绑定回车
$(document).keypress(function(e) {
	var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
	if (eCode == 13) {
		login();
	}
});

function clearUsername() {
	document.getElementById("username").value = "";
}

function clearPwd() {
	document.getElementById("pwd").value = "";
}
$("#loginButton").click(function() {
	//1、模态框中填写的表单数据提交给服务器进行保存
	//1、先对要提交给服务器的数据进行校验
	if (!validate_login_form()) {
		return false;
	};
	
	//2、发送ajax请求保存员工
	$.ajax({
		url: "/user/login",
		type: "POST",
		data: {
			username: $("#username").val(),
			pwd: $("#pwd").val(),
			level: $('input:radio:checked').val()
		},
		success: function(result) {
			console.log(result);
			if (result.code == 100) {
				clearUsername();
				clearPwd();
				$("#loginAlert").html("登录成功!");
				$("#loginAlert").css("display", "flex");
				var userData = result.extend.user;
				sessionStorage.setItem("username", userData.username);
				window.location.href = "index.html";

			} else {
				$("#loginAlert").html(result.extend.loginError);
				$("#loginAlert").css("display", "flex");
				setTimeout('$("#loginAlert").fadeOut(1000)', 2000);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			$("#loginAlert").html("查询失败，请联系管理员！");
			$("#loginAlert").css("display", "flex");
			setTimeout('$("#loginAlert").fadeOut(1000)', 2000);
		}
	});
});

//校验表单数据

function validate_login_form() {
	username = $("#username").val();
	pwd = $("#pwd").val();
	// 判断输入是否为空
	if (username == "" || pwd == "" || username == null || pwd == null) {
		$("#loginAlert").html("用户名或密码为空!");
		$("#loginAlert").css("display", "flex");
		setTimeout('$("#loginAlert").fadeOut(1000)', 2000);
		return false;
	}
	return true;
}
function judge(){
	if(sessionStorage.getItem("username")==""||sessionStorage.getItem("username")==null){
		window.location.href = "login.html";
	}
}
//点击退出系统
$("#logout_btn").click(function() {
	
	if (confirm("确认退出吗？")) {
		//发送ajax请求删除
		$.ajax({
			url: "/user/logout",
			type: "POST",
			success: function(result) {
				console.log(result);
				sessionStorage.removeItem("username");
				window.location.href = "login.html";
			}
		});
	}
});