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
import com.ssm.crud.bean.TimeLine;
import com.ssm.crud.service.TimeLineService;


@Controller
public class TimeLineController {
	
	private static Logger logger =Logger.getLogger(TimeLineController.class);
	
	@Autowired
	TimeLineService timeLineService;
	
	/**
	 * 获取时间轴信息
	 * @param 页码
	 * @return
	 */
	@RequestMapping("/timeLines")
	@ResponseBody
	public Msg getTimeLinesWithJson(
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		// 这不是一个分页查询
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pageNum, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<TimeLine> timeLines = timeLineService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		
		PageInfo pageInfo = new PageInfo(timeLines, 5);
		return Msg.success().add("pageInfo", pageInfo);
	}
	
	/**
	 * 删除时间轴
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/timeLine/{ids}",method=RequestMethod.DELETE)
	public Msg deleteTimeLine(@PathVariable("ids")String ids){
		//批量删除
		if(ids.contains(",")){
			List<Integer> del_ids = new ArrayList();
			String[] str_ids = ids.split(",");
			//组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			timeLineService.deleteBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
			timeLineService.deleteTimeLine(id);
		}
		return Msg.success();
	}
	

	/**
	 * 检查标题是否可用
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkTimeLineTitle")
	public Msg checkTitle(@RequestParam("title")String title){
		
		
		//数据库标题重复校验
		boolean b = timeLineService.check(title);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "标题不可用");
		}
	}
	
	/**
	 * 时间轴保存
	 *
	 * @return
	 */
	@RequestMapping(value="/timeLine",method=RequestMethod.POST)
	@ResponseBody
	public Msg insertTimeLine(TimeLine TimeLine){
		
			SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowDate=new Date();
			TimeLine.setCreatetime(sft.format(nowDate));
			TimeLine.setModifytime(sft.format(nowDate));
			timeLineService.savetimeLine(TimeLine);
			
			return Msg.success();
		
	}
	
	/**
	 * 
	 * @param TimeLine
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/timeLine/{id}",method=RequestMethod.PUT)
	public Msg updateTimeLine(TimeLine timeLine,HttpServletRequest request){
		SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate=new Date();
		timeLine.setModifytime(sft.format(nowDate));
		timeLineService.updateTimeLine(timeLine);
		return Msg.success();
	}
	
	/**
	 * 根据id查询时间轴
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/timeLine/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getTimeLine(@PathVariable("id")Integer id){
		TimeLine timeLine = timeLineService.getTimeLine(id);
		return Msg.success().add("timeLine", timeLine);
	}
}
