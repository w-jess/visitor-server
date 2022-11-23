package com.cf.visitor.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * @author whx
 * @date 2022-11-22 09:08:05
 * @description 评价表
 */
@Data
@TableName("reserve_evaluate")
@Accessors(chain = true)
public class ReserveEvaluatePO {


    /**
     * 创建时间
     */
    private Date createTm;

    /**
     * 评价描述
     */
    private String evaluateDesc;

    /**
     * 等级(1:*,2**:,3:***,4:****:,5:*****)
     */
    private Integer evaluateRank;

    /**
     * 评价ID
     */
    @TableId(value = "reserve_evaluate_id", type = IdType.INPUT)
    private Long reserveEvaluateId;

    /**
     * 预约记录ID
     */
    private Long reserveRecordId;

    /**
     * 更新时间
     */
    private Date updateTm;

    /**
     * userID
     */
    private Long userId;
}
