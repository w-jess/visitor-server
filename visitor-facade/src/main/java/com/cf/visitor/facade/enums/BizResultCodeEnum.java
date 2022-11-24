package com.cf.visitor.facade.enums;

import com.cf.support.result.ResultCode;

public enum BizResultCodeEnum implements ResultCode {
	/**
	 * 101 基础类
	 */
	OTHER_SYSTEM_ERROR(101001, "系统开小差了，请稍后再试"),

	PARAM_NULL(101002, "参数为空"),

	PARAM_ERROR(101003, "参数有误"),

	LOCK_ERROR(101004, "系统繁忙，请稍后再试"),

	TOKEN_BUILD_ERROR(101005, "token生成失败"),

	/* 102 用户类 */
	USER_UN_EXIST(102001, "用户不存在"),
	USER_UN_LOGIN(102002, "用户未登录"),
	WX_CODE_SESSION_ERROR(102003, "code获取session失败"),
	USER_CLOSED(102004, "用户已封禁"),

	/* 103 记录类 */
	WORD_LIMIT(103001, "超出字数限制"),
	FORMAT_ERROR(103002, "格式不正确"),
	TIME_ERROR(103003, "预约时间不能早于当前时间"),
	PERSONAL_TIME_ERROR(103004, "个人预约时间需从可预约时间中选择"),
	RESERVE_LIMIT(103005, "每人每天最多预约1次");

	BizResultCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private int code;
	private String msg;

	@Override
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
