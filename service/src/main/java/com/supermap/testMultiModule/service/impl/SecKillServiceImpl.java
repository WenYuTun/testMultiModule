package com.supermap.testMultiModule.service.impl;

import com.supermap.testMultiModule.component.RedisLock;
import com.supermap.testMultiModule.dto.miaosha.SecProductInfo;
import com.supermap.testMultiModule.pojo.miaosha.SecOrder;
import com.supermap.testMultiModule.service.SecKillService;
import com.supermap.testMultiModule.service.SecOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

@Service
public class SecKillServiceImpl implements SecKillService {

    //日志
    private final Logger LOG = LoggerFactory.getLogger(SecKillServiceImpl.class);

    private static final int TIMEOUT = 10 * 1000;

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private SecOrderService secOrderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, SecOrder> redisTemplate;


    @Override
    public long orderProductMockDiffUser(String productId, SecOrder secOrder) {
        //加锁
        long orderSize;
        long time = System.currentTimeMillis() + TIMEOUT;
        final boolean lock = redisLock.lock(productId, String.valueOf(time));
        if (!lock) {
            throw new RuntimeException("哎呦喂，人太多了");
        }
        //获取库存数量
        final Integer stockNum = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock" + productId));
        if (stockNum >= 2000) {
            throw new RuntimeException("活动结束");
        } else {
            //库存减1
            stringRedisTemplate.opsForValue().increment("stock" + productId, 1);
            //redis中加入订单
            redisTemplate.opsForSet().add("order" + productId, secOrder);
            orderSize = redisTemplate.opsForSet().size("order" + productId);
            if (orderSize >= 1000) {
                //订单信息持久化,多线程写入数据库(效率从单线程的9000s提升到了9ms)
                Set<SecOrder> members = redisTemplate.opsForSet().members("order" + productId);
                List<SecOrder> memberList = new ArrayList<>(members);
                CountDownLatch countDownLatch = new CountDownLatch(4);
                new Thread(() -> {
                    for (int i = 0; i < memberList.size() / 4; i++) {
                        secOrderService.save(memberList.get(i));
                        countDownLatch.countDown();
                    }
                }, "therad1").start();
                new Thread(() -> {
                    for (int i = memberList.size() / 4; i < memberList.size() / 2; i++) {
                        secOrderService.save(memberList.get(i));
                        countDownLatch.countDown();
                    }
                }, "therad2").start();
                new Thread(() -> {
                    for (int i = memberList.size() / 2; i < memberList.size() * 3 / 4; i++) {
                        secOrderService.save(memberList.get(i));
                        countDownLatch.countDown();
                    }
                }, "therad3").start();
                new Thread(() -> {
                    for (int i = memberList.size() * 3 / 4; i < memberList.size(); i++) {
                        secOrderService.save(memberList.get(i));
                        countDownLatch.countDown();
                    }
                }, "therad4").start();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    LOG.error(e.toString());
                }
                LOG.info("订单持久化完成");
            }

        }
        //解锁
        redisLock.unlock(productId, String.valueOf(time));
        return orderSize;
    }

    @Override
    public SecProductInfo refreshStock(String productId) {
        return redisLock.refreshStock(productId);
    }
}
