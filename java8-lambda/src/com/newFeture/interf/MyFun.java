package com.newFeture.interf;

/**
 * java8：接口中可以提供默认方法和静态方法
 *      1、"类优先"原则，一个子类同时继承和实现了有共同方法的父类和接口，在调用子类该方法的时候，
 *      以类优先原则调用，接口中同名的方法会被忽略。
 *      2、若一个类同时实现了具有相同方法名的两个接口，在调用子类该方法时，必须重写具体接口的该方法的实现
 */
public interface MyFun {
    default String getName(){
        return "哈哈";
    }

    public static void show(){
        System.out.println("接口中的静态方法");
    }
}
