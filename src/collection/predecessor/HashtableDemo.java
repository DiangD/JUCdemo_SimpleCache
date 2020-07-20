package collection.predecessor;

import java.util.Hashtable;

/**
 * @ClassName HashtableDemo
 * @Author DiangD
 * @Date 2020/7/15
 * @Version 1.0
 * @Description
 **/
public class HashtableDemo {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable  = new Hashtable<>();

        hashtable.put("薪资涨幅", "50%");

        System.out.println(hashtable.get("薪资涨幅"));
    }
}
