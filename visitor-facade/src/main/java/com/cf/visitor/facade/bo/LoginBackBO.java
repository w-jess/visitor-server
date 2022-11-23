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
public class LoginBackBO implements Serializable {
	private static final long serialVersionUID = -8965779987437724291L;

	private String token;

	private String adminName;

	private Integer isSuper;
}
