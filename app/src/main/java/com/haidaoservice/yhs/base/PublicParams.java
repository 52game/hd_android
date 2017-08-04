package com.haidaoservice.yhs.base;

import android.content.Context;
import android.text.TextUtils;

import com.haidaoservice.yhs.constants.AnyPrefConstants;
import com.haidaoservice.yhs.ui.user.User;
import com.haidaoservice.yhs.ui.user.UserSessionManager;
import com.haidaoservice.lib.common.tool.AppUtils;
import com.haidaoservice.lib.net.PublicParamsCreator;

import net.nashlegend.anypref.AnyPref;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangfei on 2017/3/23.
 */

public class PublicParams implements PublicParamsCreator {

    private Context mContext;

    public PublicParams(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Map<String, Object> createParams() {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = null;
        boolean isLogin = UserSessionManager.getInstance().hasUser();
        if (isLogin)
            user = UserSessionManager.getInstance().getUserInfo();
        map.put("userId", isLogin ? TextUtils.isEmpty(user.getUserId()) ? "" : user.getUserId() : "");
        map.put("clientType", "2");
        map.put("clientToken", AnyPref.getDefault().getString(AnyPrefConstants.CLIENT_TOKEN, ""));
        map.put("version", AppUtils.getVersionName(mContext));
        map.put("timestamp", System.currentTimeMillis());
        map.put("pAddress", isLogin ? TextUtils.isEmpty(user.getUserToken()) ? "" : user.getUserToken() : "");
        return map;
    }
}
