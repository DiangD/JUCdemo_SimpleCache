package future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @ClassName MutiFutures
 * @Author DiangD
 * @Date 2020/7/19
 * @Version 1.0
 * @Description 批量获取线程执行的结果
 **/
public class MultiFutures {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = service.submit(new CallableTask());
            futures.add(future);
        }
        for (Future<Integer> future : futures) {
            try {
                Integer num = future.get();
                System.out.println(num);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }
    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
