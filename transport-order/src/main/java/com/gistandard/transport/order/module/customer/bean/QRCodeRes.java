package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

import java.io.Serializable;

/**
 * 生成二维码响应模型
 * <p>咪站、蛙站在二维码生成入口，界面上生成1个签到二维码<p/>
 * @author 员伟
 */
public class QRCodeRes extends AppBaseResult implements Serializable {

    private static final long serialVersionUID = -5134517241096583054L;

    private Integer qrCodeAccountId;//生成二维码的账号id

    private String qrCodeAcctUserName;//生成二维码的当前登陆账号

    private String stationNo;//站点编号

    private String serialNumber;//流水号

    private String dateFormat;//格式化后时间yyMMdd

    private String qrCodeInfo;//二维码内容(站点编号+格式化后时间+流水号+生成二维码的当前登陆账号)

    private String data;


    public QRCodeRes() {
        super();
    }

    public QRCodeRes(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public Integer getQrCodeAccountId() {
        return qrCodeAccountId;
    }

    public void setQrCodeAccountId(Integer qrCodeAccountId) {
        this.qrCodeAccountId = qrCodeAccountId;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getQrCodeAcctUserName() {
        return qrCodeAcctUserName;
    }

    public void setQrCodeAcctUserName(String qrCodeAcctUserName) {
        this.qrCodeAcctUserName = qrCodeAcctUserName;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getQrCodeInfo() {
        return qrCodeInfo;
    }

    public void setQrCodeInfo(String qrCodeInfo) {
        this.qrCodeInfo = qrCodeInfo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
