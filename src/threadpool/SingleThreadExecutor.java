package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName SingleThreadExecutor
 * @Author DiangD
 * @Date 2020/6/29
 * @Version 1.0
 * @Description 演示SingleThreadExecutor
 **/
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task());
        }
    }
}
