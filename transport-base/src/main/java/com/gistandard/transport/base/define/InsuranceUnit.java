package com.gistandard.transport.base.define;

/**
 * Created by m on 2016/7/12.
 */
public enum InsuranceUnit {
    JUAN("卷","018","08"),
    PAN("盘","031","08"),
    XIAN("箱","120","01"),
    GUAN("罐","122","07"),
    TONG("桶","123","06"),
    BAO("包","125","08"),
    DAI("袋","136","02");

    private String unitName;
    private String unitCode;
    private String insuranceCode;
    InsuranceUnit(String unitName,String unitCode,String insuranceCode ){
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.insuranceCode = insuranceCode;
    }
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public static String getInsuranceCode(String unitCode) {
        for (InsuranceUnit c : InsuranceUnit.values()) {
            if (c.unitCode.equals(unitCode)) {
                return c.insuranceCode;
            }
        }
        return null;
    }
}


