package lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName PessimismOptimismLock
 * @Author DiangD
 * @Date 2020/7/10
 * @Version 1.0
 * @Description 乐观锁 悲观锁
 * 乐观锁： 总是假设最好的情况，每次去拿数据的时候都认为别人不会修改，所以不会上锁，
 * 但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号机制和CAS算法实现。乐观锁适用于多读的应用类型，这样可以提高吞吐量。
 * 悲观锁：总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，
 * 这样别人想拿这个数据就会阻塞直到它拿到锁（共享资源每次只给一个线程使用，其它线程阻塞，用完后再把资源转让给其它线程）。
 **/
public class PessimismOptimismLock {
    int a;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }

    public synchronized void testMethod() {
        a++;
    }
}
