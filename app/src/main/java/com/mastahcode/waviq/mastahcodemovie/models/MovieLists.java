package com.mastahcode.waviq.mastahcodemovie.models;

/**
 * Created by waviq on 4/12/2016.
 */

public class MovieLists {

    private String posterPath;
    private String title;
    private String releaseDate;
    //private int runtime;
    private String voteAverage;
    private String overview;
    private String id;

    public MovieLists(String posterPath, String title) {
        this.posterPath = posterPath;
        this.title = title;
    }

    public MovieLists(String posterPath, String title, String releaseDate,  String voteAverage, String overview, String id) {
        this.posterPath = posterPath;
        this.title = title;
        this.releaseDate = releaseDate;
        //this.runtime = runtime;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.id = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    /*public int getRuntime() {
        return runtime;
    }*/

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }
}
