var totalRecord, currentPage;
//1、页面加载完成以后，直接去发送ajax请求,要到分页数据
$(function() {
	judge();
	//去首页
	to_page(1);
});

function to_page(pageNum) {
	$.ajax({
		url: "/messages",
		data: "pageNum=" + pageNum,
		type: "GET",
		success: function(result) {
			console.log(result);
			//console.log(result);
			//1、解析并显示联系信息数据
			build_messages_table(result);
			//2、解析并显示分页信息
			build_page_info(result);
			//3、解析显示分页条数据
			build_page_nav(result);
		}
	});
}

function build_messages_table(result) {
	//清空table表格
	$("#messages_table tbody").empty();
	var messages = result.extend.pageInfo.list;
	$.each(messages, function(index, item) {
		var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
		var idTd = $("<td></td>").append(item.id);
		var nameTd = $("<td></td>").append(item.name);
		var emailTd = $("<td></td>").append(item.email);
		
		var createTimeTd = $("<td></td>").append(item.createtime);
		var remarkTd = $("<td></td>").append(item.remark);

		var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
			.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("查看内容");
		//为编辑按钮添加一个自定义的属性，来表示当前联系信息id
		editBtn.attr("edit-id", item.id);
		var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
			.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
		//为删除按钮添加一个自定义的属性来表示当前删除的联系信息id
		delBtn.attr("del-id", item.id);
		var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
		//var delBtn = 
		//append方法执行完成以后还是返回原来的元素
		$("<tr></tr>").append(checkBoxTd)
			.append(idTd)
			.append(nameTd)
			.append(emailTd)
			.append(createTimeTd)
			.append(remarkTd)
			.append(btnTd)
			.appendTo("#messages_table tbody");
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
	var messageName = $(this).parents("tr").find("td:eq(2)").text();
	var Id = $(this).attr("del-id");

	if (confirm("确认删除【" + messageName + "】吗？")) {
		//确认，发送ajax请求删除即可
		$.ajax({
			url: "/message/" + Id,
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
$("#message_delete_all_btn").click(function() {
	//
	var messageNames = "";
	var del_idstr = "";
	$.each($(".check_item:checked"), function() {
		//this
		messageNames += $(this).parents("tr").find("td:eq(2)").text() + ",";
		//组装员工id字符串
		del_idstr += $(this).parents("tr").find("td:eq(1)").text() + ",";
	});
	//去除messageNames多余的,
	messageNames = messageNames.substring(0, messageNames.length - 1);
	//去除删除的id多余的-
	del_idstr = del_idstr.substring(0, del_idstr.length - 1);
	if (confirm("确认删除【" + messageNames + "】吗？")) {
		//发送ajax请求删除
		$.ajax({
			url: "/message/" + del_idstr,
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


//1、我们是按钮创建之前就绑定了click，所以绑定不上。
//1）、可以在创建按钮的时候绑定。    2）、绑定点击.live()
//jquery新版没有live，使用on进行替代
$(document).on("click", ".edit_btn", function() {

	//1、查出联系信息信息，显示联系信息信息
	getMessage($(this).attr("edit-id"));

	//2、把联系信息的id传递给模态框的更新按钮
	$("#message_update_btn").attr("edit-id", $(this).attr("edit-id"));
	reset_form("#messageUpdateModal form");
	$("#messageUpdateModal").modal({
		backdrop: "static"
	});
});

function getMessage(id) {
	$.ajax({
		url: "/message/" + id,
		type: "GET",
		success: function(result) {
			console.log(result);
			var messageData = result.extend.message;
			$("#name_update_static").text(messageData.name);
			$("#email_update_static").text(messageData.email);
			$("#time_update_static").text(messageData.createtime);
			$("#msg_update_static").text(messageData.msg);
			$("#remark_update_input").val(messageData.remark);
			
		}
	});
}
//点击更新，更新联系信息信息
$("#message_update_btn").click(function() {
	

	//发送ajax请求保存更新的员工数据
	$.ajax({
		url: "/message/" + $(this).attr("edit-id"),
		type: "PUT",
		data: $("#messageUpdateModal form").serialize(),
		success: function(result) {
			console.log(result);
			//1、关闭对话框
			$("#messageUpdateModal").modal("hide");
			//2、回到本页面
			to_page(currentPage);
		}
	});
});