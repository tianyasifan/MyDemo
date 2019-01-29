package com.txt.retrofit2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by txt on 2016/6/3.
 */
public class RetrofitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder() // 通过建造者方式来构建一个retrofit对象
                .baseUrl("") // 这里是网络请求地址
                .addConverterFactory(GsonConverterFactory.create()) //具体的数据转换器
                .build(); // 生成一个retrofit对象

        GitHubService gitHubService = retrofit.create(GitHubService.class);

        Call<List<Repo>> call = gitHubService.listRepos("user");

        // 执行异步
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
        // 或执行同步
        try {
            Response<List<Repo>> response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void test(){

        // 以GitHubService为例创建一个被代理者对象
        final GitHubService gitReal = new GitHubService() {
            @Override
            public Call<List<Repo>> listRepos(String user) {
                return null;
            }
        };
        GitHubService gitProxy = (GitHubService)Proxy.newProxyInstance(GitHubService.class.getClassLoader()
                , new Class<?>[] {GitHubService.class},
                new InvocationHandler() { // 只需要实现InvocationHandler里面的invoke方法
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 所有执行gitReal这个对象的方法都会调用invoke方法
                        // 我们可以在这个方法前执行一些自己定制的东西
                        method.invoke(gitReal,args); // 甚至可以不执行method，完全自由发挥（retrofit就是这么干的）
                        // 也可以在后面执行一些定制的
                        return null; // 如果返回的是method.invoke的结果的话，就是返回了Call<List<Repo>>
                    }
                });
    }
}
