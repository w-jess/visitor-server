package com.cf.visitor.facade.enums;

public enum StateEnum {
	STATE_UN_REVIEW(1, "未审核"),
	STATE_PASSED(2, "已通过"),
	STATE_REJECTED(3, "已拒绝"),
	STATE_CANCELLED(4, "已取消"),
	STATE_ARRIVED(5, "已到达"),
	STATE_EVALUATED(6, "已评价");
	private Integer code;
	private String name;

	StateEnum(Integer code, String name) {
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
