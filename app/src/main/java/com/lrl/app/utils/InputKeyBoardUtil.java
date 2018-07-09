package com.lrl.app.utils;

import android.graphics.Rect;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * Created by jiabaofeng on 2017/11/20.
 */

public class InputKeyBoardUtil {
    private int usableHeightPrevious;
    private ViewGroup.LayoutParams frameLayoutParams;
    private ViewGroup mChildOfContent;

    /**
     * 修改键盘挡住输入框问题
     * @param mChildOfContent
     */
    public void addChange(ViewGroup mChildOfContent){
        this.mChildOfContent = mChildOfContent;
        frameLayoutParams = mChildOfContent.getLayoutParams();
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
    }


    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard/4)) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightSansKeyboard;
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(rect);
        // rect.top其实是状态栏的高度，如果是全屏主题，直接 return rect.bottom就可以了
        return (rect.bottom - rect.top);
    }
}
