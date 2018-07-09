package com.lrl.app.utils;

import android.media.AudioManager;
import android.media.SoundPool;


/**
 * 动画播放音效
 * Created by jiabaofeng on 2017/9/28.
 */

public class AnimAudioUtil {
    private SoundPool soundPool;
    private  int  soundID = 0;
    private boolean loadFinish = false;
    private boolean startPlay = false;


    public AnimAudioUtil(int animAudioRes) {
        if(null == soundPool){
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
            soundID = soundPool.load(UIUtil.getContext(), animAudioRes, 1);
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                    loadFinish = true;
                    if(startPlay){
                        play();
                    }
                    startPlay = false;
                }
            });
        }
    }

    public void play() {
        if(!loadFinish){    //防止没有加载完成，无法播放
            startPlay = true;
            return;
        }
        soundPool.play(
                soundID,
                0.1f,   //左耳道音量【0~1】
                0.5f,   //右耳道音量【0~1】
                1,     //播放优先级【0表示最低优先级】
                0,     //循环模式【0表示循环一次，-1表示一直循环，其他表示数字+1表示当前数字对应的循环次数】
                1     //播放速度【1是正常，范围从0~2】
        );
    }
    public void release(){
        if(null != soundPool){
            soundPool.release();
            soundPool = null;
        }
    }

    public void stop(){
        if(null != soundPool){
            soundPool.stop(soundID);
        }
    }
}
