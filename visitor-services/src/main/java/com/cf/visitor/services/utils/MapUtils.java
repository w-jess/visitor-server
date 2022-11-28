package com.cf.visitor.services.utils;

import com.cf.support.exception.BusinessException;
import com.cf.support.utils.CFDateUtils;
import com.cf.visitor.dao.po.ReserveRuleConfigPO;
import com.cf.visitor.facade.enums.BizResultCodeEnum;

import java.util.HashMap;

/**
 * @author whx
 * @date 2022/11/28
 */
public class MapUtils {

	/**
	 * 获得时间段查重需要的map
	 *
	 * @param reserveRuleConfigPO
	 * @return
	 */
	public static HashMap<String, String> setOriginMap(ReserveRuleConfigPO reserveRuleConfigPO) {
		HashMap<String, String> originMap = new HashMap<>();
		originMap.put("start", CFDateUtils.formatDate(reserveRuleConfigPO.getRuleStartTm()));
		originMap.put("end", CFDateUtils.formatDate(reserveRuleConfigPO.getRuleEndTm()));
		if (originMap.get("start").compareTo(originMap.get("end")) >= 0) {
			throw new BusinessException(BizResultCodeEnum.CONFIG_TIME_LATER_ERROR);
		}
		return originMap;
	}
}
