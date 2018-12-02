package com.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.crud.bean.Msg;
import com.ssm.crud.bean.User;
import com.ssm.crud.bean.UserExample;
import com.ssm.crud.bean.UserExample.Criteria;
import com.ssm.crud.dao.UserMapper;
import com.ssm.crud.utils.MD5;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	/**
	 * @author xub 获取所有用户信息（没有密码）
	 */
	public List<User> getAll() {
		return userMapper.selectByExampleNoPwd(null);
	}

	/**
	 * 批量删除
	 *
	 */
	public void deleteBatch(final List<Integer> del_ids) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		// 遍历数组
		criteria.andUseridIn(del_ids);
		userMapper.deleteByExample(example);
	}

	/**
	 * 单次删除
	 *
	 */
	public void deleteUser(final Integer id) {
		userMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 检查用户名是否可用
	 *
	 */
	public boolean check(final String userName) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		long count = userMapper.countByExample(example);
		return count == 0;
	}

	/**
	 * 保存用户
	 *
	 */
	public void saveUser(final User user) {
		userMapper.insertSelective(user);
	}

	/**
	 * 获取对应用户
	 *
	 */
	public User getUser(final Integer id) {
		return userMapper.selectByPrimaryKeyNoPwd(id);
	}

	/**
	 * 更新用户
	 *
	 */
	public void updateUser(final User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	public List<User> login(User user) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		return  userMapper.selectByExample(example);
	}

}
