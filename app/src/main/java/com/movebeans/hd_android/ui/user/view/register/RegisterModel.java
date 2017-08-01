package com.movebeans.hd_android.ui.user.view.register;

import com.movebeans.hd_android.api.HttpServerApi;
import com.movebeans.lib.net.APiUtil;
import com.movebeans.lib.net.ApiService;

import rx.Observable;

/**
 * Created by zhangfei on 2017/3/27.
 */

public class RegisterModel implements RegisterContract.RegisterModel {


    @Override
    public Observable register(String params) {
        return ApiService.createApi(HttpServerApi.class).register()
                .compose((Observable.Transformer) APiUtil.handleResult())
                .compose(APiUtil.rxSchedulerHelper());
    }
}
