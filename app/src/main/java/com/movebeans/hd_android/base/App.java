package com.movebeans.hd_android.base;

import android.content.Context;

import com.movebeans.hd_android.constants.HttpConstants;
import com.movebeans.lib.base.BaseApplication;
import com.movebeans.lib.net.ApiService;

import net.nashlegend.anypref.AnyPref;



/**
 * Created by zhangfei on 2017/3/24.
 */

public class App extends BaseApplication {


    public static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //--------------初始化SharedPrefrences库
        AnyPref.init(this);
        //--------------初始化网络请求库
        ApiService.init(HttpConstants.SERVICE_URL);

    }

}
