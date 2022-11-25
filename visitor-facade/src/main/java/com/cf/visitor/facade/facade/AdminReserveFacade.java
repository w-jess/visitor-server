package com.cf.visitor.facade.facade;

import com.cf.support.result.PageResponse;
import com.cf.visitor.facade.bo.ReserveRecordBO;
import com.cf.visitor.facade.dto.AdminReservePageDTO;

/**
 * @author whx
 * @date 2022/11/25
 */
public interface AdminReserveFacade {

	/**
	 * 获取预约记录并分页
	 *
	 * @param param
	 * @return
	 */
	PageResponse<ReserveRecordBO> getRecordPage(AdminReservePageDTO param);

	/**
	 * 获取订单详情
	 *
	 * @param reserveRecordId
	 * @return
	 */
	ReserveRecordBO getRecordDetail(Long reserveRecordId);

}
