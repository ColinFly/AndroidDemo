package com.colin.music.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by colin on 17-8-3.
 */

public class MusicFolder implements Parcelable {
    public List<MusicFile> data;
    private String folder;
    private String pinyin;
    private int sortKey;

    protected MusicFolder(Parcel in) {
        data = in.createTypedArrayList(MusicFile.CREATOR);
        folder = in.readString();
        pinyin = in.readString();
        sortKey = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
        dest.writeString(folder);
        dest.writeString(pinyin);
        dest.writeInt(sortKey);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MusicFolder> CREATOR = new Creator<MusicFolder>() {
        @Override
        public MusicFolder createFromParcel(Parcel in) {
            return new MusicFolder(in);
        }

        @Override
        public MusicFolder[] newArray(int size) {
            return new MusicFolder[size];
        }
    };
}
