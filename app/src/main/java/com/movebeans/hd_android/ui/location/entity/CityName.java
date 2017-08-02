package com.movebeans.hd_android.ui.location.entity;

/**
 * Created by tr on 2017/8/2.
 */

public class CityName implements Comparable<CityName>{
    private String name;
    private String pinyin;

    public CityName(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return "CityName{" +
                "name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
    @Override
    public int compareTo(CityName city) {
        return this.pinyin.compareTo(city.pinyin);
    }

}
