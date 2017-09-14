package com.gistandard.transport.quote.system.common.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExcelQuoteDetail implements Serializable{


    private Boolean tableHead;

    @Excel(name="服务类型", orderNum="1")
    @ExcelVerify(notNull = true)
    private String serviceType;

    @Excel(name="服务项目", orderNum="2")
    @Length(max=50 , message = "服务项目超过最大长度")
    private String serviceProject;

    @Excel(name="计价单位", orderNum="3")
    @ExcelVerify(notNull = true)
    @Length(max=50 , message = "计价单位最大长度")
    private String calcUnit;

    @Excel(name="报价", orderNum="4")
    @ExcelVerify(notNull = true)
    @Length(max=50 , message = "报价过大")
    private String price;

    @Excel(name="币制", orderNum="5")
    @ExcelVerify(notNull = true)
    private String currencyCode;


    private String field1;


    private String field2;


    private String field3;


    private String field4;


    private String field5;


    private String field6;


    private String field7;


    private String field8;

    private String field9;

    private String field10;


    public Boolean getTableHead() {
        return tableHead;
    }

    public void setTableHead(Boolean tableHead) {
        this.tableHead = tableHead;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceProject() {
        return serviceProject;
    }

    public void setServiceProject(String serviceProject) {
        this.serviceProject = serviceProject;
    }

    public String getCalcUnit() {
        return calcUnit;
    }

    public void setCalcUnit(String calcUnit) {
        this.calcUnit = calcUnit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    public String getField6() {
        return field6;
    }

    public void setField6(String field6) {
        this.field6 = field6;
    }

    public String getField7() {
        return field7;
    }

    public void setField7(String field7) {
        this.field7 = field7;
    }

    public String getField8() {
        return field8;
    }

    public void setField8(String field8) {
        this.field8 = field8;
    }

    public String getField9() {
        return field9;
    }

    public void setField9(String field9) {
        this.field9 = field9;
    }

    public String getField10() {
        return field10;
    }

    public void setField10(String field10) {
        this.field10 = field10;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
