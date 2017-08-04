package com.haidaoservice.yhs.ui.user.view.forget;

import com.haidaoservice.lib.base.BaseModel;
import com.haidaoservice.lib.base.BasePresenter;
import com.haidaoservice.lib.base.BaseView;

import rx.Observable;

/**
 * 忘记密码需求接口
 * <p>
 * Created by zhangfei on 2017/3/30.
 */

public interface ForgetContract {

    /**
     * 忘记密码-重置接口
     */
    interface ForgetModel extends BaseModel {

        /**
         * 获取验证码
         *
         * @param params 参数集合字符串
         * @return
         */
        Observable getVCode(String params);

        /**
         * 重置密码
         *
         * @param params 参数集合字符串
         * @return
         */
        Observable forgetPassword(String params);
    }

    /**
     * 忘记密码-重置接口2
     */
    interface ForgetTwoModel extends BaseModel {

        /**
         * 重置密码
         *
         * @param params 参数集合字符串
         * @return
         */
        Observable forgetPassword(String params);
    }

    /**
     * 忘记密码-响应接口
     */
    interface ForgetView extends BaseView {

        /**
         * 获取验证码成功
         */
        void getSuccess();

        /**
         * 重置成功
         */
        void resetSuccess();

        /**
         * 获取短信验证码按钮不可点击
         */
        public void checkButtonDisabled();

        /**
         * 更新倒计时
         *
         * @param time the time
         */
        void updateCountdown(int time);

        /**
         * 恢复验证码按钮状态
         */
        public void resumeCheckButton();

    }

    /**
     * 忘记密码-响应接口2
     */
    interface ForgetTwoView extends BaseView {

        /**
         * 重置成功
         */
        void resetSuccess();

    }

    /**
     * 忘记密码-数据处理
     */
    abstract class ForgetPresenter extends BasePresenter<ForgetModel, ForgetView> {

        /**
         * 获取验证码
         *
         * @param phone 手机号
         * @param type  验证码用 0：注册； 1：重置密码；
         * @return
         */
        public abstract void getVCode(String phone, int type);

        /**
         * 重置密码
         *
         * @param phone 手机号
         * @param pass  密码
         * @param VCode 验证码
         * @return
         */
        public abstract void forgetPassword(String phone, String pass, String VCode);

        /**
         * 开始验证码倒计时
         */
        public abstract void startCheckTimer();

        /**
         * 验证码倒计时结束
         */
        public abstract void endCheckTimer();
    }

    /**
     * 忘记密码-数据处理
     */
    abstract class ForgetTwoPresenter extends BasePresenter<ForgetTwoModel, ForgetTwoView> {

        /**
         * 重置密码
         *
         * @param phone 手机号
         * @param pass  密码
         * @param VCode 验证码
         * @return
         */
        public abstract void forgetPassword(String phone, String pass, String VCode);


    }
}
