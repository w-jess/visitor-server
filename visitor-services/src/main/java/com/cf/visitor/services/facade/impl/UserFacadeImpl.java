package com.cf.visitor.services.facade.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cf.support.authertication.token.TokenManager;
import com.cf.support.authertication.token.dto.UserSessionDTO;
import com.cf.support.bean.IdWorker;
import com.cf.support.bean.WechatBean;
import com.cf.support.result.Result;
import com.cf.support.utils.BeanConvertorUtils;
import com.cf.support.utils.RedissonUtil;
import com.cf.visitor.dao.po.UserPO;
import com.cf.visitor.facade.bo.UserLoginBO;
import com.cf.visitor.facade.constant.RedisConstant;
import com.cf.visitor.facade.constant.UserConstant;
import com.cf.visitor.facade.enums.BizResultCodeEnum;
import com.cf.visitor.facade.facade.UserFacade;
import com.cf.visitor.services.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author whx
 * @date 2022/11/22
 */
@Service
@Slf4j
public class UserFacadeImpl implements UserFacade {

	@Resource
	private UserService userService;
	@Resource
	private RedissonUtil redissonUtil;
	@Resource
	private TokenManager tokenManager;
	@Resource
	private WechatBean wechatBean;
	@Resource
	private IdWorker idWorker;

	@Override
	public Result<UserLoginBO> loginByCode(String code) {
		log.info("进入用户登录接口, code: {}", code);
		if (StringUtils.isEmpty(code)) {
			return Result.buildResult(BizResultCodeEnum.PARAM_NULL);
		}
		//根据code获取openId
		JSONObject wxLoginJson = wechatBean.getWxLoginJson(code);
		if (wxLoginJson == null || StringUtils.isBlank(wxLoginJson.getString("openid"))) {
			//DingAlarmUtils.alarmException("code获取session失败,code=" + code);
			return Result.buildResult(BizResultCodeEnum.WX_CODE_SESSION_ERROR);
		}
		String openId = wxLoginJson.getString("openid");

		//防止重复注册对openId加锁
		String lockKey = RedisConstant.USER_LOGIN_LOCK_KEY + openId;
		RLock rLock = redissonUtil.getRLock(lockKey);
		try {
			if (!redissonUtil.tryLock(rLock, RedisConstant.USER_LOGIN_LOCK_KEY_WAIT, RedisConstant.USER_LOGIN_LOCK_KEY_EXPIRE, TimeUnit.MINUTES)) {
				log.error("注册用户获取锁失败,等待重试,openid为{}", openId);
				return Result.buildResult(BizResultCodeEnum.LOCK_ERROR);
			}
			//判断openId是否存在
			UserPO user = userService.selectByOpenId(openId);
			if (null == user) {
				user = new UserPO().setUserId(idWorker.nextId()).setOpenId(openId);
				userService.save(user);
			} else {
				//判断用户状态为封禁状态不让登录
				if (UserConstant.USER_CLOSE.equals(user.getState())) {
					log.info("用户:{},已封禁", JSON.toJSONString(user));
					return Result.buildResult(BizResultCodeEnum.USER_CLOSED);
				}
				//已存在则更新活跃时间
				UserPO updateUser = new UserPO().setUserId(user.getUserId()).setLastActiveAt(new Date());
				userService.updateById(updateUser);
			}
			Long userId = user.getUserId();
			UserSessionDTO sessionDTO = new UserSessionDTO();
			sessionDTO.setUserId(userId);
			sessionDTO.setOpenId(openId);
			sessionDTO.setServerName("visitor");
			String token = tokenManager.buildUserToken(sessionDTO);
			if (null == token) {
				log.error("用户登录接口生成token错误,code={},userId={}", code, userId);
				//DingAlarmUtils.alarmException("user注册生成token失败，uid=" + userId);
				return Result.buildResult(BizResultCodeEnum.OTHER_SYSTEM_ERROR);
			}
			UserLoginBO resultData = BeanConvertorUtils.map(user, UserLoginBO.class);
			resultData.setToken(token);
			return Result.buildSuccessResult(resultData);
		} catch (Exception e) {
			log.error("用户登录接口异常,入参code: {},e:", code, e);
			return Result.buildResult(BizResultCodeEnum.OTHER_SYSTEM_ERROR);
		} finally {
			try {
				redissonUtil.unlock(rLock);
			} catch (Exception e) {
				log.error("解锁异常，key:[{}],e:", lockKey, e);
			}
		}
	}

}
