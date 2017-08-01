package com.movebeans.hd_android.ui.user.view.forget;

import com.movebeans.hd_android.base.PublicParams;
import com.movebeans.lib.common.tool.MD5Util;
import com.movebeans.lib.common.tool.RequestParamsUtils;
import com.movebeans.lib.net.SimpleSubscriber;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangfei on 2017/4/24.
 */

public class ForgetTwoPresenter extends ForgetContract.ForgetTwoPresenter {
    @Override
    public <M> M getmModel() {
        return (M) new ForgetTwoModel();
    }

    @Override
    public void forgetPassword(String phone, String pass, String VCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pass", MD5Util.md5Encode(pass));
        map.put("VCode", VCode);
        String params = "";
        try {
            params = RequestParamsUtils.buildRequestParams(new PublicParams(mContext).createParams(), map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRxManager.add(mModel.forgetPassword(params).subscribe(new SimpleSubscriber() {

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError(e);
                e.printStackTrace();
            }

            @Override
            public void onNext(Object o) {
                super.onNext(o);
                mView.resetSuccess();
            }
        }));
    }
}
