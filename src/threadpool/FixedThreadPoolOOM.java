package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName FixedThreadPoolOOM
 * @Author DiangD
 * @Date 2020/6/29
 * @Version 1.0
 * @Description 演示newFixedThreadPool OOM错误
 **/
public class FixedThreadPoolOOM {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new subThread());
        }
    }
}

class subThread implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}