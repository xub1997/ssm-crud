<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8" />

<script type="text/javascript"
	src="js/jquery-1.12.4.min.js"></script>
<link
	href="bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="bootstrap/js/bootstrap.min.js"></script>
<link href="css/index.css" rel="stylesheet">
<title>后台管理主页</title>

</head>

<body>
	<div class="row">
		<div class="col-md-2">
			<div class="media">
				<div class="media-left media-middle">
					<a href="#"> <img class="media-object" style="height: 150px;"
						src="img/icon.png" alt="图标">
					</a>
				</div>
			</div>
		</div>
		<div class="col-md-2 col-md-offset-10">
			<button class="btn btn-danger" id="logout_btn">退出系统</button>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<ul class="nav nav-pills nav-stacked">
				<li role="presentation" class="active"><a href="index.html">主页</a></li>
				<li role="presentation"><a href="categoryManage.html">类别管理</a></li>
				<li role="presentation"><a href="articleManage.html">文章管理</a></li>
				<li role="presentation"><a href="commentManage.html">评论管理</a></li>
				<li role="presentation"><a href="messageManage.html">联系信息管理</a></li>
				<li role="presentation"><a href="timeLineManage.html">时间轴管理</a></li>
				<li role="presentation"><a href="userManage.html">用户管理</a></li>
			</ul>
		</div>
		<div class="col-md-10">
			<div class="row">
				<div class="col-md-12">
					<ol class="breadcrumb">
						<li><a href="index.html">主页</a></li>
						<li class="active"><a href="#">时间轴</a></li>
					</ol>
				</div>
			</div>
			<!-- 显示事件 -->
			<div class="row">
				<!--事件-->
				<div class="event">
					<section id="cd-timeline" class="cd-container">
					<div class="cd-timeline-block">
						<div class="cd-timeline-img cd-green">
							<img src="img/cd-icon-location.svg" alt="location">
						</div>

						<div class="cd-timeline-content">
							<h2>恋爱开始</h2>
							<p>黎清许和张嫒珊的第一天</p>
							<span class="cd-date">2017-11-22</span>
						</div>
					</div>
					<div class="cd-timeline-block">
						<div class="cd-timeline-img cd-green">
							<img src="img/cd-icon-location.svg" alt="location">
						</div>

						<div class="cd-timeline-content">
							<h2>开始博客制作</h2>
							<p>主要是作为娱乐，以及方便展示自己。当然也为了秀恩爱</p>
							<span class="cd-date">2018-4-18</span>
						</div>
					</div>

					<div class="cd-timeline-block">
						<div class="cd-timeline-img cd-red">
							<img src="img/cd-icon-location.svg" alt="location">
						</div>

						<div class="cd-timeline-content">
							<h2>开始博客制作</h2>
							<p>完成博客架构设计。再接再厉</p>
							<span class="cd-date">2018-4-25</span>
						</div>
					</div>

					<div class="cd-timeline-block">
						<div class="cd-timeline-img cd-yellow">
							<img src="img/cd-icon-location.svg" alt="location">
						</div>

						<div class="cd-timeline-content">
							<h2>后台源码制作完成</h2>
							<p>完成博客后台。再接再厉</p>
							<span class="cd-date">2018-5-1</span>
						</div>
					</div>

					<div class="cd-timeline-block">
						<div class="cd-timeline-img cd-green">
							<img src="img/cd-icon-location.svg" alt="location">
						</div>

						<div class="cd-timeline-content">
							<h2>博客显示界面完成</h2>
							<p>心情很开心，开始老师的项目跟自己接的项目</p>
							<span class="cd-date">2018-5-12</span>
						</div>
					</div>
					</section>
				</div>
			</div>
			<!-- 显示分页信息 -->
			<div class="row">
				<!--分页文字信息  -->
				<div class="col-md-6" id="page_info_area"></div>
				<!-- 分页条信息 -->
				<div class="col-md-6" id="page_nav_area"></div>
			</div>
		</div>


		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<p>Copyright &copy; 2018.xub All rights reserved.xub出品</p>
			</div>
		</div>
</body>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</html>