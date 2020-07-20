package immutable;

/**
 * 描述：     TODO
 */
public class FinalStringDemo1 {

    public static void main(String[] args) {
        //字符串常量池
        String a = "wukong2";
        final String b = "wukong";
        String d = "wukong";
        //指向常量池
        String c = b + 2;
        //在堆中创建
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
    }
}
