package com.lrl.app.baen;

/**
 * Created by ZHP on 2017/6/24.
 */

public class BaseBean<T> extends BaseEntity {

    public static final int SUCCESS=0;
    private int code;

    private String msg;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean success(){

        return  (code==SUCCESS);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
