package com.movebeans.hd_android.ui.user.view.register;

import com.movebeans.lib.base.BaseModel;
import com.movebeans.lib.base.BasePresenter;
import com.movebeans.lib.base.BaseView;

import rx.Observable;

/**
 * 注册需求接口 -ALL
 * <p>
 * Created by zhangfei on 2017/3/27.
 */

public interface RegisterContract {

    /**
     * 注册-注册接口
     */
    interface RegisterModel extends BaseModel {

        /**
         * 注册
         *
         * @param params 参数集合字符串
         * @return
         */
        Observable register(String params);
    }

    /**
     * 注册-响应接口
     */
    interface RegisterView extends BaseView {

        /**
         * 注册成功
         */
        void success();

    }

    /**
     * 注册-数据处理
     */
    abstract class LoginPresenter extends BasePresenter<RegisterModel, RegisterView> {

        /**
         * 注册
         *
         * @param phone 手机号
         * @param pass  密码
         * @return
         */
        public abstract void register(String phone, String pass);
    }
}
