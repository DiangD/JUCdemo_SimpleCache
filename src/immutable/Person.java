package immutable;

/**
 * @ClassName Person
 * @Author DiangD
 * @Date 2020/7/14
 * @Version 1.0
 * @Description 不可变对象，演示其他类无法修改这个类对象
 * public也不行
 **/
public class Person {
    public final int age = 18;
    public final String name = "Alice";
    String bag = "computer";

}
