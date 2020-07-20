package threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ShutDown
 * @Author DiangD
 * @Date 2020/6/29
 * @Version 1.0
 * @Description 演示关闭线程池
 **/
public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 50; i++) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(1500);
        List<Runnable> runnableList = executorService.shutdownNow();
        runnableList.forEach(System.out::println);

//        executorService.shutdown();
//        System.out.println(executorService.isShutdown());
//        Thread.sleep(10000);
//        System.out.println(executorService.isTerminated());
//        executorService.execute(new ShutDownTask());

//        System.out.println(executorService.awaitTermination(7L, TimeUnit.SECONDS));
    }
}

class ShutDownTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断了");
        }
    }
}
