package collection.predecessor;

import java.util.*;

/**
 * @ClassName SynList
 * @Author DiangD
 * @Date 2020/7/15
 * @Version 1.0
 * @Description
 **/
public class SynList {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        list.add(5);
        System.out.println(list.get(0));

        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());
    }
}
