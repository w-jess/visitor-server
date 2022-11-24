package com.cf.visitor.api.repuest;

import lombok.Data;

import java.util.Date;

/**
 * @author whx
 * @date 2022/11/23
 */
@Data
public class ReserveRecordReq {
	/**
	 * 预约记录id
	 */
	private Long reserveRecordId;

	/**
	 * userid
	 */
	private Long userId;

	/**
	 * 类型(1:个人，2:工业旅游，3:业务来访，4:参观交流，5:政务参观，6:其他)
	 */
	private Integer type;

	/**
	 * 预约日期
	 */
	private Date reserveDate;

	/**
	 * 预约时间
	 */
	private String reserveTime;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 电话
	 */
	private String tel;

	/**
	 * 人数
	 */
	private Integer recNumber;

	/**
	 * 个人:性别(0:男，1:女)
	 */
	private Integer sex;

	/**
	 * 个人:身份证
	 */
	private String idNo;

	/**
	 * 描述
	 */
	private String recDesc;

	/**
	 * 来访者单位
	 */
	private String recBusiness;

	/**
	 * 欢迎词
	 */
	private String recWelcome;

	/**
	 * 讲解需求(0:不需要，1:需要)
	 */
	private Integer speakNeed;

	private String speakerName;

	private String adminNotes;

	/**
	 * 对接人部门
	 */
	private String department;

	/**
	 * 对接人姓名
	 */
	private String depName;

	/**
	 * 对接人电话
	 */
	private String depTel;

	/**
	 * 特殊要求
	 */
	private String specialReq;

	private Integer state;
}
