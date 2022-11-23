package com.cf.visitor.services.facade.impl;

import com.alibaba.fastjson.JSONObject;
import com.cf.support.authertication.token.TokenManager;
import com.cf.support.authertication.token.TokenRedisConstant;
import com.cf.support.authertication.token.dto.AdminSessionDTO;
import com.cf.support.result.Result;
import com.cf.support.utils.BeanConvertorUtils;
import com.cf.support.utils.RedisUtil;
import com.cf.visitor.dao.po.AdminUserPO;
import com.cf.visitor.facade.bo.LoginBackBO;
import com.cf.visitor.facade.enums.BizResultCodeEnum;
import com.cf.visitor.facade.facade.AdminUserFacade;
import com.cf.visitor.services.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @author whx
 * @date 2022/11/22
 */
@Slf4j
@Service
public class AdminUserFacadeImpl implements AdminUserFacade {

	@Resource
	private TokenManager tokenManager;

	@Resource
	private AdminUserService adminUserService;

	@Resource
	private RedisUtil redisUtil;

	public static void main(String[] args) {
		System.out.println(DigestUtils.md5DigestAsHex("CFDL14966123456".getBytes()));
	}

	@Override
	public Result<LoginBackBO> login(String emplNo, String password) {
		log.info("进入用户登录接口:emplNo:{}", emplNo);
		if (StringUtils.isEmpty(emplNo)) {
			return Result.buildResult(BizResultCodeEnum.PARAM_NULL);
		}
		AdminUserPO user = adminUserService.getUserByEmplNo(emplNo);
		if (null == user) {
			return Result.buildErrorResult("用户不存在");
		}
		password = DigestUtils.md5DigestAsHex((emplNo + password).getBytes());
		if (!password.equals(user.getPassword())) {
			return Result.buildErrorResult("密码不正确");
		}
		AdminSessionDTO sessionDTO = BeanConvertorUtils.map(user, AdminSessionDTO.class);
		String token = tokenManager.buildAdminToken(sessionDTO);
		LoginBackBO result = new LoginBackBO().setToken(token)
				.setAdminName(user.getAdminName()).setIsSuper(user.getIsSuper());
		log.info("用户登录接口返回结果为:{}", JSONObject.toJSONString(result));
		return Result.buildSuccessResult(result);
	}

	@Override
	public Result logout(Long adminId) {
		String userKey = TokenRedisConstant.ADMIN_SESSION_PREFIX + adminId;
		redisUtil.del(userKey);
		return Result.buildSuccessResult();
	}

	@Override
	public Result updatePwd(String emplNo, String oldPassword, String newPassword) {
		AdminUserPO user = adminUserService.getById(emplNo);
		oldPassword = DigestUtils.md5DigestAsHex((emplNo + oldPassword).getBytes());
		if (!oldPassword.equals(user.getPassword())) {
			return Result.buildErrorResult("原密码不正确");
		}
		String password = DigestUtils.md5DigestAsHex((emplNo + newPassword).getBytes());
		adminUserService.updatePwd(user.getAdminUserId(), password);
		String userKey = TokenRedisConstant.ADMIN_SESSION_PREFIX + user.getAdminUserId();
		redisUtil.del(userKey);
		return Result.buildSuccessResult();
	}

}
