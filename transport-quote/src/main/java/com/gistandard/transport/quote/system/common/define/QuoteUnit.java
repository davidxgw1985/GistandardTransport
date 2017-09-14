package com.gistandard.transport.quote.system.common.define;

/**
 * Created by shenzhijun on 2016/2/29.
 */
public enum QuoteUnit {
    KG("035", "035","千克"), CM("147", "147","千米");

    // 成员变量
    private String unitCode;
    private String unitEn;
    private String unitCh;

    // 构造方法
    private QuoteUnit(String unitCode, String unitEn,String unitCh) {
        this.unitCode = unitCode;
        this.unitEn = unitEn;
        this.unitCh = unitCh;
    }

    // 普通方法
    public static String getUnitCh(String unitCode) {
        for (QuoteUnit c : QuoteUnit.values()) {
            if (c.getUnitCode().equals(unitCode)) {
                return c.unitCh;
            }
        }
        return null;
    }


    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitEn() {
        return unitEn;
    }

    public void setUnitEn(String unitEn) {
        this.unitEn = unitEn;
    }

    public String getUnitCh() {
        return unitCh;
    }

    public void setUnitCh(String unitCh) {
        this.unitCh = unitCh;
    }
}
