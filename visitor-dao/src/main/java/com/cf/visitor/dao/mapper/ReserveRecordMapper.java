package com.cf.visitor.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.visitor.dao.po.ReserveRecordPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author whx
 * @date 2022-11-22 09:07:50
 * @description 预约记录表
 */
@Mapper
public interface ReserveRecordMapper extends BaseMapper<ReserveRecordPO> {

}

