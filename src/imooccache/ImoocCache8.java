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
 * @Description 利用future避免重复计算
 * 使用CurrentHashMap的putIfAbsent函数来确保了缓存不会被重复设置，cas确保了只有并发只有一个线程能成功修改数据
 **/
public class ImoocCache8<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> comparator;

    public ImoocCache8(Computable<A, V> comparator) {
        this.comparator = comparator;
    }

    @Override
    public V compute(A arg) throws Exception {
        Future<V> future = cache.get(arg);
        if (future == null) {
            Callable<V> callable = () -> comparator.compute(arg);
            FutureTask<V> futureTask = new FutureTask<>(callable);
            //有且只有一个线程成功执行修改并返回数据，如果key存在，不会修改old value
            future = cache.putIfAbsent(arg, futureTask);
            //为null，即该key没有被设置过
            if (future==null) {
                future = futureTask;
                System.out.println("从FutureTask调用了计算函数");
                futureTask.run();
            }
        }
        return future.get();
    }

    public static void main(String[] args) throws Exception {
        ImoocCache8<String, Integer> expensiveComputer = new ImoocCache8<>(new ExpensiveFunction());
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
