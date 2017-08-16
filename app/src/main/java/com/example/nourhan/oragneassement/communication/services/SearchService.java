package com.example.nourhan.oragneassement.communication.services;


import com.example.nourhan.oragneassement.communication.pojos.SearchResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Eman on 13/8/2017.
 */

public interface SearchService {

    @GET("rest/")
    Observable<SearchResponse> getAllImages(@Query("method") String method,@Query("api_key") String apiKey,
                                            @Query("format") String format,@Query("nojsoncallback") int noJsonCallBack,
                                            @Query("text") String searchWord,@Query("extras") String urlSupport);

    @GET(" ")
    Observable<SearchResponse> getAllImages();
}
