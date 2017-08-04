package com.haidaoservice.yhs.ui.user.view.login;

import com.haidaoservice.yhs.api.HttpServerApi;
import com.haidaoservice.yhs.ui.user.User;
import com.haidaoservice.lib.net.APiUtil;
import com.haidaoservice.lib.net.ApiService;

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
