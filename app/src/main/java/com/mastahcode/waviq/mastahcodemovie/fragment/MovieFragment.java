package com.mastahcode.waviq.mastahcodemovie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    final String URL_BASE = "https://api.themoviedb.org/3/";
    final String URL_BASE_IMAGE = "http://image.tmdb.org/t/p/w185/";
    final String URL_POPULAR_MOVIE = "discover/movie?sort_by=popularity.desc";
    final String URL_PUPULAR_KID_MOVIE = "discover/movie?certification_country=US&certification.lte=G&sort_by=popularity.desc";
    final String URL_MOST_RATED_MOVIE = "discover/movie/?certification_country=US&certification=R&sort_by=vote_average.desc";
    final String URL_SEARCH_ID = "movie/";
    final String URL_API_KEY = "&api_key=2bea38317c7da072ccff5b9ad2bcc5a2";

    private ArrayList<MovieLists> aMovieListses = new ArrayList<>();
    private MovieStationAdapter movieListAdapter;


    public MovieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieFragment newInstance(String param1, String param2) {
        MovieFragment fragment = new MovieFragment();
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

        final View v = inflater.inflate(R.layout.fragment_movie, container, false);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        MovieStationFragment movieStationFragment1;
        MovieStationFragment movieStationFragment2;
        MovieStationFragment movieStationFragment3;

        movieStationFragment1 = MovieStationFragment.newInstance(MovieStationFragment.STATION_TYPE_POPULAR_MOVIE);
        fm.beginTransaction().add(R.id.container_top_row, movieStationFragment1).commit();

        movieStationFragment2 = MovieStationFragment.newInstance(MovieStationFragment.STATION_TYPE_KIDS);
        fm.beginTransaction().add(R.id.container_midle_row, movieStationFragment2).commit();

        movieStationFragment3 = MovieStationFragment.newInstance(MovieStationFragment.STATION_TYPE_RATED);
        fm.beginTransaction().add(R.id.container_buttom_row, movieStationFragment3).commit();


        return v;
    }

}
