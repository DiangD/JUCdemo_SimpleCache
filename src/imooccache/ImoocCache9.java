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
 * @Description 解决缓存污染的问题，在异常捕获的时候偶，即使清除脏数据避免缓存污染
 * 用MayFail来模拟计算失败，以及自旋（while）来来实现错误重试
 **/
public class ImoocCache9<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> comparator;

    public ImoocCache9(Computable<A, V> comparator) {
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

    public static void main(String[] args) throws Exception {
        ImoocCache9<String, Integer> expensiveComputer = new ImoocCache9<>(new MayFail());
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666");
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
    }
}
