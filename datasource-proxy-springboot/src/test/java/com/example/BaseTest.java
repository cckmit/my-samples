package com.example;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-25
 */
public class BaseTest {

    private static final String CLASS_NAME = "com.example.UseMyAnnotation2";

    @Test
    public void test1() throws Exception{
        Class<?> c = Class.forName(CLASS_NAME);

        //变量
        Field[] fs = c.getDeclaredFields();
        for(Field f:fs){
            if(f.isAnnotationPresent(MyAnnotation2.class)){
                System.out.println("有@MyAnnotation注释的变量："+f.getName());
            }
        }

        //方法
        Method[] ms = c.getDeclaredMethods();
        for(Method m:ms){
            if(m.isAnnotationPresent(MyAnnotation2.class)){
                System.out.println("有@MyAnnotation注释的方法："+m.getName());
            }
        }
    }

}

@Retention(RetentionPolicy.RUNTIME)//记录在.class文件中，并且在运行时保留"注释"
@Target({ElementType.FIELD,ElementType.METHOD})//指定注解只能放在"变量"和"方法"上
 @interface MyAnnotation2 {
    public String value() default "默认值...";
}

 class UseMyAnnotation2 {
    @SuppressWarnings("unused")
    @MyAnnotation2
    private int num = 10;

    @SuppressWarnings("unused")
    private String str = "hncu";

    @MyAnnotation2("aaa...")
    public void a(){
    }

    public void b(){
    }

    @MyAnnotation2("ccc...")
    public void c(){
    }
}
