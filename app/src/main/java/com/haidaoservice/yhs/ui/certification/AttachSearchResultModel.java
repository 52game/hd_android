package com.haidaoservice.yhs.ui.certification;

import com.haidaoservice.lib.base.BaseModel;

/**
 * ClassName: AttachSearchResultModel
 * Description: say something
 * Creator: chenwei
 * Date: 2017/8/3 14:10
 * Version: 1.0
 */
public class AttachSearchResultModel implements BaseModel {
    /**
     * 仅显示标题
     */
    public static final int DISPLAY_TITLE = 0;
    /**
     * 企业
     */
    public static final int DISPLAY_ENTERPRISE = 1;
    /**
     * 法人
     */
    public static final int DISPLAY_LEGAL_PERSON = 2;

    private String name;
    private String legalPerson;
    /**
     * 显示类型
     * @see #DISPLAY_TITLE
     * @see #DISPLAY_ENTERPRISE
     * @see #DISPLAY_LEGAL_PERSON
     */
    private int displayType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }
}
