package com.lrl.app.http.net.rto_exception;

/**
 * Created by ZHP on 2017/6/26.
 */

public class BaseException extends Exception {

    //Token 失效
    public static final int  ERROR_TOKEN=10001;

    //余额不足
    public static final int  ERROR_AMOUNT=10002;





    private int code;

    private String displayMessage;

    public BaseException() {
    }

    public BaseException(int code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public BaseException(String message, int code, String displayMessage) {
        super(message);
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
