package com.cf.visitor.services.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.visitor.dao.po.ReserveRuleConfigPO;
import com.cf.visitor.dao.mapper.ReserveRuleConfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author whx
 * @date 2022-11-22 09:07:35
 * @description 规则配置表
 */
@Service
public class ReserveRuleConfigService extends ServiceImpl<ReserveRuleConfigMapper, ReserveRuleConfigPO> implements IService<ReserveRuleConfigPO> {

	@Resource
	private ReserveRuleConfigMapper reserveRuleConfigMapper;

	/**
	 * 根据日期获取当天的配置记录
	 *
	 * @param date
	 * @return
	 */
	public List<ReserveRuleConfigPO> getListByDate(Date date) {
		return reserveRuleConfigMapper.selectList(new LambdaQueryWrapper<ReserveRuleConfigPO>().eq(ReserveRuleConfigPO::getRuleDate, date));
	}
}

