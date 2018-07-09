package com.lrl.app.http.net.rto_rxbuild;

import android.content.Context;

import com.lrl.app.http.net.rto_exception.ApiException;
import com.lrl.app.http.net.rto_exception.BaseException;
import com.lrl.app.http.net.rto_exception.ErrorMessageFactory;


/**
 * Created by ZHP on 2017/6/25.
 */

public class RxErrorHandler {


    private Context mContext;

    public RxErrorHandler(Context context){

        this.mContext = context;
    }

    public BaseException handleError(Throwable e){

        BaseException exception = new BaseException();
        if(e instanceof ApiException){
            exception.setCode(((ApiException)e).getCode());
            exception.setDisplayMessage(((ApiException)e).getDisplayMessage());

        }
        /*if(e instanceof ApiException){

            exception.setCode(((ApiException)e).getCode());

        }
        else if (e instanceof JsonParseException){
            exception.setCode(BaseException.JSON_ERROR);
        }
        else  if(e instanceof HttpException){

            exception.setCode(((HttpException)e).code());
        }
        else  if(e instanceof SocketTimeoutException){

            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        }
        else if(e instanceof SocketException){

        }
        else {

            exception.setCode(BaseException.UNKNOWN_ERROR);

        }*/

        exception.setDisplayMessage(ErrorMessageFactory.create(mContext,exception.getCode()));

        return  exception;
    }

    public void  showErrorMessage(BaseException e){

//        UIUtil.showToastSafe(e.getDisplayMessage());

    }
}
