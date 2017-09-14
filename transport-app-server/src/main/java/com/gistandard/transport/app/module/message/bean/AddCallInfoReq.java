package com.gistandard.transport.app.module.message.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author: xgw
 * @ClassName: AddCallInfoReq
 * @Date: 2017/2/14
 * @Description: 新增通话记录请求参数
 */
@ApiModel(description = "登录参数对象", parent = AppBaseRequest.class)
public class AddCallInfoReq extends AppBaseRequest{

    @ApiModelProperty(value = "拨打电话发起时间",required = true, position = 1)
    private Date createDate;//拨打电话发起时间

    @ApiModelProperty(value = "拨打电话号码",required = false, position = 2)
    private String createTel;//拨打电话号码

    @ApiModelProperty(value = "接电话人账号ID",required = false, position = 3)
    private Integer answerUserId;//接电话人账号ID

    @ApiModelProperty(value = "接电话人账号",required = false, position = 4)
    private String answerUser;//接电话人账号

    @ApiModelProperty(value = "接电话号码",required = true, position = 5)
    private String answerTel;//接电话号码

    @ApiModelProperty(value = "电话来源：1、找人下单",required = true, position = 6)
    private Integer callFrom;//电话来源：1、找人下单

    @ApiModelProperty(value = "手机类型：1、Android；2、IOS",required = true, position = 7)
    private Integer phoneType;//手机类型：1、Android；2、IOS

    @ApiModelProperty(value = "通话时间：单位秒",required = false, position = 8)
    private Integer talkTime;//通话时间：单位秒

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateTel() {
        return createTel;
    }

    public void setCreateTel(String createTel) {
        this.createTel = createTel;
    }

    public Integer getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(Integer answerUserId) {
        this.answerUserId = answerUserId;
    }

    public String getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(String answerUser) {
        this.answerUser = answerUser;
    }

    public String getAnswerTel() {
        return answerTel;
    }

    public void setAnswerTel(String answerTel) {
        this.answerTel = answerTel;
    }

    public Integer getCallFrom() {
        return callFrom;
    }

    public void setCallFrom(Integer callFrom) {
        this.callFrom = callFrom;
    }

    public Integer getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Integer phoneType) {
        this.phoneType = phoneType;
    }

    public Integer getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Integer talkTime) {
        this.talkTime = talkTime;
    }
}
