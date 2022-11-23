package com.cf.visitor.facade.enums;

public enum UserStateEnum {
	USER_IS_NORMAL(1, "正常"),
	USER_IS_CLOSED(2, "封禁");

	private Integer index;
	private String name;

	UserStateEnum(Integer index, String name) {
		this.index = index;
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
