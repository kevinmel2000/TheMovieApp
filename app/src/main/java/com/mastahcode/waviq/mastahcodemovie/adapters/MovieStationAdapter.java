package com.mastahcode.waviq.mastahcodemovie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mastahcode.waviq.mastahcodemovie.R;
import com.mastahcode.waviq.mastahcodemovie.activities.MainActivity;
import com.mastahcode.waviq.mastahcodemovie.fragment.DetailMovieFragment;
import com.mastahcode.waviq.mastahcodemovie.holder.MovieStationViewHolder;
import com.mastahcode.waviq.mastahcodemovie.models.MovieLists;

import java.util.ArrayList;

/**
 * Created by waviq on 4/12/2016.
 *
 */

public class MovieStationAdapter extends RecyclerView.Adapter<MovieStationViewHolder> {

    private ArrayList<MovieLists>aMovieLists;
    private Context context;
    private static String URL_MOVE_ID_SEND = null;
    private static  String TitleTest = null;

    public MovieStationAdapter(Context context) {
        this.context = context;
    }

    public MovieStationAdapter(ArrayList<MovieLists> aMovieLists) {
        this.aMovieLists = aMovieLists;
    }

    public static String getUrlMoveIdSend() {
        return URL_MOVE_ID_SEND;
    }

    public static void setUrlMoveIdSend(String urlMoveIdSend) {
        URL_MOVE_ID_SEND = urlMoveIdSend;
    }

    @Override
    public MovieStationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);

        return  new MovieStationViewHolder(card, parent.getContext());



    }

    @Override
    public void onBindViewHolder(final MovieStationViewHolder holder, final int position) {
        final MovieLists movieLists = aMovieLists.get(position);

        final String URL_BASE = "https://api.themoviedb.org/3/";
        final String URL_SEARCH_ID = "movie/";
        final String URL_API_KEY = "&api_key=2bea38317c7da072ccff5b9ad2bcc5a2";

        holder.updateUI(movieLists);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //load detail movie
                MainActivity.getMainActivity().loadDetailMovie(movieLists);

                String URL_MOVIE_BY_ID = URL_BASE + URL_SEARCH_ID +movieLists.getId() +"?" + URL_API_KEY + "&append_to_response=videos";
                setUrlMoveIdSend(URL_MOVIE_BY_ID);


                //View detail = LayoutInflater.from(context).inflate(R.layout.card_movie, parent, false);

                //titleDetail.setText("ergerg");
                Log.i("Response : ", getUrlMoveIdSend());
            }
        });
    }

    @Override
    public int getItemCount() {
        return aMovieLists.size();
    }

}
