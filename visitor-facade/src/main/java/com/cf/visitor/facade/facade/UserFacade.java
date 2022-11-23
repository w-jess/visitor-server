package com.cf.visitor.facade.facade;

import com.cf.support.result.Result;
import com.cf.visitor.facade.bo.UserLoginBO;

/**
 * @author whx
 * @date 2022/11/22
 */
public interface UserFacade {

	Result<UserLoginBO> loginByCode(String code);
}
