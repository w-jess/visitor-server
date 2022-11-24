package com.cf.visitor.api.controller;

import com.cf.support.authertication.AdminUserAuthentication;
import com.cf.support.result.PageResponse;
import com.cf.support.result.Result;
import com.cf.support.utils.BeanConvertorUtils;
import com.cf.visitor.api.repuest.AdminReservePageReq;
import com.cf.visitor.api.response.AdminReservePageResp;
import com.cf.visitor.facade.bo.AdminReservePageBO;
import com.cf.visitor.facade.dto.AdminReservePageDTO;
import com.cf.visitor.facade.enums.BizResultCodeEnum;
import com.cf.visitor.facade.facade.ReserveRecordFacade;
import com.cf.visitor.services.utils.EnumUtils;
import com.cf.visitor.services.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
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
@AdminUserAuthentication
@RequestMapping("/admin/reserve")
@Api(tags = "后台预约管理相关")
public class AdminReserveController {
	@Resource
	private ReserveRecordFacade reserveRecordFacade;

	@PostMapping("/record")
	@ApiOperation(value = "获取预约记录分页接口", notes = "获取预约记录分页接口")
	public Result<PageResponse<AdminReservePageResp>> getRecordPage(@RequestBody AdminReservePageReq param) {
		//类型校验
		if (ObjectUtils.isNotEmpty(param.getType()) && !EnumUtils.isInType(param.getType())) {
			return Result.buildErrorResult(BizResultCodeEnum.PARAM_ERROR.getMsg());
		}
		//状态校验
		if (ObjectUtils.isNotEmpty(param.getState()) && !EnumUtils.isInState(param.getState())) {
			return Result.buildErrorResult(BizResultCodeEnum.PARAM_ERROR.getMsg());
		}
		PageResponse<AdminReservePageBO> record = reserveRecordFacade.getRecordPage(BeanConvertorUtils.map(param, AdminReservePageDTO.class));
		if (record.getTotal() == 0) {
			return PageUtils.emptyPageResult(record);
		}
		return PageUtils.pageResult(record, BeanConvertorUtils.copyList(record.getList(), AdminReservePageResp.class));
	}
}
