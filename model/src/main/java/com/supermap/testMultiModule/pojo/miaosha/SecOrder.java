package com.supermap.testMultiModule.pojo.miaosha;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wenyutun
 * @description: 商品订单实体类
 * @date: 2019/6/17
 * @version: 1.0
 */
@Data
public class SecOrder {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 
     */
    private BigDecimal amount;

}