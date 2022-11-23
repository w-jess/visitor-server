package com.cf.visitor.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.visitor.dao.po.AdminUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author whx
 * @date 2022-11-22 09:07:19
 * @description 后台用户表
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUserPO> {

}

