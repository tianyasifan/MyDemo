package com.share.meizu.newsfeedapi;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by tongxiaotao on 18-3-6.
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T>{

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        // 解析code
        JsonPrimitive jsonPrimitive = jsonObject.getAsJsonPrimitive("ret");
        int ret = 0;
        if (jsonPrimitive != null) {
            ret = jsonPrimitive.getAsInt();
        }
        if (ret != 0 ) { //获取数据不成功
            // 抛出异常，在异常信息中处理其他情况
            throw new DataResultException(ret);
        }

        JsonReader jsonReader = new JsonReader(new StringReader(json));
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }

    /*@Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        // 解析code
        JsonPrimitive jsonPrimitive = jsonObject.getAsJsonPrimitive("ret");
        int ret = 0;
        if (jsonPrimitive != null) {
            ret = jsonPrimitive.getAsInt();
        }

        T t = null;
        try {
            // 通过反射获取泛型的实例对象
            Class<T> clazz = (Class<T>) type;
            t = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ret == 0 ) { //获取数据成功
            // 按标准格式解析
            return gson.fromJson(json, type);
        } else {
            // 抛出异常，在异常信息中处理其他情况
            throw new DataResultException(baseBean.getMsg(),baseBean.getCode());
        }

        return t;
    }*/
}
