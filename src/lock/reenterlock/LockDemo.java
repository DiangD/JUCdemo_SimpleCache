package lock.reenterlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockDemo
 * @Author DiangD
 * @Date 2020/7/10
 * @Version 1.0
 * @Description 演示reenterLock的基本用法
 **/
public class LockDemo {
    public static void main(String[] args) {
        OutPutter outPutter = new OutPutter();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outPutter.output("悟空");
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outPutter.output("大师兄");
            }
        }).start();
    }

    static class OutPutter {
        Lock lock = new ReentrantLock();

        public void output(String name) {
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }
    }
}
