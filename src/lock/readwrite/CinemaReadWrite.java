package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName CinemaReadWrite
 * @Author DiangD
 * @Date 2020/7/10
 * @Version 1.0
 * @Description 读锁：共享锁 写锁：独占锁
 * 可以多个线程读取数据且不能更新数据。只能有一个线程更新数据。
 **/
public class CinemaReadWrite {
    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
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
        new Thread(CinemaReadWrite::read,"read1").start();
        new Thread(CinemaReadWrite::read,"read2").start();
        new Thread(CinemaReadWrite::write,"write1").start();
        new Thread(CinemaReadWrite::write,"write2").start();
    }
}
