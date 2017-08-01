package com.movebeans.hd_android.ui.user;


import net.nashlegend.anypref.AnyPref;

/**
 * ClassName: UserSessionManager
 * Description: 用户信息获取
 * Creator: chenwei
 * Date: 16/9/3 12:12
 * Version: 1.0
 */
public class UserSessionManager {

    private static class UserSessionManagerInstance {
        public static UserSessionManager mManager = new UserSessionManager();
    }

    /**
     * 是否已经读取
     */
    private boolean isLoad = false;
    /**
     * 当前登录用户对象
     */
    private User userInfo;

    private UserSessionManager() {
        userInfo = getUserInfo();
    }

    public static UserSessionManager getInstance() {
        return UserSessionManagerInstance.mManager;
    }

    /**
     * 是否有登录的用户
     *
     * @return
     */
    public boolean hasUser() {
        return userInfo != null && AnyPref.getDefault().getBoolean("login_state", false);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public User getUserInfo() {
        if (isLoad) {
            return userInfo;
        }
        userInfo = AnyPref.get(User.class, "pref_user", true);
        isLoad = true;
        return userInfo;
    }

    /**
     * 保存用户信息
     *
     * @param userInfo
     */
    public void saveUserInfo(User userInfo) {
        this.userInfo = userInfo;
        isLoad = true;
        AnyPref.put(userInfo, "pref_user");
    }

    /**
     * 清除用户信息
     */
    public void clearUserInfo() {
        userInfo = null;
        isLoad = false;
        AnyPref.clear(User.class, "pref_user");
    }

    /**
     * 退出登录
     */
    public void logout() {
        AnyPref.getDefault().putBoolean("login_state", false).commit();
    }

    /**
     * 成功登录
     */
    public void login() {
        AnyPref.getDefault().putBoolean("login_state", true).commit();
    }
}
