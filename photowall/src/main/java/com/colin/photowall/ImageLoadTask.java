package com.colin.photowall;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.colin.photowall.ImageScrollView.tasks;

/**
 * 图片下载类
 */
public class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = "ImageLoadTask";
    private static final int mRequiredImageWidth = ImageScrollView.mColumnWidth;
    private String mImageUrl;


    public interface ImageLoadCallback {
        void onDownloadFinish(Bitmap bitmap, String url);
    }

    private ImageLoadCallback mImageLoadCallback;

    public ImageLoadTask(ImageLoadCallback downloadCallback) {
        this.mImageLoadCallback = downloadCallback;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {//起一个子线程去下载
        mImageUrl = strings[0];
        Log.i(TAG, "doInBackground: " + mImageUrl);
        Bitmap imageBitmap = ImageCache.getInstance().getBitmapFromMemoryCache(mImageUrl);
        //内存找不到-->本地找不到--网络下载
        if (imageBitmap == null) {
            imageBitmap = loadImage(mImageUrl);
        }
        return imageBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {//下载完毕后执行的方法
        Log.i(TAG, "onPostExecute: ");
        if (mImageLoadCallback != null) {
            mImageLoadCallback.onDownloadFinish(bitmap, mImageUrl);
            tasks.remove(this);
        }
    }


    private Bitmap loadImage(String imageUrl) {
        //先从本地找
        File imageFile = new File(getImagePath(imageUrl));
        Bitmap bitmap = null;
        //找不到就去网络下载
        if (!imageFile.exists()) {
            bitmap = downloadImage(imageUrl);
        } else {//本地存在也要加载到内存
            bitmap = BitmapUtils.decodeSampledBitmapFromResource(imageFile.getPath(), mRequiredImageWidth);
            if (bitmap != null) {
                ImageCache.getInstance().addBitmapToMemoryCache(imageUrl, bitmap);
                return bitmap;
            }
        }

        return bitmap;
    }

    private Bitmap downloadImage(String imageUrl) {
        Log.i(TAG, "downloadImage: " + imageUrl);
        HttpURLConnection con = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        File imageFile = null;
        try {
            URL url = new URL(imageUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5 * 1000);
            con.setReadTimeout(15 * 1000);
            con.setDoInput(true);
            con.setDoOutput(true);
            bis = new BufferedInputStream(con.getInputStream());
            imageFile = new File(getImagePath(imageUrl));
            fos = new FileOutputStream(imageFile);
            bos = new BufferedOutputStream(fos);
            byte[] b = new byte[1024];
            int length;
            while ((length = bis.read(b)) != -1) {
                bos.write(b, 0, length);
                bos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (con != null) {
                    con.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (imageFile != null) {
            return saveToCache(imageFile, imageUrl);

        }
        return null;
    }

    /**
     * @param imageFile 保存压缩后的图片到内存
     * @param imageUrl
     */
    private Bitmap saveToCache(File imageFile, String imageUrl) {
        Log.i(TAG, "saveToCache: ");
        Bitmap bitmap = BitmapUtils.decodeSampledBitmapFromResource(imageFile.getPath(), mRequiredImageWidth);
        if (bitmap != null) {
            ImageCache.getInstance().addBitmapToMemoryCache(imageUrl, bitmap);
            return bitmap;
        }
        return null;
    }

    /**
     * @return 获取图片的本地存储路径
     */
    private String getImagePath(String imageUrl) {
        int lastSlashIndex = imageUrl.lastIndexOf("/");
        String imageName = imageUrl.substring(lastSlashIndex + 1);
        String imageDir = Environment.getExternalStorageDirectory()
                .getPath() + "/PhotoWallFalls/";
        File file = new File(imageDir);
        if (!file.exists()) {
            file.mkdir();
        }
        String imagePath = imageDir + imageName;
        Log.i(TAG, "getImagePath: " + imagePath);
        return imagePath;
    }
}
