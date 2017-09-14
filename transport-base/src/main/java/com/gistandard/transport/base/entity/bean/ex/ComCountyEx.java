package com.gistandard.transport.base.entity.bean.ex;

/**
 * @author yujie
 * @ClassName CountyBean
 * @Description
 * @Version 1.0
 * @Date 2015-08-13
 */
public class ComCountyEx {

    private String countyId;

    private String countyName;

    private String cityId;

    private String pinYin;

    private String pinYinChar;

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getPinYinChar() {
        return pinYinChar;
    }

    public void setPinYinChar(String pinYinChar) {
        this.pinYinChar = pinYinChar;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }
}
