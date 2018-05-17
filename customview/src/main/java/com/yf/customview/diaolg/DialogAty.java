package com.yf.customview.diaolg;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yf.customview.R;
import com.yf.customview.view.RoadLineLayout;

import static android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE;

/**
 * Created by colin on 18-4-16.
 */

public class DialogAty extends Activity implements View.OnClickListener {
    private PopupWindowUtil popupWindowUtil;
    private Button button;
    ViewGroup viewGroup;

    private TextView mSpannableText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_dialog);
        viewGroup= (ViewGroup) LayoutInflater.from(this).inflate(R.layout.aty_dialog, null);
        button = (Button) findViewById(R.id.btn1);
        button.setOnClickListener(this);
        PopupWindowUtil.getInstance().init(this,viewGroup);
        RoadLineLayout lineLayout=new RoadLineLayout(this);
        lineLayout.show();
        viewGroup.addView(lineLayout);
        mSpannableText = (TextView) findViewById(R.id.spannableText);

        String txt = "剩余166米";
        ForegroundColorSpan colorGray = new ForegroundColorSpan(getResources().getColor(R.color.colorAccent));
        ForegroundColorSpan colorGrayCopy = new ForegroundColorSpan(getResources().getColor(R.color.colorAccent));
        SpannableStringBuilder turnDistanceSb = new SpannableStringBuilder();
        turnDistanceSb.append(txt);
        turnDistanceSb.setSpan(colorGray,txt.length()-2,txt.length(),SPAN_INCLUSIVE_EXCLUSIVE);
        int indexMeter = txt.indexOf("米");
        turnDistanceSb.setSpan(colorGrayCopy,indexMeter,indexMeter+1,SPAN_INCLUSIVE_EXCLUSIVE);
        mSpannableText.setText(turnDistanceSb);

    }

    @Override
    public void onClick(View v) {
//        popupWindowUtil = new PopupWindowUtil();
        PopupWindowUtil.getInstance().showPop(this,button);
    }
}
