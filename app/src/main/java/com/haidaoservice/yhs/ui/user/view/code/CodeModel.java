package com.haidaoservice.yhs.ui.user.view.code;

import com.haidaoservice.yhs.api.HttpServerApi;
import com.haidaoservice.lib.net.APiUtil;
import com.haidaoservice.lib.net.ApiService;

import rx.Observable;

/**
 * Created by zhangfei on 2017/3/29.
 */

public class CodeModel implements CodeContract.CodeModel {


    @Override
    public Observable getVCode(String params) {
        return ApiService.createApi(HttpServerApi.class)
                .getVCode()
//                .getVCode(params)
                .compose((Observable.Transformer) APiUtil.handleResult())
                .compose(APiUtil.rxSchedulerHelper());
    }

    @Override
    public Observable checkVCode(String params) {

        return ApiService.createApi(HttpServerApi.class)
                .checkVCode()
//                .checkVCode(params)
                .compose((Observable.Transformer) APiUtil.handleResult())
                .compose(APiUtil.rxSchedulerHelper());
    }
}
