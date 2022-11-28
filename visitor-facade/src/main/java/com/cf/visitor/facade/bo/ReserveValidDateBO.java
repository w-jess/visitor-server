package com.cf.visitor.facade.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author whx
 * @date 2022/11/28
 */
@Data
@Accessors(chain = true)
public class ReserveValidDateBO {
	private String text;

	private Date value;

	private List<ReserveValidTimeBO> children;
}
