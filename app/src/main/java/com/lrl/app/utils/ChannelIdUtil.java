package com.lrl.app.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.lrl.app.App;
/**
 * 获取代理商编号
 * Created by jiabaofeng on 2017/12/12.
 */
public class ChannelIdUtil {

    /**
     * 获取多渠道数据
     * @return
     */
    public static String getChannelName(){
        String channelName = "menhu";
        try {
            ApplicationInfo appInfo = UIUtil.getContext().getPackageManager()
                    .getApplicationInfo(UIUtil.getContext().getPackageName(),
                            PackageManager.GET_META_DATA);
            if(null != appInfo.metaData){
                channelName = appInfo.metaData.getString("channelName");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelName;
    }

    /**
     * 代理商编号
     * @return
     */
    public static String getProxyCode(){
        //提交数据,默认自己门户的渠道
        String hostName = "00000";
        try {
            ApplicationInfo appInfo = App.getApplication().getPackageManager()
                    .getApplicationInfo(App.getApplication().getPackageName(),
                            PackageManager.GET_META_DATA);
            if(null != appInfo.metaData){
                //hostName编号是int所以这里进行处理
                String hostNameTemp = appInfo.metaData.getString("hostName");
                if(!TextUtils.isEmpty(hostNameTemp)){
                    hostName = hostNameTemp.substring(hostNameTemp.length()-hostName.length(),hostNameTemp.length());
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            hostName = "00000";
        }
        return hostName;
    }
}
