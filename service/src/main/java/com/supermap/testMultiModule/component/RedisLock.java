package com.supermap.testMultiModule.component;

import com.supermap.testMultiModule.dto.miaosha.SecProductInfo;
import com.supermap.testMultiModule.pojo.miaosha.ProductInfo;
import com.supermap.testMultiModule.service.ProductInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wenyutun
 * @description: 分布式乐观锁
 * @date: 2019/8/29
 * @version: 1.0
 */
@Component
public class RedisLock {

    private final Logger LOG = LoggerFactory.getLogger(RedisLock.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 加锁
     *
     * @param key key值
     * @param value value值
     * @return
     */
    public boolean lock(String key, String value) {
        //setIfAbsent，key存在的话返回false，不存在返回true
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        //两个问题
        // Question1: 超时时间
        final String currentValue = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) &&
                Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //Question2: 在线程超时的时候，多个线程争抢锁的问题
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 释放锁
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            LOG.error("redis分布上锁解锁异常, {}", e.toString());
        }
    }

    /**
     * 刷新索
     * @param productId
     * @return
     */
    public SecProductInfo refreshStock(String productId) {
        ProductInfo productInfo = productInfoService.findOne(productId);
        if (productId == null) {
            throw new RuntimeException("秒杀商品不存在");
        }
        SecProductInfo secProductInfo = new SecProductInfo();
        try {
            redisTemplate.opsForValue().set("stock" + productInfo.getProductId(),
                    String.valueOf(productInfo.getProductStock()));
            String value = redisTemplate.opsForValue().get("stock" + productInfo.getProductId());
            secProductInfo.setProductId(productId);
            secProductInfo.setStock(value);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return secProductInfo;
    }

}


