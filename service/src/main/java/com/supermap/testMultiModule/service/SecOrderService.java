package com.supermap.testMultiModule.service;

import com.supermap.testMultiModule.pojo.miaosha.SecOrder;

import java.util.List;

public interface SecOrderService {

    /**
     * 通过商品ID查询订单
     * @param productId
     * @return
     */
    List<SecOrder> findByProductId(String productId);

    /**
     * 保存订单
     * @param secOrder
     * @return
     */
    SecOrder save(SecOrder secOrder);
}
