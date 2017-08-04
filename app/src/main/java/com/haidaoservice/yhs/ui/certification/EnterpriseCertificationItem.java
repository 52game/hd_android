package com.haidaoservice.yhs.ui.certification;

/**
 * ClassName: CarCertificationItem
 * Description: 认证中心中企业认证列表实体
 * Creator: chenwei
 * Date: 2017/8/2 12:16
 * Version: 1.0
 */
public class EnterpriseCertificationItem extends CertificationModel {
    /**
     * 企业认证
     */
    public final static int TYPE_ENTERPRISE_CERTIFICATION = 0;
    /**
     * 挂靠企业
     */
    public final static int TYPE_ATTACH_ENTERPRISE = 1;
    /**
     * 企业图标
     */
    private String icon;
    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业类型
     */
    private int type;

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

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
