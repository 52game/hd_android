package com.movebeans.hd_android.ui.user.view.modify.pass;

import com.movebeans.hd_android.api.HttpServerApi;
import com.movebeans.lib.net.APiUtil;
import com.movebeans.lib.net.ApiService;

import rx.Observable;

/**
 * Created by zhangfei on 2017/4/10.
 */

public class ModifyPassModel implements ModifyPassContract.ModifyPassModel {


    @Override
    public Observable modifyPassword(String params) {
        return ApiService.createApi(HttpServerApi.class)
                .modifyPassword()
//                .modifyPassword(params)
                .compose((Observable.Transformer) APiUtil.handleResult())
                .compose(APiUtil.rxSchedulerHelper());
    }
}
