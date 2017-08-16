package com.example.nourhan.oragneassement.communication.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by eman on 7/17/2017.
 */

public class SearchResponse{

    @SerializedName("photos")
    private Photos photos;

    @SerializedName("stat")
    private String state;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
