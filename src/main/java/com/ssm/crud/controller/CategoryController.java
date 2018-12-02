package com.ssm.crud.controller;


import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.crud.bean.Msg;
import com.ssm.crud.bean.Category;
import com.ssm.crud.service.CategoryService;


@Controller
public class CategoryController {
	
	private static Logger logger =Logger.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService categoryService;
	
	/**
	 * 获取类别信息
	 * @param 页码
	 * @return
	 */
	
	@RequestMapping("/categorys")
	@ResponseBody
	public Msg getCategorysWithJson(
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		// 这不是一个分页查询
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pageNum, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Category> categorys = categoryService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		
		PageInfo pageInfo = new PageInfo(categorys, 5);
		return Msg.success().add("pageInfo", pageInfo);
	}
	
	/**
	 * 删除类别
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/category/{ids}",method=RequestMethod.DELETE)
	public Msg deleteCategory(@PathVariable("ids")String ids){
		//批量删除
		if(ids.contains(",")){
			List<Integer> del_ids = new ArrayList();
			String[] str_ids = ids.split(",");
			//组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			categoryService.deleteBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
			categoryService.deleteCategory(id);
		}
		return Msg.success();
	}
	
	/**
	 * 检查类型名是否可用
	 * @param categoryName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkCategoryName")
	public Msg checkUser(@RequestParam("categoryName")String categoryName){
		
		
		//数据库用户名重复校验
		boolean b = categoryService.check(categoryName);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "类别名不可用");
		}
	}
	
	/**
	 * 类别保存
	 *
	 * @return
	 */
	@RequestMapping(value="/category",method=RequestMethod.POST)
	@ResponseBody
	public Msg insertCategory(Category category){
		
			categoryService.saveCategory(category);
			
			return Msg.success();

	}
	
	/**
	 * 
	 * @param Category
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/category/{id}",method=RequestMethod.PUT)
	public Msg updateCategory(Category Category,HttpServletRequest request){
		return Msg.success();
	}
	
	/**
	 * 根据id查询类别
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/category/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getCategory(@PathVariable("id")Integer id){
		Category category = categoryService.getCategory(id);
		return Msg.success().add("category", category);
	}
}
