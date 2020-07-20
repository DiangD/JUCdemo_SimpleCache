package future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @ClassName OneFuture
 * @Author DiangD
 * @Date 2020/7/19
 * @Version 1.0
 * @Description 演示一个future的使用方法
 * get（）方法可以当任务结束后返回一个结果，如果调用时，工作还没有结束，则会阻塞线程，直到任务执行完毕
 *
 * get（long timeout,TimeUnit unit）做多等待timeout的时间就会返回结果
 *
 * cancel（boolean mayInterruptIfRunning）方法可以用来停止一个任务，如果任务可以停止（通过mayInterruptIfRunning来进行判断），则可以返回true,如果任务已经完成或者已经停止，或者这个任务无法停止，则会返回false.
 *
 * isDone（）方法判断当前方法是否完成
 *
 * isCancel（）方法判断当前方法是否取消
 **/
public class OneFuture {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(new CallableTask());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
