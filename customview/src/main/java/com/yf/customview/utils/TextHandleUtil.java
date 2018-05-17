package com.yf.customview.utils;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import com.yf.customview.R;

import static android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE;

/**
 * Created by colin on 18-5-11.
 */
public class TextHandleUtil {
    private static TextHandleUtil ourInstance = new TextHandleUtil();

    public static TextHandleUtil getInstance() {

        return ourInstance;
    }

    private TextHandleUtil() {
    }

    private ForegroundColorSpan colorGray, colorGrayCopy;


    void init(Context context) {
        colorGray = new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent));
        colorGrayCopy = new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent));
    }

    //剩余..米/剩余..公里
    CharSequence getRemainDisTxt(String txt) {
        SpannableStringBuilder stringBuilder=new SpannableStringBuilder(txt);
        if (txt.contains("米")) {
            stringBuilder.setSpan(colorGray,0,2,SPAN_INCLUSIVE_EXCLUSIVE);
            int indexMeter = txt.indexOf("米");
            stringBuilder.setSpan(colorGray,indexMeter,indexMeter+1,SPAN_INCLUSIVE_EXCLUSIVE);
        } else if (txt.contains("公里")) {
            stringBuilder.setSpan(colorGray,0,2,SPAN_INCLUSIVE_EXCLUSIVE);
            int indexMeter = txt.indexOf("公");
            stringBuilder.setSpan(colorGray,indexMeter,indexMeter+2,SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return stringBuilder;
    }

    //01分30秒/1小时30分
    CharSequence getRemainTimeTxt(String txt) {
        SpannableStringBuilder stringBuilder=new SpannableStringBuilder(txt);
        if (txt.contains("秒")) {
            int indexMin = txt.indexOf("分");
            stringBuilder.setSpan(colorGray,indexMin,indexMin+1,SPAN_INCLUSIVE_EXCLUSIVE);
            int indexSec = txt.indexOf("秒");
            stringBuilder.setSpan(colorGray,indexSec,indexSec+1,SPAN_INCLUSIVE_EXCLUSIVE);
        } else if (txt.contains("小")) {
            int indexHour = txt.indexOf("小");
            stringBuilder.setSpan(colorGray,indexHour,indexHour+2,SPAN_INCLUSIVE_EXCLUSIVE);
            int indexMin = txt.indexOf("分");
            stringBuilder.setSpan(colorGray,indexMin,indexMin+1,SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return stringBuilder;
    }

    //..到达
    CharSequence getArriveTimeTxt(String txt) {
        SpannableStringBuilder stringBuilder=new SpannableStringBuilder(txt);
        stringBuilder.setSpan(colorGray,txt.length()-2,txt.length(),SPAN_INCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }

}
