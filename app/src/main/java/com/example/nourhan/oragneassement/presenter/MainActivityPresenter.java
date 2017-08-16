package com.example.nourhan.oragneassement.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.nourhan.oragneassement.MyApplication;
import com.example.nourhan.oragneassement.caching.CacheManager;
import com.example.nourhan.oragneassement.communication.model.ServiceProvider;
import com.example.nourhan.oragneassement.communication.pojos.SearchResponse;
import com.example.nourhan.oragneassement.communication.services.SearchService;
import com.example.nourhan.oragneassement.contracts.MainViewContract;
import com.example.nourhan.oragneassement.view.MainActivity;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Created by NourHan on 8/13/2017.
 */

public class MainActivityPresenter implements MainViewContract.MainPresenter {

    private MainViewContract.MainView mainView;
    private Context context;

    private final String METHOS = "flickr.photos.search";
    private final String API_KEY = "39f62d8531b837a850c4708c723dee81";
    private final String FORMAT = "json";
    private final int NO_JSON_CALLBACK = 1;
    private final String EXTRAS = "url_o";

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mainView = mainActivity;
        context = mainActivity.getContext();
    }

    @Override
    public boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public void getAllImagesBySearch(final String searchWord) {

        SearchResponse searchResponse = CacheManager.getInstance(context).getSearchResultBySearchKey(searchWord);
        if (searchResponse != null) {
            mainView.populateRecyclerView(searchResponse.getPhotos().getPhotos());
        } else {
            if (checkInternetConnection()) {
                SearchService searchService = ServiceProvider.createRetrofitService(SearchService.class);
                searchService.getAllImages(METHOS, API_KEY, FORMAT, NO_JSON_CALLBACK, searchWord, EXTRAS)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<SearchResponse>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                            }

                            @Override
                            public void onNext(@NonNull SearchResponse searchResponse) {
                                if (searchResponse != null && searchResponse.getPhotos() != null) {
                                    CacheManager.getInstance(mainView.getContext()).addSearchKey(searchWord, searchResponse);
                                    mainView.populateRecyclerView(searchResponse.getPhotos().getPhotos());
                                    mainView.showNoDataError(false);
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                mainView.showProgressBar(false);
                                mainView.showNoDataError(true);
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            }else {
                mainView.showProgressBar(false);
                mainView.showNoInternetConnection();
            }
        }
    }
}
