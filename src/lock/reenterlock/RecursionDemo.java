package lock.reenterlock;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName RecursionDemo
 * @Author DiangD
 * @Date 2020/7/10
 * @Version 1.0
 * @Description 可重入的演示 重入次数
 **/
public class RecursionDemo {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        accessResource();
    }

    private static void accessResource() {
        lock.lock();
        try {
            System.out.println("已经对资源进行处理" + lock.getHoldCount() + "次。");
            if (lock.getHoldCount() < 5) {
                //递归调用5次
                accessResource();
            }
        } finally {
            lock.unlock();
        }
    }
}
