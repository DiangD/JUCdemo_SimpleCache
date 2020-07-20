package aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @ClassName OneShotLatch
 * @Author DiangD
 * @Date 2020/7/19
 * @Version 1.0
 * @Description 自己用aqs写一个简单的线程协作器
 **/
public class OneShotLatch {
    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() {
        sync.acquireShared(0);
    }

    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"尝试获取latch，获取失败那就等待");
                oneShotLatch.await();
                System.out.println("开闸放行"+Thread.currentThread().getName()+"继续运行");
            }).start();
        }
        Thread.sleep(5000);
        oneShotLatch.signal();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"尝试获取latch，获取失败那就等待");
            oneShotLatch.await();
            System.out.println("开闸放行"+Thread.currentThread().getName()+"继续运行");
        }).start();
    }
}
