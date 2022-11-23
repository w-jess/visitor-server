package com.cf.visitor.api.repuest;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "修改密码入参")
public class UpdatePwdReq implements Serializable {

	@ApiModelProperty(value = "原密码")
	private String oldPassword;

	@ApiModelProperty(value = "新密码")
	private String newPassword;
}
