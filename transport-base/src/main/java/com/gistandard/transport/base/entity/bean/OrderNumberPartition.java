package com.gistandard.transport.base.entity.bean;

import java.util.Date;

public class OrderNumberPartition {
    private Integer id;

    private Integer cityId;

    private Integer partitionNo;

    private Integer nextSeq;

    private String numberSequence;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getPartitionNo() {
        return partitionNo;
    }

    public void setPartitionNo(Integer partitionNo) {
        this.partitionNo = partitionNo;
    }

    public Integer getNextSeq() {
        return nextSeq;
    }

    public void setNextSeq(Integer nextSeq) {
        this.nextSeq = nextSeq;
    }

    public String getNumberSequence() {
        return numberSequence;
    }

    public void setNumberSequence(String numberSequence) {
        this.numberSequence = numberSequence;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}