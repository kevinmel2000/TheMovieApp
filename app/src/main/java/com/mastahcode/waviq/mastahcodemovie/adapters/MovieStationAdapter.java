package com.mastahcode.waviq.mastahcodemovie.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mastahcode.waviq.mastahcodemovie.R;
import com.mastahcode.waviq.mastahcodemovie.activities.MainActivity;
import com.mastahcode.waviq.mastahcodemovie.holder.MovieStationViewHolder;
import com.mastahcode.waviq.mastahcodemovie.models.MovieLists;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by waviq on 4/12/2016.
 *
 */

public class MovieStationAdapter extends RecyclerView.Adapter<MovieStationViewHolder> {

    private ArrayList<MovieLists>aMovieLists;
    private Context context;

    public MovieStationAdapter(Context context) {
        this.context = context;
    }

    public MovieStationAdapter(ArrayList<MovieLists> aMovieLists) {
        this.aMovieLists = aMovieLists;
    }

    @Override
    public MovieStationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);

        return  new MovieStationViewHolder(card, parent.getContext());



    }

    @Override
    public void onBindViewHolder(final MovieStationViewHolder holder, final int position) {
        final MovieLists movieLists = aMovieLists.get(position);
        holder.updateUI(movieLists);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //load detail movie
                MainActivity.getMainActivity().loadDetailMovie(movieLists);

                //View detail = LayoutInflater.from(context).inflate(R.layout.card_movie, parent, false);


                TextView titleDetail = (TextView)view.findViewById(R.id.titleDetail);
                //titleDetail.setText("ergerg");
                Log.i("Response : ",movieLists.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return aMovieLists.size();
    }

}
