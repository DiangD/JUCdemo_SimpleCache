package collection.predecessor;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MapDemo
 * @Author DiangD
 * @Date 2020/7/15
 * @Version 1.0
 * @Description
 **/
public class MapDemo {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        System.out.println(map.isEmpty());
        map.put("东哥", 38);
        map.put("西哥", 28);
        System.out.println(map.keySet());
        System.out.println(map.get("西哥"));
        System.out.println(map.size());
        System.out.println(map.containsKey("东哥"));
        map.remove("东哥");
        System.out.println(map.containsKey("东哥"));
    }
}
