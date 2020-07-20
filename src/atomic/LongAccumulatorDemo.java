package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @ClassName LongAccumulatorDemo
 * @Author DiangD
 * @Date 2020/7/13
 * @Version 1.0
 * @Description 演示LongAccumulator的用法
 **/
public class LongAccumulatorDemo {
    public static void main(String[] args) {
        LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        IntStream.range(1, 10)
                .forEach(i->executorService.submit(()->{
                    longAccumulator.accumulate(i);
                }));
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        System.out.println(longAccumulator.getThenReset());
    }
}
