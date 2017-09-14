package com.gistandard.transport.system.calc.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class FormulaParameters implements Serializable{

    private static final long serialVersionUID = -2050727568492303586L;
    private BigDecimal startRangeValue;//开始范围数字
    private BigDecimal endRangeValue;//截至范围数字
    private String rangeUnit;//范围
    private BigDecimal price;//单价
    private String currency;//货币单位
    private int formula;//类型	1：重量 2：体积 3：里程
    private BigDecimal addScalar;//标量
    private int scalarUnit;//类型 1 公里 2 重量 0 两者

    /**
     * 备注：1，如果整车，则List<FormulaParameters>的长度为1，并且只有Price 和 Currency为必填,其他可不填。
     *       2,如果零担，则List<FormulaParameters>长度为2，并且只有StartRangeValue,RangeUnit,Price,Currency,Formula为必填。
     *         Formula=1代表重货价格，Formula=2代表轻货价格。
     *       3,如果是按里程保价，则List<FormulaParameters>长度为1，并且只有StartRangeValue,RangeUnit,Price,Currency为必填，其他可不填。
     *       4,如果是里程分段，则List<FormulaParameters>长度大于或等于1，全部字段都必填，并且公式的顺序和List<FormulaParameters>的序号保持一致。
     *       5,如果是重量分段，则其规则和4一致。
     **/

    public BigDecimal getStartRangeValue() {
        return startRangeValue;
    }

    public void setStartRangeValue(BigDecimal startRangeValue) {
        this.startRangeValue = startRangeValue;
    }

    public BigDecimal getEndRangeValue() {
        return endRangeValue;
    }

    public void setEndRangeValue(BigDecimal endRangeValue) {
        this.endRangeValue = endRangeValue;
    }

    public String getRangeUnit() {
        return rangeUnit;
    }

    public void setRangeUnit(String rangeUnit) {
        this.rangeUnit = rangeUnit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getFormula() {
        return formula;
    }

    public void setFormula(int formula) {
        this.formula = formula;
    }

    public BigDecimal getAddScalar() {
        return addScalar;
    }

    public void setAddScalar(BigDecimal addScalar) {
        this.addScalar = addScalar;
    }

    public int getScalarUnit() {
        return scalarUnit;
    }

    public void setScalarUnit(int scalarUnit) {
        this.scalarUnit = scalarUnit;
    }
}
