package com.newFeture.annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 重复注解和类型注解
 */
public class TestAnnotation {
    //checker framework
    private /*@NonNull*/ Object obj=null;

    @Test
    public void test1()throws Exception{
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method method = clazz.getMethod("show");
        //System.out.println(method);
        MyAnnotation[] myAnnotations =method.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation anno:
                myAnnotations) {
            System.out.println(anno.value());
        }
    }

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show(@MyAnnotation("abc") String str){

    }
}
