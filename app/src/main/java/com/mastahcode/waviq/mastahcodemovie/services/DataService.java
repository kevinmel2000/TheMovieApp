package com.mastahcode.waviq.mastahcodemovie.services;

import com.mastahcode.waviq.mastahcodemovie.models.MovieLists;

import java.util.ArrayList;

/**
 * Created by waviq on 5/12/2016.
 */
public class DataService {
    private static DataService ourInstance = new DataService();


    public static DataService getInstance() {
        return ourInstance;
    }

    private DataService() {
    }

    public ArrayList<MovieLists>getPopularStation(){
        //download popular data from internet
        ArrayList<MovieLists>list = new ArrayList<>();
       /* list.add(new MovieLists("/gri0DDxsERr6B2sOR1fGLxLpSLx.jpg","Batman mandi d kali"));
        list.add(new MovieLists("/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg","Spriderman mandi d kali"));
        list.add(new MovieLists("/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg","Ninja Hatori mandi d kali"));
        list.add(new MovieLists("/nHXiMnWUAUba2LZ0dFkNDVdvJ1o.jpg","Ninja Hatori mandi d kali"));
        list.add(new MovieLists("/gri0DDxsERr6B2sOR1fGLxLpSLx.jpg","Batman mandi d kali"));
        list.add(new MovieLists("/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg","Spriderman mandi d kali"));
        list.add(new MovieLists("/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg","Ninja Hatori mandi d kali"));
        list.add(new MovieLists("/nHXiMnWUAUba2LZ0dFkNDVdvJ1o.jpg","Ninja Hatori mandi d kali"));
*/

        return list;
    }

    public ArrayList<MovieLists>getPopularKidsStation(){
        ArrayList<MovieLists>list = new ArrayList<>();
        return list;
    }

    public ArrayList<MovieLists>getPopularRatedStation(){
        ArrayList<MovieLists>list = new ArrayList<>();
        return list;
    }
}
