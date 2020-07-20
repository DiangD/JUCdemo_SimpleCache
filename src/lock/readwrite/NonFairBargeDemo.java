package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName NonFairBargeDemo
 * @Author DiangD
 * @Date 2020/7/10
 * @Version 1.0
 * @Description 演示非公平和公平的读写锁的策略
 * 1. 写锁随时可以插队
 * 2. 读锁在线程队列第一位不是写锁时可以插队
 * 适合读多写少的场景
 **/
public class NonFairBargeDemo {
    public final static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
    public static final ReentrantReadWriteLock.ReadLock READ_LOCK = reentrantReadWriteLock.readLock();
    public static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = reentrantReadWriteLock.writeLock();

    private static void read() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取读锁");
        READ_LOCK.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            READ_LOCK.unlock();
        }
    }

    private static void write() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取写锁");
        WRITE_LOCK.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到写锁，正在写入");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            WRITE_LOCK.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(NonFairBargeDemo::write, "write1").start();
        new Thread(NonFairBargeDemo::read, "read1").start();
        new Thread(NonFairBargeDemo::read, "read2").start();
        new Thread(NonFairBargeDemo::write, "write2").start();
        new Thread(NonFairBargeDemo::read, "read3").start();
        new Thread(()->{
            Thread[] threads = new Thread[1000];
            for (int i = 0; i < 1000; i++) {
                threads[i] = new Thread(NonFairBargeDemo::read,"子线程创建的Thread"+i);
            }
            for (Thread thread : threads) {
                thread.start();
            }

        }).start();
    }
}
