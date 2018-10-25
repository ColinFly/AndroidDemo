package com.colin.photowall;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 基于ScrollView改造成瀑布流图片容器
 */
public class ImageScrollView extends ScrollView implements ImageDownloadTask.ImageDownloadCallback {
    //每页要加载的图片个数
    private static final int PHOTO_SIZE = 15;
    //记录当前已加载到第几列
    private int mPage;
    //每一列的宽
    public static int mColumnWidth;
    //当前第一列的高
    private int mFirstColumnHeight;
    //当前第二列的高
    private int mSecondColumnHeight;
    //当前第三列的高
    private int mThirdColumnHeight;
    //是否已经执行过onLayout
    boolean isExcuteOnLayout;
    //图片缓存管理类
    private ImageLoader mImageLoader;
    //三列布局
    private LinearLayout mFirstLayout, mSecondLayout, mThirdLayout;
    //图片正在下载或等待下载的任务列表
    private static Set<ImageDownloadTask> tasks;

    //ScrollView直接子布局(这里是LinearLayout)
    private View mScrollViewLayout;
    //ScrollView直接子布局的高度
    private static int mScrollViewHeight;
    //记录上垂直方向上的滚动距离
    private static int lastScrollY;
    //记录界面上的所有图片，用以控制对图片的释放
    private List<ImageView> mImageViewList = new ArrayList<>();
    //在Handler中进行图片可见性的判断，以及加载更多图片操作


    private static final String TAG = "ImageScrollView";

    public ImageScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Log.i(TAG, "++init: ");
//        setOnTouchListener(this);
        tasks = new CopyOnWriteArraySet<>();
        Log.i(TAG, "--init: ");

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "onLayout: changed: " + changed + " l: " + l + " t " + t + " r " + r + " b " + b);
        super.onLayout(changed, l, t, r, b);
        if (!isExcuteOnLayout) {
            mScrollViewHeight = getHeight();
            mScrollViewLayout = getChildAt(0);
            mFirstLayout = findViewById(R.id.ll_first_column);
            mSecondLayout = findViewById(R.id.ll_second_column);
            mThirdLayout = findViewById(R.id.ll_third_column);
            mColumnWidth = mFirstLayout.getWidth();
            loadMoreImages();
            isExcuteOnLayout = true;
        }

    }

    public void loadMoreImages() {
        if (hasSDCard()) {
            int startIndex = mPage * PHOTO_SIZE;
            int endIndex = mPage * PHOTO_SIZE + PHOTO_SIZE;
            if (startIndex < ImagesUrl.imageUrls.length) {
                toast("正在加载");
                if (endIndex > ImagesUrl.imageUrls.length) {
                    endIndex = ImagesUrl.imageUrls.length;
                }
                for (int i = startIndex; i < endIndex; i++) {
                    ImageDownloadTask task = new ImageDownloadTask(this);
                    tasks.add(task);
                    task.execute(ImagesUrl.imageUrls[i]);
                }
                mPage++;
            } else {
                toast("没有更多图片了");
            }

        } else {
            toast("没有SD卡 ");
        }
    }

    private void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    @Override
    public void onDownloadFinish(Bitmap bitmap,String url) {
        Log.i(TAG, "onDownloadFinish: ");
        if (bitmap != null) {
            double ratio = bitmap.getWidth() / (mColumnWidth * 1.0);
            int scaledHeight = (int) (bitmap.getHeight() / ratio);
            addImage(bitmap, mColumnWidth, scaledHeight,url);
        }
    }

    private void addImage(Bitmap bitmap, int columnWidth, int scaledHeight, String url) {
        Log.i(TAG, "addImage: bitmap: "+ bitmap.toString());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(columnWidth, scaledHeight);
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(params);
        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setPadding(5, 5, 5, 5);
        imageView.setTag(R.string.image_url, url);
        findColumnToAdd(imageView, scaledHeight).addView(imageView);
        mImageViewList.add(imageView);
    }

    /**
     * 找到此时应该添加图片的一列。原则就是对三列的高度进行判断，当前高度最小的一列就是应该添加的一列。
     *
     * @param imageView
     * @param imageHeight
     * @return 应该添加图片的一列
     */
    private LinearLayout findColumnToAdd(ImageView imageView,int imageHeight) {
        if (mFirstColumnHeight <= mSecondColumnHeight) {
            if (mFirstColumnHeight <= mThirdColumnHeight) {
                imageView.setTag(R.string.border_top, mFirstColumnHeight);
                mFirstColumnHeight += imageHeight;
                imageView.setTag(R.string.border_bottom, mFirstColumnHeight);
                return mFirstLayout;
            }
            imageView.setTag(R.string.border_top, mThirdColumnHeight);
            mThirdColumnHeight += imageHeight;
            imageView.setTag(R.string.border_bottom, mThirdColumnHeight);
            return mThirdLayout;
        } else {
            if (mSecondColumnHeight <= mThirdColumnHeight) {
                imageView.setTag(R.string.border_top, mSecondColumnHeight);
                mSecondColumnHeight += imageHeight;
                imageView
                        .setTag(R.string.border_bottom, mSecondColumnHeight);
                return mSecondLayout;
            }
            imageView.setTag(R.string.border_top, mThirdColumnHeight);
            mThirdColumnHeight += imageHeight;
            imageView.setTag(R.string.border_bottom, mThirdColumnHeight);
            return mThirdLayout;
        }
    }

}
