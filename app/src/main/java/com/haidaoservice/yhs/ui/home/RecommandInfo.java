package com.haidaoservice.yhs.ui.home;

import com.haidaoservice.lib.base.BaseModel;

/**
 * ClassName: RecommandInfo
 * Description: say something
 * Creator: chenwei
 * Date: 2017/8/4 17:54
 * Version: 1.0
 */
public class RecommandInfo implements BaseModel {
    private String cover;
    private String title;
    private String des;
    private String address;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
