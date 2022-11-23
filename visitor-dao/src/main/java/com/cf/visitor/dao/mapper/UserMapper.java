package com.cf.visitor.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.visitor.dao.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author whx
 * @date 2022-11-22 09:46:01
 * @description 用户表
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

}

