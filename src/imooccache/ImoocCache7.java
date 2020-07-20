package imooccache;

import imooccache.computable.Computable;
import imooccache.computable.ExpensiveFunction;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @ClassName ImoocCache2
 * @Author DiangD
 * @Date 2020/7/20
 * @Version 1.0
 * @Description 利用future避免重复计算,依然存在问题。
 * 这里考虑的过于简单，但使用future是一个很好的思想，利用future get方法的特性来避免重复计算。
 **/
public class ImoocCache7<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> comparator;

    public ImoocCache7(Computable<A, V> comparator) {
        this.comparator = comparator;
    }

    @Override
    public V compute(A arg) throws Exception {
        Future<V> future = cache.get(arg);
        if (future == null) {
            Callable<V> callable = () -> comparator.compute(arg);
            FutureTask<V> futureTask = new FutureTask<>(callable);
            future= futureTask;
            cache.put(arg, futureTask);
            System.out.println("从FutureTask调用了计算函数");
            futureTask.run();
        }
        return future.get();
    }

    public static void main(String[] args) throws Exception {
        ImoocCache7<String, Integer> expensiveComputer = new ImoocCache7<>(new ExpensiveFunction());
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
