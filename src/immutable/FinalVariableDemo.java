package immutable;

import java.util.Random;

/**
 * @ClassName FinalVariableDemo
 * @Author DiangD
 * @Date 2020/7/14
 * @Version 1.0
 * @Description 演示final变量
 **/
public class FinalVariableDemo {
    private static final int a;

    static {
        a = 9;
    }

    //    public FinalVariableDemo(int a) {
//        this.a = a;
//    }
//    {
//        a = 7;
//    }

    void testField() {
        final int b;
        b= Integer.parseInt(String.valueOf(Math.random()));
        int c = b;
    }

}
