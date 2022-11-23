package com.cf.visitor.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * @author whx
 * @date 2022-11-22 09:07:19
 * @description 后台用户表
 */
@Data
@TableName("admin_user")
@Accessors(chain = true)
public class AdminUserPO {


	/**
	 * 姓名
	 */
	private String adminName;

	/**
	 * 主键ID
	 */
	@TableId(value = "admin_user_id", type = IdType.INPUT)
	private Long adminUserId;

	/**
	 * 创建时间
	 */
	private Date createTm;

	/**
	 * 工号
	 */
	private String emplNo;

	/**
	 * 是否超级管理员 0-否 1-是
	 */
	private Integer isSuper;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 1-正常 2-封禁
	 */
	private Integer state;

	/**
	 * 更新时间
	 */
	private Date updateTm;
}
