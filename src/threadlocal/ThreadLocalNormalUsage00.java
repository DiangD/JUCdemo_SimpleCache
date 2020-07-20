package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ThreadLocalNormalUsage00
 * @Author DiangD
 * @Date 2020/7/8
 * @Version 1.0
 * @Description 2个线程打印日期
 **/
public class ThreadLocalNormalUsage00 {
    public static void main(String[] args) {
        new Thread(() -> {
            //东八区
            String date = new ThreadLocalNormalUsage00().date(10);
            System.out.println(date);
        }).start();
        new Thread(() -> {
            String date = new ThreadLocalNormalUsage00().date(1004707);
            System.out.println(date);
        }).start();
    }

    public String date(int seconds) {
        //参数的单位是毫秒，从1970.1.1 00：00：00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
