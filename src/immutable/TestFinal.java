package immutable;

/**
 * @ClassName TestFinal
 * @Author DiangD
 * @Date 2020/7/14
 * @Version 1.0
 * @Description 测试final能否被修改
 **/
public class TestFinal {
    public static void main(String[] args) {
       final Person person = new Person();
//        person.age = 1;
//        person.name = "test";
//        person = new Person();
        person.bag = "book";
    }

}
