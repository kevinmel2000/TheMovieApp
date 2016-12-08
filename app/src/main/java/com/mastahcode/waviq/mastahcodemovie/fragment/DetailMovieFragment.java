package com.mastahcode.waviq.mastahcodemovie.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mastahcode.waviq.mastahcodemovie.R;
import com.mastahcode.waviq.mastahcodemovie.adapters.MovieStationAdapter;
import com.mastahcode.waviq.mastahcodemovie.models.Movie;
import com.mastahcode.waviq.mastahcodemovie.models.MovieLists;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailMovieFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView titleView;
    ImageView posterView;
    TextView releaseView;
    TextView runtimeView;
    TextView rateView;
    TextView descriptionView;
    ImageView trailerImageView;
    TextView trailerTitleView;


    public DetailMovieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailMovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailMovieFragment newInstance(String param1, String param2) {
        DetailMovieFragment fragment = new DetailMovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //MovieLists movieLists = new MovieLists();

        Log.i("URL RESPONSE : ", MovieStationAdapter.getUrlMoveIdSend());

        View v = inflater.inflate(R.layout.fragment_detail_movie, container, false);

        titleView = (TextView) v.findViewById(R.id.titleView);
        posterView = (ImageView) v.findViewById(R.id.posterView);
        releaseView = (TextView) v.findViewById(R.id.releaseView);
        runtimeView = (TextView) v.findViewById(R.id.runtimeView);
        rateView = (TextView) v.findViewById(R.id.rateView);
        descriptionView = (TextView) v.findViewById(R.id.descriptionView);
        trailerImageView = (ImageView) v.findViewById(R.id.trailerImageView);
        trailerTitleView = (TextView) v.findViewById(R.id.trailerTitleView);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, MovieStationAdapter.getUrlMoveIdSend(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                final String URL_BASE_IMAGE = "http://image.tmdb.org/t/p/w185/";
                final String URL_YOUTUBE_TRAILER = "https://www.youtube.com/watch?v=";

                try {

                    String title = response.getString("title");
                    String poster = response.getString("poster_path");
                    String relase = response.getString("release_date");
                    Integer runtime = response.getInt("runtime");
                    String vote = response.getString("vote_average");
                    String description = response.getString("overview");

                    Movie movie = new Movie(title, poster, relase, runtime, vote, description);
                    titleView.setText(movie.getTitle());
                    Picasso.with(getContext()).load(URL_BASE_IMAGE + movie.getPosterPath()).into(posterView);
                    releaseView.setText(movie.getReleaseDate());
                    runtimeView.setText(String.valueOf(movie.getRuntime())+" Menit");
                    rateView.setText(movie.getVoteAverage());
                    descriptionView.setText(movie.getOverview());

                    JSONObject videos = response.getJSONObject("videos");
                    JSONArray results = videos.getJSONArray("results");
                    JSONObject object = results.getJSONObject(0);
                    String key = object.getString("key");

                    final Movie m = new Movie(key);

                    trailerTitleView.setText(
                            Html.fromHtml(
                                    "<a href=\"https://www.youtube.com/watch?v="+m.getTrailer()+"\">Play Trailer</a> "));
                    trailerTitleView.setMovementMethod(LinkMovementMethod.getInstance());


                    trailerImageView.setImageResource(R.drawable.play);
                    trailerImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.addCategory(Intent.CATEGORY_BROWSABLE);
                            intent.setData(Uri.parse("https://www.youtube.com/watch?v="+m.getTrailer()));
                            startActivity(intent);
                        }
                    });


                } catch (JSONException e) {
                    Log.i("Error", e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
        // Inflate the layout for this fragment
        return v;
    }

}
