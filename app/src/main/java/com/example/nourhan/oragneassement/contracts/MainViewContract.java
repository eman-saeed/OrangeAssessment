package com.example.nourhan.oragneassement.contracts;

import com.example.nourhan.oragneassement.communication.pojos.Photo;
import com.example.nourhan.oragneassement.communication.pojos.SearchResponse;
import com.example.nourhan.oragneassement.view.MainActivity;

import java.util.ArrayList;

import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.internal.Context;

/**
 * Created by NourHan on 8/13/2017.
 */

public interface MainViewContract {

    interface MainView{
        void initRecyclerView();
        void populateRecyclerView(ArrayList<Photo> imagesUrls);
        void showNoDataError(boolean showError);
        void showNoInternetConnection();
        void showProgressBar(boolean showProgressBar);
        MainActivity getContext();
    }

    interface MainPresenter{
        boolean checkInternetConnection();
        void getAllImagesBySearch(String searchWord);
    }
}
