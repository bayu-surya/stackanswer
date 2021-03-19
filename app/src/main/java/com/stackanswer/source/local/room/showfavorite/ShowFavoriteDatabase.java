package com.stackanswer.source.local.room.showfavorite;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ShowFavorite.class}, version = 1, exportSchema = false)
public abstract class ShowFavoriteDatabase extends RoomDatabase {

    private static ShowFavoriteDatabase instance;
    public abstract ShowFavoriteDao showDao();

    public static synchronized ShowFavoriteDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ShowFavoriteDatabase.class, "show_database")
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
//            final ShowFavorite showFavorite = DataDummy.generateDummyShowFavoriteTunggal();
//            instance.showDao().insert(showFavorite);
//        });
//    }
}
