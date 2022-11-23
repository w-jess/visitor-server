package com.cf.visitor.facade.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author whx
 * @date 2022/11/22
 */
@Data
@Accessors(chain = true)
public class UserLoginBO implements Serializable {

	private Long userId;

	private String openId;

	private String token;
}
