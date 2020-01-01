package com.supermap.testMultiModule.service;

import com.supermap.testMultiModule.pojo.miaosha.ProductInfo;

public interface ProductInfoService {

    /**
     * 通过id查询商品
     * @param id
     * @return
     */
    ProductInfo findOne(String id);
}
