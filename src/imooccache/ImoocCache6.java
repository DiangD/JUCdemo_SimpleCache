package imooccache;

import imooccache.computable.Computable;
import imooccache.computable.ExpensiveFunction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ImoocCache2
 * @Author DiangD
 * @Date 2020/7/20
 * @Version 1.0
 * @Description 存在重复计算的问题，就在两个线程同时get时，都为null，于是2次计算
 **/
public class ImoocCache6<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> comparator;

    public ImoocCache6(Computable<A, V> comparator) {
        this.comparator = comparator;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = comparator.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        ImoocCache6<String, Integer> expensiveComputer = new ImoocCache6<>(new ExpensiveFunction());
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
                Integer result = expensiveComputer.compute("666");
                System.out.println("result3 = " + result);
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
    }
}
