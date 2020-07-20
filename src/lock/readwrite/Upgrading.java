package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName CinemaReadWrite
 * @Author DiangD
 * @Date 2020/7/10
 * @Version 1.0
 * @Description ReentrantReadWriteLock可降级不能升级  升级可导致死锁（两个线程同时等待对方放弃读锁）
 **/
public class Upgrading {
    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void readUpgrading() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"我得到读锁，正在读取");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"读升级,会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+"升级为写锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放读锁");
            readLock.unlock();
        }
    }

    public static void writeDowngrading() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"我得到写锁，正在写入");
            Thread.sleep(1000);
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"在不释放写锁的情况下，直接获取读锁，成功降级");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(Upgrading::writeDowngrading,"write1").start();
        new Thread(Upgrading::readUpgrading,"read1").start();
    }
}
