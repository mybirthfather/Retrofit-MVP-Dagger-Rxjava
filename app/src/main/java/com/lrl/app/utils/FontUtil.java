package com.lrl.app.utils;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by ZHP on 2017/7/30.
 */

public class FontUtil {

    public static void setFont(TextView textView){
        textView.setTypeface(Typeface.createFromAsset(UIUtil.getContext().getAssets(),"uifont.TTF"));
    }

    public static void setLHCFont(TextView textView){
        textView.setTypeface(Typeface.createFromAsset(UIUtil.getContext().getAssets(),"liuhecai.ttf"));
    }

    public static void setPMZDFont(TextView textView){
        textView.setTypeface(Typeface.createFromAsset(UIUtil.getContext().getAssets(),"pmzd.ttf"));
    }

    public static void setPUKEFont(TextView textView){
        textView.setTypeface(Typeface.createFromAsset(UIUtil.getContext().getAssets(),"puke.ttf"));
    }
}
