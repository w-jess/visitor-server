package com.cf.visitor.services.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.visitor.dao.po.UserPO;
import com.cf.visitor.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author whx
 * @date 2022-11-22 09:46:01
 * @description 用户表
 */
@Service
public class UserService extends ServiceImpl<UserMapper, UserPO> implements IService<UserPO> {

	/**
	 * 根据openId查询用户信息
	 */
	public UserPO selectByOpenId(String openId) {
		return this.getOne(new LambdaQueryWrapper<UserPO>().eq(UserPO::getOpenId, openId));
	}
}

