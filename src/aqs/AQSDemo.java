package aqs;

import java.util.concurrent.Semaphore;

/**
 * @ClassName AQSDemo
 * @Author DiangD
 * @Date 2020/7/18
 * @Version 1.0
 * @Description AQS最核心的3大部分:1、state 2、控制线程抢锁和配合的FIFO队列
 * 3、期望协作工具类去实现的获取/释放等重要方法
 **/
public class AQSDemo {
    public static void main(String[] args) {
        new Semaphore(1);
    }

}
