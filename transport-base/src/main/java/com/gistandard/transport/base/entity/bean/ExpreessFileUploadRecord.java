package com.gistandard.transport.base.entity.bean;

import java.util.Date;

public class ExpreessFileUploadRecord {
    private Integer id;

    private String busiBookNo;

    private Date uploadDate;

    private String uploadPeople;

    private String stationAccountUsername;

    private String filePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploadPeople() {
        return uploadPeople;
    }

    public void setUploadPeople(String uploadPeople) {
        this.uploadPeople = uploadPeople;
    }

    public String getStationAccountUsername() {
        return stationAccountUsername;
    }

    public void setStationAccountUsername(String stationAccountUsername) {
        this.stationAccountUsername = stationAccountUsername;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}