package com.txt.javatest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tongxiaotao on 18-7-25.
 */
public class Reflection {
    public static void main(String[] args) {
        Class clazz = Student.class;
        try {
            // 操作属性
            System.out.println("操作属性-----------------------------");
            Object student =  clazz.newInstance(); // 用无参构造函数创建对象
            Field field = clazz.getDeclaredField("name"); // 根据属性名获得
            field.setAccessible(true);//使权限校验失效
            field.set(student, "woshishui"); // 对新创建的对象student的属性name设置值为“woshishui”
            System.out.println(field.get(student));// 打印这个值

            //操作构造函数
            System.out.println("操作构造函数--------------------------");
            Object student1 = clazz.getConstructor().newInstance(); // 实际使用的是clazz.newInstance
            Object student2 = clazz.getConstructor(String.class).newInstance("woshiwo"); // 用一个参数的构造函数创建实例

            System.out.println("操作方法------------------------------");
            Method method = clazz.getMethod("setName", String.class); // 通过方法名和方法的参数类型获得方法对象
            method.invoke(student,"lalala");// 执行这个方法，传入对象和方法参数值
            Method method1 = clazz.getMethod("print"); // 获得无参的方法
            method1.invoke(student);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    public static class Student{
        public Student(){
            System.out.println("创建无参构造函数");
        }

        public Student(String name){
            System.out.println("创建有参构造函数:" + name);
        }

        private String name;

        public void setName(String name){
            this.name = name;
            System.out.println("setName:" + name);
        }

        public void print(){
            System.out.println("方法打印");
        }
    }
}
