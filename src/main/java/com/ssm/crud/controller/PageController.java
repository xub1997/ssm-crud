package com.ssm.crud.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	private static Logger logger =Logger.getLogger(PageController.class);
	
	@RequestMapping("/login.html")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/articleManage.html")
	public String articleManage() {
		return "articleManage";
	}
	
	@RequestMapping("/categoryManage.html")
	public String categoryManage() {
		return "categoryManage";
	}
	
	@RequestMapping("/commentManage.html")
	public String commentManage() {
		return "commentManage";
	}
	
	@RequestMapping("/index.html")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/messageManage.html")
	public String messageManage() {
		return "messageManage";
	}
	
	@RequestMapping("/timeLineManage.html")
	public String timeLineManage() {
		return "timeLineManage";
	}
	
	@RequestMapping("/userManage.html")
	public String userManage() {
		return "userManage";
	}
	
	@RequestMapping("/addArticle.html")
	public String addArticle() {
		return "addArticle";
	}
	
	@RequestMapping("/editArticle.html")
	public String editArticle() {
		return "editArticle";
	}
}
