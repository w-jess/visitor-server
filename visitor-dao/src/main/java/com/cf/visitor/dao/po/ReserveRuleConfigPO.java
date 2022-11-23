package com.cf.visitor.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * @author whx
 * @date 2022-11-22 09:07:35
 * @description 规则配置表
 */
@Data
@TableName("reserve_rule_config")
@Accessors(chain = true)
public class ReserveRuleConfigPO {


	/**
	 * 创建时间
	 */
	private Date createTm;

	/**
	 * 规则ID
	 */
	@TableId(value = "reserve_rule_config_id", type = IdType.INPUT)
	private Long reserveRuleConfigId;

	/**
	 * 日期
	 */
	private Date ruleDate;

	/**
	 * 结束时间
	 */
	private String ruleEndTm;

	/**
	 * 限制人数
	 */
	private Integer ruleNumber;

	/**
	 * 开始时间
	 */
	private String ruleStartTm;

	/**
	 * 更新时间
	 */
	private Date updateTm;
}
