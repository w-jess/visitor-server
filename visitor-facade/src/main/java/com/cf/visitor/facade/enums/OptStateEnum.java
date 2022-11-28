package com.cf.visitor.facade.enums;

public enum OptStateEnum {
	STATE_ARRIVED(1, "已到达"),
	STATE_EVALUATED(2, "已评价"),
	STATE_TO_BE_CANCELLED(3, "取消"),
	STATE_TO_BE_EVALUATED(4, "评价");;

	private Integer code;
	private String name;

	OptStateEnum(Integer code, String name) {
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
