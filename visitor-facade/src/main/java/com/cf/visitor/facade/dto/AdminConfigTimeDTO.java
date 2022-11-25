package com.cf.visitor.facade.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author whx
 * @date 2022/11/25
 */
@Data
@Accessors(chain = true)
public class AdminConfigTimeDTO {
	private Long reserveRuleConfigId;

	private String ruleStartTm;

	private String ruleEndTm;

	private Integer ruleNumber;
}
