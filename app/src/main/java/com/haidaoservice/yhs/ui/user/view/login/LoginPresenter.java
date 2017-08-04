package com.haidaoservice.yhs.ui.user.view.login;

import com.haidaoservice.yhs.base.PublicParams;
import com.haidaoservice.yhs.ui.user.User;
import com.haidaoservice.lib.common.tool.MD5Util;
import com.haidaoservice.lib.common.tool.RequestParamsUtils;
import com.haidaoservice.lib.net.SimpleSubscriber;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * 登录-数据处理
 * <p>
 * Created by zhangfei on 2017/3/23.
 */

public class LoginPresenter extends LoginContract.LoginPresenter {



    @Override
    public void login(String phone, String pass) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pass", MD5Util.md5Encode(pass));
        String params = "";
        try {
            params = RequestParamsUtils.buildRequestParams(new PublicParams(mContext).createParams(), map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Subscription subscription = mModel.login(params).subscribe(new SimpleSubscriber<User>() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError(e);
            }

            @Override
            public void onNext(User user) {
                super.onNext(user);
                mView.success(user);
            }
        });
        mRxManager.add(subscription);
    }

    @Override
    public void thirdLogin(String openId, int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("openId", openId);
        map.put("type", type);
        String params = "";
        try {
            params = RequestParamsUtils.buildRequestParams(new PublicParams(mContext).createParams(), map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Subscription subscription = mModel.login(params).subscribe(new SimpleSubscriber<User>() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError(e);
            }

            @Override
            public void onNext(User user) {
                super.onNext(user);
                mView.thirdSuccess(user);
            }
        });
        mRxManager.add(subscription);
    }


    @Override
    public <M> M getmModel() {
        return (M) new LoginModel();
    }
}
