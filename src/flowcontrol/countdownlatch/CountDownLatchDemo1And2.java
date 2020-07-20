package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CountDownLatchDemo2
 * @Author DiangD
 * @Date 2020/7/17
 * @Version 1.0
 * @Description 模拟100米跑步，5名选手都准备好了，只等裁判员一声令下，所有人同时开始跑步。所有人到达终点后比赛结束
 **/
public class CountDownLatchDemo1And2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i+1;
            Runnable runnable = ()->{
                System.out.println("No." + no + "准备完毕，等待发令枪");
                try {
                    begin.await();
                    System.out.println("No." + no + "开始跑步了");
                    Thread.sleep((long) (Math.random()*10000));
                    System.out.println("No." + no +"跑到终点了");
                    end.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            service.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("发令枪响，比赛开始！");
        begin.countDown();
        end.await();
        System.out.println("所有人到达终点，比赛结束！");
    }
}
