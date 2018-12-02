package com.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.crud.bean.Message;
import com.ssm.crud.bean.MessageExample;
import com.ssm.crud.bean.MessageExample.Criteria;
import com.ssm.crud.dao.MessageMapper;

@Service
public class MessageService {
	
	@Autowired
	private MessageMapper messageMapper;
	
	public List<Message> getAll() {
		return messageMapper.selectByExample(null);
	}

	public void deleteBatch(final List<Integer> del_ids) {
		MessageExample example = new MessageExample();
		Criteria criteria = example.createCriteria();
		//遍历数组
		criteria.andIdIn(del_ids);
		messageMapper.deleteByExample(example);
	}

	public void deleteMessage(final Integer id) {
		messageMapper.deleteByPrimaryKey(id);
	}

	public void saveMessage(final Message message) {
		messageMapper.insertSelective(message);
	}

	public void updateMessage(final Message message) {
		messageMapper.updateByPrimaryKeySelective(message);
	}

	public Message getMessage(final Integer id) {
		return messageMapper.selectByPrimaryKey(id);
	}
}
