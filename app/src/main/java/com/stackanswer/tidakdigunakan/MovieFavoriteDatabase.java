package com.stackanswer.tidakdigunakan;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.stackanswer.source.local.room.moviefavorite.MovieFavorite;

@Database(entities = {MovieFavorite.class}, version = 1, exportSchema = false)
public abstract class MovieFavoriteDatabase extends RoomDatabase {

    private static MovieFavoriteDatabase instance;
    public abstract MovieFavoriteDao movieDao();

    public static synchronized MovieFavoriteDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieFavoriteDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
//                    .addCallback(new Callback() {
//                        @Override
//                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                            add();
//                        }
//                    })
                    .build();
        }
        return instance;
    }

//    private static void add() {
//        Executors.newSingleThreadExecutor().execute(() -> {
//            final MoviePopular movieFavorite = DataDummy.generateDummyMovieFavoriteTunggal();
//            instance.movieDao().insert(movieFavorite);
//        });
//    }
}