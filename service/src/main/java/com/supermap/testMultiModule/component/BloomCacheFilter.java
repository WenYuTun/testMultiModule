package com.supermap.testMultiModule.component;

import com.google.common.hash.BloomFilter;
import org.springframework.stereotype.Component;

/**
 * @author wenyutun
 * @description: 布隆缓存过滤器，防止redis缓存击穿
 * @date: 2019/8/16
 * @version: 1.0
 * <p>
 * 实现原理：预先将所有缓存的主键key哈希到一个足够大的BitMap中，
 * 每次请求都会经过BitMap的拦截，如果key不存在，直接返回异常。
 * 这样就避免了缓存击穿
 * <p/>
 */
@Component
public class BloomCacheFilter {

    public static BloomFilter<Integer> bloomFilter = null;


}
