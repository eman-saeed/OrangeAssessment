package com.example.nourhan.oragneassement.communication.model;


/**
 * Created by EMAN on 09/05/2016.
 * this class for creating retrofit object
 */
public class ServiceProvider {

    //just one method to create the retrofit service
    public static <T> T createRetrofitService(final Class<T> T){
        return RetrofitObject.getInstance().create(T);
    }

}
