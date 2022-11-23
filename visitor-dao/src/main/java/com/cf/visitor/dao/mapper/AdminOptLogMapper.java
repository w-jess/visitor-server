package com.cf.visitor.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.visitor.dao.po.AdminOptLogPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author whx
 * @date 2022-11-22 09:08:19
 * @description 后台用户操作日志表
 */
@Mapper
public interface AdminOptLogMapper extends BaseMapper<AdminOptLogPO> {

}

