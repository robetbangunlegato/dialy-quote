package com.robet.dialyquote.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
//    dibuat untuk menuju ke alamat API berada
    private static final String baseURL = "https://type.fit/api/";
    private static Retrofit retro;

    public static Retrofit connectRetrofit(){
        if (retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
