package com.lrl.app.utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;


/**
 * Created by jiabaofeng on 2017/8/29.
 */

public class LotteryPukeAnimationUtil {

    /**
     */
    public static void startChipAnim(final View startView, View endView, final ViewGroup parentView, final ILotteryChipAnimStopListener lotteryChipAnimStopListener)
    {
        if(null != parentView.getTag() && !TextUtils.isEmpty(parentView.getTag()+"")){
            return;
        }
        parentView.setTag("animStart");

        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        startView.getLocationInWindow(startLoc);
        parentView.removeView(startView);


      //一、创造出执行动画的主题---imageview
        //代码new一个imageview，图片资源是上面的imageview的图片
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(startView.getWidth(), startView.getHeight());
        parentView.addView(startView, params);
       // 二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLocation = new int[2];
        parentView.getLocationInWindow(parentLocation);


        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        endView.getLocationInWindow(endLoc);


//        三、正式开始计算动画开始/结束的坐标
        //开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
        float startX = startLoc[0] - parentLocation[0]*3/2.5f;
        float startY = startLoc[1] - parentLocation[1]*3/2.5f;

        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/8
        float toX = endLoc[0] - parentLocation[0];
        float toY = endLoc[1] - parentLocation[1];
        if(toY==0 || toX==0){ //位置发生偏差时
            parentView.removeView(startView);
            parentView.setTag("");
            return;
        }

//        四、计算中间动画的插值坐标（贝塞尔曲线）（其实就是用贝塞尔曲线来完成起终点的过程）
        //开始绘制贝塞尔曲线
        Path path = new Path();
        //移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY);
        //使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
        path.quadTo((startX + toX) / 2, startY, toX, toY);
        //mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，
        // 如果是true，path会形成一个闭环
        final PathMeasure mPathMeasure = new PathMeasure(path, false);

        /**
         * 贝塞尔曲线中间过程的点的坐标
         */
        final float[] mCurrentPosition = new float[2];
        //★★★属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(300);
        // 匀速线性插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 当插值计算进行时，获取中间的每个值，
                // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                float value = (Float) animation.getAnimatedValue();
                if(value>mPathMeasure.getLength()){ //超过最大长度
                    return;
                }

                float newValue = (mPathMeasure.getLength()-value);
                if(newValue<mPathMeasure.getLength()/3){
                    newValue = mPathMeasure.getLength()/3;
                }
//                startView.setPivotX(startView.getWidth()/2);

                startView.setScaleX((newValue/mPathMeasure.getLength()));
                startView.setScaleY((newValue/mPathMeasure.getLength()));


                // 获取当前点坐标封装到mCurrentPosition
                // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
                // 离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                mPathMeasure.getPosTan(value, mCurrentPosition, null);//mCurrentPosition此时就是中间距离点的坐标值
                // 移动的图片（动画图片）的坐标设置为该中间点的坐标
                startView.setTranslationX(mCurrentPosition[0]);
                startView.setTranslationY(mCurrentPosition[1]);

            }
        });
//      五、 开始执行动画
        valueAnimator.start();

//      六、动画结束后的处理
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            //当动画结束后：
            @Override
            public void onAnimationEnd(Animator animation) {
                parentView.removeView(startView);
                // 把移动的图片imageview从父布局里移除
                parentView.setTag("");
                lotteryChipAnimStopListener.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public interface ILotteryChipAnimStopListener{
        public void finish();
    }
}
