package com.cf.visitor.facade.enums;

public enum WhetherEnum {
	YES(1, "是"),
	NO(2, "否");

	private Integer code;
	private String name;

	WhetherEnum(Integer code, String name) {
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
