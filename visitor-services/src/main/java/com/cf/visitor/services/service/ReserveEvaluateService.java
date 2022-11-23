package com.cf.visitor.services.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.visitor.dao.po.ReserveEvaluatePO;
import com.cf.visitor.dao.mapper.ReserveEvaluateMapper;
import org.springframework.stereotype.Service;

/**
 * @author whx
 * @date 2022-11-22 09:08:05
 * @description 评价表
 */
@Service
public class ReserveEvaluateService extends ServiceImpl<ReserveEvaluateMapper, ReserveEvaluatePO> implements IService<ReserveEvaluatePO> {

}

