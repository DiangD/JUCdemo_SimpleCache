package cas;

/**
 * @ClassName SimulatedCAS
 * @Author DiangD
 * @Date 2020/7/14
 * @Version 1.0
 * @Description 模拟cas操作。等价代码
 * Unsafe 类提供了硬件级别的原子操作。
 **/
public class SimulatedCAS {
    //模拟内存值
    private volatile int value;
    //模拟cpu内部的原子cas
    public synchronized int compareAndSwap(int expectValue, int newValue) {
        int oldValue = value;
        //compare
        if (oldValue == expectValue) {
            //swap
            value = newValue;
        }
        return oldValue;
    }
}
