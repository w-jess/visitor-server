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

	;

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
