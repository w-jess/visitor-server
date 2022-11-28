package com.cf.visitor.services.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.visitor.dao.po.ReserveRecordPO;
import com.cf.visitor.dao.mapper.ReserveRecordMapper;
import com.cf.visitor.facade.enums.StateEnum;
import com.cf.visitor.facade.enums.TypeEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author whx
 * @date 2022-11-22 09:07:50
 * @description 预约记录表
 */
@Service
public class ReserveRecordService extends ServiceImpl<ReserveRecordMapper, ReserveRecordPO> implements IService<ReserveRecordPO> {

	@Resource
	private ReserveRecordMapper reserveRecordMapper;

	/**
	 * 按照条件获取预约记录并分页(名字模糊查询)
	 *
	 * @param page
	 * @param param
	 * @return
	 */
	public IPage getRecordPage(Page page, ReserveRecordPO param) {
		LambdaQueryWrapper<ReserveRecordPO> queryWrapper = new LambdaQueryWrapper<ReserveRecordPO>()
				.like(StringUtils.isNotBlank(param.getName()), ReserveRecordPO::getName, param.getName())
				.like(StringUtils.isNotBlank(param.getDepName()), ReserveRecordPO::getDepName, param.getDepName())
				.eq(ObjectUtils.isNotEmpty(param.getType()), ReserveRecordPO::getType, param.getType())
				.eq(ObjectUtils.isNotEmpty(param.getState()), ReserveRecordPO::getState, param.getState())
				.eq(ObjectUtils.isNotEmpty(param.getReserveDate()), ReserveRecordPO::getReserveDate, param.getReserveDate())
				.orderByDesc(ReserveRecordPO::getReserveDate, ReserveRecordPO::getReserveRecordId);
		return reserveRecordMapper.selectPage(page, queryWrapper);
	}

	/**
	 * 查询用户预约日期当天的有效订单数
	 *
	 * @param param
	 * @return
	 */
	public Long countUserValidRecord(ReserveRecordPO param) {
		LambdaQueryWrapper<ReserveRecordPO> queryWrapper = new LambdaQueryWrapper<ReserveRecordPO>()
				.eq(ReserveRecordPO::getUserId, param.getUserId()).eq(ReserveRecordPO::getReserveDate, param.getReserveDate())
				.in(ReserveRecordPO::getState, Arrays.asList(StateEnum.STATE_UN_REVIEW.getCode(), StateEnum.STATE_PASSED.getCode(),
						StateEnum.STATE_ARRIVED.getCode(), StateEnum.STATE_EVALUATED.getCode()));
		return this.count(queryWrapper);
	}

	/**
	 * 配置时间段内已预约人数
	 *
	 * @param param
	 * @return
	 */
	public Long countValidRecordByTime(ReserveRecordPO param) {
		LambdaQueryWrapper<ReserveRecordPO> queryWrapper = new LambdaQueryWrapper<ReserveRecordPO>().eq(ReserveRecordPO::getType, TypeEnum.TYPE_PERSONAL.getCode())
				.eq(ReserveRecordPO::getReserveDate, param.getReserveDate()).eq(ReserveRecordPO::getReserveTime, param.getReserveTime())
				.in(ReserveRecordPO::getState, Arrays.asList(StateEnum.STATE_UN_REVIEW.getCode(), StateEnum.STATE_PASSED.getCode()));
		return this.count(queryWrapper);
	}

	/**
	 * 获取该用户的预约记录
	 *
	 * @param page
	 * @param param
	 * @return
	 */
	public IPage getRecordPageByUserId(Page page, ReserveRecordPO param) {
		LambdaQueryWrapper<ReserveRecordPO> queryWrapper = new LambdaQueryWrapper<ReserveRecordPO>()
				.eq(ReserveRecordPO::getUserId, param.getUserId())
				.orderByDesc(ReserveRecordPO::getReserveDate, ReserveRecordPO::getReserveRecordId);
		return reserveRecordMapper.selectPage(page, queryWrapper);
	}
}

