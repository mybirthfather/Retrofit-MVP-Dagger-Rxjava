package com.lrl.app.utils;

import android.provider.Settings;

import com.lrl.app.di.module.BaseSingleton;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZHP on 2017/6/24.
 */
public class StringUtil extends BaseSingleton {

    /**
     * 实例化
     */
    public static StringUtil getInstance() {
        return getSingleton(StringUtil.class);
    }


    /**
     * 判断字符串是 空
     *
     * @param str null、“ ”、“null”都返回true
     * @return
     */
    public static boolean isNullString(String str) {
        return (null == str || str.trim().isEmpty() || "null".equals(str.trim()
                .toLowerCase(Locale.getDefault())));
    }

    /**
     * 判断字符串不是 空
     *
     * @param str null、“ ”、“null”都返回true
     * @return
     */
    public static boolean isNotNullString(String str) {
        return !isNullString(str);
    }

    public static String formatNullString(String str) {
        return isNotNullString(str) ? str : "";
    }

    /**
     * 特殊比较字符串 ""、null、"null"
     *
     * @param lhs
     * @param rhs
     * @return
     */
    public static boolean equalSpecialStr(String lhs, String rhs) {
        if (isNullString(lhs) && isNullString(rhs)) {
            return true;
        } else {
            if (lhs.equals(rhs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 截取数字
     *
     * @return
     */
    public static String cutNumber(String content) {
        if (isNotNullString(content)) {
            Pattern p = Pattern.compile("[^0-9]");
            Matcher m = p.matcher(content);
            return m.replaceAll("");
        } else {
            return "";
        }
    }

    /*
     *替换日期格式
     * 2016-05-13 --> 2016/05/13
    */
    public static String replaceDate(String Date) {
        return Date.replaceAll("-", "/");
    }


    /**
     * 时间转换
     * 900 --> 09:00
     * 2100 --> 21:00
     */
    public static String changeTime(String start_time) {
        String h;
        String m;
        if (start_time.length() < 4) {
            h = "0" + start_time.substring(0, 1);
            m = start_time.substring(1);
        } else {
            h = start_time.substring(0, 2);
            m = start_time.substring(2);
        }
        return h + ":" + m;
    }

    /**
     * 根据设备生成一个唯一标识
     *
     * @return
     */
    public static String generateOpenUDID() {
        // Try to get the ANDROID_ID
        String OpenUDID = Settings.Secure.getString(UIUtil.getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        if (OpenUDID == null || OpenUDID.equals("9774d56d682e549c")
                | OpenUDID.length() < 15) {
            // if ANDROID_ID is null, or it's equals to the GalaxyTab generic
            // ANDROID_ID or bad, generates a new one
            final SecureRandom random = new SecureRandom();
            OpenUDID = new BigInteger(64, random).toString(16);
        }
        return OpenUDID;
    }

    /**
     * String 转 double 并保留2位小数
     * 6.2041   -->  6.20
     *
     * @param str
     * @return
     */
    public static String getDecimal(String str) {
        Double cny = Double.parseDouble(str);//6.2041
        DecimalFormat df = new DecimalFormat("0.00");
        String result = df.format(cny);
        return result + "";
    }

    public static String getDecimalMoney(String str) {
        if (StringUtil.isNullString(str)) {
            return null;
        }
        DecimalFormat myformat = new DecimalFormat("###,###");
        return myformat.format(Integer.valueOf(str));
    }

//    /**
//     * 富文本
//     *
//     * @param str
//     * @param start
//     * @param end
//     * @return
//     */
//    public static SpannableString getSpannableString(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_16), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_10), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//
//    public static SpannableString getSpannableColorString(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_16), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_10), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//
//    public static SpannableString getSpannableColorStringColorSurePage(String str, String red1, String red2) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_black_10), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_red_14), 1, red1.length() + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_red_14), red1.length() + 7, red2.length() + red1.length() + 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_black_10), str.length() - 1, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//
//    public static SpannableString getSpannableMoneyString(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_black_20), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_red_20), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//
//    public static SpannableString getSpannableBlueString(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_black_16), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_blue_16), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//
//    public static SpannableString getSpannableBettingMoney(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_10), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_14), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//
//    public static SpannableString getSpannableAcount(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_14), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_10), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//    public static SpannableString getSpannableSwing(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_14), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_12), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//
//    public static SpannableString getSpannableMoney(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_21), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_40), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//
//    public static SpannableString getSpannableBettingSureMoney(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_14), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_yellow_14), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//
//    public static SpannableString getSpannableMineMoney(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_14), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_yellow_14), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//
//    public static SpannableString getSpannableMinePoint(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_14), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_yellow_14_DC), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//
//    public static SpannableString getSpannableMineBlance(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_12), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_yellow_18), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }
//    public static SpannableString getSpannablePay(String str, int start, int end) {
//        SpannableString styledText = new SpannableString(str);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_12), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), R.style.text_size_yellow_12), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        return styledText;
//    }

}
