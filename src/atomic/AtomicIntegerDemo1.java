package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicIntegerDemo1
 * @Author DiangD
 * @Date 2020/7/13
 * @Version 1.0
 * @Description 演示AtomicInteger的基本用法，对比非原子类的线程安全问题，使用了原子类后，不需要加锁，也可以保证线程安全。
 **/
public class AtomicIntegerDemo1 implements Runnable{
    public static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();
    public static volatile int basicCount;


    public void decreaseAtomic() {
        ATOMIC_INTEGER.getAndDecrement();
    }

    public void addAtomic(int delta) {
        ATOMIC_INTEGER.getAndAdd(delta);
    }

    public void increaseAtomic() {
        ATOMIC_INTEGER.getAndIncrement();
    }

    public synchronized void increaseBasic() {
        basicCount++;
    }


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            increaseAtomic();
            increaseBasic();
            addAtomic(10);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 r = new AtomicIntegerDemo1();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("原子类的结果是："+ATOMIC_INTEGER.get());
        System.out.println("普通变量的结果："+basicCount);
    }
}
