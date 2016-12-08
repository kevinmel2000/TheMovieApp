package com.mastahcode.waviq.mastahcodemovie.holder;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mastahcode.waviq.mastahcodemovie.R;
import com.mastahcode.waviq.mastahcodemovie.models.MovieLists;
import com.squareup.picasso.Picasso;

import java.net.URI;

/**
 * Created by waviq on 4/12/2016.
 *
 */

public class MovieStationViewHolder extends RecyclerView.ViewHolder {

    private ImageView poster;
    private TextView title;
    private Context context;

    private TextView titleDetail;


    public MovieStationViewHolder(View itemView, Context context) {
        super(itemView);

        this.context = context;
        this.poster = (ImageView)itemView.findViewById(R.id.movie_card);
        this.title = (TextView)itemView.findViewById(R.id.title_card);



    }

    public void updateUI(MovieLists movieLists){

        final String URL_IMAGE = "http://image.tmdb.org/t/p/w185"+movieLists.getPosterPath();

        Picasso.with(context).load(URL_IMAGE).into(poster);
        title.setText(movieLists.getTitle());
    }

    public void updateUiDetail(MovieLists movieListsDetail){

        //titleDetail.setText(movieListsDetail.getTitle());
        Log.i("Title : ",movieListsDetail.getTitle());
    }






}
