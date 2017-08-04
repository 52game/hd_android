package com.haidaoservice.yhs.ui.user.view.forget;

import com.haidaoservice.yhs.api.HttpServerApi;
import com.haidaoservice.lib.net.APiUtil;
import com.haidaoservice.lib.net.ApiService;

import rx.Observable;

/**
 * Created by zhangfei on 2017/4/24.
 */

public class ForgetTwoModel implements ForgetContract.ForgetTwoModel{


    @Override
    public Observable forgetPassword(String params) {
        return ApiService.createApi(HttpServerApi.class)
                .forgetPassword()
//                .getVCode(params)
                .compose((Observable.Transformer) APiUtil.handleResult())
                .compose(APiUtil.rxSchedulerHelper());
    }

}
