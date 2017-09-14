package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: RouteStep
 * @Date: 2017/5/18
 * @Description: I单各路由参与人员对象
 */
public class RouteStep implements Serializable{
    private static final long serialVersionUID = 9068468524304935354L;

    private String gfUserFromCode;//收款客户编号
    private String gfUserFromName;//收款客户姓名
    private String gfUserToCode;  //付款客户编号
    private String gfUserToName;  //付款客户姓名
    private String startAccountId;//起始accountId 咪站为23 起点、终点为-1
    private String endAccountId;  //终点accountId 咪站为23 起点、终点为-1
    private String operateCode;   //操作人账号

    public String getGfUserFromCode() {
        return gfUserFromCode;
    }

    public void setGfUserFromCode(String gfUserFromCode) {
        this.gfUserFromCode = gfUserFromCode;
    }

    public String getGfUserFromName() {
        return gfUserFromName;
    }

    public void setGfUserFromName(String gfUserFromName) {
        this.gfUserFromName = gfUserFromName;
    }

    public String getGfUserToCode() {
        return gfUserToCode;
    }

    public void setGfUserToCode(String gfUserToCode) {
        this.gfUserToCode = gfUserToCode;
    }

    public String getGfUserToName() {
        return gfUserToName;
    }

    public void setGfUserToName(String gfUserToName) {
        this.gfUserToName = gfUserToName;
    }

    public String getStartAccountId() {
        return startAccountId;
    }

    public void setStartAccountId(String startAccountId) {
        this.startAccountId = startAccountId;
    }

    public String getEndAccountId() {
        return endAccountId;
    }

    public void setEndAccountId(String endAccountId) {
        this.endAccountId = endAccountId;
    }

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }
}























