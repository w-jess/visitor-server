package com.cf.visitor.facade.facade;

import com.cf.support.result.PageResponse;
import com.cf.support.result.Result;
import com.cf.visitor.facade.bo.ReserveRecordBO;
import com.cf.visitor.facade.dto.AdminConfigTimeDTO;
import com.cf.visitor.facade.dto.AdminReservePageDTO;

import java.util.Date;
import java.util.List;

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

	/**
	 * 批量保存时间配置
	 *
	 * @param param
	 * @return
	 */
	Result saveRuleConfig(Date ruleDate, List<AdminConfigTimeDTO> param);

}
