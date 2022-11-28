package com.cf.visitor.api.controller;

import com.cf.support.authertication.UserAuthentication;
import com.cf.support.authertication.UserAuthenticationServer;
import com.cf.support.authertication.token.dto.UserSessionDTO;
import com.cf.support.result.Result;
import com.cf.support.utils.BeanConvertorUtils;
import com.cf.visitor.api.repuest.ReserveRecordReq;
import com.cf.visitor.api.response.ReserveValidDateResp;
import com.cf.visitor.facade.dto.ReserveRecordDTO;
import com.cf.visitor.facade.facade.ReserveRecordFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author whx
 * @date 2022/11/22
 */
@UserAuthentication
@RestController
@RequestMapping("/reserve")
@Api(tags = "小程序预约相关")
public class ReserveController {

	@Resource
	private ReserveRecordFacade reserveRecordFacade;
	@Resource
	private UserAuthenticationServer userAuthenticationServer;

	private UserSessionDTO getUser() {
		return userAuthenticationServer.getCurrentUser();
	}

	@PostMapping("/commit")
	@ApiOperation(value = "预约提交接口", notes = "预约提交接口")
	public Result reserveCommit(@RequestBody ReserveRecordReq param) {
		ReserveRecordDTO reserveRecordDTO = BeanConvertorUtils.map(param, ReserveRecordDTO.class);
		reserveRecordDTO.setUserId(getUser().getUserId());
		return reserveRecordFacade.reserveCommit(reserveRecordDTO);
	}

	@PostMapping("/select/date")
	@ApiOperation(value = "获取预约可选择日期接口", notes = "获取预约可选择日期接口")
	public Result<List<ReserveValidDateResp>> getValidDate() {
		return Result.buildSuccessResult(BeanConvertorUtils.copyList(reserveRecordFacade.getValidDate(), ReserveValidDateResp.class));
	}
}
