package com.supermap.testMultiModule.util;

import org.springframework.util.DigestUtils;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author wenyutun
 * @description: 重复提交锁
 * @date: 2019/8/29
 * @version: 1.0
 */
public final class ResubmitLock {

    private static final ConcurrentHashMap<String, Object> LOCK_CACHE = new ConcurrentHashMap<>(200);

    private static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(5,
            new ThreadPoolExecutor.DiscardPolicy());

    private ResubmitLock() {
    }

    /**
     *静态内部类，单例模式
     */
    private static class SingletonInstance {
        private static final ResubmitLock INSTANCE = new ResubmitLock();
    }

    public static ResubmitLock getInstance() {
        return SingletonInstance.INSTANCE;
    }

    /**
     * md5加密
     * @param params 参数
     * @return 加密后字符串
     */
    public static String handleKey(String params) {
        return DigestUtils.md5DigestAsHex(params.getBytes());
    }

    /**
     * 加锁 putIfAbsent为原子操作，保证线程安全
     * @param key 对应的key
     * @param value 值
     * @return 是否加锁
     */
    public boolean lock(final String key,Object value) {
        return Objects.isNull(LOCK_CACHE.putIfAbsent(key,value));
    }

    /**
     * 延时释放锁，用以控制短时间内的重复提交
     * @param lock 是否需要解锁
     * @param key 对应的key
     * @param delaySeconds 延时时间
     */
    public void unLock(final boolean lock,final String key,final int delaySeconds) {
        if(lock) {
            EXECUTOR.schedule(() -> {
                LOCK_CACHE.remove(key);
            },delaySeconds, TimeUnit.SECONDS);
        }
    }

}
