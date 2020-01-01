package com.supermap.testMultiModule.util;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * @author wenyutun
 * @description: 并发场景模拟的工具类
 *
 * <p>
 * 模拟高并发环境，测试服务高并发承受能力
 * </p>
 * @date: 2019/8/11
 * @version: 1.0
 */
public class CountDownLatchUtil {

    private CountDownLatch start;

    private CountDownLatch end;

    private int poolSize;

    public CountDownLatchUtil() {
        this(10);
    }

    private CountDownLatchUtil(int poolSize) {
        start = new CountDownLatch(1);
        end = new CountDownLatch(poolSize);
        this.poolSize = poolSize;
    }

    /**
     * 并发请求接口
     * <p>
     * 使用两个线程计数器CountDownLatch实现接口并发请求
     * 首先for循环中子线程通过start.await()方法使其处于等待状态
     * 当，调用start.countDown()使得线程计数器减一,减到0,所有处于等待状态的子线程并行执行来模拟高并发
     * 每个子线程执行完毕后，调用end.countDown(),使end计数器减一，所有子线程执行完毕后,end计数器减到0
     * 此时调用end.关闭线程池
     * </p>
     *
     * @param myFunctionalInterface 被测试接口
     * @throws InterruptedException
     */
    public void latch(MyFunctionalInterface myFunctionalInterface) throws InterruptedException {
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(poolSize,
                new BasicThreadFactory.Builder().namingPattern("test-concurrent-%d").daemon(true).build());
        for (int i = 0; i < poolSize; i++) {
            Runnable run = () -> {
                try {
                    start.await();
                    myFunctionalInterface.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //线程计数器减一
                    end.countDown();
                }
            };
            executor.submit(run);
        }
        start.countDown();
        //等待，当计数减到0时，执行
        end.await();
        executor.shutdown();

    }

    @FunctionalInterface
    public interface MyFunctionalInterface {
        /**
         * 执行
         */
        void run();
    }
}
