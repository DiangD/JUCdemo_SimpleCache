package cas;

/**
 * @ClassName SimulatedCAS
 * @Author DiangD
 * @Date 2020/7/14
 * @Version 1.0
 * @Description 模拟cas操作。等价代码
 **/
public class TwoThreadCompetition implements Runnable {
    private volatile int value;

    public synchronized int compareAndSwap(int expectValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectValue) {
            System.out.println(Thread.currentThread().getName() + "cas成功！");
            value = newValue;
            return oldValue;
        } else {
            System.out.println(Thread.currentThread().getName() + "cas失败！");
            return oldValue;
        }

    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadCompetition r = new TwoThreadCompetition();
        r.value = 0;
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.value);
    }
}
