package com.colin.music.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by colin on 17-8-3.
 */

public class MusicFile implements Parcelable {
    /**
     * ID
     */
    private int id;
    /**
     * 文件名
     */
    private String name;

    private String title;
    private String album;
    private String artist;
    private long duration;
    private long size;
    //文件路径
    private String data;
    /**
     * 是否为喜好曲目
     */
    private boolean like = false;

    private int tag = -1;

    private int albumId = -1;

    private String pinyin;

    private String sortKey;

    protected MusicFile(Parcel in) {
        id = in.readInt();
        name = in.readString();
        title = in.readString();
        album = in.readString();
        artist = in.readString();
        duration = in.readLong();
        size = in.readLong();
        data = in.readString();
        like = in.readByte() != 0;
        tag = in.readInt();
        albumId = in.readInt();
        pinyin = in.readString();
        sortKey = in.readString();
    }

    public static final Creator<MusicFile> CREATOR = new Creator<MusicFile>() {
        @Override
        public MusicFile createFromParcel(Parcel in) {
            return new MusicFile(in);
        }

        @Override
        public MusicFile[] newArray(int size) {
            return new MusicFile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(title);
        dest.writeString(album);
        dest.writeString(artist);
        dest.writeLong(duration);
        dest.writeLong(size);
        dest.writeString(data);
        dest.writeByte((byte) (like ? 1 : 0));
        dest.writeInt(tag);
        dest.writeInt(albumId);
        dest.writeString(pinyin);
        dest.writeString(sortKey);
    }
}
