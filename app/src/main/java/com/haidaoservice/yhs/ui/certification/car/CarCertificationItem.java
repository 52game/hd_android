package com.haidaoservice.yhs.ui.certification.car;

import com.haidaoservice.yhs.ui.certification.CertificationModel;

/**
 * ClassName: CarCertificationItem
 * Description: 认证中心中车辆认证列表实体
 * Creator: chenwei
 * Date: 2017/8/2 12:16
 * Version: 1.0
 */
public class CarCertificationItem extends CertificationModel {
    /**
     * 车辆图标
     */
    private String icon;
    /**
     * 车辆名称
     */
    private String name;
    /**
     * 车牌
     */
    private String licence;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }
}
