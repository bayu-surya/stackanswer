package com.stackanswer.source.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.stackanswer.source.datasource.LocalMovieDataSource;
import com.stackanswer.source.local.room.moviefavorite.MovieFavorite;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieFavoriteRepository {

    private final ExecutorService executorService;
    private final LocalMovieDataSource localDataSource;
    private volatile static MovieFavoriteRepository INSTANCE = null;

    public MovieFavoriteRepository(LocalMovieDataSource localDataSource){
        executorService = Executors.newSingleThreadExecutor();
        this.localDataSource = localDataSource;
    }

    public static MovieFavoriteRepository getInstance(LocalMovieDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (MovieFavoriteRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieFavoriteRepository(localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<PagedList<MovieFavorite>> getAllMovie() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getAllMovie(), config).build();
    }

    public void insert(MovieFavorite movieFavorite){
        executorService.execute(() -> new InsertMovieAsyncTask(localDataSource).execute(movieFavorite));
    }

    public void update(MovieFavorite movieFavorite){
        executorService.execute(() -> new UpdateMovieAsyncTask(localDataSource).execute(movieFavorite));
    }

    public void delete(MovieFavorite movieFavorite){
        executorService.execute(() -> new DeleteMovieAsyncTask(localDataSource).execute(movieFavorite));
    }

    public void deleteAll(){
        executorService.execute(() -> new DeleteAllMovieAsyncTask(localDataSource).execute());
    }

    private static class InsertMovieAsyncTask extends AsyncTask<MovieFavorite, Void, Void> {
        private final LocalMovieDataSource dataSource;

        private InsertMovieAsyncTask(LocalMovieDataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        protected Void doInBackground(MovieFavorite... movieFavorites) {
            dataSource.insert(movieFavorites[0]);
            return null;
        }
    }

    private static class UpdateMovieAsyncTask extends AsyncTask<MovieFavorite, Void, Void> {
        private final LocalMovieDataSource dataSource;

        private UpdateMovieAsyncTask(LocalMovieDataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        protected Void doInBackground(MovieFavorite... movieFavorites) {
            dataSource.update(movieFavorites[0]);
            return null;
        }
    }

    private static class DeleteMovieAsyncTask extends AsyncTask<MovieFavorite, Void, Void> {
        private final LocalMovieDataSource dataSource;

        private DeleteMovieAsyncTask(LocalMovieDataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        protected Void doInBackground(MovieFavorite... movieFavorites) {
            dataSource.delete(movieFavorites[0]);
            return null;
        }
    }

    private static class DeleteAllMovieAsyncTask extends AsyncTask<Void, Void, Void> {
        private final LocalMovieDataSource dataSource;

        private DeleteAllMovieAsyncTask(LocalMovieDataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dataSource.deleteAllMovie();
            return null;
        }
    }
}
