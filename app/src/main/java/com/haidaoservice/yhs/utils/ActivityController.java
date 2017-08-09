package com.haidaoservice.yhs.utils;

import com.haidaoservice.yhs.base.BaseActivity;
import com.haidaoservice.yhs.ui.user.view.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * author:davidinchina on 2017/7/17 09:40
 * email:davicdinchina@gmail.com
 * tip:
 */
public class ActivityController {
    private static List<BaseActivity> activities = new ArrayList<>();

    public static void addActivity(BaseActivity activity) {
        activities.add(activity);
    }

    public static void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }

    /**
     * 清除所有活动，退出应用
     */
    public static void quitSystem() {
        for (BaseActivity activity :
                activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }

    /**
     * 返回登录页
     *
     * @param currentActivity
     */
    public static void restartSystem(BaseActivity currentActivity) {
        for (BaseActivity activity :
                activities) {
            if (activity != currentActivity && !activity.isFinishing()) {
                activity.finish();//先清除其它活动
            }
        }
        currentActivity.startActivity(LoginActivity.createIntent(currentActivity));//打开登录页面
        currentActivity.finish();//关闭当前活动
        activities.clear();
    }

    /**
     * 结束当前活动之前的活动，用来调试崩溃信息
     *
     * @param currentActivity
     */
    public static void finishBefore(BaseActivity currentActivity) {
        for (BaseActivity activity :
                activities) {
            if (activity != currentActivity && !activity.isFinishing()) {
                activity.finish();//先清除其它活动
            }
        }
    }
}
