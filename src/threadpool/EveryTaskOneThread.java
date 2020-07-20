package threadpool;

/**
 * @ClassName EveryTaskOneThread
 * @Author DiangD
 * @Date 2020/6/28
 * @Version 1.0
 * @Description 一个任务创建一个线程
 **/
public class EveryTaskOneThread {
    public static void main(String[] args) {
        new Thread(new Task()).start();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }
}
