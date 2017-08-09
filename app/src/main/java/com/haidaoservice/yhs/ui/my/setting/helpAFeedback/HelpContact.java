package com.haidaoservice.yhs.ui.my.setting.helpAFeedback;

import com.haidaoservice.lib.base.BaseModel;
import com.haidaoservice.lib.base.BasePresenter;
import com.haidaoservice.lib.base.BaseView;

import rx.Observable;

/**
 * author:davidinchina on 2017/8/8 09:42
 * email:davicdinchina@gmail.com
 * version:1.0.0
 * des:帮助反馈接口声明
 */
public interface HelpContact {

    /**
     * @author davidinchina
     * @description 数据交互接口声明
     */
    interface HelpContactModel extends BaseModel {
        /**
         * 数据交互方法示例
         *
         * @param params
         * @return
         */
        Observable<Object> query(String params);
    }

    /**
     * @author davidinchina
     * @description 页面交互接口声明
     */
    interface HelpContactView extends BaseView {

        /**
         * 页面交互方法示例
         *
         * @param result
         */
        void querySuccess(Object result);
    }

    /**
     * @author davidinchina
     * @description 交互控制接口声明
     */
    abstract class HelpContactPresenter extends BasePresenter<HelpContactModel, HelpContactView> {
        /**
         * 控制交互方法示例
         */
        public abstract void query();
    }
}
