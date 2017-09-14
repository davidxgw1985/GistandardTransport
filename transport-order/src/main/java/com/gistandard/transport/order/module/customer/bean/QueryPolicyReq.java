package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

/**
 * Created by m on 2016/7/14.
 */
public class QueryPolicyReq  extends AppBaseRequest implements ValidTokenMark{
    private String unitCode;//分公司代码
    private String applyNo;//投保单号
    private String applyEndOrseNo;//批改申请号 查询投保单审核结果时，此字段可为空。查询批单审核结果则必填
    private String busiBookNo; //订单的流水号
    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getApplyEndOrseNo() {
        return applyEndOrseNo;
    }

    public void setApplyEndOrseNo(String applyEndOrseNo) {
        this.applyEndOrseNo = applyEndOrseNo;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
