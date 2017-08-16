package com.example.nourhan.oragneassement.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nourhan.oragneassement.R;
import com.example.nourhan.oragneassement.communication.pojos.Photo;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

/**
 * Created by eman on 8/13/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Photo> imagesUrls;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<Photo> imagesUrls){
        this.imagesUrls = imagesUrls;
        this.context = context;
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_view_cell,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Picasso.with(context).load(imagesUrls.get(position).getImageUrl()).placeholder(R.drawable.default_image).resize(150,150).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imagesUrls.size();
    }

    //************************************View holder***************************************
    public class ViewHolder extends RecyclerView.ViewHolder{

//        @BindView(R.id.search_image)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this,itemView);
            imageView = itemView.findViewById(R.id.search_image);
        }
    }
}
