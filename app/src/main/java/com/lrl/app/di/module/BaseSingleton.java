package com.lrl.app.di.module;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by ZHP on 2017/6/24.
 */
public abstract class BaseSingleton {

    /***
     * 采用ConcurrentHashMap集合进行存储
     * Class 作为key ---对象的类型
     * Object 作为value---对象的实例化
     * 实现对象的类型和对象的实例化 一一对应
     */
    private static final ConcurrentMap<Class, Object> map = new ConcurrentHashMap<>();

    /***
     * @param type 传入进行实例化对象的类型
     * @return T 即返回对象的实例化
     */
    public static <T> T getSingleton(Class<T> type) {
        /***
         * 从map中取出对象的相对应的实例
         *
         * 为减少对map的操作，在此处使用局部变量ob  --- 符合优化性能要求
         */
        Object ob = map.get(type);

        try {
            /***
             * 对该对象的实例进行null判断
             */
            if (ob == null) {
                /***
                 * 为使用安全的map及其操作  设置同步锁
                 */
                synchronized (map) {
                    /***
                     * 创建此 Class 对象所表示的类的一个新实例。
                     * 如同用一个带有一个空参数列表的 new 表达式实例化该类。
                     * 如果该类尚未初始化，则初始化这个类。
                     */
                    ob = type.newInstance();
                    /***
                     * 将对象类型和创建的新实例放进map
                     */
                    map.put(type, ob);
                }
            }

            /***
             *  是type.newInstance()产生的两个异常处理
             *
             *IllegalAccessException - 如果该类或其 null 构造方法是不可访问的。
             *InstantiationException - 如果此 Class 表示一个抽象类、接口、数组类、
             *基本类型或 void； 或者该类没有 null 构造方法； 或者由于其他某种原因导致实例化失败
             *
             *在此不做过多处理
             */
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        /***
         * 将从map中取出的对象的实例化 转换成泛型参数 返回
         */
        return (T) ob;
    }

    /***
     * 该方法 是从map中移除对象实例化操作
     *
     * @param type
     */
    public static <T> void Remove(Class<T> type) {
        /***
         * map移除操作
         */
        map.remove(type);

    }
}
