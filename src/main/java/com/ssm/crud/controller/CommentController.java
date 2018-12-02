package com.ssm.crud.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ssm.crud.bean.Comment;
import com.ssm.crud.bean.Msg;
import com.ssm.crud.service.CommentService;

@Controller
public class CommentController {
	
	private static Logger logger =Logger.getLogger(CommentController.class);
	
	@Autowired
	CommentService commentService;

	/**
	 * 获取文章评论信息
	 * 
	 * @param 页码
	 * @return
	 */
	@RequestMapping("/comments")
	@ResponseBody
	public Msg getCommentsWithJson(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		// 这不是一个分页查询
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pageNum, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Comment> Comments = commentService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数

		PageInfo pageInfo = new PageInfo(Comments, 5);
		return Msg.success().add("pageInfo", pageInfo);
	}

	/**
	 * 删除文章评论
	 * 
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/comment/{ids}", method = RequestMethod.DELETE)
	public Msg deleteComment(@PathVariable("ids") String ids) {
		// 批量删除
		if (ids.contains(",")) {
			List<Integer> del_ids = new ArrayList();
			String[] str_ids = ids.split(",");
			// 组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			commentService.deleteBatch(del_ids);
		} else {
			Integer id = Integer.parseInt(ids);
			commentService.deleteComment(id);
		}
		return Msg.success();
	}

	/**
	 * 文章评论保存
	 *
	 * @return
	 */
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	@ResponseBody
	public Msg insertComment(Comment comment) {

		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		comment.setCommenttime(sft.format(nowDate));
		commentService.saveComment(comment);

		return Msg.success();

	}

	/**
	 * 
	 * @param Comment
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/comment/{id}", method = RequestMethod.PUT)
	public Msg updateComment(Comment comment, HttpServletRequest request) {
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		comment.setReplytime(sft.format(nowDate));
		commentService.updateComment(comment);
		return Msg.success();
	}

	/**
	 * 根据id查询文章评论
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Msg getComment(@PathVariable("id") Integer id) {
		Comment comment = commentService.getComment(id);
		return Msg.success().add("comment", comment);
	}
}
