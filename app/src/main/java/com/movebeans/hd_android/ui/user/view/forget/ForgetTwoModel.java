package com.movebeans.hd_android.ui.user.view.forget;

import com.movebeans.hd_android.api.HttpServerApi;
import com.movebeans.lib.net.APiUtil;
import com.movebeans.lib.net.ApiService;

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
