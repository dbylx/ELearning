package com.elearing.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRequest {
    public static final String BASE_URL = "http://172.24.33.3:8080/elearn/";
    public static Retrofit retrofit;

    public static Retrofit getApiClient(){

        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public void request() {

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<List<Course>> call = request.getCourses();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<List<Course>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                // 步骤7：处理返回的数据结果
                System.out.println(response.body().size());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<List<Course>> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }
}

