package com.mastahcode.waviq.mastahcodemovie.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mastahcode.waviq.mastahcodemovie.R;
import com.mastahcode.waviq.mastahcodemovie.adapters.MovieStationAdapter;
import com.mastahcode.waviq.mastahcodemovie.models.MovieLists;
import com.mastahcode.waviq.mastahcodemovie.services.DataService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieStationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieStationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_STATION_POPULAR = "station_popular";


    public static final int STATION_TYPE_POPULAR_MOVIE = 0;
    public static final int STATION_TYPE_KIDS = 1;
    public static final int STATION_TYPE_RATED = 2;


    final String URL_BASE = "https://api.themoviedb.org/3/";
    final String URL_BASE_IMAGE = "http://image.tmdb.org/t/p/w185/";
    final String URL_POPULAR_MOVIE = "discover/movie?sort_by=popularity.desc";
    final String URL_PUPULAR_KID_MOVIE = "discover/movie?certification_country=US&certification.lte=G&sort_by=popularity.desc";
    final String URL_MOST_DRAMA_MOVIE = "discover/movie?with_genres=18&sort_by=vote_average.desc&vote_count.gte=10";
    final String URL_SEARCH_ID = "movie/";
    final String URL_API_KEY = "&api_key=2bea38317c7da072ccff5b9ad2bcc5a2";

    private ArrayList<MovieLists> aMovieListses = new ArrayList<>();
    private MovieStationAdapter movieListAdapter;

    private int stationPopular;


    public MovieStationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MovieStationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieStationFragment newInstance(int stationPopular) {
        MovieStationFragment fragment = new MovieStationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATION_POPULAR,stationPopular);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stationPopular = getArguments().getInt(ARG_STATION_POPULAR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_movie_station, container, false);

        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recycler_movie);
        recyclerView.setHasFixedSize(true);


        if (stationPopular == STATION_TYPE_POPULAR_MOVIE){
            getPopularMovie();
        }else if (stationPopular == STATION_TYPE_KIDS){
            getKidsMovie();
        }else if (stationPopular == STATION_TYPE_RATED){
            getDramaMovie();

        }

        recyclerView.addItemDecoration(new HorizontalSpaceItemDecorator(30));
        recyclerView.setAdapter(movieListAdapter);

        //set posisi
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        return v;
    }

    private void getPopularMovie() {
        final String url_popular = URL_BASE + URL_POPULAR_MOVIE + URL_API_KEY;

        movieListAdapter = new MovieStationAdapter(aMovieListses);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_popular, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    for (int x = 0; x<10; x++){
                        MovieLists movieListsDetail = objectModelMovie(response, x);

                        aMovieListses.add(movieListsDetail);


                        movieListAdapter.notifyDataSetChanged();
                    }



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
    }

    @NonNull
    private MovieLists objectModelMovie(JSONObject response, int x) throws JSONException {
        JSONArray result = response.getJSONArray("results");
        JSONObject object = result.getJSONObject(x);

        String poster = object.getString("poster_path");
        String title = object.getString("title");

        String relaseDate = object.getString("release_date");
        //Integer runtime = object.getInt("runtime");
        String voteAvarage = object.getString("vote_average");
        String overview = object.getString("overview");
        String id = object.getString("id");


        //MovieLists movieLists = new MovieLists(poster, title);
        return new MovieLists(poster, title, relaseDate, voteAvarage, overview, id );
    }

    private void getKidsMovie() {
        final String url_popular = URL_BASE + URL_PUPULAR_KID_MOVIE + URL_API_KEY;

        movieListAdapter = new MovieStationAdapter(aMovieListses);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_popular, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    for (int x = 0; x<10; x++){
                        MovieLists movieListsDetail = objectModelMovie(response, x);

                        aMovieListses.add(movieListsDetail);


                        movieListAdapter.notifyDataSetChanged();
                    }



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
    }

    private void getDramaMovie() {
        final String url_popular = URL_BASE + URL_MOST_DRAMA_MOVIE + URL_API_KEY;

        movieListAdapter = new MovieStationAdapter(aMovieListses);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_popular, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    for (int x = 0; x<10; x++){
                        MovieLists movieListsDetail = objectModelMovie(response, x);

                        aMovieListses.add(movieListsDetail);


                        movieListAdapter.notifyDataSetChanged();
                    }



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
    }

}

class HorizontalSpaceItemDecorator extends RecyclerView.ItemDecoration {

    private final int spacer;

    public HorizontalSpaceItemDecorator(int spacer) {
        this.spacer = spacer;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //biar viewnya reccyleView eclipse
        outRect.right = spacer;

    }
}
