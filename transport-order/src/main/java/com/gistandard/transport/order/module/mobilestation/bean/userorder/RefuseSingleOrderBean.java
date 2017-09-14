package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: SingleOrderOperateBean
 * @Date: 2016/6/11
 * @Description: MS3.0商户接单请求Bean
 */
public class RefuseSingleOrderBean implements Serializable{
    private static final long serialVersionUID = 8683266530229439496L;
    private String busiBookNo;//订单Bus号
    private Integer createUserId;//MS登录账号ID
    private String createUser;//MS登录账号
    private String teamComsixNo;//操作HUBCode
    private Integer revUserId;//操作人账号ID
    private String revUser;//操作人登录账号

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getTeamComsixNo() {
        return teamComsixNo;
    }

    public void setTeamComsixNo(String teamComsixNo) {
        this.teamComsixNo = teamComsixNo;
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

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }
}
