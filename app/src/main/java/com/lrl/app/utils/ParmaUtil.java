package com.lrl.app.utils;


import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ZHP on 2017/7/18.
 */

public class ParmaUtil {

    public static Map<String, String> getParma(TreeMap<String, String> map) {

        map.put("_timestamp", String.valueOf(System.currentTimeMillis()/1000));
        StringBuilder all = new StringBuilder();
        String app_sign = null;
        for (Map.Entry e : map.entrySet()) {
            all.append(e.getKey())
                    .append("=")
                    .append(e.getValue())
                    .append("&");
        }
        all.deleteCharAt(all.length() - 1);
//        all.append(Constant.secretKey);

        try {
            app_sign = MD5.md5(all.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        map.put(Constant.app_sign, app_sign);
        return map;
    }

    public static String getAppSign(TreeMap<String, String> map) {

        StringBuilder all = new StringBuilder();
        String app_sign = null;
        for (Map.Entry e : map.entrySet()) {
            all.append(e.getKey())
                    .append("=")
                    .append(e.getValue())
                    .append("&");
        }
        all.deleteCharAt(all.length() - 1);
//        all.append(Constant.secretKey);

        try {
            app_sign = MD5.md5(all.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return app_sign;
    }

    /**
     * map转成json
     * @param map
     * @return
     */
    public static String getMapJson(TreeMap<String, String> map) {
        StringBuilder all = new StringBuilder();
        for (Map.Entry e : map.entrySet()) {
            all.append(e.getKey())
                    .append("=")
                    .append(e.getValue())
                    .append("&");
        }
        all.deleteCharAt(all.length() - 1);
        return all.toString();
    }
}
