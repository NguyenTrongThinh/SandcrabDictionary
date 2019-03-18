package com.solo.sandcrabdictionary.servers;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DictionaryRetrofitBase {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String base_url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
