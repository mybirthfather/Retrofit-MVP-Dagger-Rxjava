package com.lrl.app.utils;

/**
 * Created by ZHP on 2017/6/24.
 */

public class Constant {
    //环境 1:线下环境 2:线上测试 3:正式环境
    public static final boolean IS_RELEASE = false;
    public static boolean SHOW_LOG = !IS_RELEASE;
    public static String BASE_URL = "";//retrofit 的base url


}