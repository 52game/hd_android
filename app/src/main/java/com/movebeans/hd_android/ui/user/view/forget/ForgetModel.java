package com.movebeans.hd_android.ui.user.view.forget;

import com.movebeans.hd_android.api.HttpServerApi;
import com.movebeans.lib.net.APiUtil;
import com.movebeans.lib.net.ApiService;

import rx.Observable;

/**
 * Created by zhangfei on 2017/3/30.
 */

public class ForgetModel implements ForgetContract.ForgetModel {

    @Override
    public Observable getVCode(String params) {

        return ApiService.createApi(HttpServerApi.class)
                .getVCode()
//                .getVCode(params)
                .compose((Observable.Transformer) APiUtil.handleResult())
                .compose(APiUtil.rxSchedulerHelper());
    }

    @Override
    public Observable forgetPassword(String params) {
        return ApiService.createApi(HttpServerApi.class)
                .forgetPassword()
//                .getVCode(params)
                .compose((Observable.Transformer) APiUtil.handleResult())
                .compose(APiUtil.rxSchedulerHelper());
    }
}
