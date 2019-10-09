// MusicPlayControl.aidl
package com.colin.music.common;

// Declare any non-default types here with import statements
import com.colin.music.common.MusicPlayCallback;
import com.colin.music.common.entity.MusicFile;
//客户端调用服务端的接口
interface MusicPlayControl {
    void registerPlayCallback(MusicPlayCallback callback);
    void unregisterPlayCallback(MusicPlayCallback callback);

    void play(in MusicFile music);
    void previous();
    void next();
    void playOrPause();
    void setPlayProgress(int position);
}
