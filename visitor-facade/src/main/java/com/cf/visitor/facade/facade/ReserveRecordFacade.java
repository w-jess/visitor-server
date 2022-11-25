package com.cf.visitor.facade.facade;

import com.cf.support.result.Result;
import com.cf.visitor.facade.dto.ReserveRecordDTO;

public interface ReserveRecordFacade {

	/**
	 * 预约记录提交校验，并保存
	 *
	 * @param param
	 * @return
	 */
	Result reserveCommit(ReserveRecordDTO param);
}
