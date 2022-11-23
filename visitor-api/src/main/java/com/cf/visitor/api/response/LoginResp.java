package com.cf.visitor.api.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author whx
 * @date 2022/11/22
 */
@Data
@Accessors(chain = true)
public class LoginResp implements Serializable {
	private static final long serialVersionUID = -2575142326254609976L;

	private String token;

	private String adminName;

	private Integer isSuper;
}
