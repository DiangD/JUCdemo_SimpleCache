package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadLocalNormalUsage00
 * @Author DiangD
 * @Date 2020/7/8
 * @Version 1.0
 * @Description 利用ThreadLocal分配自己的dateFormat对象，不会消耗资源
 **/
public class ThreadLocalNormalUsage05 {
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(() -> {
                String date = new ThreadLocalNormalUsage05().date(finalI);
                System.out.println(date);
            });
        }
        executorService.shutdown();
    }

    public String date(int seconds) {
        //参数的单位是毫秒，从1970.1.1 00：00：00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
        return dateFormat.format(date);
    }
}

class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

}
