package com.haidaoservice.yhs.ui.certification.car;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * ClassName: CarModel
 * Description: 车型
 * Creator: chenwei
 * Date: 2017/8/4 09:59
 * Version: 1.0
 */
public class CarModel implements IndexableEntity {
    private String logo;
    private String name;

    public CarModel(String logo, String name) {
        this.logo = logo;
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getFieldIndexBy() {
        return name;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        this.name = indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }
}
