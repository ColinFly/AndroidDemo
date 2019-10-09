package com.colin.demo.android_room_with_view;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static volatile WordRoomDatabase INSTANCE;

    static WordRoomDatabase getDataBase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDataBaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WordDAO wordDAO();


    private static RoomDatabase.Callback sRoomDataBaseCallback=new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private WordDAO mDAO;

        public PopulateDbAsync(WordRoomDatabase db) {
            this.mDAO =db.wordDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDAO.deleteAll();
            Word word=new Word("Hello");
            mDAO.insert(word);
            word=new Word("World");
            mDAO.insert(word);
            return null;
        }
    }


}
