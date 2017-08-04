package com.haidaoservice.yhs.ui.user;

import net.nashlegend.anypref.annotations.PrefModel;
import net.nashlegend.anypref.annotations.PrefSub;

/**
 * 实体 - 用户
 * <p>
 * Created by zhangfei on 2017/3/22.
 */

@PrefModel("pref_user")
public class User {

    /**
     * 用户编码
     */
    @PrefSub(nullable = false)
    public String userId;
    /**
     * 用户名
     */
    @PrefSub(nullable = false)
    public String userName;
    /**
     * 用户登录时返回的token
     */
    @PrefSub(nullable = false)
    public String userToken;
    /**
     * 用户头像
     */
    @PrefSub(nullable = false)
    public String userIcon;
    /**
     * 用户密码
     */
    @PrefSub(nullable = false)
    public String pass;
    /**
     * 及时聊天账户
     */
    @PrefSub(nullable = false)
    public String IMaccount;
    /**
     * 性别 性别 0：男 1：女 2：未知
     */
    public int sex;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public int getSex() {
        return sex;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIMaccount() {
        return IMaccount;
    }
}
