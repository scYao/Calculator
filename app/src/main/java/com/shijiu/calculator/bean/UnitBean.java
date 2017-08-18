package com.shijiu.calculator.bean;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class UnitBean {
    private String unit;
    private Integer imgae;

    public UnitBean(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getImgae() {
        return imgae;
    }

    public void setImgae(Integer imgae) {
        this.imgae = imgae;
    }

    @Override
    public String toString() {
        return "UnitBean{" +
                "unit='" + unit + '\'' +
                ", imgae=" + imgae +
                '}';
    }
}
