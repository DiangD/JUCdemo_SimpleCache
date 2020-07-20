package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName CinemaReadWrite
 * @Author DiangD
 * @Date 2020/7/10
 * @Version 1.0
 * @Description 非公平锁，如果队列的首节点是一个请求写锁的线程，将不允许抢锁
 **/
public class CinemaReadWriteQueue {
    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"我得到读锁，正在读取");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放读锁");
            readLock.unlock();
        }
    }

    public static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"我得到写锁，正在写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(CinemaReadWriteQueue::write,"write1").start();
        new Thread(CinemaReadWriteQueue::read,"read1").start();
        new Thread(CinemaReadWriteQueue::read,"read2").start();
        new Thread(CinemaReadWriteQueue::write,"write2").start();
        new Thread(CinemaReadWriteQueue::read,"read3").start();
    }
}
