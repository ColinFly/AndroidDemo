package com.colin.demo.android_room_with_view;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "word")
    private String word;

    public Word(String word) {
        this.word = word;
    }

    //You need a constructor and a "getter" method for the data model class,
    // because that's how Room knows to instantiate your objects.
    public String getWord() {
        return word;
    }
}
