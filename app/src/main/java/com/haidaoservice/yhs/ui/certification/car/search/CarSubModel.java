package com.haidaoservice.yhs.ui.certification.car.search;

import com.haidaoservice.lib.base.BaseModel;

/**
 * ClassName: CarSubModel
 * Description: 品牌车型
 * Creator: chenwei
 * Date: 2017/8/4 14:54
 * Version: 1.0
 */
public class CarSubModel implements BaseModel {
    private String name;

    public CarSubModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
