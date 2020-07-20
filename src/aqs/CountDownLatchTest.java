package aqs;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchTest
 * @Author DiangD
 * @Date 2020/7/19
 * @Version 1.0
 * @Description
 **/
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(2);
        System.out.println("main thread waiting");
        new Thread(()->{
            try {
                Thread.sleep(100);
                latch1.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(100);
                latch2.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        latch1.await();
        latch2.await();
        System.out.println("main thread running");

    }
}
