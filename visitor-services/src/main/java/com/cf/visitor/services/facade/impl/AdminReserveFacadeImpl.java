package com.cf.visitor.services.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cf.support.exception.BusinessException;
import com.cf.support.result.PageResponse;
import com.cf.support.result.Result;
import com.cf.support.utils.BeanConvertorUtils;
import com.cf.support.utils.CFDateUtils;
import com.cf.visitor.dao.po.ReserveEvaluatePO;
import com.cf.visitor.dao.po.ReserveRecordPO;
import com.cf.visitor.dao.po.ReserveRuleConfigPO;
import com.cf.visitor.facade.bo.ReserveRecordBO;
import com.cf.visitor.facade.dto.AdminConfigTimeDTO;
import com.cf.visitor.facade.dto.AdminReservePageDTO;
import com.cf.visitor.facade.enums.BizResultCodeEnum;
import com.cf.visitor.facade.enums.StateEnum;
import com.cf.visitor.facade.facade.AdminReserveFacade;
import com.cf.visitor.services.service.ReserveEvaluateService;
import com.cf.visitor.services.service.ReserveRecordService;
import com.cf.visitor.services.service.ReserveRuleConfigService;
import com.cf.visitor.services.utils.MapUtils;
import com.cf.visitor.services.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author whx
 * @date 2022/11/25
 */
@Slf4j
@Service
public class AdminReserveFacadeImpl implements AdminReserveFacade {
	@Resource
	private ReserveRecordService reserveRecordService;
	@Resource
	private ReserveEvaluateService reserveEvaluateService;
	@Resource
	private ReserveRuleConfigService reserveRuleConfigService;

	@Override
	public PageResponse<ReserveRecordBO> getRecordPage(AdminReservePageDTO param) {
		IPage recordPage = reserveRecordService.getRecordPage(PageUtils.toPage(param), BeanConvertorUtils.map(param, ReserveRecordPO.class));
		if (recordPage.getTotal() == 0) {
			return PageUtils.emptyResponseList(recordPage);
		}
		List<ReserveRecordBO> recordPageBOS = BeanConvertorUtils.copyList(recordPage.getRecords(), ReserveRecordBO.class);
		recordPageBOS.forEach(item -> item.setRecTime(DateFormatUtils.format(item.getReserveDate(), "yyyy-MM-dd") + " " + item.getReserveTime()));
		return PageUtils.toResponseList(recordPage, recordPageBOS);
	}

	@Override
	public ReserveRecordBO getRecordDetail(Long reserveRecordId) {
		ReserveRecordPO reserveRecordPO = reserveRecordService.getById(reserveRecordId);
		if (ObjectUtils.isEmpty(reserveRecordPO)) {
			throw new BusinessException(BizResultCodeEnum.RESERVE_RECORD_IS_NULL);
		}
		ReserveRecordBO reserveRecordBO = BeanConvertorUtils.map(reserveRecordPO, ReserveRecordBO.class);
		reserveRecordBO.setRecTime(DateFormatUtils.format(reserveRecordBO.getReserveDate(), "yyyy-MM-dd") + " " + reserveRecordBO.getReserveTime());
		if (StateEnum.STATE_EVALUATED.equals(reserveRecordBO.getState())) {
			ReserveEvaluatePO evaluatePO = reserveEvaluateService.getEvaluateByRecordId(reserveRecordId);
			reserveRecordBO.setEvaluateRank(evaluatePO.getEvaluateRank()).setEvaluateDesc(evaluatePO.getEvaluateDesc());
		}
		return reserveRecordBO;
	}

	@Override
	@Transactional
	public Result saveRuleConfig(Date ruleDate, List<AdminConfigTimeDTO> param) {
		List<ReserveRuleConfigPO> ruleConfigPOS = BeanConvertorUtils.copyList(param, ReserveRuleConfigPO.class);
		List<ReserveRuleConfigPO> configPOList = reserveRuleConfigService.getListByDate(ruleDate);
		List<ReserveRuleConfigPO> addList = new ArrayList<>();
		List<Map<String, String>> addMapList = new ArrayList<>();
		//分别获得新增、更新、删除的列表
		List<ReserveRuleConfigPO> updateList = new ArrayList<>();
		List<Map<String, String>> originList = new ArrayList<>();
		List<Long> deleteList = configPOList.stream().map(ReserveRuleConfigPO::getReserveRuleConfigId).collect(Collectors.toList());
		ruleConfigPOS.forEach(item -> {
			item.setRuleDate(ruleDate);
			Long reserveRuleConfigId = item.getReserveRuleConfigId();
			if (ObjectUtils.isEmpty(reserveRuleConfigId)) {
				addList.add(item);
				addMapList.add(MapUtils.setOriginMap(item));
			} else if (deleteList.contains(reserveRuleConfigId)) {
				updateList.add(item);
				deleteList.remove(reserveRuleConfigId);
				originList.add(MapUtils.setOriginMap(item));
			}
		});
		//新增配置时间段 与原配置 重合校验
		this.addListCheck(addList, originList, false);
		reserveRuleConfigService.updateBatchById(updateList);
		reserveRuleConfigService.removeBatchByIds(deleteList);
		//新增配置时间段 之间 的重合校验
		this.addListCheck(addList, addMapList, true);
		reserveRuleConfigService.saveBatch(addList);
		return Result.buildSuccessResult();
	}

	/**
	 * 新增时间段重合校验
	 *
	 * @param addList
	 * @param originList
	 * @param type       true:之间 false:之前配置
	 */
	private void addListCheck(List<ReserveRuleConfigPO> addList, List<Map<String, String>> originList, Boolean type) {
		if (type == true) {
			addList.forEach(item -> {
				originList.remove(MapUtils.setOriginMap(item));
				if (!CFDateUtils.isNormal(item.getRuleStartTm(), item.getRuleEndTm(), originList)) {
					throw new BusinessException(BizResultCodeEnum.CONFIG_TIME_ERROR);
				}
			});
		} else {
			addList.forEach(item -> {
				if (!CFDateUtils.isNormal(item.getRuleStartTm(), item.getRuleEndTm(), originList)) {
					throw new BusinessException(BizResultCodeEnum.CONFIG_TIME_ERROR);
				}
			});
		}
	}
}
