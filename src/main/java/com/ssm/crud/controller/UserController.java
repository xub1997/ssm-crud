package com.ssm.crud.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.crud.bean.Msg;
import com.ssm.crud.bean.User;
import com.ssm.crud.service.UserService;
import com.ssm.crud.utils.MD5;

@Controller
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/**
	 * 获取用户信息
	 * 
	 * @param 页码
	 * @return
	 */

	@RequestMapping("/users")
	@ResponseBody
	public Msg getUsersWithJson(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		// 这不是一个分页查询
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pageNum, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<User> users = userService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数

		PageInfo pageInfo = new PageInfo(users, 5);
		return Msg.success().add("pageInfo", pageInfo);
	}

	/**
	 * 删除用户
	 * 
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/{ids}", method = RequestMethod.DELETE)
	public Msg deleteUser(@PathVariable("ids") String ids) {
		// 批量删除
		if (ids.contains(",")) {
			List<Integer> del_ids = new ArrayList();
			String[] str_ids = ids.split(",");
			// 组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			userService.deleteBatch(del_ids);
		} else {
			Integer id = Integer.parseInt(ids);
			userService.deleteUser(id);
		}
		return Msg.success();
	}

	/**
	 * 检查用户名是否可用
	 * 
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/check")
	public Msg checkUser(@RequestParam("userName") String userName) {
		// 先判断用户名是否是合法的表达式;
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)";
		if (!userName.matches(regx)) {
			return Msg.fail().add("va_msg", "用户名必须是6-16位（英文或者数字）");
		}

		// 数据库用户名重复校验
		boolean b = userService.check(userName);
		if (b) {
			return Msg.success();
		} else {
			return Msg.fail().add("va_msg", "用户名不可用");
		}
	}

	/**
	 * 用户保存 1、支持JSR303校验 2、导入Hibernate-Validator
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public Msg insertUser(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			// 校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名：" + fieldError.getField());
				System.out.println("错误信息：" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		} else {
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowDate = new Date();
			user.setCreatetime(sft.format(nowDate));
			user.setModifytime(sft.format(nowDate));
			user.setPwd(MD5.encryptByMD5(user.getPwd()));
			userService.saveUser(user);
			return Msg.success();
		}

	}

	/**
	 * 
	 * 原因： Tomcat： 1、将请求体中的数据，封装一个map。 2、request.getParameter("empName")就会从这个map中取值。
	 * 3、SpringMVC封装POJO对象的时候。 会把POJO中每个属性的值，request.getParamter("email");
	 * AJAX发送PUT请求引发的血案： PUT请求，请求体中的数据，request.getParameter("empName")拿不到
	 * Tomcat一看是PUT不会封装请求体中的数据为map，只有POST形式的请求才封装请求体为map
	 * org.apache.catalina.connector.Request--parseParameters() (3111);
	 * 
	 * protected String parseBodyMethods = "POST"; if(
	 * !getConnector().isParseBodyMethod(getMethod()) ) { success = true; return; }
	 * 
	 * 
	 * 解决方案； 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据 1、配置上HttpPutFormContentFilter；
	 * 2、他的作用；将请求体中的数据解析包装成一个map。
	 * 3、request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据 用户更新方法
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/{userid}", method = RequestMethod.PUT)
	public Msg updateUser(User user, HttpServletRequest request) {
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		user.setModifytime(sft.format(nowDate));
		userService.updateUser(user);
		return Msg.success();
	}

	/**
	 * 根据id查询用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Msg getUser(@PathVariable("id") Integer id) {
		User user = userService.getUser(id);
		return Msg.success().add("user", user);
	}

	// 用户登录
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public Msg userLogin(User user, HttpServletRequest request) {
		System.out.println(user);
		List<User> list = userService.login(user);
		// 如果没有此用户名
		if (null == list || list.size() == 0) {
			request.setAttribute("user", "");
			return Msg.fail().add("loginError", "用户名错误");
		}
		User userGet = list.get(0);
		// 比对密码
		if (!userGet.getPwd().equals(MD5.encryptByMD5(user.getPwd()))) {
			request.setAttribute("user", "");
			return Msg.fail().add("loginError", "密码错误");
		}
		// 比对等级
		if (userGet.getLevel() != user.getLevel()) {
			request.setAttribute("user", "");
			return Msg.fail().add("loginError", "等级错误");
		}

		userGet.setPwd(null);
		// 放进session
		HttpSession session = request.getSession();
		session.setAttribute("username", userGet.getUsername());
		session.setAttribute("level", userGet.getLevel());

		/* System.out.println(session.getAttribute("username")+"登录了"); */
		return Msg.success().add("user", userGet);

	}

	// 用户登录
	@RequestMapping(value = "/user/logout", method = RequestMethod.POST)
	@ResponseBody
	public Msg userLogout(HttpServletRequest request, HttpServletResponse response) {

		/*try {

			response.sendRedirect("login.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		// 清除 session 中的所有信息
		HttpSession session = request.getSession();
		session.invalidate();
		return Msg.success();

	}
}
