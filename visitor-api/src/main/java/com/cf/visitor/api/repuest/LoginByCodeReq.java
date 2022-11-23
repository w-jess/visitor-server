package com.cf.visitor.api.repuest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author whx
 * @date 2022/11/22
 */
@Data
@ApiModel(value = "用户授权登录接口")
public class LoginByCodeReq implements Serializable {
	private static final long serialVersionUID = -520025705848342564L;

	@ApiModelProperty("微信Code(用来换取openId)")
	private String code;
}
