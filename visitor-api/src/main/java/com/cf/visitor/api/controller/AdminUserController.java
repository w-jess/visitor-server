package com.cf.visitor.api.controller;

import com.cf.support.authertication.AdminAuthenticationServer;
import com.cf.support.authertication.AdminUserAuthentication;
import com.cf.support.authertication.token.dto.AdminSessionDTO;
import com.cf.support.result.Result;
import com.cf.support.utils.BeanConvertorUtils;
import com.cf.visitor.api.repuest.LoginReq;
import com.cf.visitor.api.repuest.UpdatePwdReq;
import com.cf.visitor.api.response.LoginResp;
import com.cf.visitor.facade.bo.LoginBackBO;
import com.cf.visitor.facade.facade.AdminUserFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author whx
 * @date 2022/11/22
 */
@RestController
@RequestMapping("/admin/user")
@Api(tags = "后台用户管理相关")
public class AdminUserController {

	@Resource
	private AdminUserFacade adminUserFacade;
	@Resource
	private AdminAuthenticationServer adminAuthenticationServer;

	private AdminSessionDTO getAdmin() {
		return adminAuthenticationServer.getCurrentUser();
	}

	@ApiOperation(value = "登录接口", notes = "登录接口")
	@PostMapping(value = "/login")
	public Result<LoginResp> login(@RequestBody LoginReq param) {
		Result<LoginBackBO> facadeResult = adminUserFacade.login(param.getEmplNo(), param.getPassword());
		if (facadeResult.isSuccess() && null != facadeResult.getData()) {
			return Result.buildSuccessResult(BeanConvertorUtils.map(facadeResult.getData(), LoginResp.class));
		}
		return BeanConvertorUtils.map(facadeResult, Result.class);
	}

	@AdminUserAuthentication
	@PostMapping(value = "/logout")
	@ApiOperation(value = "退出登录", notes = "退出登录")
	public Result logout() {
		return adminUserFacade.logout(getAdmin().getAdminId());
	}

	@AdminUserAuthentication
	@PostMapping(value = "/updatePwd")
	@ApiOperation(value = "修改密码接口", notes = "修改密码接口")
	public Result updatePwd(@RequestBody UpdatePwdReq param) {
		return adminUserFacade.updatePwd(getAdmin().getEmplNo(), param.getOldPassword(), param.getNewPassword());
	}
}
