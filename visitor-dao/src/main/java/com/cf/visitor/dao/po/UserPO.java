package com.cf.visitor.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * @author whx
 * @date 2022-11-22 09:46:01
 * @description 用户表
 */
@Data
@TableName("user")
@Accessors(chain = true)
public class UserPO {


    /**
     * 创建时间
     */
    private Date createTm;

    /**
     * 最后活跃时间
     */
    private Date lastActiveAt;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 1-正常
     */
    private Integer state;

    /**
     * 更新时间
     */
    private Date updateTm;

    /**
     * userID
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;
}
