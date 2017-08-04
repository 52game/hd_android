package com.haidaoservice.yhs.ui.user;

/**
 * Created by zhangfei on 2017/4/17.
 */

public class UserConstants {

    /**
     * 默认倒计时长
     **/
    public static final int DEFAULT_CHECK_TIME_LENGTH = 60;


    public class Extra {
        public static final String EXTRA_INTEGER_CODE_TYPE = "code_type";
        public static final String EXTRA_STRING_PHONE = "phone";
        public static final String EXTRA_STRING_CODE = "code";
    }

    public enum CodeType {
        /**
         * 注册验证码
         */
        TYPE_REGISTER(0),

        /**
         * 重置密码验证码
         */
        TYPE_RESET_PASS(1);

        Integer value;

        CodeType(Integer value) {
            this.value = value;
        }

        public Integer value() {
            return value;
        }
    }

    public enum ThirdType {
        /**
         * qq
         **/
        TYPE_QQ(0),
        /**
         * 微信
         **/
        TYPE_WX(1),
        /**
         * 微信
         **/
        TYPE_SINA(2);

        /**
         * 编号
         **/
        private Integer value;

        ThirdType(Integer value) {
            this.value = value;
        }

        public Integer value() {
            return value;
        }
    }
}
