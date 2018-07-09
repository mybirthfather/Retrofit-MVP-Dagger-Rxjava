package com.lrl.app.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 布局文件自己写
 */

public class EmptyViewUtil {
    private View emptyView;
    private TextView tv_empty_title;
    private ImageView iv_empty_message;

    private Context context;
    private View view;
    public EmptyViewUtil(Context context){
        this(context,null);
    }

    public EmptyViewUtil(Context context, View view){
        this.context = context;
        this.view = view;
    }

    //标题栏View-------------------------------------------------------------------------------
    /**
     * EmptyView设置空内容提示
     * @param message 提示语
     */
    public EmptyViewUtil emptyViewMsg(String message){
        initEmptyView();
        tv_empty_title.setText(message);
        return this;
    }

    public EmptyViewUtil emptyViewMsg(int messageId){
        emptyViewMsg(context.getResources().getString(messageId));
        return this;
    }

    /**
     * EmptyView设置空内容提示
     * @param emptyResId 提示图片
     */
    public EmptyViewUtil emptyViewImage(int emptyResId){
        initEmptyView();
        iv_empty_message.setImageResource(emptyResId);
        return this;
    }

    /**
     * EmptyView设置空内容显示
     */
    public EmptyViewUtil emptyViewShow(){
        initEmptyView();
        emptyView.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * EmptyView设置空内容隐藏
     */
    public EmptyViewUtil emptyViewHiden(){
        initEmptyView();
        emptyView.setVisibility(View.GONE);
        return this;
    }

    private void initEmptyView(){
        if(null == emptyView){
            if(null != view){
//                emptyView = view.findViewById(R.id.rl_empty);
            }else {
                if(null != context){
//                    emptyView = ((Activity)context).findViewById(R.id.rl_empty);
                }
            }

        }
        if(null == tv_empty_title){
//            tv_empty_title = emptyView.findViewById(R.id.tv_empty_title);
        }

        if(null == iv_empty_message)
        {
//            iv_empty_message = emptyView.findViewById(R.id.iv_empty_message);
        }
    }
}
