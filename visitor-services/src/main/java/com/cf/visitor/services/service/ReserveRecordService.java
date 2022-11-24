package com.cf.visitor.services.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.visitor.dao.po.ReserveRecordPO;
import com.cf.visitor.dao.mapper.ReserveRecordMapper;
import com.cf.visitor.facade.enums.StateEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
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

	public IPage getRecordPage(Page page, ReserveRecordPO param) {
		LambdaQueryWrapper<ReserveRecordPO> queryWrapper = new LambdaQueryWrapper<ReserveRecordPO>()
				.like(StringUtils.isNotEmpty(param.getName()), ReserveRecordPO::getName, param.getName())
				.like(StringUtils.isNotEmpty(param.getDepName()), ReserveRecordPO::getDepName, param.getDepName())
				.eq(ObjectUtils.isNotEmpty(param.getType()), ReserveRecordPO::getType, param.getType())
				.eq(ObjectUtils.isNotEmpty(param.getState()), ReserveRecordPO::getState, param.getState())
				.eq(ObjectUtils.isNotEmpty(param.getReserveDate()), ReserveRecordPO::getReserveDate, param.getReserveDate())
				.orderByDesc(ReserveRecordPO::getReserveTime, ReserveRecordPO::getReserveRecordId);
		return reserveRecordMapper.selectPage(page, queryWrapper);
	}

	/**
	 * 查询用户预约日期当天的有效订单
	 *
	 * @param param
	 * @return
	 */
	public Long countValidRecord(ReserveRecordPO param) {
		LambdaQueryWrapper<ReserveRecordPO> queryWrapper = new LambdaQueryWrapper<ReserveRecordPO>()
				.eq(ReserveRecordPO::getUserId, param.getUserId()).eq(ReserveRecordPO::getReserveDate, param.getReserveDate())
				.notIn(ReserveRecordPO::getState, Arrays.asList(StateEnum.STATE_UN_REVIEW.getCode(), StateEnum.STATE_PASSED.getCode(),
						StateEnum.STATE_ARRIVED.getCode(), StateEnum.STATE_EVALUATED.getCode()));
		return this.count(queryWrapper);
	}
}

