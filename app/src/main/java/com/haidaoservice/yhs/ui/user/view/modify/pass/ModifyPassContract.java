package com.haidaoservice.yhs.ui.user.view.modify.pass;

import com.haidaoservice.lib.base.BaseModel;
import com.haidaoservice.lib.base.BasePresenter;
import com.haidaoservice.lib.base.BaseView;

import rx.Observable;

/**
 * 修改密码需求接口 -ALL
 * <p>
 * Created by zhangfei on 2017/4/10.
 */

public interface ModifyPassContract {
    /**
     * 修改密码 -修改接口
     */
    interface ModifyPassModel extends BaseModel {

        /**
         * 修改密码
         *
         * @param params 参数集合字符串
         * @return
         */
        Observable modifyPassword(String params);
    }

    /**
     * 忘记密码-响应接口
     */
    interface ModifyPassView extends BaseView {

        /**
         * 修改成功
         */
        void success();
    }

    /**
     * 忘记密码-数据处理
     */
    abstract class ModifyPassPresenter extends BasePresenter<ModifyPassModel, ModifyPassView> {


        /**
         * 修改密码
         *
         * @param oldPass 就密码
         * @param pass    新密码
         * @return
         */
        public abstract void modifyPassword(String oldPass, String pass);

    }
}
