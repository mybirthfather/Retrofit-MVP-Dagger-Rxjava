package com.lrl.app.presenter.contract;
import com.lrl.app.baen.BaseBean;
import com.lrl.app.baen.EmptyBean;
import com.lrl.app.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by jiabaofeng on 2017/11/6.
 */
public interface LogContract {

    interface ILogModel{
        Observable<BaseBean<EmptyBean>> addLog(String msg);
    }

    interface LogView extends BaseView {
        void logSuccess();
    }

}
