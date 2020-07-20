package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CountDownLatchDemo2
 * @Author DiangD
 * @Date 2020/7/17
 * @Version 1.0
 * @Description 模拟100米跑步，5名选手都准备好了，只等裁判员一声令下，所有人同时开始跑步。(多等一)
 **/
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i+1;
            Runnable runnable = ()->{
                System.out.println("No." + no + "准备完毕，等待发令枪");
                try {
                    begin.await();
                    System.out.println("No." + no + "开始跑步了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            service.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("发令枪响，比赛开始！");
        begin.countDown();
    }
}
