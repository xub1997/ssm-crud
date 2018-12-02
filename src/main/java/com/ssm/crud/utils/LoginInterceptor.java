package com.ssm.crud.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//在Handler执行之前处理
		
		
		//判断用户是否登录(取session)
		/*System.out.println("拦截了");*/
		
		/*System.out.println(request.getSession().getAttribute("username")+"要跳转");*/
		
		//取不到用户信息
		if (request.getSession().getAttribute("username")==null||request.getSession().getAttribute("username").equals("")) {
			
			//跳转到登录页面，把用户请求的url作为参数传递给登录页面。
			response.sendRedirect("login.html");

			//返回false
			return false;
		}
		//取到用户信息，放行

		//返回值决定handler是否执行。true：执行，false：不执行。
		return true;
	}


	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// handler执行之后，返回ModelAndView之前

	}

	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 返回ModelAndView之后。
		//响应用户之后。

	}

}
