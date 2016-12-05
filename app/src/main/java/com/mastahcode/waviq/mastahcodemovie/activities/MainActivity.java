package com.mastahcode.waviq.mastahcodemovie.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mastahcode.waviq.mastahcodemovie.R;

import com.mastahcode.waviq.mastahcodemovie.fragment.DetailMovieFragment;
import com.mastahcode.waviq.mastahcodemovie.fragment.MovieFragment;
import com.mastahcode.waviq.mastahcodemovie.models.Movie;
import com.mastahcode.waviq.mastahcodemovie.models.MovieLists;

public class MainActivity extends AppCompatActivity {

    private static MainActivity mainActivity;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        MainActivity.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.setMainActivity(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MovieFragment movieFragment = (MovieFragment)fragmentManager.findFragmentById(R.id.main_frame);

        if (movieFragment == null){
            movieFragment = MovieFragment.newInstance("serah","bebas");
            fragmentManager.beginTransaction().add(R.id.main_frame, movieFragment).commit();
        }
    }

    public void loadDetailMovie(MovieLists selectedMovie){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, new DetailMovieFragment())
                .addToBackStack(null).commit();
    }
}
