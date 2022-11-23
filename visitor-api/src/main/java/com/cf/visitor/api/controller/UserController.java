package com.cf.visitor.api.controller;

import com.cf.support.result.Result;
import com.cf.support.utils.BeanConvertorUtils;
import com.cf.visitor.api.repuest.LoginByCodeReq;
import com.cf.visitor.api.response.UserLoginResp;
import com.cf.visitor.facade.bo.UserLoginBO;
import com.cf.visitor.facade.facade.UserFacade;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author whx
 * @date 2022/11/22
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "小程序用户相关")
public class UserController {

	@Resource
	private UserFacade userFacade;

	@PostMapping("/loginByCode")
	public Result<UserLoginResp> loginByCode(@RequestBody LoginByCodeReq param) {
		Result<UserLoginBO> facadeResult = userFacade.loginByCode(param.getCode());
		if (!facadeResult.isSuccess()) {
			return BeanConvertorUtils.map(facadeResult, Result.class);
		}
		return Result.buildSuccessResult(BeanConvertorUtils.map(facadeResult.getData(), UserLoginResp.class));
	}
}
