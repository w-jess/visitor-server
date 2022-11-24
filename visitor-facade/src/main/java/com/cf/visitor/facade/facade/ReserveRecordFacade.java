package com.cf.visitor.facade.facade;

import com.cf.support.result.PageResponse;
import com.cf.support.result.Result;
import com.cf.visitor.facade.bo.AdminReservePageBO;
import com.cf.visitor.facade.dto.AdminReservePageDTO;
import com.cf.visitor.facade.dto.ReserveRecordDTO;

public interface ReserveRecordFacade {

	/**
	 * 获取预约记录并分页
	 *
	 * @param param
	 * @return
	 */
	PageResponse<AdminReservePageBO> getRecordPage(AdminReservePageDTO param);

	/**
	 * 预约记录提交校验，并保存
	 *
	 * @param param
	 * @return
	 */
	Result reserveCommit(ReserveRecordDTO param);
}
