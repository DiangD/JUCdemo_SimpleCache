package imooccache;

import imooccache.computable.Computable;
import imooccache.computable.MayFail;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName ImoocCache2
 * @Author DiangD
 * @Date 2020/7/20
 * @Version 1.0
 * @Description 出于安全性考虑，缓存需要失效，自动失效。
 * 利用ScheduledExecutorService线程池来完成，即掩饰队列的任务去执行过期操作
 **/
public class ImoocCache10<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> comparator;

    public ImoocCache10(Computable<A, V> comparator) {
        this.comparator = comparator;
    }

    @Override
    public V compute(A arg) throws ExecutionException, InterruptedException {
        while (true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                Callable<V> callable = () -> comparator.compute(arg);
                FutureTask<V> futureTask = new FutureTask<>(callable);
                future = cache.putIfAbsent(arg, futureTask);
                if (future == null) {
                    future = futureTask;
                    System.out.println("从FutureTask调用了计算函数");
                    futureTask.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                System.out.println("被取消了");
                cache.remove(arg);
                throw e;
            } catch (InterruptedException e) {
                e.printStackTrace();
                cache.remove(arg);
                throw e;
            } catch (ExecutionException e) {
                System.out.println("计算错误，需要重试");
                cache.remove(arg);
            }
        }
    }
    private final static ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(5);
    public V compute(A arg, long expire) throws ExecutionException, InterruptedException {
        if (expire > 0) {
            EXECUTOR_SERVICE.schedule(() -> {
                expire(arg);
            }, expire, TimeUnit.MILLISECONDS);
        }
        return compute(arg);
    }

    public V computeRandomExpire(A arg) throws ExecutionException, InterruptedException {
       long expire = (long) (Math.random() * 10000);
        if (expire > 0) {
            EXECUTOR_SERVICE.schedule(() -> {
                expire(arg);
            }, expire, TimeUnit.MILLISECONDS);
        }
        return compute(arg);
    }

    private synchronized void expire(A arg) {
        Future<V> future = cache.get(arg);
        if (future!=null) {
            if (!future.isDone()) {
                System.out.println("Future任务被取消");
                future.cancel(true);
            }
            System.out.println("过期时间到，缓存被清除");
            cache.remove(arg);
        }
    }

    public static void main(String[] args) throws Exception {
        ImoocCache10<String, Integer> expensiveComputer = new ImoocCache10<>(new MayFail());
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666",5000);
                System.out.println("result1 = " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("123");
                System.out.println("result2 = " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("result3 = " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(6000);
        System.out.println("expensiveComputer.compute(\"666\") = " + expensiveComputer.cache.get("666"));
    }
}
