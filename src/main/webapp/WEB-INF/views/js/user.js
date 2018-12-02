var totalRecord, currentPage;
//1、页面加载完成以后，直接去发送ajax请求,要到分页数据
$(function() {
	judge();
	//去首页
	to_page(1);
});

function to_page(pageNum) {
	$.ajax({
		url: "/users",
		data: "pageNum=" + pageNum,
		type: "GET",
		success: function(result) {
			console.log(result);
			//console.log(result);
			//1、解析并显示用户数据
			build_users_table(result);
			//2、解析并显示分页信息
			build_page_info(result);
			//3、解析显示分页条数据
			build_page_nav(result);
		}
	});
}

function build_users_table(result) {
	//清空table表格
	$("#users_table tbody").empty();
	var users = result.extend.pageInfo.list;
	$.each(users, function(index, item) {
		var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
		var userIdTd = $("<td></td>").append(item.userid);
		var userNameTd = $("<td></td>").append(item.username);
		var levelTd = $("<td></td>").append(item.level == 1 ? "管理员" : "普通用户");
		var createTimeTd = $("<td></td>").append(item.createtime);
		var modifyTimeTd = $("<td></td>").append(item.modifytime);

		var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
			.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
		//为编辑按钮添加一个自定义的属性，来表示当前用户id
		editBtn.attr("edit-id", item.userid);
		var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
			.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
		//为删除按钮添加一个自定义的属性来表示当前删除的用户id
		delBtn.attr("del-id", item.userid);
		var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
		//var delBtn = 
		//append方法执行完成以后还是返回原来的元素
		$("<tr></tr>").append(checkBoxTd)
			.append(userIdTd)
			.append(userNameTd)
			.append(levelTd)
			.append(createTimeTd)
			.append(modifyTimeTd)
			.append(btnTd)
			.appendTo("#users_table tbody");
	});
}
//解析显示分页信息
function build_page_info(result) {
	$("#page_info_area").empty();
	$("#page_info_area").append("当前" + result.extend.pageInfo.pageNum + "页,总" +
		result.extend.pageInfo.pages + "页,总" +
		result.extend.pageInfo.total + "条记录");
	totalRecord = result.extend.pageInfo.total;
	currentPage = result.extend.pageInfo.pageNum;
}
//解析显示分页条，点击分页要能去下一页....
function build_page_nav(result) {
	//page_nav_area
	$("#page_nav_area").empty();
	var ul = $("<ul></ul>").addClass("pagination");

	//构建元素
	var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
	var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
	if (result.extend.pageInfo.hasPreviousPage == false) {
		firstPageLi.addClass("disabled");
		prePageLi.addClass("disabled");
	} else {
		//为元素添加点击翻页的事件
		firstPageLi.click(function() {
			to_page(1);
		});
		prePageLi.click(function() {
			to_page(result.extend.pageInfo.pageNum - 1);
		});
	}

	var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
	var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
	if (result.extend.pageInfo.hasNextPage == false) {
		nextPageLi.addClass("disabled");
		lastPageLi.addClass("disabled");
	} else {
		nextPageLi.click(function() {
			to_page(result.extend.pageInfo.pageNum + 1);
		});
		lastPageLi.click(function() {
			to_page(result.extend.pageInfo.pages);
		});
	}

	//添加首页和前一页 的提示
	ul.append(firstPageLi).append(prePageLi);
	//1,2，3遍历给ul中添加页码提示
	$.each(result.extend.pageInfo.navigatepageNums, function(index, item) {

		var numLi = $("<li></li>").append($("<a></a>").append(item));
		if (result.extend.pageInfo.pageNum == item) {
			numLi.addClass("active");
		}
		numLi.click(function() {
			to_page(item);
		});
		ul.append(numLi);
	});
	//添加下一页和末页 的提示
	ul.append(nextPageLi).append(lastPageLi);

	//把ul加入到nav
	var navEle = $("<nav></nav>").append(ul);
	navEle.appendTo("#page_nav_area");
}

//完成全选/全不选功能
$("#check_all").click(function() {
	//attr获取checked是undefined;
	//我们这些dom原生的属性；attr获取自定义属性的值；
	//prop修改和读取dom原生属性的值
	$(".check_item").prop("checked", $(this).prop("checked"));
});

//check_item
$(document).on("click", ".check_item", function() {
	//判断当前选择中的元素是否5个
	var flag = $(".check_item:checked").length == $(".check_item").length;
	$("#check_all").prop("checked", flag);
});

//单个删除
$(document).on("click", ".delete_btn", function() {
	//1、弹出是否确认删除对话框
	var userName = $(this).parents("tr").find("td:eq(2)").text();
	var userId = $(this).attr("del-id");

	if (confirm("确认删除【" + userName + "】吗？")) {
		//确认，发送ajax请求删除即可
		$.ajax({
			url: "/user/" + userId,
			type: "DELETE",
			success: function(result) {
				console.log(result);
				alert(result.msg);
				//回到本页
				to_page(currentPage);
			}
		});
	}
});
//点击全部删除，就批量删除
$("#user_delete_all_btn").click(function() {
	//
	var userNames = "";
	var del_idstr = "";
	$.each($(".check_item:checked"), function() {
		//this
		userNames += $(this).parents("tr").find("td:eq(2)").text() + ",";
		//组装员工id字符串
		del_idstr += $(this).parents("tr").find("td:eq(1)").text() + ",";
	});
	//去除userNames多余的,
	userNames = userNames.substring(0, userNames.length - 1);
	//去除删除的id多余的-
	del_idstr = del_idstr.substring(0, del_idstr.length - 1);
	if (confirm("确认删除【" + userNames + "】吗？")) {
		//发送ajax请求删除
		$.ajax({
			url: "/user/" + del_idstr,
			type: "DELETE",
			success: function(result) {
				console.log(result);
				alert(result.msg);
				$("#check_all").prop("checked",false);
				//回到当前页面
				to_page(currentPage);
			}
		});
	}
});
//清空表单样式及内容
function reset_form(ele) {
	$(ele)[0].reset();
	//清空表单样式
	$(ele).find("*").removeClass("has-error has-success");
	$(ele).find(".help-block").text("");
}

//点击新增按钮弹出模态框。
$("#user_add_modal_btn").click(function() {
	//清除表单数据（表单完整重置（表单的数据，表单的样式））
	reset_form("#userAddModal form");

	//弹出模态框
	$("#userAddModal").modal({
		backdrop: "static"
	});
});

//显示校验结果的提示信息
function show_validate_msg(ele, status, msg) {
	//清除当前元素的校验状态
	$(ele).parent().removeClass("has-success has-error");
	$(ele).next("span").text("");
	if ("success" == status) {
		$(ele).parent().addClass("has-success");
		$(ele).next("span").text(msg);
	} else if ("error" == status) {
		$(ele).parent().addClass("has-error");
		$(ele).next("span").text(msg);
	}
}

//校验用户名是否可用
$("#userName_add_input").change(function() {
	//发送ajax请求校验用户名是否可用
	var userName = this.value;
	$.ajax({
		url: "/check",
		data: "userName=" + userName,
		type: "POST",
		success: function(result) {
			console.log(result);
			if (result.code == 100) {
				show_validate_msg("#userName_add_input", "success", "用户名可用");
				$("#user_save_btn").attr("ajax-va", "success");
			} else {
				show_validate_msg("#userName_add_input", "error", result.extend.va_msg);
				$("#user_save_btn").attr("ajax-va", "error");
			}
		}
	});
});

//校验表单数据
function validate_add_form() {
	//1、拿到要校验的数据，使用正则表达式
	var userName = $("#userName_add_input").val();
	var regName = /^[a-zA-Z0-9_-]{6,16}$/;
	if (!regName.test(userName)) {
		show_validate_msg("#userName_add_input", "error", "用户名必须是6-16位（英文或者数字）");
		return false;
	} else {
		show_validate_msg("#userName_add_input", "success", "");
	};

	//2、校验密码信息
	var pwd = $("#pwd_add_input").val();
	var regPwd = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/;
	if (!regPwd.test(pwd)) {
		//应该清空这个元素之前的样式
		show_validate_msg("#pwd_add_input", "error", "密码至少8-16个字符，至少1个大写字母，1个小写字母和1个数字");
		return false;
	} else {
		show_validate_msg("#pwd_add_input", "success", "密码格式正确");
	};

	//校验两次密码是否一致
	var pwdConfirm = $("#pwdConfirm_add_input").val();
	if (pwdConfirm != pwd) {
		//应该清空这个元素之前的样式
		show_validate_msg("#pwdConfirm_add_input", "error", "两次密码不一致");
		return false;
	} else {
		show_validate_msg("#pwdConfirm_add_input", "success", "密码一致");
	}
	return true;
}

//点击保存，保存用户。
$("#user_save_btn").click(function() {
	//1、模态框中填写的表单数据提交给服务器进行保存
	//1、先对要提交给服务器的数据进行校验
	if (!validate_add_form()) {
		return false;
	};
	//1、判断之前的ajax用户名校验是否成功。如果成功。
	if ($(this).attr("ajax-va") == "error") {
		return false;
	}

	//2、发送ajax请求保存员工
	$.ajax({
		url: "/user",
		type: "POST",
		data: $("#userAddModal form").serialize(),
		success: function(result) {
			console.log(result);
			if (result.code == 100) {
				//员工保存成功；
				//1、关闭模态框
				$("#userAddModal").modal('hide');

				//2、来到最后一页，显示刚才保存的数据
				//发送ajax请求显示最后一页数据即可
				to_page(totalRecord);
			} else {
				//有哪个字段的错误信息就显示哪个字段的；
				if (undefined != result.extend.errorFields.pwd) {
					//显示密码错误信息
					show_validate_msg("#pwd_add_input", "error", result.extend.errorFields.pwd);
				}
				if (undefined != result.extend.errorFields.userName) {
					//显示用户名的错误信息
					show_validate_msg("#userName_add_input", "error", result.extend.errorFields.userName);
				}
			}
		}
	});
});

//1、我们是按钮创建之前就绑定了click，所以绑定不上。
//1）、可以在创建按钮的时候绑定。    2）、绑定点击.live()
//jquery新版没有live，使用on进行替代
$(document).on("click", ".edit_btn", function() {

	//1、查出用户信息，显示用户信息
	getUser($(this).attr("edit-id"));

	//2、把用户的id传递给模态框的更新按钮
	$("#user_update_btn").attr("edit-id", $(this).attr("edit-id"));
	$("#userUpdateModal").modal({
		backdrop: "static"
	});
});

function getUser(id) {
	$.ajax({
		url: "/user/" + id,
		type: "GET",
		success: function(result) {
			console.log(result);
			var userData = result.extend.user;
			$("#userName_update_static").text(userData.username);
			$("#userUpdateModal input[name=level]").val([userData.level]);
		}
	});
}
//点击更新，更新用户信息
$("#user_update_btn").click(function() {
	

	//发送ajax请求保存更新的员工数据
	$.ajax({
		url: "/user/" + $(this).attr("edit-id"),
		type: "PUT",
		data: $("#userUpdateModal form").serialize(),
		success: function(result) {
			console.log(result);
			//1、关闭对话框
			$("#userUpdateModal").modal("hide");
			//2、回到本页面
			to_page(currentPage);
		}
	});
});