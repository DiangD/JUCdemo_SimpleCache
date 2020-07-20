package imooccache;

import imooccache.computable.Computable;
import imooccache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ImoocCache2
 * @Author DiangD
 * @Date 2020/7/20
 * @Version 1.0
 * @Description 用装饰者模式，给计算器自动添加缓存功能
 **/
public class ImoocCache3<A,V> implements Computable<A,V> {
    private final Map<A,V> cache = new HashMap<>();
    private final Computable<A,V> comparator ;

    public ImoocCache3(Computable<A, V> comparator) {
        this.comparator = comparator;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        V result = cache.get(arg);
        if (result == null) {
            result = comparator.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        ImoocCache3<String, Integer> expensiveComputer = new ImoocCache3<>(new ExpensiveFunction());
        new Thread(()->{
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("result1 = " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("result3 = " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                Integer result = expensiveComputer.compute("123");
                System.out.println("result2 = " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
