package com.example.nourhan.oragneassement.communication.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by NourHan on 8/13/2017.
 */

public class Photos {

    @SerializedName("page")
    private int page;
    @SerializedName("pages")
    private int pages;
    @SerializedName("perpage")
    private int prePage;
    @SerializedName("photo")
    private ArrayList<Photo> photos;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }
}
