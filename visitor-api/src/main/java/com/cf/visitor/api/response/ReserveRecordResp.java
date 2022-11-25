package com.cf.visitor.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author whx
 * @date 2022/11/23
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "")
public class ReserveRecordResp {

	/**
	 * 预约记录ID
	 */
	private Long reserveRecordId;

	private Long userId;

	@ApiModelProperty(value = "类型(1:个人,2:工业旅游,3:业务来访,4:参观交流,5:政务参观,6:其他)")
	private Integer type;

	@ApiModelProperty(value = "参观时间(2022-8-23 9:00-10:00）")
	private String recTime;

	private Date reserveDate;

	private String reserveTime;

	private String name;

	private String tel;

	private Integer recNumber;

	private Integer sex;

	private String idNo;

	private String recDesc;

	private String recBusiness;

	private String recWelcome;

	private Integer speakNeed;

	private String department;

	private String depName;

	private String depTel;

	private String specialReq;

	private String reason;

	@ApiModelProperty(value = "状态(1:未审核,2:已通过,3:已拒绝,4:已取消,5:已到达,6:已评价)")
	private Integer state;

	private Integer optState;

	private Integer evaluateRank;

	private String evaluateDesc;

	@ApiModelProperty(value = "讲解人")
	private String speakerName;

	@ApiModelProperty(value = "管理员备注")
	private String adminNotes;
}
