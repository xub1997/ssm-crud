package com.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.crud.bean.Category;
import com.ssm.crud.bean.CategoryExample;
import com.ssm.crud.bean.CategoryExample.Criteria;
import com.ssm.crud.dao.CategoryMapper;

@Service
public class CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	public List<Category> getAll() {
		return categoryMapper.selectByExample(null);
	}

	public void deleteBatch(final List<Integer> del_ids) {
		CategoryExample example = new CategoryExample();
		Criteria criteria = example.createCriteria();
		//遍历数组
		criteria.andIdIn(del_ids);
		categoryMapper.deleteByExample(example);
	}

	public void deleteCategory(final Integer id) {
		categoryMapper.deleteByPrimaryKey(id);
	}

	public void saveCategory(final Category category) {
		categoryMapper.insertSelective(category);
	}

	public void updateCategory(final Category category) {
		categoryMapper.updateByPrimaryKeySelective(category);
	}

	public Category getCategory(final Integer id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

	public boolean check(final String categoryName) {
		CategoryExample example = new CategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategorynameEqualTo(categoryName);
		long count = categoryMapper.countByExample(example);
		return count == 0;
	}
	
}
