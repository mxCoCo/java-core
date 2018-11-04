package com.newFeture.optional;

import com.newFeture.lambda.User;
import org.junit.Test;

import java.util.Optional;

public class TestOptional {
    /**
     * Optional 容器类的常用方法：
     * Optional.of(T t):创建一个Optional实例
     * Optional.empty():创建一个空的Optional实例
     * Optional.ofNullable(T t):若t不为null,创建Optional实例,否则创建空实例
     * isParent():判断是否包含值
     * orElse(T t):如果调用对象包含值,返回值,否则返回t
     * orElseGet(Supplier s):如果调用对象包含值,返回值,否则返回s获取的值
     * map(Function f):如果有值对其处理,则返回处理后的Optional,否则返回Optional.empty()
     * flatMap(Function mapper):与map类似,要求返回值必须是Optional
     */

    @Test
    public void test1(){
        Optional<User> opt=Optional.of(new User());
        System.out.println(opt.get());
    }

    @Test
    public void test2(){
        Optional<User> opt=Optional.empty();
        //System.out.println(opt.get());
    }

    @Test
    public void test3(){
        Optional<User> opt=Optional.ofNullable(null);
        /*if (opt.isPresent()){//判断Optional中是否有值
            System.out.println(opt.get());
        }*/

        /*User user = opt.orElse(new User(6, "张三", 34));
        System.out.println(user);*/

        User user2 = opt.orElseGet(()->new User(6, "张三", 34, User.Status.BUSY));
        System.out.println(user2);
    }

    @Test
    public void test4(){
        Optional<User> opt=Optional.ofNullable(new User(6, "张三", 34, User.Status.BUSY));
        //Optional<String> optionalS = opt.map((u) -> u.getName());
        //System.out.println(optionalS.get());

        Optional<String> optionalS = opt.flatMap((u) ->Optional.ofNullable(u.getName()) );
        System.out.println(optionalS.get());
    }
}
