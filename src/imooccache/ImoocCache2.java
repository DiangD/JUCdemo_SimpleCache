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
 * @Description 用装饰者模式，给计算器自动添加缓存功能，进一步解耦
 **/
public class ImoocCache2<A,V> implements Computable<A,V> {
    private final Map<A,V> cache = new HashMap<>();
    private final Computable<A,V> comparator ;

    public ImoocCache2(Computable<A, V> comparator) {
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
        ImoocCache2<String, Integer> expensiveComputer = new ImoocCache2<>(new ExpensiveFunction());
        Integer c1 = expensiveComputer.compute("666");
        Integer c2 = expensiveComputer.compute("666");
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
    }
}
