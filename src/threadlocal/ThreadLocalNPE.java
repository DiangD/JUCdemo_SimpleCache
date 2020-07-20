package threadlocal;

/**
 * @ClassName ThreadLocalNP
 * @Author DiangD
 * @Date 2020/7/9
 * @Version 1.0
 * @Description ThreadLocalNPE空指针异常
 * 第一次get 会调用setInitialValue方法，如果我们不设置InitialValue则返回null
 **/
public class ThreadLocalNPE {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    public Long get() {
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());
        threadLocalNPE.set();
        System.out.println(threadLocalNPE.get());
    }
}
