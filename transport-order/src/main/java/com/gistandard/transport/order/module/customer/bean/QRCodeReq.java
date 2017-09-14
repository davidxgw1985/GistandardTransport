package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.util.Date;

/**
 * 快递员、司机、咪站签到
 * 二维码生成请求接口
 * @author 员伟
 */
public class QRCodeReq extends AppBaseRequest implements ValidTokenMark {

    private static final long serialVersionUID = 4733357308052582788L;
    /**
     * 当前操作人员的角色id
     */
    private Integer roleId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
