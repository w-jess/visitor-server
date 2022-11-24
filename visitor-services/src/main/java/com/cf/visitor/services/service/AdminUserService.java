package com.cf.visitor.services.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.visitor.dao.po.AdminUserPO;
import com.cf.visitor.dao.mapper.AdminUserMapper;
import com.cf.visitor.facade.enums.UserStateEnum;
import org.springframework.stereotype.Service;

/**
 * @author whx
 * @date 2022-11-22 09:07:19
 * @description 后台用户表
 */
@Service
public class AdminUserService extends ServiceImpl<AdminUserMapper, AdminUserPO> implements IService<AdminUserPO> {

	public AdminUserPO getUserByEmplNo(String emplNo) {
		LambdaQueryWrapper<AdminUserPO> queryWrapper = new LambdaQueryWrapper<AdminUserPO>()
				.eq(AdminUserPO::getEmplNo, emplNo).eq(AdminUserPO::getState, UserStateEnum.USER_IS_NORMAL.getCode());
		return this.getOne(queryWrapper);
	}

	public Boolean updatePwd(Long adminUserId, String password) {
		LambdaUpdateWrapper<AdminUserPO> wrapper = new LambdaUpdateWrapper<AdminUserPO>()
				.eq(AdminUserPO::getAdminUserId, adminUserId).set(AdminUserPO::getPassword, password);
		return this.update(wrapper);
	}
}

