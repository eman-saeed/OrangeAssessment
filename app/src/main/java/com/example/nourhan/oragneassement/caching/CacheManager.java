package com.example.nourhan.oragneassement.caching;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nourhan.oragneassement.communication.pojos.SearchResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Eman on 8/16/2017.
 */

public class CacheManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String sharedPreferencesName = "SearchPreferences";

    private static CacheManager myInstance;
    private Context context;

    private CacheManager(Context context) {
        this.context = context;
    }

    public void addSearchKey(String searchKeyName, SearchResponse searchResponse) {
        sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String str = gson.toJson(searchResponse);
        editor.putString(searchKeyName.toLowerCase(), str);
        editor.commit();
    }

    public SearchResponse getSearchResultBySearchKey(String searchKey){
        SearchResponse searchResponse = null;

        sharedPreferences = context.getSharedPreferences(sharedPreferencesName,Context.MODE_PRIVATE);
        if (sharedPreferences.contains(searchKey.toLowerCase())){
            String searchResult = sharedPreferences.getString(searchKey.toLowerCase(),null);
            Gson gson = new Gson();
            searchResponse = gson.fromJson(searchResult,SearchResponse.class);
        }

        return searchResponse;
    }

    public static CacheManager getInstance(Context context) {
        if (myInstance == null){
            myInstance = new CacheManager(context);
        }
        return myInstance;
    }
}
