package com.cf.visitor.facade.constant;

public interface RedisConstant {

	//用户登录锁
	String USER_LOGIN_LOCK_KEY = "visitor:user:login:lock:";
	int USER_LOGIN_LOCK_KEY_WAIT = 0;
	int USER_LOGIN_LOCK_KEY_EXPIRE = 5;
}
