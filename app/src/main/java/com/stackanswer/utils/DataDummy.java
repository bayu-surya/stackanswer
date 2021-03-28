package com.stackanswer.utils;

import com.stackanswer.model.Movie;
import com.stackanswer.model.Show;
import com.stackanswer.source.local.room.moviefavorite.MovieFavorite;
import com.stackanswer.source.local.room.showfavorite.ShowFavorite;

import java.util.ArrayList;
import java.util.List;

public class DataDummy {

    public static ArrayList<Movie> generateDummyMovie() {

        ArrayList<Movie> movieArrayList = new ArrayList<>();

        for (int i=0;i<5;i++) {
            Movie movie = new Movie();
            movie.setOverview("detail");
            movie.setOriginalLanguage("en");
            movie.setOriginalTitle("judul");
            movie.setVideo(true);
            movie.setTitle("judul");
            movie.setPosterPath("poster");
            movie.setBackdropPath("backdrop");
            movie.setReleaseDate("rilis");
            movie.setPopularity(1);
            movie.setVoteCount(1);
            movie.setId(i);
            movie.setVoteAverage(1);
            movie.setAdult(true);
            List<String> strings =new ArrayList<>();
            strings.add("genre");
            movie.setGenreIds(strings);
            movieArrayList.add(movie);
        }
        return movieArrayList;
    }

    public static ArrayList<Show> generateDummyShow() {

        ArrayList<Show> showArrayList = new ArrayList<>();

        for (int i=0;i<5;i++) {
            Show show = new Show();
            show.setOverview("detail");
            show.setOriginalLanguage("en");
            show.setName("judul");
            show.setOriginalName("nama");
            show.setPosterPath("poster");
            show.setBackdropPath("backdrop");
            show.setFirstAirDate("rilis");
            show.setPopularity(1);
            show.setVoteCount(1);
            show.setId(i);
            show.setVoteAverage(1);
            List<String> strings =new ArrayList<>();
            strings.add("genre");
            show.setOriginCountry(strings);
            show.setGenreIds(strings);
            showArrayList.add(show);
        }
        return showArrayList;
    }

    public static ArrayList<MovieFavorite> generateDummyMovieFavorite() {

        ArrayList<MovieFavorite> movieArrayList = new ArrayList<>();

        for (int i=0;i<5;i++) {
            MovieFavorite movie = new MovieFavorite();
            movie.setOverview("detail");
            movie.setOriginalLanguage("en");
            movie.setOriginalTitle("judul");
            movie.setVideo(true);
            movie.setTitle("judul");
            movie.setPosterPath("poster");
            movie.setBackdropPath("backdrop");
            movie.setReleaseDate("rilis");
            movie.setPopularity(1);
            movie.setVoteCount(1);
            movie.setId(i);
            movie.setVoteAverage(1);
            movie.setAdult(true);
            List<String> strings =new ArrayList<>();
            strings.add("genre");
            movie.setGenreIds(strings.toString());
            movieArrayList.add(movie);
        }
        return movieArrayList;
    }

    public static ArrayList<ShowFavorite> generateDummyShowFavorite() {

        ArrayList<ShowFavorite> showArrayList = new ArrayList<>();

        for (int i=0;i<5;i++) {
            ShowFavorite show = new ShowFavorite();
            show.setOverview("detail");
            show.setOriginalLanguage("en");
            show.setName("judul");
            show.setOriginalName("nama");
            show.setPosterPath("poster");
            show.setBackdropPath("backdrop");
            show.setFirstAirDate("rilis");
            show.setPopularity(1);
            show.setVoteCount(1);
            show.setId(i);
            show.setVoteAverage(1);
            List<String> strings =new ArrayList<>();
            strings.add("genre");
            show.setOriginCountry(strings.toString());
            show.setGenreIds(strings.toString());
            showArrayList.add(show);
        }
        return showArrayList;
    }

    public static MovieFavorite generateDummyMovieFavoriteTunggal(){

        MovieFavorite films=new MovieFavorite();
        films.setTitle("Wonder Woman 1984");
        films.setVoteAverage(6.9);
        films.setOriginalLanguage("en");
        films.setReleaseDate("2020-12-16");
        List<String> strings = new ArrayList<>();
        strings.add("14");
        strings.add("28");
        strings.add("12");
        films.setGenreIds(strings.toString());
        films.setAdult(false);
        films.setOriginalTitle("Wonder Woman 1984");
        films.setPopularity(2421.374);
        films.setVoteCount(3799);
        films.setVideo(false);
        films.setPosterPath("/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg");
        films.setBackdropPath("/srYya1ZlI97Au4jUYAktDe3avyA.jpg");
        films.setId(464052);
        films.setOverview("Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.");

        return films;
    }

    public static ShowFavorite generateDummyShowFavoriteTunggal(){

        ShowFavorite films=new ShowFavorite();
        films.setName("WandaVision");
        films.setVoteAverage(8.5);
        films.setOriginalLanguage("en");
        films.setFirstAirDate("2021-01-15");
        List<String> strings = new ArrayList<>();
        strings.add("10765");
        strings.add("9648");
        strings.add("18");
        films.setGenreIds(strings.toString());
        List<String> strings2 = new ArrayList<>();
        strings2.add("US");
        films.setOriginCountry(strings2.toString());
        films.setPopularity(4374.956);
        films.setVoteCount(5507);
        films.setOriginalName("WandaVision");
        films.setPosterPath("/glKDfE6btIRcVB5zrjspRIs4r52.jpg");
        films.setBackdropPath("/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg");
        films.setId(85271);
        films.setOverview("Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.");

        return films;
    }

    public static Movie generateDummyMovieTunggal(){

        Movie films=new Movie();
        films.setTitle("Wonder Woman 1984");
        films.setVoteAverage(6.9);
        films.setOriginalLanguage("en");
        films.setReleaseDate("2020-12-16");
        List<String> strings = new ArrayList<>();
        strings.add("14");
        strings.add("28");
        strings.add("12");
        films.setGenreIds(strings);
        films.setAdult(false);
        films.setOriginalTitle("Wonder Woman 1984");
        films.setPopularity(2421.374);
        films.setVoteCount(3799);
        films.setVideo(false);
        films.setPosterPath("/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg");
        films.setBackdropPath("/srYya1ZlI97Au4jUYAktDe3avyA.jpg");
        films.setId(464052);
        films.setOverview("Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.");

        return films;
    }

    public static Show generateDummyShowTunggal(){

        Show films=new Show();
        films.setName("WandaVision");
        films.setVoteAverage(8.5);
        films.setOriginalLanguage("en");
        films.setFirstAirDate("2021-01-15");
        List<String> strings = new ArrayList<>();
        strings.add("10765");
        strings.add("9648");
        strings.add("18");
        films.setGenreIds(strings);
        List<String> strings2 = new ArrayList<>();
        strings2.add("US");
        films.setOriginCountry(strings2);
        films.setPopularity(4374.956);
        films.setVoteCount(5507);
        films.setOriginalName("WandaVision");
        films.setPosterPath("/glKDfE6btIRcVB5zrjspRIs4r52.jpg");
        films.setBackdropPath("/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg");
        films.setId(85271);
        films.setOverview("Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.");

        return films;
    }

    public static MovieFavorite generateDummyMovieFavoriteTunggal2(){

        MovieFavorite films=new MovieFavorite();
        films.setTitle("Wonder Woman 1984");
        films.setVoteAverage(6.9);
        films.setOriginalLanguage("English");
        films.setReleaseDate("2020-12-16");
        films.setGenreIds("[Fantasy, Action, Adventure]");
        films.setAdult(false);
        films.setOriginalTitle("Wonder Woman 1984");
        films.setPopularity(2421.374);
        films.setVoteCount(3799);
        films.setVideo(false);
        films.setPosterPath("/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg");
        films.setBackdropPath("/srYya1ZlI97Au4jUYAktDe3avyA.jpg");
        films.setId(464052);
        films.setOverview("Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.");

        return films;
    }

    public static ShowFavorite generateDummyShowFavoriteTunggal2(){

        ShowFavorite films=new ShowFavorite();
        films.setName("WandaVision");
        films.setVoteAverage(8.5);
        films.setOriginalLanguage("English");
        films.setFirstAirDate("2021-01-15");
        films.setGenreIds("[Sci-Fi & Fantasy, Mystery, Drama]");
        films.setOriginCountry("[US]");
        films.setPopularity(4374.956);
        films.setVoteCount(5507);
        films.setOriginalName("WandaVision");
        films.setPosterPath("/d0d0gI46dadUPwF4t5XluXR96eA.jpg");
        films.setBackdropPath("/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg");
        films.setId(85271);
        films.setOverview("Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.");

        return films;
    }

}
