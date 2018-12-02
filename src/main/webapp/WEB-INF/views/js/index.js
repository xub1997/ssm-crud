var totalRecord, currentPage;
var pageNum=1;
//1、页面加载完成以后，直接去发送ajax请求,要到分页数据
$(function() {
	judge();
	//去首页
	to_page(1);
});

function to_page(pageNum) {
	$.ajax({
		url: "/timeLines",
		data: "pageNum=" + pageNum,
		type: "GET",
		success: function(result) {
			console.log(result);
			//console.log(result);
			//1、解析并显示用户数据
			build_timeLines_table(result);
			//2、解析并显示分页信息
			build_page_info(result);
			//3、解析显示分页条数据
			build_page_nav(result);
		}
	});
}

function build_timeLines_table(result) {
	//清空
	$(".event").empty();
	str="<section id=\"cd-timeline\" class=\"cd-container\">";
	$.each(result.extend.pageInfo.list, function(index, item) {
		var event = item.title;
		var description = item.content;
		var time = item.time;
		var color = (item.color == 'Y' ? "yellow" : (item.color == 'G' ? "green" : "red"));
		//定位
		str += "<div class=\"cd-timeline-block\"><div class=\"cd-timeline-img cd-" + color 
		+ "\"><img src=\"img/cd-icon-location.svg\" alt=\"location\"></div><div class=\"cd-timeline-content\"><h2>" 
		+ event + "</h2><p>" + description + "</p><span class=\"cd-date\">" 
		+ time + "</span></div></div>";
	});
	str += "</section>";
	$(".event").empty();
	$(".event").append(str);

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
