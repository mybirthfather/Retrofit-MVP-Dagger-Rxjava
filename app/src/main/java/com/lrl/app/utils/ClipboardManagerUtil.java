package com.lrl.app.utils;

import android.content.ClipboardManager;
import android.content.Context;

/**
 * 描述：粘贴板管理器工具类
 * 创建人：贾保峰
 * 创建时间：2017/8/4
 */
public class ClipboardManagerUtil {
    /**
     * 实现文本复制功能
     * @param content
     */
    public static void copy(String content, Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     *
     * @param context
     * @return
     */
    public static String paste(Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }
}
