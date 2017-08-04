package com.haidaoservice.yhs.ui.user.view.code;

import com.haidaoservice.lib.base.BaseModel;
import com.haidaoservice.lib.base.BasePresenter;
import com.haidaoservice.lib.base.BaseView;
import com.haidaoservice.lib.base.JsonEntity;

import rx.Observable;

/**
 * * 验证码需求接口-ALL
 * <p>
 * Created by zhangfei on 2017/3/27.
 */

public interface CodeContract {

    /**
     * 验证码 -获取与验证接口
     */
    interface CodeModel extends BaseModel {
        /**
         * 获取验证码
         *
         * @param params 参数集合字符串
         * @return
         */
        Observable<JsonEntity> getVCode(String params);

        /**
         * 验证验证码
         *
         * @param params 参数集合字符串
         * @return
         */
        Observable checkVCode(String params);
    }

    /**
     * 验证码 -响应接口
     */
    interface CodeView extends BaseView {


        /**
         * 获取短信验证码成功
         */
        void getSuccess();

        /**
         * 验证成功
         */
        void checkSuccess();

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
     * 验证码 -数据处理
     */
    abstract class CodePresenter extends BasePresenter<CodeModel, CodeView> {

        /**
         * 获取验证码
         *
         * @param phone 手机号
         * @param type  验证码用 0：注册； 1：重置密码；
         * @return
         */
        public abstract void getVCode(String phone, int type);

        /**
         * 验证验证码
         *
         * @param phone 手机号
         * @param type  验证码用 0：注册； 1：重置密码；
         * @param VCode 验证码
         * @return
         */
        public abstract void checkVCode(String phone, int type, String VCode);

        /**
         * 开始验证码倒计时
         */
        public abstract void startCheckTimer();

        /**
         * 验证码倒计时结束
         */
        public abstract void endCheckTimer();

    }
}
