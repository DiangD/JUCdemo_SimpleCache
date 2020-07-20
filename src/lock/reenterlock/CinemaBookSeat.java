package lock.reenterlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName CinemaBookSeat
 * @Author DiangD
 * @Date 2020/7/10
 * @Version 1.0
 * @Description 演示简单的电影院预定的例子
 **/
public class CinemaBookSeat {
    public static final ReentrantLock LOCK = new ReentrantLock();

    private static void bookSeat() {
        LOCK.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始预定座位");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
            System.out.println(Thread.currentThread().getName()+"完成预定座位");
        }
    }

    public static void main(String[] args) {
        new Thread(CinemaBookSeat::bookSeat).start();
        new Thread(CinemaBookSeat::bookSeat).start();
        new Thread(CinemaBookSeat::bookSeat).start();
        new Thread(CinemaBookSeat::bookSeat).start();
        new Thread(CinemaBookSeat::bookSeat).start();
    }
}
