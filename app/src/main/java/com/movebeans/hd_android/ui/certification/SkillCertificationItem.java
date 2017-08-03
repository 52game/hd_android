package com.movebeans.hd_android.ui.certification;

/**
 * ClassName: SkillCertificationItem
 * Description: 认证中心中家政服务认证及企业认证列表实体
 * Creator: chenwei
 * Date: 2017/8/2 12:16
 * Version: 1.0
 */
public class SkillCertificationItem extends CertificationModel {
    /**
     * 服务图标
     */
    private String icon;
    /**
     * 名称
     */
    private String name;

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

}
