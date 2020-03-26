package com.doctorn.notify;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Retrofit retrofit=null;



    public static  Retrofit getRetrofit(String url){

        if(retrofit==null){

            HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client=new OkHttpClient
                    .Builder().addInterceptor(interceptor)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120,TimeUnit.SECONDS)
                    .writeTimeout(120,TimeUnit.SECONDS)
                    .build();


            retrofit=new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
