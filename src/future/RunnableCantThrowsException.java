package future;

/**
 * @ClassName RunnableCantThrowsException
 * @Author DiangD
 * @Date 2020/7/19
 * @Version 1.0
 * @Description runnable的2大缺陷 不能返回一个返回值 也不能抛出checked exception
 **/
public class RunnableCantThrowsException {
    public static void main(String[] args) {
        Runnable runnable = () -> {
//            throw new Exception();
        };
    }
}