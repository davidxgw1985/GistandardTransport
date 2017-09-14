package com.gistandard.transport.order.module.mobilestation.bean.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/11/2.
 */
@ApiModel(description = "集包详情信息返回")
public class QueryDetailByPkgBean {
    @ApiModelProperty(value = "打包站点code", required = true, position = 1)
    private String packStationCode;
    @ApiModelProperty(value = "打包站点名称", required = true, position = 2)
    private String packStationName;
    @ApiModelProperty(value = "打包站点城市code", required = true, position = 3)
    private String packCityId;
    @ApiModelProperty(value = "打包站点城市名字", required = true, position = 4)
    private String packCityName;
    @ApiModelProperty(value = "打包站点联系人", required = true, position = 5)
    private String packFlinkMan;
    @ApiModelProperty(value = "打包站点联系电话", required = true, position = 6)
    private String packFworkTel;
    @ApiModelProperty(value = "上一站点code", required = true, position = 7)
    private String previousStationCode;
    @ApiModelProperty(value = "上一站点名称", required = true, position = 8)
    private String previousStationName;
    @ApiModelProperty(value = "上一站点城市code", required = true, position = 9)
    private String previousCityId;
    @ApiModelProperty(value = "上一站点城市名字", required = true, position = 10)
    private String previousCityName;
    @ApiModelProperty(value = "上一站点联系人", required = true, position = 11)
    private String previousFlinkMan;
    @ApiModelProperty(value = "上一站点联系电话", required = true, position = 12)
    private String previousFworkTel;
    @ApiModelProperty(value = "下一站点code", required = true, position = 13)
    private String nextStationCode;
    @ApiModelProperty(value = "下一站点名称", required = true, position = 14)
    private String nextStationName;
    @ApiModelProperty(value = "下一站点城市code", required = true, position = 15)
    private String nextCityId;
    @ApiModelProperty(value = "下一站点城市名字", required = true, position = 16)
    private String nextCityName;
    @ApiModelProperty(value = "下一站点联系人", required = true, position = 17)
    private String nextFlinkMan;
    @ApiModelProperty(value = "下一站点联系电话", required = true, position = 18)
    private String nextFworkTel;

    public String getPackStationCode() {
        return packStationCode;
    }

    public void setPackStationCode(String packStationCode) {
        this.packStationCode = packStationCode;
    }

    public String getPackStationName() {
        return packStationName;
    }

    public void setPackStationName(String packStationName) {
        this.packStationName = packStationName;
    }

    public String getPackCityId() {
        return packCityId;
    }

    public void setPackCityId(String packCityId) {
        this.packCityId = packCityId;
    }

    public String getPackCityName() {
        return packCityName;
    }

    public void setPackCityName(String packCityName) {
        this.packCityName = packCityName;
    }

    public String getPackFlinkMan() {
        return packFlinkMan;
    }

    public void setPackFlinkMan(String packFlinkMan) {
        this.packFlinkMan = packFlinkMan;
    }

    public String getPackFworkTel() {
        return packFworkTel;
    }

    public void setPackFworkTel(String packFworkTel) {
        this.packFworkTel = packFworkTel;
    }

    public String getPreviousStationCode() {
        return previousStationCode;
    }

    public void setPreviousStationCode(String previousStationCode) {
        this.previousStationCode = previousStationCode;
    }

    public String getPreviousStationName() {
        return previousStationName;
    }

    public void setPreviousStationName(String previousStationName) {
        this.previousStationName = previousStationName;
    }

    public String getPreviousCityId() {
        return previousCityId;
    }

    public void setPreviousCityId(String previousCityId) {
        this.previousCityId = previousCityId;
    }

    public String getPreviousCityName() {
        return previousCityName;
    }

    public void setPreviousCityName(String previousCityName) {
        this.previousCityName = previousCityName;
    }

    public String getPreviousFlinkMan() {
        return previousFlinkMan;
    }

    public void setPreviousFlinkMan(String previousFlinkMan) {
        this.previousFlinkMan = previousFlinkMan;
    }

    public String getPreviousFworkTel() {
        return previousFworkTel;
    }

    public void setPreviousFworkTel(String previousFworkTel) {
        this.previousFworkTel = previousFworkTel;
    }

    public String getNextStationCode() {
        return nextStationCode;
    }

    public void setNextStationCode(String nextStationCode) {
        this.nextStationCode = nextStationCode;
    }

    public String getNextStationName() {
        return nextStationName;
    }

    public void setNextStationName(String nextStationName) {
        this.nextStationName = nextStationName;
    }

    public String getNextCityId() {
        return nextCityId;
    }

    public void setNextCityId(String nextCityId) {
        this.nextCityId = nextCityId;
    }

    public String getNextCityName() {
        return nextCityName;
    }

    public void setNextCityName(String nextCityName) {
        this.nextCityName = nextCityName;
    }

    public String getNextFlinkMan() {
        return nextFlinkMan;
    }

    public void setNextFlinkMan(String nextFlinkMan) {
        this.nextFlinkMan = nextFlinkMan;
    }

    public String getNextFworkTel() {
        return nextFworkTel;
    }

    public void setNextFworkTel(String nextFworkTel) {
        this.nextFworkTel = nextFworkTel;
    }
}
