package com.gistandard.transport.app.dubbo.pojo.res;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.io.Serializable;

/**
 * 洼站获取二维码dubbo返回
 * @author 员伟
 */
public class WaQRCodeDubboResBean extends MsDubboResBean implements Serializable {


    private static final long serialVersionUID = -172732583749326614L;

    public WaQRCodeDubboResBean(MsDubboReqBean appBaseRequest) {
        super(appBaseRequest);
    }

    public WaQRCodeDubboResBean() {
        super();
    }

    private Integer qrCodeAccountId;//生成二维码的账号id

    private String qrCodeAcctUserName;//生成二维码的当前登陆账号

    private String stationNo;//站点编号

    private String serialNumber;//流水号

    private String dateFormat;//格式化后时间yyMMdd

    private String qrCodeInfo;//二维码内容(站点编号+格式化后时间+流水号+生成二维码的当前登陆账号)

    private String data;//二维码内容(站点编号+格式化后时间+流水号+生成二维码的当前登陆账号)


    public Integer getQrCodeAccountId() {
        return qrCodeAccountId;
    }

    public void setQrCodeAccountId(Integer qrCodeAccountId) {
        this.qrCodeAccountId = qrCodeAccountId;
    }

    public String getQrCodeAcctUserName() {
        return qrCodeAcctUserName;
    }

    public void setQrCodeAcctUserName(String qrCodeAcctUserName) {
        this.qrCodeAcctUserName = qrCodeAcctUserName;
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