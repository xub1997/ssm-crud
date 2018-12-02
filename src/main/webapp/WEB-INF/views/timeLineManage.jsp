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
<title>时间轴管理</title>

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
				<li role="presentation"><a href="commentManage.html">评论管理</a></li>
				<li role="presentation"><a href="messageManage.html">联系信息管理</a></li>
				<li role="presentation" class="active"><a
					href="timeLineManage.html">时间轴管理</a></li>
				<li role="presentation"><a href="userManage.html">用户管理</a></li>
			</ul>
		</div>
		<div class="col-md-10">
			<div class="row">
				<div class="col-md-12">
					<ol class="breadcrumb">
						<li><a href="timeLineManage.html">时间轴管理</a></li>
						<li class="active"><a href="#">时间轴增删改查</a></li>
					</ol>
				</div>
			</div>
			<!-- 按钮 -->
			<div class="row">
				<div class="col-md-3 col-md-offset-9">
					<button class="btn btn-primary" id="timeLine_add_modal_btn">新增</button>
					<button class="btn btn-danger" id="timeLine_delete_all_btn">删除</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table table-hover table-bordered"
						id="timeLines_table">
						<thead>
							<tr>
								<th><input type="checkbox" id="check_all" /></th>
								<th>#</th>
								<th>标题</th>
								<th>标记颜色</th>
								<th>事件时间</th>
								<th>创建时间</th>
								<th>修改时间</th>
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

	<!-- 时间轴添加的模态框 -->
	<div class="modal fade" id="timeLineAddModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加时间轴</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">标题:</label>
							<div class="col-sm-10">
								<input type="text" name="title" class="form-control"
									id="title_add_input" placeholder="标题"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">标记颜色:</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="color" id="level1_add_input" value="G" checked="checked">绿色
								</label> <label class="radio-inline"> <input type="radio"
									name="color" id="level2_add_input" value="Y">黄色
								</label> <label class="radio-inline"> <input type="radio"
									name="color" id="level3_add_input" value="R">红色
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">事件内容:</label>
							<div class="col-sm-10">
								<textarea name="content" class="form-control" rows="5"></textarea>
								<span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">事件时间:</label>
							<div class="col-sm-10">
								<input type="date" name="time" class="form-control"
									id="time_add_input"> <span class="help-block"></span>
							</div>
						</div>


					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						id="timeLine_save_btn">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 时间轴修改的模态框 -->
	<div class="modal fade" id="timeLineUpdateModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改时间轴</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">标题:</label>
							<div class="col-sm-10">
								<input type="text" name="title" class="form-control"
									id="title_update_input" placeholder="标题"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">标记颜色:</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="color" id="level1_update_input" value="G"
									checked="checked">绿色
								</label> <label class="radio-inline"> <input type="radio"
									name="color" id="level2_update_input" value="Y">黄色
								</label> <label class="radio-inline"> <input type="radio"
									name="color" id="level3_update_input" value="R">红色
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">事件内容:</label>
							<div class="col-sm-10">
								<textarea name="content" class="form-control" rows="5"
									id="content_update_input"></textarea>
								<span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">事件时间:</label>
							<div class="col-sm-10">
								<input type="date" name="time" class="form-control"
									id="time_update_input"> <span class="help-block"></span>
							</div>
						</div>


					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						id="timeLine_update_btn">更新</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/timeLine.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</html>