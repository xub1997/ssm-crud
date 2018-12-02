package com.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.crud.bean.Comment;
import com.ssm.crud.bean.CommentExample;
import com.ssm.crud.bean.CommentExample.Criteria;
import com.ssm.crud.dao.CommentMapper;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	public List<Comment> getAll() {
		return commentMapper.selectByExampleWithArticle(null);
	}

	public void deleteBatch(final List<Integer> del_ids) {
		CommentExample example = new CommentExample();
		Criteria criteria = example.createCriteria();
		//遍历数组
		criteria.andIdIn(del_ids);
		commentMapper.deleteByExample(example);
	}

	public void deleteComment(final Integer id) {
		commentMapper.deleteByPrimaryKey(id);
	}

	public void saveComment(final Comment comment) {
		commentMapper.insertSelective(comment);
	}

	public void updateComment(final Comment comment) {
		commentMapper.updateByPrimaryKeySelective(comment);
	}

	public Comment getComment(final Integer id) {
		return commentMapper.selectByPrimaryKeyWithArticle(id);
	}
}
