package com.cf.visitor.services.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cf.support.exception.BusinessException;
import com.cf.support.result.PageResponse;
import com.cf.support.result.Result;
import com.cf.support.utils.BeanConvertorUtils;
import com.cf.support.utils.CFDateUtils;
import com.cf.visitor.dao.po.ReserveRecordPO;
import com.cf.visitor.dao.po.ReserveRuleConfigPO;
import com.cf.visitor.facade.bo.ReserveRecordBO;
import com.cf.visitor.facade.bo.ReserveValidDateBO;
import com.cf.visitor.facade.bo.ReserveValidTimeBO;
import com.cf.visitor.facade.dto.ReserveRecordDTO;
import com.cf.visitor.facade.dto.ReserveRecordsDTO;
import com.cf.visitor.facade.enums.*;
import com.cf.visitor.facade.facade.ReserveRecordFacade;
import com.cf.visitor.services.service.ReserveRecordService;
import com.cf.visitor.services.service.ReserveRuleConfigService;
import com.cf.visitor.services.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author whx
 * @date 2022/11/24
 */
@Slf4j
@Service
public class ReserveRecordFacadeImpl implements ReserveRecordFacade {

	@Resource
	private ReserveRecordService reserveRecordService;
	@Resource
	private ReserveRuleConfigService reserveRuleConfigService;

	@Override
	@Transactional
	public Result reserveCommit(ReserveRecordDTO param) {
		ReserveRecordPO recordPO = BeanConvertorUtils.map(param, ReserveRecordPO.class);
		//校验必填字段,字数限制等
		this.commitCheck(recordPO);
		//时间校验
		this.timeCheck(recordPO);
		//每人每天限制预约1次
		Long countRecordByState = reserveRecordService.countUserValidRecord(recordPO);
		if (countRecordByState != 0) {
			throw new BusinessException(BizResultCodeEnum.RESERVE_LIMIT);
		}
		//个人自动通过
		if (TypeEnum.TYPE_PERSONAL.getCode().equals(param.getType())) {
			recordPO.setState(StateEnum.STATE_PASSED.getCode());
		}
		reserveRecordService.save(recordPO);
		return Result.buildSuccessResult();
	}

	/**
	 * 根据提交类型进行校验
	 *
	 * @param param
	 */
	private void commitCheck(ReserveRecordPO param) {
		Integer type = param.getType();
		//通用字段为空校验
		if (StringUtils.isBlank(param.getName()) || ObjectUtils.isEmpty(param.getReserveDate()) || StringUtils.isBlank(param.getReserveTime()) || ObjectUtils.isEmpty(param.getRecNumber())) {
			throw new BusinessException(BizResultCodeEnum.PARAM_NULL);
		}
		//人数、字数校验 	描述字数限制
		if (param.getRecNumber() <= 0 || param.getName().length() > 20 || (StringUtils.isNotBlank(param.getRecDesc()) && param.getRecDesc().length() > 200)
				|| (StringUtils.isNotBlank(param.getRecBusiness()) && param.getRecBusiness().length() > 50)) {
			throw new BusinessException(BizResultCodeEnum.WORD_LIMIT);
		}
		//电话号格式校验
		Pattern pattern = Pattern.compile("0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|13[0-9]\\d{8}|15[1089]\\d{8}");
		if ((ObjectUtils.isNotEmpty(param.getTel()) && !pattern.matcher(param.getTel()).matches())
				|| (ObjectUtils.isNotEmpty(param.getDepTel()) && !pattern.matcher(param.getDepTel()).matches())) {
			throw new BusinessException(BizResultCodeEnum.FORMAT_ERROR);
		}
		//根据类型校验
		if (TypeEnum.TYPE_PERSONAL.getCode().equals(type)) {
			//个人
			if (ObjectUtils.isEmpty(param.getTel()) || ObjectUtils.isEmpty(param.getSex()) || StringUtils.isBlank(param.getIdNo())) {
				throw new BusinessException(BizResultCodeEnum.PARAM_NULL);
			}
			Pattern idNoPattern = Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
			if (!idNoPattern.matcher(param.getIdNo()).matches() || !Arrays.asList(SexEnum.MAN.getCode(), SexEnum.WOMAN.getCode()).contains(param.getSex())) {
				throw new BusinessException(BizResultCodeEnum.FORMAT_ERROR);         //身份证格式校验,性别校验
			}
		} else if (Arrays.asList(TypeEnum.TYPE_INDUSTRIAL_TOURISM.getCode(), TypeEnum.TYPE_OTHER.getCode()).contains(type)) {
			//研学实践、其他
			if (ObjectUtils.isEmpty(param.getTel()) || StringUtils.isBlank(param.getRecDesc())) {
				throw new BusinessException(BizResultCodeEnum.PARAM_NULL);
			}
		} else if (Arrays.asList(TypeEnum.TYPE_BUSINESS_VISIT.getCode(), TypeEnum.TYPE_GOVERNMENT_VISIT.getCode()).contains(type)) {
			//业务来访、政务来访
			if (StringUtils.isBlank(param.getRecBusiness()) || ObjectUtils.isEmpty(param.getSpeakNeed()) || StringUtils.isBlank(param.getRecDesc())
					|| StringUtils.isBlank(param.getDepartment()) || StringUtils.isBlank(param.getDepName()) || StringUtils.isBlank(param.getDepTel())) {
				throw new BusinessException(BizResultCodeEnum.PARAM_NULL);
			}
			if (param.getDepartment().length() > 50 || param.getDepName().length() > 20 || param.getRecWelcome().length() > 50 || param.getSpecialReq().length() > 200
					|| Arrays.asList(WhetherEnum.YES.getCode(), WhetherEnum.NO.getCode()).contains(param.getSpeakNeed())) {
				throw new BusinessException(BizResultCodeEnum.WORD_LIMIT);          //字数限制,类型限制
			}
		} else if (TypeEnum.TYPE_VISIT_AND_COMMUNICATION.getCode().equals(type)) {
			//参观交流
			if (StringUtils.isBlank(param.getRecBusiness()) || ObjectUtils.isEmpty(param.getTel()) || StringUtils.isBlank(param.getRecDesc())) {
				throw new BusinessException(BizResultCodeEnum.PARAM_NULL);
			}
		} else {
			//异常情况
			throw new BusinessException(BizResultCodeEnum.PARAM_ERROR);
		}
	}

	/**
	 * 时间校验
	 *
	 * @param param
	 */
	private void timeCheck(ReserveRecordPO param) {
		Date reserveDate = param.getReserveDate();
		String startTime = CFDateUtils.formatDate(DateFormatUtils.format(reserveDate, "yyyy-MM-dd") + param.getReserveTime().split("-")[0] + ":00");
		//预约时间不能早于现在
		if (CFDateUtils.formatDate(CFDateUtils.getCurrentTime()).compareTo(startTime) <= 0) {
			throw new BusinessException(BizResultCodeEnum.TIME_ERROR);
		}
		//个人预约时间须在配置时间中选择
		if (TypeEnum.TYPE_PERSONAL.getCode().equals(param.getType())) {
			List<ReserveRuleConfigPO> configListByDate = reserveRuleConfigService.getListByDate(reserveDate);
			if (CollectionUtils.isEmpty(configListByDate)) {
				throw new BusinessException(BizResultCodeEnum.PERSONAL_TIME_ERROR);
			}
			Integer ruleNumber = 0;
			for (ReserveRuleConfigPO item : configListByDate) {
				if ((item.getRuleStartTm() + "-" + item.getRuleEndTm()).equals(param.getReserveTime())) {
					ruleNumber = item.getRuleNumber();
				}
			}
			//人数校验，不能超过限制人数
			if (ruleNumber == 0 || reserveRecordService.countValidRecordByTime(param) >= ruleNumber) {
				throw new BusinessException(BizResultCodeEnum.PERSONAL_TIME_NUMBER_FULL);
			}
		}
	}

	@Override
	public List<ReserveValidDateBO> getValidDate() {
		List<ReserveRuleConfigPO> validDatePOS = reserveRuleConfigService.getValidDate();
		Map<Date, List<ReserveValidTimeBO>> dateListMap = new HashMap<>();
		//按日期组装列表
		validDatePOS.forEach(item -> {
			Date ruleDate = item.getRuleDate();
			String text = item.getRuleStartTm() + "-" + item.getRuleEndTm();
			List<ReserveValidTimeBO> timeBOList = new ArrayList<>();
			if (dateListMap.containsKey(ruleDate)) {
				timeBOList = dateListMap.get(ruleDate);
			}
			timeBOList.add(new ReserveValidTimeBO().setText(text).setValue(DateFormatUtils.format(ruleDate, "yyyy-MM-dd") + " " + text));
			dateListMap.put(ruleDate, timeBOList);
		});
		List<Date> dateList = validDatePOS.stream().map(ReserveRuleConfigPO::getRuleDate).distinct().collect(Collectors.toList());
		List<ReserveValidDateBO> validDateBOList = new ArrayList<>();
		dateList.forEach(ruleDate -> {
			validDateBOList.add(new ReserveValidDateBO().setValue(ruleDate)
					.setText(DateFormatUtils.format(ruleDate, "yyyy-MM-dd")).setChildren(dateListMap.get(ruleDate)));
		});
		return validDateBOList;
	}

	@Override
	public PageResponse<ReserveRecordBO> getUserRecords(ReserveRecordsDTO param) {
		IPage recordPage = reserveRecordService.getRecordPageByUserId(PageUtils.toPage(param), BeanConvertorUtils.map(param, ReserveRecordPO.class));
		if (recordPage.getTotal() == 0) {
			return PageUtils.emptyResponseList(recordPage);
		}
		List<ReserveRecordBO> recordPageBOS = BeanConvertorUtils.copyList(recordPage.getRecords(), ReserveRecordBO.class);
		recordPageBOS.forEach(item -> {
			String formatDate = DateFormatUtils.format(item.getReserveDate(), "yyyy-MM-dd");
			item.setRecTime(formatDate + " " + item.getReserveTime());
			Integer optState = 0;
			if (StateEnum.STATE_PASSED.getCode().equals(item.getState())) {
				String startTime = CFDateUtils.formatDate(formatDate + item.getReserveTime().split("-")[0] + ":00");
				String endTime = CFDateUtils.formatDate(formatDate + item.getReserveTime().split("-")[1] + ":00");
				String currentTime = CFDateUtils.formatDate(CFDateUtils.getCurrentTime());
				if (currentTime.compareTo(startTime) <= 0) {                //开始时间之前可取消
					optState = OptStateEnum.STATE_TO_BE_CANCELLED.getCode();
				} else if (currentTime.compareTo(endTime) >= 0) {            //结束时间之后可评价
					optState = OptStateEnum.STATE_TO_BE_EVALUATED.getCode();
				} else {                                                    //预约时间段之内可已到达
					optState = OptStateEnum.STATE_ARRIVED.getCode();
				}
			} else if (StateEnum.STATE_UN_REVIEW.getCode().equals(item.getState())) {
				optState = OptStateEnum.STATE_TO_BE_CANCELLED.getCode();
			} else if (StateEnum.STATE_ARRIVED.getCode().equals(item.getState())) {
				optState = OptStateEnum.STATE_TO_BE_EVALUATED.getCode();
			}
			item.setOptState(optState);
		});
		return PageUtils.toResponseList(recordPage, recordPageBOS);
	}
}
