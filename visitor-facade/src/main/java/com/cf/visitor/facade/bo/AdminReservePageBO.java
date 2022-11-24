package com.cf.visitor.facade.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author whx
 * @date 2022/11/23
 */
@Data
@Accessors(chain = true)
public class AdminReservePageBO {

	private Date reserveDate;

	private String reserveTime;

	private Long reserveRecordId;

	private Long userId;

	private Integer type;

	private String recTime;

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

	private Integer state;

	private Integer optState;

	private Integer evaluateRank;

	private String evaluateDesc;

	private String speakerName; // 讲解人

	private String adminNotes; // 管理员备注
}
