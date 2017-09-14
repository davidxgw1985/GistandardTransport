package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

/**
 * @author: xgw
 * @ClassName: MobileOrderListReq
 * @Date: 2016/12/12
 * @Description: 订单管理 列表查询请求对象
 */
public class MobileOrderListReq extends AppBasePagerRequest {

    private Integer orderStatue;
    private Integer roleId; //当前角色  3 司机、7 快递员、23咪站
    private String busiBookNo;  //订单号

    public Integer getOrderStatue() {
        return orderStatue;
    }

    public void setOrderStatue(Integer orderStatue) {
        this.orderStatue = orderStatue;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

}
