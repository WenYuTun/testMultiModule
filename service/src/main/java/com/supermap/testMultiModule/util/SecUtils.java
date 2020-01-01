package com.supermap.testMultiModule.util;

import com.supermap.testMultiModule.pojo.miaosha.ProductInfo;
import com.supermap.testMultiModule.pojo.miaosha.SecOrder;

import java.util.Random;

public class SecUtils {

    /**
     * 创建虚拟订单
     *
     * @param productInfo 商品信息
     * @return 订单
     */
    public static SecOrder createDummyOrder(ProductInfo productInfo) {
        String key = getUniqueKey();
        SecOrder secOrder = new SecOrder();
        secOrder.setId(key);
        secOrder.setUserId("userId=" + key);
        secOrder.setProductId(productInfo.getProductId());
        secOrder.setProductPrice(productInfo.getProductPrice());
        secOrder.setAmount(productInfo.getProductPrice());
        return secOrder;
    }

    /**
     * 伪支付
     *
     * @return 是否支付成功
     */
    public static boolean dummyPay() {
        Random random = new Random();
        int result = random.nextInt(1000) % 2;
        return result == 0;
    }

    /**
     * 获取唯一标识符
     *
     * @return 标识符字符串
     */
    private static synchronized String getUniqueKey() {
        Random random = new Random();
        int num = random.nextInt(100000);
        return Integer.toString(num) + System.currentTimeMillis();
    }
}
