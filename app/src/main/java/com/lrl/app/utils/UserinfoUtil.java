package com.lrl.app.utils;

/**
 * 登录、注册保存用户信息
 * Created by jiabaofeng on 2017/9/11.
 */

public class UserinfoUtil {
    /**
     * 登录成功后的消息tag
     */
    public  static  final String TAG_MSG_LOGIN = "login";

//    /**
//     * 登录成功以后保存用户信息
//     * @param loginBean
//     */
//    public static  void save(LoginBean loginBean, Activity activity){
//        if(null == loginBean){
//            return;
//        }
//        ACache aCache = ACache.get(activity);
//        //登录成功刷新我的页面余额
//        aCache.put(Constant.uid, loginBean.getId());
//        aCache.put(Constant.uname, loginBean.getUsername());
//        aCache.put(Constant.token, loginBean.getLogin_token());
//        aCache.put(Constant.session_id, loginBean.getSession_id());
//        aCache.put(Constant.FREE_BETTING, loginBean.getIs_test_player());
//        //个人中心
//        RxBusEvent.getDefault().post(TAG_MSG_LOGIN);
//        //投注记录
//        RxBusEvent.getDefault().post(BettingRecordManagerFragment.TAG_BETTING_RECORD);
//    }
//
//    /**
//     * 保存用户手机号
//     * @param phone
//     */
//    public static  void setPhone(String phone, Activity activity){
//        ACache aCache = ACache.get(activity);
//        aCache.put(Constant.phone, phone);
//    }
//
//    /**
//     * 返回id
//     * @return
//     */
//    public static String getUid(Activity activity){
//        ACache aCache = ACache.get(activity);
//        return aCache.getAsString(Constant.uid);
//    }
//
//    /**
//     * 返回用户名
//     * @return
//     */
//    public static String getUserName(Activity activity){
//        ACache aCache = ACache.get(activity);
//        return aCache.getAsString(Constant.uname);
//    }
//
//    /**
//     * 返回用户手机号
//     * @return
//     */
//    public static String getUserPhone(Activity activity){
//        ACache aCache = ACache.get(activity);
//        return aCache.getAsString(Constant.phone);
//    }
//
//    /**
//     * 退出登录以后删除用户信息
//     */
//    public static  void loginOut(Context context){
//        ACache aCache = ACache.get(context);
//        //登录成功刷新我的页面余额
//        aCache.remove(Constant.uid);
//        aCache.remove(Constant.uname);
//        aCache.remove(Constant.token);
//        aCache.remove(Constant.session_id);
//        aCache.remove(Constant.is_set_pay_pass);
//        aCache.remove(Constant.is_bind_bank_card);
//        aCache.remove(Constant.phone);
//        aCache.remove(Constant.amount);
//        aCache.remove(Constant.FREE_BETTING);
//        aCache.remove(Constant.COOKIE);
//    }
//
//    /**
//     * 是否设置支付密码  1表示设置
//     * @param flag
//     */
//    public static  void setSetPayPass(String flag, Activity activity){
//        ACache aCache = ACache.get(activity);
//        aCache.put(Constant.is_set_pay_pass, flag);
//    }
//
//    /**
//     * 是否设置支付密码  1表示设置
//     */
//    public static  boolean getHasSetPayPass(Activity activity){
//        ACache aCache = ACache.get(activity);
//        return aCache.getAsString(Constant.is_set_pay_pass).equals("1");
//    }
//
//    /**
//     * 是否绑卡    1表示绑定
//     * @param flag
//     */
//    public static  void setBindBankCard(String flag, Activity activity){
//        ACache aCache = ACache.get(activity);
//        aCache.put(Constant.is_bind_bank_card, flag);
//    }
//
//    /**
//     * 是否绑卡    1表示绑定
//     */
//    public static  boolean getHasBindBankCard(Activity activity){
//        ACache aCache = ACache.get(activity);
//        return aCache.getAsString(Constant.is_bind_bank_card).equals("1");
//    }
//
//    public static void setAmount(String amount, Activity activity) {
//        ACache aCache = ACache.get(activity);
//        aCache.put(Constant.amount, amount);
//    }
//
//    public static void setUserName(String username, Activity activity) {
//        ACache aCache = ACache.get(activity);
//        aCache.put(Constant.uname, username);
//    }
//
//    public static String getAmount(Activity activity){
//        ACache aCache = ACache.get(activity);
//        return aCache.getAsString(Constant.amount);
//    }
}
