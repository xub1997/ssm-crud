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
<title>评论管理</title>

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
				<li role="presentation"><a href="index.html">主页</a></li>
				<li role="presentation"><a href="categoryManage.html">类别管理</a></li>
				<li role="presentation"><a href="articleManage.html">文章管理</a></li>
				<li role="presentation" class="active"><a
					href="commentManage.html">评论管理</a></li>
				<li role="presentation"><a href="messageManage.html">联系信息管理</a></li>
				<li role="presentation"><a href="timeLineManage.html">时间轴管理</a></li>
				<li role="presentation"><a href="userManage.html">用户管理</a></li>
			</ul>
		</div>
		<div class="col-md-10">
			<div class="row">
				<div class="col-md-12">
					<ol class="breadcrumb">
						<li><a href="commentManage.html">评论管理</a></li>
						<li class="active"><a href="#">评论删改查</a></li>
					</ol>
				</div>
			</div>
			<!-- 按钮 -->
			<div class="row">
				<div class="col-md-2 col-md-offset-10">
					<button class="btn btn-danger" id="comment_delete_all_btn">删除</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table table-hover table-bordered" id="comments_table">
						<thead>
							<tr>
								<th><input type="checkbox" id="check_all" /></th>
								<th>#</th>
								<th>文章名称</th>
								<th>评论人</th>
								<th>评论时间</th>
								<th>回复人</th>
								<th>回复时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
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
	</div>

	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<p>Copyright &copy; 2018.xub All rights reserved.xub出品</p>
		</div>
	</div>
	<!-- 评论修改的模态框 -->
	<div class="modal fade" id="commentUpdateModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改评论信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">文章名称:</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="title_update_static"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">评论人:</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="name_update_static"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">评论内容:</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="comment_update_static"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">评论时间:</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="commentTime_update_static"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">回复人:</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="replyPerson_update_static"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">回复内容:</label>
							<div class="col-sm-10">
								<textarea name="reply" class="form-control" rows="5"
									id="reply_update_input"></textarea>
								<span class="help-block"></span>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						id="comment_update_btn">更新</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/comment.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</html>