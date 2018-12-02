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
import com.ssm.crud.bean.Msg;
import com.ssm.crud.bean.Message;
import com.ssm.crud.service.MessageService;

@Controller
public class MessageController {
	
	private static Logger logger =Logger.getLogger(MessageController.class);
	
	@Autowired
	MessageService messageService;
	
	/**
	 * 获取联系信息信息
	 * @param 页码
	 * @return
	 */
	@RequestMapping("/messages")
	@ResponseBody
	public Msg getMessagesWithJson(
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		// 这不是一个分页查询
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pageNum, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Message> Messages = messageService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		
		PageInfo pageInfo = new PageInfo(Messages, 5);
		return Msg.success().add("pageInfo", pageInfo);
	}
	
	/**
	 * 删除联系信息
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/message/{ids}",method=RequestMethod.DELETE)
	public Msg deleteMessage(@PathVariable("ids")String ids){
		//批量删除
		if(ids.contains(",")){
			List<Integer> del_ids = new ArrayList();
			String[] str_ids = ids.split(",");
			//组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			messageService.deleteBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
			messageService.deleteMessage(id);
		}
		return Msg.success();
	}
	
	
	/**
	 * 联系信息保存
	 *
	 * @return
	 */
	@RequestMapping(value="/message",method=RequestMethod.POST)
	@ResponseBody
	public Msg insertMessage(Message message){
		
			SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowDate=new Date();
			message.setCreatetime(sft.format(nowDate));

			messageService.saveMessage(message);
			
			return Msg.success();
		
	}
	
	/**
	 * 
	 * @param Message
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/message/{id}",method=RequestMethod.PUT)
	public Msg updateMessage(Message message,HttpServletRequest request){

		messageService.updateMessage(message);
		return Msg.success();
	}
	
	/**
	 * 根据id查询联系信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/message/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getMessage(@PathVariable("id")Integer id){
		Message message = messageService.getMessage(id);
		return Msg.success().add("message", message);
	}
}
