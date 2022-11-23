package com.cf.visitor.facade.enums;

public enum TypeEnum {
	TYPE_PERSONAL(1, "个人"),
	TYPE_INDUSTRIAL_TOURISM(2, "研学实践"),
	TYPE_BUSINESS_VISIT(3, "业务来访"),
	TYPE_VISIT_AND_COMMUNICATION(4, "参观交流"),
	TYPE_GOVERNMENT_VISIT(5, "政务来访"),
	TYPE_OTHER(6, "其他");

	private Integer code;
	private String name;

	TypeEnum(Integer code, String name) {
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
