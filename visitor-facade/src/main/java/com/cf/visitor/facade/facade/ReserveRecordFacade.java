package com.cf.visitor.facade.facade;

import com.cf.support.result.PageResponse;
import com.cf.support.result.Result;
import com.cf.visitor.facade.bo.ReserveRecordBO;
import com.cf.visitor.facade.bo.ReserveValidDateBO;
import com.cf.visitor.facade.dto.ReserveRecordDTO;
import com.cf.visitor.facade.dto.ReserveRecordsDTO;

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

	/**
	 * 查看用户预约记录
	 *
	 * @param param
	 * @return
	 */
	PageResponse<ReserveRecordBO> getUserRecords(ReserveRecordsDTO param);
}
