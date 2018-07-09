package com.lrl.app.utils;

import android.graphics.drawable.Drawable;

/**
 * Created by ZHP on 2017/7/26.
 */

public class TextViewUtil {


    public static Drawable setLeftDrawable(int dra) {

        Drawable drawable = UIUtil.getDrawable(dra);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

}
