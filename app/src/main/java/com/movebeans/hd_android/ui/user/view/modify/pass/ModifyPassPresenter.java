package com.movebeans.hd_android.ui.user.view.modify.pass;

import com.movebeans.hd_android.base.PublicParams;
import com.movebeans.lib.common.tool.RequestParamsUtils;
import com.movebeans.lib.net.SimpleSubscriber;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangfei on 2017/4/10.
 */

public class ModifyPassPresenter extends ModifyPassContract.ModifyPassPresenter {


    @Override
    public <M> M getmModel() {
        return (M) new ModifyPassModel();
    }

    @Override
    public void modifyPassword(String oldPass, String pass) {
        Map<String, Object> map = new HashMap<>();
        map.put("oldPassword", oldPass);
        map.put("newPassword", pass);
        String params = "";
        try {
            params = RequestParamsUtils.buildRequestParams(new PublicParams(mContext).createParams(), map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRxManager.add(mModel.modifyPassword(params).subscribe(new SimpleSubscriber() {

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
