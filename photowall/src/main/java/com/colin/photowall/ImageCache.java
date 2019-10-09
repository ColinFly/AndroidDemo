package com.colin.photowall;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 图片缓存工具类(内存缓存)
 */
public class ImageCache {
    private static ImageCache mImageCache;

    public static ImageCache getInstance() {
        if (mImageCache == null) {
            mImageCache =new ImageCache();
        }
        return mImageCache;
    }

    private ImageCache() {
        initConfig();
    }


    private static LruCache<String, Bitmap> mMemoryCache;

    private void initConfig() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache=new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();//?
            }
        };
    }

    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

}
