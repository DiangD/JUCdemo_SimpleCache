package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockInterruptibly
 * @Author DiangD
 * @Date 2020/7/10
 * @Version 1.0
 * @Description LockInterruptibly演示
 **/
public class LockInterruptibly implements Runnable {
    private  Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptibly r1 = new LockInterruptibly();
        Thread thread0 = new Thread(r1);
        Thread thread1 = new Thread(r1);
        thread0.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread0.interrupt();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "拿到了了锁");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "睡眠期间被中断");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "获取锁期间被中断");
        }
    }
}
