package collection.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName OptionNotSafe
 * @Author DiangD
 * @Date 2020/7/15
 * @Version 1.0
 * @Description 组合操作不保证线程安全
 **/
public class OptionNotSafe implements Runnable {
    private static final ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);
        Thread t1 = new Thread(new OptionNotSafe());
        Thread t2 = new Thread(new OptionNotSafe());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            while (true) {
                Integer score = scores.get("小明");
                Integer newScore = score + 1;
                boolean replace = scores.replace("小明", score, newScore);
                if (replace) {
                    break;
                }
            }
        }
    }
}
