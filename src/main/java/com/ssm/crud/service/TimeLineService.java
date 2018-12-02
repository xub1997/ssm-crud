package com.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.crud.bean.TimeLine;
import com.ssm.crud.bean.TimeLineExample;
import com.ssm.crud.bean.TimeLineExample.Criteria;
import com.ssm.crud.dao.TimeLineMapper;

@Service
public class TimeLineService {
	
	@Autowired
	private TimeLineMapper timeLineMapper;
	
	public List<TimeLine> getAll() {
		return timeLineMapper.selectByExample(null);
	}

	public void deleteBatch(final List<Integer> del_ids) {
		TimeLineExample example = new TimeLineExample();
		Criteria criteria = example.createCriteria();
		//遍历数组
		criteria.andIdIn(del_ids);
		timeLineMapper.deleteByExample(example);
	}

	public void deleteTimeLine(final Integer id) {
		timeLineMapper.deleteByPrimaryKey(id);
	}

	public void savetimeLine(final TimeLine timeLine) {
		timeLineMapper.insertSelective(timeLine);
	}

	public void updateTimeLine(final TimeLine timeLine) {
		timeLineMapper.updateByPrimaryKeySelective(timeLine);
	}

	public TimeLine getTimeLine(final Integer id) {
		return timeLineMapper.selectByPrimaryKey(id);
	}

	public boolean check(final String title) {
		TimeLineExample example = new TimeLineExample();
		Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		long count = timeLineMapper.countByExample(example);
		return count == 0;
	}

	
}
