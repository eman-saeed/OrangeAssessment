package com.example.nourhan.oragneassement.communication.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by EMAN on 09/05/2016.
 */

public class RetrofitObject {

    private static final String BASE_URL = "https://api.flickr.com/services/";

    private static Retrofit ourInstance = null;

    public static synchronized Retrofit getInstance() {

        Gson gson = new GsonBuilder().setLenient().create();
        if (ourInstance == null) {
            ourInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return ourInstance;
    }
}