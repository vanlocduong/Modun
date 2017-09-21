package com.paulduong.iloadmore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PaulDuong on 20/09/2017.
 */

public class Movie {
    private String title;
    public  Movie(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public  Movie(String title){
        this.title = title;
    }
    // creating 10 dummy content for list
    public static List<Movie> createMovies(int itemCount) {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Movie movie = new Movie("Movie " + (itemCount == 0 ?
                    (itemCount + 1 + i) : (itemCount + i)));
            movies.add(movie);
        }
        return movies;
    }

    // creating 10 dummy content for list
    public  static  List<Movie> CreateMovies(int itemCount){
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i <=10 ; i ++){
            Movie movie = new Movie("Movies "+(itemCount == 0 ?(itemCount + 1 + i):(itemCount + i)));
            movies.add(movie);
        }
        return  movies;
    }

}
