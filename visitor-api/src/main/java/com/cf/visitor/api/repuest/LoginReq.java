package com.cf.visitor.api.repuest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author whx
 * @date 2022/11/22
 */
@Data
@Accessors(chain = true)
public class LoginReq implements Serializable {
	private static final long serialVersionUID = 2683244820076120841L;

	@ApiModelProperty(value = "工号")
	private String emplNo;

	@ApiModelProperty(value = "密码")
	private String password;
}
