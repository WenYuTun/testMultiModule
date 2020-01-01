package com.supermap.testMultiModule.web;

import com.supermap.testMultiModule.dto.miaosha.SecProductInfo;
import com.supermap.testMultiModule.pojo.miaosha.ProductInfo;
import com.supermap.testMultiModule.pojo.miaosha.SecOrder;
import com.supermap.testMultiModule.service.SecKillService;
import com.supermap.testMultiModule.util.SecUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/skill")
public class SecKillController {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(SecKillController.class);

    @Autowired
    private SecKillService secKillService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, SecOrder> redisTemplate;

    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) {
        //判断是否抢光
        int amount = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock" + productId));
        if (amount >= 2000) {
            return "不好意思，活动结束啦";
        }
        //初始化抢购商品信息，创建虚拟订单。
        ProductInfo productInfo = new ProductInfo(productId);
        SecOrder secOrder = SecUtils.createDummyOrder(productInfo);
        //付款，付款时时校验库存，如果成功redis存储订单信息，库存加1
        if (!SecUtils.dummyPay()) {
            LOG.error("付款慢啦抢购失败，再接再厉哦");
            return "抢购失败，再接再厉哦";
        }
        LOG.info("抢购成功 商品id=:" + productId);
        //订单信息保存在redis中
        secKillService.orderProductMockDiffUser(productId, secOrder);
        return "订单数量: " + redisTemplate.opsForSet().size("order" + productId) +
                "  剩余数量:" + (2000 - Integer.valueOf(stringRedisTemplate.opsForValue().get("stock" + productId)));
    }

    /**
     * 在redis中刷新库
     *
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/refresh/{productId}")
    public String refreshStock(@PathVariable String productId) {
        SecProductInfo secProductInfo = secKillService.refreshStock(productId);
        return "库存id为 " + productId + " <br>  库存总量为 " + secProductInfo.getStock();
    }

}
