package com.cf.visitor.services.utils;

import com.cf.visitor.facade.enums.StateEnum;
import com.cf.visitor.facade.enums.TypeEnum;

/**
 * @author whx
 * @date 2022/11/23
 */
public class EnumUtils {

	/**
	 * isInType
	 *
	 * @param type
	 * @return
	 */
	public static Boolean isInType(Integer type) {
		for (TypeEnum typeEnum : TypeEnum.values()) {
			if (typeEnum.getCode().equals(type)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * isInState
	 *
	 * @param state
	 * @return
	 */
	public static Boolean isInState(Integer state) {
		for (StateEnum stateEnum : StateEnum.values()) {
			if (stateEnum.getCode().equals(state)) {
				return true;
			}
		}
		return false;
	}
}
