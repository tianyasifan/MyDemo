package com.txt.mydemo;

import android.app.Activity;
import android.os.Bundle;

import com.txt.annotation.MyAnnotation;
import com.txt.annotation.ParseAnnotation;
import com.txt.annotation.UserAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by txt on 2016/4/19.
 */
public class AnnotationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testReflect();

        try {
            ParseAnnotation.parseTypeAnnotation();
            ParseAnnotation.parseFieldAnnotation();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void testReflect(){
        Class clazz = UserAnnotation.class;
        Method[] methods = clazz.getDeclaredMethods();
        if(methods==null)
            return;
        StringBuilder sb = new StringBuilder();
        for(Method method : methods){
            sb.append(Modifier.toString(method.getModifiers())).append(" ");
            sb.append(method.getReturnType()).append(" ");
            sb.append(method.getName()).append("(");
            Class[] parameters = method.getParameterTypes();
            if(parameters!=null){
                for(int i=0;i<parameters.length;i++){
                    Class paramClz = parameters[i];
                    sb.append(paramClz.getSimpleName());
                    if(i<parameters.length-1) sb.append(",");
                }
            }
            sb.append(")\n\n");
        }
        System.out.println(sb.toString());
    }
}
