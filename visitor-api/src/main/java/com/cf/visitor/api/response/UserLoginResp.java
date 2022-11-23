package com.cf.visitor.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author whx
 * @date 2022/11/22
 */
@Data
@Accessors(chain = true)
public class UserLoginResp {
	private static final long serialVersionUID = -4261336516190224656L;

	@ApiModelProperty("用户id")
	private Long userId;

	@ApiModelProperty("openId")
	private String openId;

	@ApiModelProperty("token")
	private String token;
}
