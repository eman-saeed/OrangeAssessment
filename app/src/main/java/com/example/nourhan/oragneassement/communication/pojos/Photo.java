package com.example.nourhan.oragneassement.communication.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Created by NourHan on 8/13/2017.
 */

public class Photo {
    @SerializedName("url_o")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
