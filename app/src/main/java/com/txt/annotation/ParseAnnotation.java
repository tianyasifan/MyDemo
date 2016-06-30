package com.txt.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by txt on 2016/4/20.
 */
public class ParseAnnotation {
    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("com.txt.annotation.UserAnnotation");
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for(Annotation annotation:annotations){
            TestA testA = (TestA)annotation;
            System.out.println("class name="+clazz.getName()+
            "    Tname="+testA.name()+
            "    Tid="+testA.id()+
            "    Tgid="+testA.gid());
        }
    }

    public static void parseFieldAnnotation(){
        Field[] fields = UserAnnotation.class.getDeclaredFields();
        for(Field field : fields){
            boolean hasAnnotation = field.isAnnotationPresent(TestA.class);
            if(hasAnnotation){
                TestA testA = field.getAnnotation(TestA.class);
                System.out.println("field name="+field.getName()+
                        "    Tname="+testA.name()+
                        "    Tid="+testA.id()+
                        "    Tgid="+testA.gid());
            }
        }
    }
}
