package com.cf.visitor.facade.enums;

public enum SexEnum {
	MAN(1, "男"),
	WOMAN(2, "女");

	private Integer code;
	private String name;

	SexEnum(Integer code, String name) {
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
