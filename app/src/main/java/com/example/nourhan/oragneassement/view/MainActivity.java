package com.example.nourhan.oragneassement.view;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nourhan.oragneassement.R;
import com.example.nourhan.oragneassement.adapters.RecyclerViewAdapter;
import com.example.nourhan.oragneassement.communication.pojos.Photo;
import com.example.nourhan.oragneassement.contracts.MainViewContract;
import com.example.nourhan.oragneassement.presenter.MainActivityPresenter;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainViewContract.MainView{

    private SearchView searchView;
    private String searchWord;
    private ProgressBar progressBar;

//    @BindView(R.id.recyler_view)
    private RecyclerView recyclerView;
    private TextView errorText;

    MainViewContract.MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        errorText = (TextView) findViewById(R.id.error_Text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mainPresenter = new MainActivityPresenter(this);
        initRecyclerView();
    }

    @Override
    public void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        //set the layout manager
        RecyclerView.LayoutManager layoutManager  = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void populateRecyclerView(ArrayList<Photo> imagesUrls) {
        //take object from the adapter
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,imagesUrls);
        //then set the adapter to the recyclerview
        recyclerView.setAdapter(recyclerViewAdapter);
        showProgressBar(false);
    }

    @Override
    public void showNoDataError(boolean showError) {
        if (showError){
            errorText.setText(R.string.error_message);
            recyclerView.setVisibility(View.GONE);
            errorText.setVisibility(View.VISIBLE);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            errorText.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNoInternetConnection() {
        errorText.setText(R.string.check_internet_connection);
        recyclerView.setVisibility(View.GONE);
        errorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar(boolean showProgressBar) {
        if (showProgressBar){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public MainActivity getContext() {
        return MainActivity.this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        final MenuItem menuItem = menu.findItem(R.id.action_search);
        if (menuItem != null){
            searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
            if (searchView!= null){

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        searchView.setIconified(true);
                        searchView.clearFocus();
                        showProgressBar(true);
                        mainPresenter.getAllImagesBySearch(query);
                        if (menuItem != null){
                            menuItem.collapseActionView();
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });

                searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean queryTextFocused) {
                        if(!queryTextFocused) {
                            menuItem.collapseActionView();
                            searchView.setQuery("", false);
                        }
                    }
                });
            }
        }
        return super.onCreateOptionsMenu(menu);
    }
}
