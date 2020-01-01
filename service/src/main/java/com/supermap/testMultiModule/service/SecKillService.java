package com.supermap.testMultiModule.service;

import com.supermap.testMultiModule.dto.miaosha.SecProductInfo;
import com.supermap.testMultiModule.pojo.miaosha.SecOrder;

public interface SecKillService {

    long orderProductMockDiffUser(String productId, SecOrder secOrder);

    SecProductInfo refreshStock(String productId);
}
