package collection.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName CopyOnWriteArrayListDemo1
 * @Author DiangD
 * @Date 2020/7/16
 * @Version 1.0
 * @Description 对比两个迭代器
 **/
public class CopyOnWriteArrayListDemo2 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});
        Iterator<Integer> itr1 = list.iterator();
        list.add(4);
        System.out.println(list);
        Iterator<Integer> itr2 = list.iterator();
        itr1.forEachRemaining(System.out::println);
        itr2.forEachRemaining(System.out::println);
    }
}
