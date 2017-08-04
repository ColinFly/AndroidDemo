// MusicPlayCallback.aidl
package com.colin.music.common;

// Declare any non-default types here with import statements
import com.colin.music.common.entity.MusicFile;
import java.util.List;
//服务端调用客户端的接口(客户端传接口给服务端)
interface MusicPlayCallback {
    //当前播放的音乐
   void playingMusic(in MusicFile musicFile);
   void onProgress(int progress);
   void onPrepared(int duration);

   void listAllFileMusic(in List<MusicFile> allFiles);

}
