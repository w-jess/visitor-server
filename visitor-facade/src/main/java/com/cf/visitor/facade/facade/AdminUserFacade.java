package com.cf.visitor.facade.facade;

import com.cf.support.result.Result;
import com.cf.visitor.facade.bo.LoginBackBO;

public interface AdminUserFacade {
	Result<LoginBackBO> login(String emplNo, String password);

	Result logout(Long adminId);

	Result updatePwd(String emplNo, String oldPassword, String newPassword);
}
