package com.lrl.app.utils;

import android.media.AudioManager;
import android.media.SoundPool;

import com.lrl.app.R;


/**
 * Created by zhangpeng on 2017/9/14.
 */

public class ChipSoundUtil {

    private static SoundPool soundPool;
    private static int  soundID = 0;

    public ChipSoundUtil() {
        create();
    }

    private void create(){
        if(null == soundPool){
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
            soundID = soundPool.load(UIUtil.getContext(), R.raw.chip, 1);
        }
    }

    public void playSound() {
        create();
        soundPool.play(
                soundID,
                0.1f,   //左耳道音量【0~1】
                0.5f,   //右耳道音量【0~1】
                0,     //播放优先级【0表示最低优先级】
                0,     //循环模式【0表示循环一次，-1表示一直循环，其他表示数字+1表示当前数字对应的循环次数】
                1     //播放速度【1是正常，范围从0~2】
        );
    }
    public void close(){
        if(null != soundPool){
            soundPool.release();
            soundPool = null;
        }
    }

}
