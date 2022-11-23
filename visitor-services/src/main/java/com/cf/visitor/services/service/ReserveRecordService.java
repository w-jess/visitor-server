package com.cf.visitor.services.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.visitor.dao.po.ReserveRecordPO;
import com.cf.visitor.dao.mapper.ReserveRecordMapper;
import org.springframework.stereotype.Service;

/**
 * @author whx
 * @date 2022-11-22 09:07:50
 * @description 预约记录表
 */
@Service
public class ReserveRecordService extends ServiceImpl<ReserveRecordMapper, ReserveRecordPO> implements IService<ReserveRecordPO> {

}

