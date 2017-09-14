package com.gistandard.transport.order.module.mobilestation.bean.userorder;


import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: BatchAssignReq
 * @Date: 2016/6/11
 * @Description: 转单中心-批量转单接口
 */
public class BatchAssignReq extends AppBaseRequest {
    private static final long serialVersionUID = -75312541463028798L;

    private Integer toGFuserID;//被指派的HUB账户ID
    private String toGFuserName;//被指派的HUB账户名称
    private String toGFuserTTL;//被指派的HUB简称
    private String narrate;  //操作要求
    private List<AssignOrderBean> orderBeanList;//批量指派的待转单列表

    public String getToGFuserName() {
        return toGFuserName;
    }

    public void setToGFuserName(String toGFuserName) {
        this.toGFuserName = toGFuserName;
    }

    public String getToGFuserTTL() {
        return toGFuserTTL;
    }

    public void setToGFuserTTL(String toGFuserTTL) {
        this.toGFuserTTL = toGFuserTTL;
    }

    public Integer getToGFuserID() {
        return toGFuserID;
    }

    public void setToGFuserID(Integer toGFuserID) {
        this.toGFuserID = toGFuserID;
    }

    public List<AssignOrderBean> getOrderBeanList() {
        return orderBeanList;
    }

    public void setOrderBeanList(List<AssignOrderBean> orderBeanList) {
        this.orderBeanList = orderBeanList;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }
}
