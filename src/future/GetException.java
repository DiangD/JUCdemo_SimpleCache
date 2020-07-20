package future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @ClassName GetExecption
 * @Author DiangD
 * @Date 2020/7/19
 * @Version 1.0
 * @Description callable允许抛出异常
 **/
public class GetException {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Future<Integer> future = service.submit(new CallableTask());
        for (int i = 0; i < 5; i++) {
            Thread.sleep(500);
        }
        System.out.println(future.isDone());
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException");
        }
    }
    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}
