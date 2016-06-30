package com.txt.javatest;

import android.util.SparseArray;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by txt on 2016/6/27.
 */
public class Test {
    public static int noException(){
        int i=10;
        try{
            return --i;//①先执行了--i语句，i变为9，但是并不返回结果
        }catch(Exception e){
            return --i;
        }finally{
            return --i;//②接着执行了这里的--i语句，并且马上返回结果，函数退出。最终结果为8；
        }
    }

    public static int noException1(){
        int i=10;
        try{
            return --i;//①先执行了--i语句，i变为9（该变量被保存在返回栈中，一个i的副本），但是并不返回结果。
            // ③等待finally{}执行完后，返回当前i的副本的值，即还是返回9；
        }catch(Exception e){
            return --i;
        }finally{
            --i;//②接着执行了这里的--i语句；
        }
    }

    public static int withException(){
        int i=10;
        try{
            i = i/0;//①这里会抛出一个异常，跳过return，马上执行catch中的语句；
            return --i;
        }catch(Exception e){
            return --i;//②执行完--i后，此时i为9（虽然被保存在了返回栈中，但是没有机会执行这个返回值了），接着执行finally{}
        }finally{
            return --i;//③执行--i，此时i为8，并且马上返回了该结果，最终该方法返回结果为8
        }
    }

    public static int withException1(){
        int i=10;
        try{
            i = i/0;//①这里会抛出一个异常，跳过return，马上执行catch中的语句；
            return --i;
        }catch(Exception e){
            return --i;//②执行完--i后，此时i为9（该变量保存在一个返回栈中，是个副本），接着执行finally{}
            //④等待finally{}执行完后，返回当前i的副本的值，即还是返回9；
        }finally{
            --i;//③执行--i，此时i为8
        }
    }

    public static String noExceptionString(){
        String s = "abc";
        try{
            s = s+"d";
            return s;
        }catch (Exception e){
            return s+"e";
        }finally {
            s = s+"f";//这句话被编译后 s =  new StringBuilder().append(s).append("f");这里重新创建了一个对象把这个对象赋值给s
        }
    }

    public static HashMap noExceptionMap(){
        HashMap s = new HashMap();
        try{
            s.put("abc","try");
            return s;
        }catch (Exception e){
            s.put("abce","catch");
            return s;
        }finally {
            s.put("abcf","fina");
        }
    }

    public static void print(){
        HashMap map = Test.noExceptionMap();
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        for(;iterator.hasNext();){
            String key = (String)iterator.next();
            String value = (String) map.get(key);
            System.out.println("key:"+key+"===value:"+value);
        }

        Set set1 = map.entrySet();
        Iterator iterator1 = set1.iterator();
        for(;iterator1.hasNext();){
            Map.Entry entry = (Map.Entry) iterator1.next();
            System.out.println("key:"+entry.getKey()+"===value:"+entry.getValue());
        }
    }

    public static void sparseArrayTest(SparseArray sparseArray){
        for(int i=0;i<sparseArray.size();i++){
            int key = sparseArray.keyAt(i);
            Object value = sparseArray.get(key);
        }
    }
}
