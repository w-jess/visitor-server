package com.cf.visitor.api.repuest;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author whx
 * @date 2022/11/25
 */
@Data
public class AdminRuleConfigReq {
	private Date ruleDate;

	private List<AdminConfigTimeReq> saveList;
}
