package immutable;

/**
 * @ClassName FinalMethodDemo
 * @Author DiangD
 * @Date 2020/7/14
 * @Version 1.0
 * @Description final的方法 1.不允许修饰构造方法 2.不能被重写
 **/
public class FinalMethodDemo {
    int a;

    //不允许修饰构造方法
//    public final FinalMethodDemo(int a) {
//        this.a = a;
//    }
    public void drink() {

    }

    public final void eat() {

    }

    public static void sleep() {

    }
}
class SubClass extends FinalMethodDemo{
    @Override
    public void drink() {
        super.drink();
    }

//    @Override 不能被重写
//    public  void eat() {
//
//    }

    public static void sleep() {

    }

}
