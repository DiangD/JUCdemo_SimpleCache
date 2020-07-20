package collection.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName CopyOnWriteArrayListDemo1
 * @Author DiangD
 * @Date 2020/7/16
 * @Version 1.0
 * @Description 演示CopyOnWriteArrayList可以在迭代过程中修改数组内容，但ArrayList不行，对比
 * 读写规制：写写互斥（ReentrantLock）读读、读写不互斥
 * copy on write思想：内存副本，不直接修改所在内存。
 * -> 复制一个新的数组（len + 1），将新插入的object放入到arr[len],成功后将array指向新数组（可重入锁内完成）
 **/
public class CopyOnWriteArrayListDemo1 {
    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("list is" + list);
            String next = iterator.next();
            System.out.println(next);
            if (next.equals("2")) {
                list.remove("5");
            }
            if (next.equals("3")) {
                list.add("3 found");
            }
        }
    }
}
