package com.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.crud.bean.Article;
import com.ssm.crud.bean.ArticleExample;
import com.ssm.crud.bean.TimeLineExample;
import com.ssm.crud.bean.ArticleExample.Criteria;
import com.ssm.crud.dao.ArticleMapper;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;
	
	public List<Article> getAll() {
		return articleMapper.selectByExampleWithCategory(null);
	}

	public void deleteBatch(List<Integer> del_ids) {
		ArticleExample example = new ArticleExample();
		Criteria criteria = example.createCriteria();
		//遍历数组
		criteria.andIdIn(del_ids);
		articleMapper.deleteByExample(example);
	}

	public void deleteArticle(Integer id) {
		articleMapper.deleteByPrimaryKey(id);
	}

	public void saveArticle(Article Article) {
		articleMapper.insertSelective(Article);
	}

	public void updateArticle(Article Article) {
		articleMapper.updateByPrimaryKeySelective(Article);
	}

	public Article getArticle(Integer id) {
		return articleMapper.selectByPrimaryKeyWithCategory(id);
	}

	public boolean check(String title) {
		ArticleExample example = new ArticleExample();
		Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		long count = articleMapper.countByExample(example);
		return count == 0;
	}
}
