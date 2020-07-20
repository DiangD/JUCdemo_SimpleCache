package future;

import java.util.concurrent.*;

/**
 * @ClassName Timeout
 * @Author DiangD
 * @Date 2020/7/19
 * @Version 1.0
 * @Description 演示get的超时方法，需要注意超时后处理，调用future.cancel()
 * 演示cancel传入true和false的区别，代表是否中断正在执行的任务。
 * true时可使线程响应中断 可看FutureTask的cancel方法
 **/
public class Timeout {
    private static final Ad DEFAULT_AD = new Ad("无网络时候的默认广告");
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    static class Ad {
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            try {

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断");
                return new Ad("被中断时候的默认广告");
            }

            return new Ad("aileme");
        }
    }

    void printAd() {
        Future<Ad> future = executor.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = future.get(1, TimeUnit.SECONDS);
            System.out.println(ad);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            System.out.println("超时，未获取到广告");
            boolean cancel = future.cancel(true);
            System.out.println("cancel的结果：" + cancel);
            System.out.println(ad);
        }
        executor.shutdown();
    }

    public static void main(String[] args) {
        new Timeout().printAd();
    }
}
