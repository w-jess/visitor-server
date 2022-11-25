package com.cf.visitor.api.controller;

import com.cf.support.authertication.AdminUserAuthentication;
import com.cf.support.exception.BusinessException;
import com.cf.support.result.PageResponse;
import com.cf.support.result.Result;
import com.cf.support.utils.BeanConvertorUtils;
import com.cf.visitor.api.repuest.AdminReservePageReq;
import com.cf.visitor.api.repuest.AdminRuleConfigReq;
import com.cf.visitor.api.repuest.ReserveRecordIdReq;
import com.cf.visitor.api.response.ReserveRecordResp;
import com.cf.visitor.facade.bo.ReserveRecordBO;
import com.cf.visitor.facade.dto.AdminConfigTimeDTO;
import com.cf.visitor.facade.dto.AdminReservePageDTO;
import com.cf.visitor.facade.enums.BizResultCodeEnum;
import com.cf.visitor.facade.facade.AdminReserveFacade;
import com.cf.visitor.services.utils.EnumUtils;
import com.cf.visitor.services.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.CollectionUtils;
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
	private AdminReserveFacade adminReserveFacade;

	@PostMapping("/record")
	@ApiOperation(value = "获取预约记录分页接口", notes = "获取预约记录分页接口")
	public Result<PageResponse<ReserveRecordResp>> getRecordPage(@RequestBody AdminReservePageReq param) {
		//类型校验
		if (ObjectUtils.isNotEmpty(param.getType()) && !EnumUtils.isInType(param.getType())) {
			return Result.buildErrorResult(BizResultCodeEnum.PARAM_ERROR.getMsg());
		}
		//状态校验
		if (ObjectUtils.isNotEmpty(param.getState()) && !EnumUtils.isInState(param.getState())) {
			return Result.buildErrorResult(BizResultCodeEnum.PARAM_ERROR.getMsg());
		}
		AdminReservePageDTO pageDTO = BeanConvertorUtils.map(param, AdminReservePageDTO.class);
		pageDTO.setPageNo(param.getPageNo());
		pageDTO.setPageSize(param.getPageSize());
		PageResponse<ReserveRecordBO> record = adminReserveFacade.getRecordPage(pageDTO);
		if (record.getTotal() == 0) {
			return PageUtils.emptyPageResult(record);
		}
		return PageUtils.pageResult(record, BeanConvertorUtils.copyList(record.getList(), ReserveRecordResp.class));
	}

	@PostMapping("/detail")
	@ApiOperation(value = "获取预约记录详情接口", notes = "获取预约记录详情接口")
	public Result<ReserveRecordResp> getRecordDetail(@RequestBody ReserveRecordIdReq param) {
		Long reserveRecordId = param.getReserveRecordId();
		if (ObjectUtils.isEmpty(reserveRecordId)) {
			throw new BusinessException(BizResultCodeEnum.PARAM_NULL);
		}
		return Result.buildSuccessResult(BeanConvertorUtils.map(adminReserveFacade.getRecordDetail(reserveRecordId), ReserveRecordResp.class));
	}

	@PostMapping("/config/save")
	@ApiOperation(value = "保存配置接口", notes = "保存配置接口")
	public Result saveRuleConfig(@RequestBody AdminRuleConfigReq param) {
		if (ObjectUtils.isEmpty(param.getRuleDate()) || CollectionUtils.isEmpty(param.getSaveList())) {
			throw new BusinessException(BizResultCodeEnum.PARAM_NULL);
		}
		//限制最多配置6
		if (param.getSaveList().size() > 6) {
			throw new BusinessException(BizResultCodeEnum.CONFIG_NUMBER_FULL);
		}
		return adminReserveFacade.saveRuleConfig(param.getRuleDate(), BeanConvertorUtils.copyList(param.getSaveList(), AdminConfigTimeDTO.class));
	}
}
