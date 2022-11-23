package com.cf.visitor.services.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.visitor.dao.po.ReserveRuleConfigPO;
import com.cf.visitor.dao.mapper.ReserveRuleConfigMapper;
import org.springframework.stereotype.Service;

/**
 * @author whx
 * @date 2022-11-22 09:07:35
 * @description 规则配置表
 */
@Service
public class ReserveRuleConfigService extends ServiceImpl<ReserveRuleConfigMapper, ReserveRuleConfigPO> implements IService<ReserveRuleConfigPO> {

}

