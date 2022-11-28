package com.cf.visitor.facade.facade;

import com.cf.support.result.Result;
import com.cf.visitor.facade.bo.ReserveValidDateBO;
import com.cf.visitor.facade.dto.ReserveRecordDTO;

import java.util.List;

public interface ReserveRecordFacade {

	/**
	 * 预约记录提交校验，并保存
	 *
	 * @param param
	 * @return
	 */
	Result reserveCommit(ReserveRecordDTO param);

	/**
	 * 获取个人预约可选择日期
	 *
	 * @return
	 */
	List<ReserveValidDateBO> getValidDate();
}
