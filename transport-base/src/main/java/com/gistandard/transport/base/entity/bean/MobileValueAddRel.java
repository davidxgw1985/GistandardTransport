package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class MobileValueAddRel implements Serializable {
    private static final long serialVersionUID = -3661617955379965323L;
    private Integer id;

    private Date createDate;

    private Integer createUserId;

    private String createUser;

    private Integer bookingFormId;

    private Integer valueAddId;

    private Integer mobileBookingFormId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getBookingFormId() {
        return bookingFormId;
    }

    public void setBookingFormId(Integer bookingFormId) {
        this.bookingFormId = bookingFormId;
    }

    public Integer getValueAddId() {
        return valueAddId;
    }

    public void setValueAddId(Integer valueAddId) {
        this.valueAddId = valueAddId;
    }

    public Integer getMobileBookingFormId() {
        return mobileBookingFormId;
    }

    public void setMobileBookingFormId(Integer mobileBookingFormId) {
        this.mobileBookingFormId = mobileBookingFormId;
    }
}