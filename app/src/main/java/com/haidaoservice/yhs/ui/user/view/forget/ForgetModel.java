package com.haidaoservice.yhs.ui.user.view.forget;

import com.haidaoservice.yhs.api.HttpServerApi;
import com.haidaoservice.lib.net.APiUtil;
import com.haidaoservice.lib.net.ApiService;

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
