package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ThreadLocalNormalUsage00
 * @Author DiangD
 * @Date 2020/7/8
 * @Version 1.0
 * @Description 演示ThreadLocal用法2 避免传递参数的麻烦
 * 拿到的user不共享，没有线程安全问题
 **/
public class ThreadLocalNormalUsage06 {
    public static void main(String[] args) {
        new Service1().process();
    }
}
class UserContextHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class Service1 {
    public void process() {
        User user = new User("QZH");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}
class Service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("service2拿到用户名: "+user.getName());
        new Service3().process();
    }
}
class Service3 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("service3拿到用户名: "+user.getName());
        UserContextHolder.holder.remove();
        if (UserContextHolder.holder.get()==null) {
            System.out.println("removed successful");
        }
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}