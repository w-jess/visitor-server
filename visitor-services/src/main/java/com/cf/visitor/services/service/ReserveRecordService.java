package com.cf.visitor.services.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.support.result.PageResponse;
import com.cf.support.utils.BeanConvertorUtils;
import com.cf.visitor.dao.po.ReserveRecordPO;
import com.cf.visitor.dao.mapper.ReserveRecordMapper;
import com.cf.visitor.facade.bo.AdminRecordPageBO;
import com.cf.visitor.facade.dto.AdminRecordPageDTO;
import com.cf.visitor.services.utils.PageUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author whx
 * @date 2022-11-22 09:07:50
 * @description 预约记录表
 */
@Service
public class ReserveRecordService extends ServiceImpl<ReserveRecordMapper, ReserveRecordPO> implements IService<ReserveRecordPO> {

	@Resource
	private ReserveRecordMapper reserveRecordMapper;

	public PageResponse<AdminRecordPageBO> getRecordPage(AdminRecordPageDTO param) {
		LambdaQueryWrapper<ReserveRecordPO> queryWrapper = new LambdaQueryWrapper<ReserveRecordPO>()
				.like(StringUtils.isNotEmpty(param.getName()), ReserveRecordPO::getName, param.getName())
				.like(StringUtils.isNotEmpty(param.getDepName()), ReserveRecordPO::getDepName, param.getDepName())
				.eq(ObjectUtils.isNotEmpty(param.getType()), ReserveRecordPO::getType, param.getType())
				.eq(ObjectUtils.isNotEmpty(param.getState()), ReserveRecordPO::getState, param.getState())
				.eq(ObjectUtils.isNotEmpty(param.getReserveDate()), ReserveRecordPO::getReserveDate, param.getReserveDate())
				.orderByDesc(ReserveRecordPO::getReserveTime, ReserveRecordPO::getReserveRecordId);
		IPage recordPage = reserveRecordMapper.selectPage(PageUtils.toPage(param), queryWrapper);
		if (recordPage.getTotal() == 0) {
			return PageUtils.emptyResponseList(recordPage);
		}
		List<AdminRecordPageBO> recordPageBOS = BeanConvertorUtils.copyList(recordPage.getRecords(), AdminRecordPageBO.class);
		recordPageBOS.stream().forEach(item -> item.setRecTime(DateFormatUtils.format(item.getReserveDate(), "yyyy-MM-dd ") + item.getReserveTime()));
		return PageUtils.toResponseList(recordPage, recordPageBOS);
	}
}

