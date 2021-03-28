package com.stackanswer.tidakdigunakan;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.stackanswer.source.local.room.showfavorite.ShowFavorite;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShowFavoriteRepository {

    private final ExecutorService executorService;
    private final LocalShowDataSource localDataSource;
    private volatile static ShowFavoriteRepository INSTANCE = null;

    public ShowFavoriteRepository(LocalShowDataSource localDataSource){
        executorService = Executors.newSingleThreadExecutor();
        this.localDataSource = localDataSource;
    }

    public static ShowFavoriteRepository getInstance(LocalShowDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (ShowFavoriteRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ShowFavoriteRepository(localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<PagedList<ShowFavorite>> getAllShow() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getAllShow(), config).build();
    }

    public void insert(ShowFavorite showFavorite){
        executorService.execute(() -> new InsertShowAsyncTask(localDataSource).execute(showFavorite));
    }

    public void update(ShowFavorite showFavorite){
        executorService.execute(() -> new UpdateShowAsyncTask(localDataSource).execute(showFavorite));
    }

    public void delete(ShowFavorite showFavorite){
        executorService.execute(() -> new DeleteShowAsyncTask(localDataSource).execute(showFavorite));
    }

    public void deleteAll(){
        executorService.execute(() -> new DeleteAllShowAsyncTask(localDataSource).execute());
    }

    private static class InsertShowAsyncTask extends AsyncTask<ShowFavorite, Void, Void> {
        private final LocalShowDataSource dataSource;

        private InsertShowAsyncTask(LocalShowDataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        protected Void doInBackground(ShowFavorite... showFavorites) {
            dataSource.insert(showFavorites[0]);
            return null;
        }
    }

    private static class UpdateShowAsyncTask extends AsyncTask<ShowFavorite, Void, Void> {
        private final LocalShowDataSource dataSource;

        private UpdateShowAsyncTask(LocalShowDataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        protected Void doInBackground(ShowFavorite... showFavorites) {
            dataSource.update(showFavorites[0]);
            return null;
        }
    }

    private static class DeleteShowAsyncTask extends AsyncTask<ShowFavorite, Void, Void> {
        private final LocalShowDataSource dataSource;

        private DeleteShowAsyncTask(LocalShowDataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        protected Void doInBackground(ShowFavorite... showFavorites) {
            dataSource.delete(showFavorites[0]);
            return null;
        }
    }

    private static class DeleteAllShowAsyncTask extends AsyncTask<Void, Void, Void> {
        private final LocalShowDataSource dataSource;

        private DeleteAllShowAsyncTask(LocalShowDataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dataSource.deleteAllShow();
            return null;
        }
    }
}