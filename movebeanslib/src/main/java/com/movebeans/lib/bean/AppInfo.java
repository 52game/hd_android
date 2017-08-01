package com.movebeans.lib.bean;

import android.graphics.drawable.Drawable;

/**
 * author:davidinchina on 2017/7/17 16:10
 * email:davicdinchina@gmail.com
 * tip:
 */
public class AppInfo {
    private String appName;
    private Drawable appIcon;
    private String pkgName;
    private String appSize;//app大小,单位：M
    private String updateDate;//多久未更新
    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public AppInfo() {
    }
}
