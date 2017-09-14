package com.gistandard.transport.order.module.agency.bean;


import com.gistandard.transport.base.entity.bean.BookingForm;

import java.math.BigDecimal;

/**
 * @author yjf
 * @ClassName BookingFormCustomer
 * @Description 客户下单
 * @Version 1.0
 * @Date 2016/3/14
 */
public class BookingFormCustomer extends BookingForm {
    private BigDecimal staLongitude;
    private BigDecimal staLatitude;
    private String itemName;

    public BigDecimal getStaLongitude() {
        return staLongitude;
    }

    public void setStaLongitude(BigDecimal staLongitude) {
        this.staLongitude = staLongitude;
    }

    public BigDecimal getStaLatitude() {
        return staLatitude;
    }

    public void setStaLatitude(BigDecimal staLatitude) {
        this.staLatitude = staLatitude;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
