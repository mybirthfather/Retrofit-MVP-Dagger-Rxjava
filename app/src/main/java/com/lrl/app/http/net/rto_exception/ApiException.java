package com.lrl.app.http.net.rto_exception;

/**
 * Created by ZHP on 2017/6/26.
 */

public class ApiException extends BaseException {
    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
