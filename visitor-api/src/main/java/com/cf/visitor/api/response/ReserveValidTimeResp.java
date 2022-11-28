package com.cf.visitor.api.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author whx
 * @date 2022/11/28
 */
@Data
@Accessors(chain = true)
public class ReserveValidTimeResp {
	private String text;

	private String value;
}
