package com.cf.visitor.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * @author whx
 * @date 2022-11-22 09:08:19
 * @description 后台用户操作日志表
 */
@Data
@TableName("admin_opt_log")
@Accessors(chain = true)
public class AdminOptLogPO {


	/**
	 * 后台用户姓名
	 */
	private String adminName;

	/**
	 * 主键id
	 */
	@TableId(value = "admin_opt_log_id", type = IdType.INPUT)
	private Long adminOptLogId;

	/**
	 * 后台用户id
	 */
	private Long adminUserId;

	/**
	 * 创建时间
	 */
	private Date createAt;

	/**
	 * 接口执行时间
	 */
	private Integer executeTime;

	/**
	 * 访问ip
	 */
	private String ip;

	/**
	 * 接口名称
	 */
	private String optTitle;

	/**
	 * 请求参数
	 */
	private String params;

	/**
	 * 返回值
	 */
	private String response;

	/**
	 * 更新时间
	 */
	private Date updateAt;
}
