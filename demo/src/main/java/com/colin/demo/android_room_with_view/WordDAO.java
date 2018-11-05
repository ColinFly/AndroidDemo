package com.colin.demo.android_room_with_view;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDAO {
    @Insert
    void insert(Word word);


    @Query("DELETE FROM word_table")
    void deleteAll();

//    @Query("SELECT * FROM word_table ORDER BY word ASC")
//    List<Word> getAllWords();

//    LiveData, a lifecycle library class for data observation, solves this problem.
// Use a return value of type LiveData in your method description,
// and Room generates all necessary code to update the LiveData when the database is updated
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
}
