package com.bawei.net;

import com.bawei.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *@Auther:姓名
 *@Date: 时间
 *@Description:功能
 * */public class RetrfitUtils {
    private static RetrfitUtils utils;
    private final Retrofit retrofit;

    private RetrfitUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static RetrfitUtils getUtils() {
        if (utils == null) {
            synchronized (RetrfitUtils.class) {
                if (utils == null) {
                    utils = new RetrfitUtils();
                }
            }
        }
        return utils;
    }

    /***
     * 动态代理
     */
    public <T> T createService(Class<T> cls) {
        return retrofit.create(cls);
    }
}
