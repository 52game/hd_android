package com.movebeans.hd_android.ui.user.view.login;

import com.movebeans.hd_android.api.HttpServerApi;
import com.movebeans.hd_android.ui.user.User;
import com.movebeans.lib.net.APiUtil;
import com.movebeans.lib.net.ApiService;

import rx.Observable;

/**
 * Created by zhangfei on 2017/3/22.
 */

public class LoginModel implements LoginContract.LoginModel {

    @Override
    public Observable<User> login(String params) {

        return ApiService.createApi(HttpServerApi.class)
//                .login(params)
                .login()
                .compose(APiUtil.<User>handleResult())
                .compose(APiUtil.<User>rxSchedulerHelper());
//

    }
    @Override
    public Observable<User> thirdLogin(String params) {
        return ApiService.createApi(HttpServerApi.class)
//                .thirdLogin(params)
                .thirdLogin()
                .compose(APiUtil.<User>handleResult())
                .compose(APiUtil.<User>rxSchedulerHelper());
    }

}
