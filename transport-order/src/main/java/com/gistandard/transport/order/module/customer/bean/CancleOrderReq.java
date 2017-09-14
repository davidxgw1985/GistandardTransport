
package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.io.Serializable;

/**
 * 用户取消订单模型
 * @author 员伟
 */
public class CancleOrderReq extends AppBaseRequest implements Serializable, ValidTokenMark {


    private String productType;//产品类型

    private String busiBookNo;//订单编号

    private Integer orderId;//订单id

    private String reason; //取消原因


    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
