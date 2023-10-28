package com.example.callapi.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static String baseURL ="https://65373398bb226bb85dd2ec1a.mockapi.io";
    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if(retrofit ==  null){
            retrofit = new Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
