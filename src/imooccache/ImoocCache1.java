package imooccache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ImoocCache1
 * @Author DiangD
 * @Date 2020/7/20
 * @Version 1.0
 * @Description 最简单的缓存形式：HashMap
 * 特点：线程不安全
 * 解决方法：同步方法（synchronized） -> 并发性能低，可扩展性低，待改进
 **/
public class ImoocCache1 {
    private final HashMap<String, Integer> cache = new HashMap<>();
    public synchronized Integer compute(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        if (result == null) {
            result= doCompute(userId);
            cache.put(userId, result);
        }
        return result;
    }

    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);

    }

    public static void main(String[] args) throws InterruptedException {
        ImoocCache1 imoocCache1 = new ImoocCache1();
        Integer c1 = imoocCache1.doCompute("12121");
        Integer c2 = imoocCache1.doCompute("12121");
        System.out.println(c1);
        System.out.println(c2);

    }
}
