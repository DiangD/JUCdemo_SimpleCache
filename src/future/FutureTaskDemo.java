package future;

import java.util.concurrent.*;

/**
 * @ClassName FutureTaskDemo
 * @Author DiangD
 * @Date 2020/7/19
 * @Version 1.0
 * @Description 演示FutureTask的用法
 **/
public class FutureTaskDemo {
    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
//        new Thread(futureTask).start();
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(futureTask);
        try {
            System.out.println("futureTask.get() = " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在计算");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
