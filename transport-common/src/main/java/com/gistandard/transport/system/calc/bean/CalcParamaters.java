package com.gistandard.transport.system.calc.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CalcParamaters implements Serializable{
    private static final long serialVersionUID = -6500029989984725310L;

    public static final int QuotationType_CBU = 1;//整车
    public static final int QuotationType_BulkCargo = 2;//散货
    public static final int QuotationType_Mileage = 3;//按里程
    public static final int QuotationType_MileageRange = 4;//按里程分段
    public static final int QuotationType_WeightRange = 5;//重量分段
    public static final int QuotationType_Negotiable = 6;//面议
    public static final int QuotationType_Express = 8;//快递
    public static final int QuotationType_Taxi = 101;//出租车（类似快递重量分段）

    private String orderNo;//订单号
    private BigDecimal volume;//总体积
    private String volumeUnit;//体积单位
    private BigDecimal weight;//总重量
    private String weightUnit;//总量单位
    private BigDecimal mileage;//该单配送里程
    private String mileageUnit;//里程单位
    private int quotationType;//结算方式

    private List<FormulaParameters> formualParameters;//分段报价信息

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getVolumeUnit() {
        return volumeUnit;
    }

    public void setVolumeUnit(String volumeUnit) {
        this.volumeUnit = volumeUnit;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public String getMileageUnit() {
        return mileageUnit;
    }

    public void setMileageUnit(String mileageUnit) {
        this.mileageUnit = mileageUnit;
    }

    public int getQuotationType() {
        return quotationType;
    }

    public void setQuotationType(int quotationType) {
        this.quotationType = quotationType;
    }

    public List<FormulaParameters> getFormualParameters() {
        return formualParameters;
    }

    public void setFormualParameters(List<FormulaParameters> formualParameters) {
        this.formualParameters = formualParameters;
    }
}
