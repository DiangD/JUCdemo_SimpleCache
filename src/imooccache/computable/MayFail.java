package imooccache.computable;

import java.io.IOException;

/**
 * @ClassName MayFail
 * @Author DiangD
 * @Date 2020/7/20
 * @Version 1.0
 * @Description 可能抛出异常的计算类
 **/
public class MayFail implements Computable<String,Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        double random = Math.random();
        if (random>0.5) {
            throw new IOException("读取文件出错");
        }
        Thread.sleep(3000);
        return Integer.valueOf(arg);
    }
}
