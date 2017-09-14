package com.gistandard.transport.app.dubbo.fleet.bean;

import java.io.Serializable;

/**
 * 车队分发业务-车辆订单参数
 * @author 员伟
 */
public class VehicleOrder implements Serializable {


    private static final long serialVersionUID = 2860804524326739634L;

    private String docNo;//分发的订单编号

    private String productType;//产品类型 OTCZS OTCYSEX ITCYS

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
