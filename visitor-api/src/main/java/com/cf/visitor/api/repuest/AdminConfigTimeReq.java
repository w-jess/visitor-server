package com.cf.visitor.api.repuest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author whx
 * @date 2022/11/25
 */
@Data
public class AdminConfigTimeReq {
	@ApiModelProperty(value = "配置规则ID,ID存在进行修改操作")
	private Long reserveRuleConfigId;

	private String ruleStartTm;

	private String ruleEndTm;

	private Integer ruleNumber;
}
