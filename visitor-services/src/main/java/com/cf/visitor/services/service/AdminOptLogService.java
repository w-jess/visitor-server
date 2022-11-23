package com.cf.visitor.services.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.visitor.dao.po.AdminOptLogPO;
import com.cf.visitor.dao.mapper.AdminOptLogMapper;
import org.springframework.stereotype.Service;

/**
 * @author whx
 * @date 2022-11-22 09:08:19
 * @description 后台用户操作日志表
 */
@Service
public class AdminOptLogService extends ServiceImpl<AdminOptLogMapper, AdminOptLogPO> implements IService<AdminOptLogPO> {

}

