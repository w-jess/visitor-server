package com.cf.visitor.facade.enums;

public enum UserStateEnum {
	USER_IS_NORMAL(1, "正常"),
	USER_IS_CLOSED(2, "封禁");

	private Integer code;
	private String name;

	UserStateEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
