package com.cf.visitor.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * @author whx
 * @date 2022-11-22 09:07:50
 * @description 预约记录表
 */
@Data
@TableName("reserve_record")
@Accessors(chain = true)
public class ReserveRecordPO {


	/**
	 * 管理员备注
	 */
	private String adminNotes;

	/**
	 * 创建时间
	 */
	private Date createTm;

	/**
	 * 对接人姓名
	 */
	private String depName;

	/**
	 * 对接人电话
	 */
	private String depTel;

	/**
	 * 对接人部门
	 */
	private String department;

	/**
	 * 个人:身份证
	 */
	private String idNo;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 拒绝原因
	 */
	private String reason;

	/**
	 * 来访者单位
	 */
	private String recBusiness;

	/**
	 * 描述
	 */
	private String recDesc;

	/**
	 * 人数
	 */
	private Integer recNumber;

	/**
	 * 欢迎词
	 */
	private String recWelcome;

	/**
	 * 预约日期
	 */
	private Date reserveDate;

	/**
	 * 预约记录ID
	 */
	@TableId(value = "reserve_record_id", type = IdType.INPUT)
	private Long reserveRecordId;

	/**
	 * 预约时间
	 */
	private String reserveTime;

	/**
	 * 个人:性别(1:男,2:女)
	 */
	private Integer sex;

	/**
	 * 讲解需求(0:不需要,1:需要)
	 */
	private Integer speakNeed;

	/**
	 * 讲解人姓名
	 */
	private String speakerName;

	/**
	 * 特殊要求
	 */
	private String specialReq;

	/**
	 * 状态(1:未审核,2:已通过,3:已拒绝,4:已取消,5:已到达,6:已评价)
	 */
	private Integer state;

	/**
	 * 电话
	 */
	private String tel;

	/**
	 * 类型(1:个人,2:工业旅游,3:业务来访,4:参观交流,5:政务参观,6:其他)
	 */
	private Integer type;

	/**
	 * 更新时间
	 */
	private Date updateTm;

	/**
	 * userID
	 */
	private Long userId;
}
