package lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName SpinLock
 * @Author DiangD
 * @Date 2020/7/12
 * @Version 1.0
 * @Description 自旋锁
 *  do {
 *             var5 = this.getIntVolatile(var1, var2);
 *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
 * 自旋锁的原理CAS while循环不断循环，直到修改成功。
 **/
public class SpinLock {
    private final AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {
            System.out.println("自旋获取失败，再次尝试");
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "开始获取自旋锁");
            spinLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到自旋锁");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放自旋锁");
                spinLock.unlock();
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();

    }
}
