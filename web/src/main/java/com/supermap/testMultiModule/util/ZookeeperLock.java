package com.supermap.testMultiModule.util;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 * @author wyt
 * @description: 基于zookeeper的分布式锁
 * @date: 2019/11/24
 * @version: 1.0
 */
public class ZookeeperLock implements Lock {

    /**
     * 保存zk临时节点的根节点
     */
    private static final String LOCK_NAME = "/lock";

    /**
     * 当前节点目录
     */
    private ThreadLocal<String> currentNode = new ThreadLocal<>();

    /**
     * zookeeper
     */
    private ThreadLocal<ZooKeeper> zookeeper = new ThreadLocal<>();

    @Override
    public void lock() {
        init();
        if (tryLock()) {
            System.out.println("获取到分布式锁......");
        }

    }

    @Override
    public void lockInterruptibly() {

    }

    @Override
    public boolean tryLock() {
        String nodeName = LOCK_NAME + "/zk_";
        try {
            //创建临时节点
            currentNode.set(zookeeper.get().create(nodeName, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL));
            //查子节点
            final List<String> children = zookeeper.get().getChildren(LOCK_NAME, false);
            //获取到第一个临时节点
            final String firstNode = children.stream().sorted().findFirst().get();
            if (firstNode.equals(currentNode.get())) {
                return true;
            } else {
                /*
                等待锁(watch监听,监听前一个结点删除事件)
                 */
                //当前节点的下标
                final int currentNodeIndex =
                        children.indexOf(currentNode.get().substring(currentNode.get().lastIndexOf("/") + 1));
                //获取当前节点的前一个结点
                final String preNodeName = children.get(currentNodeIndex - 1);
                final CountDownLatch downLatch = new CountDownLatch(1);
                //监听前一个节点删除事件
                zookeeper.get().exists(LOCK_NAME + "/" + preNodeName, watchedEvent -> {
                    if (Watcher.Event.EventType.NodeDeleted.equals(watchedEvent.getType())) {
                        downLatch.countDown();
                        System.out.println(Thread.currentThread().getName() + "唤醒锁......");
                    }
                });
                System.out.println(Thread.currentThread().getName() + "等待锁......");
                downLatch.await();
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            zookeeper.remove();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        return false;
    }

    @Override
    public void unlock() {
        try {
            zookeeper.get().delete(currentNode.get(), -1);
            zookeeper.get().close();
            currentNode.remove();
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        } finally {
            zookeeper.remove();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * 初始化zk连接
     */
    private void init() {
        if (zookeeper.get() == null) {
            try {
                zookeeper.set(new ZooKeeper("localhost:2182", 3000, watchedEvent -> {
                    //...
                }));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//    public static void main(String[] args) {
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("0004");
//        strings.add("0007");
//        strings.add("0001");
//        strings.add("0003");
//        strings.forEach(System.out::println);
//        final List<String> collect = strings.stream().sorted().collect(Collectors.toList());
//        collect.forEach(System.out::println);
//        final String s = collect.stream().sorted().findFirst().get();
//        System.out.println(s);
//
//    }
}
