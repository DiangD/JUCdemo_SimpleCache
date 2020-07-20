package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MustUnlock
 * @Author DiangD
 * @Date 2020/7/10
 * @Version 1.0
 * @Description lock不会像synchronized一样，异常的时候释放锁，所以最佳实践是，在finally中释放锁，以便保证发生异常的时候锁一定被释放。
 **/
public class MustUnlock {

    public static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始执行任务");
        } finally {
            lock.unlock();
        }

    }
}
