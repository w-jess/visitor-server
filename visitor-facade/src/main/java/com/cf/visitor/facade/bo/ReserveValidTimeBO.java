package com.cf.visitor.facade.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author whx
 * @date 2022/11/28
 */
@Data
@Accessors(chain = true)
public class ReserveValidTimeBO {
	private String text;

	private String value;
}
