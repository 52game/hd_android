package com.haidaoservice.yhs.ui.user.view.register;

import com.haidaoservice.yhs.base.PublicParams;
import com.haidaoservice.lib.common.tool.RequestParamsUtils;
import com.haidaoservice.lib.net.SimpleSubscriber;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangfei on 2017/3/27.
 */

public class RegisterPresenter extends RegisterContract.LoginPresenter {

    @Override
    public <M> M getmModel() {
        return (M) new RegisterModel();
    }


    @Override
    public void register(String phone, String pass) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pass", pass);
        String params = "";
        try {
            params = RequestParamsUtils.buildRequestParams(new PublicParams(mContext).createParams(), map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRxManager.add(mModel.register(params).subscribe(new SimpleSubscriber() {

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError(e);
                e.printStackTrace();
            }

            @Override
            public void onNext(Object o) {
                super.onNext(o);
                mView.success();
            }
        }));
    }
}
