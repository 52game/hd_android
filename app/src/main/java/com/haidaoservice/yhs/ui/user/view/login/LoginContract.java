package com.haidaoservice.yhs.ui.user.view.login;

import com.haidaoservice.yhs.ui.user.User;
import com.haidaoservice.lib.base.BaseModel;
import com.haidaoservice.lib.base.BasePresenter;
import com.haidaoservice.lib.base.BaseView;

import rx.Observable;

/**
 * 登录需求接口-ALL
 * <p>
 * Created by zhangfei on 2017/3/22.
 */

public interface LoginContract {

    /**
     * 登录-登录接口
     */
    interface LoginModel extends BaseModel {
        /**
         * 登录
         *
         * @param params 参数集合字符串
         */
        Observable<User> login(String params);

        /**
         * 第三方登录
         *
         * @param params 参数集合字符串
         */
        Observable<User> thirdLogin(String params);
    }

    /**
     * 登录-响应接口
     */
    interface LoginView extends BaseView {

        /**
         * 登录请求成功
         *
         * @param user 用户实体
         */
        void success(User user);

        /**
         * 登录请求成功
         *
         * @param user 用户实体
         */
        void thirdSuccess(User user);

    }

    /**
     * 登录-数据处理
     */
    abstract class LoginPresenter extends BasePresenter<LoginModel, LoginView> {

        /**
         * 登录
         *
         * @param account 账号
         * @param pass    密码
         */
        public abstract void login(String account, String pass);

        /**
         * 第三方登录
         *
         * @param openId 第三方用户id
         * @param type   类型：0：qq 1：微信 2：新浪微博
         */
        public abstract void thirdLogin(String openId, int type);


    }
}
