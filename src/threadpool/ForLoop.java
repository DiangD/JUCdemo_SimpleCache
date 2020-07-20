package threadpool;

/**
 * @ClassName EveryTaskOneThread
 * @Author DiangD
 * @Date 2020/6/28
 * @Version 1.0
 * @Description 多个任务创建多个线程
 **/
public class ForLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task()).start();
        }
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }
}
