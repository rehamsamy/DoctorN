package com.doctorn.utils;

import android.net.SSLCertificateSocketFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit=null;
    public static final String base_url="https://doctorn.app";

    public static RetrofitInterface getRetrofit(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

       HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
       interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


       OkHttpClient client=new OkHttpClient
               .Builder().addInterceptor(interceptor)
               .connectTimeout(120, TimeUnit.SECONDS)
               .readTimeout(120,TimeUnit.SECONDS)
               .writeTimeout(120,TimeUnit.SECONDS)
               .build();



//        try {
//            SSLContext context = SSLContext.getInstance("TLS");
//            context.init(null, tmf.getTrustManagers(), null);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }

        retrofit= new Retrofit.Builder().baseUrl(base_url)
               .client(client)
               .addConverterFactory(GsonConverterFactory.create(gson))
               .build();

       RetrofitInterface retrofitInterface=retrofit.create(RetrofitInterface.class);

       return retrofitInterface;
    }
}