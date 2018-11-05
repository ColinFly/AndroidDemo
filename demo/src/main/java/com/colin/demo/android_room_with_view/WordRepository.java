package com.colin.demo.android_room_with_view;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {
    private WordDAO mWordDAO;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDataBase(application);
        mWordDAO = db.wordDAO();
        mAllWords = mWordDAO.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new insertAsyncTask(mWordDAO).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDAO mWordDAO;

        public insertAsyncTask(WordDAO mWordDAO) {
            this.mWordDAO = mWordDAO;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mWordDAO.insert(words[0]);
            return null;
        }

    }
}
