package com.gistandard.transport.order.module.mobilestation.bean.stock;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by m on 2016/12/2.
 */
public class StockBaseReq extends AppBaseRequest {
    private String busiNoTag; //订单号类型（四通一达 or 标准订单号）

    public String getBusiNoTag() {
        return busiNoTag;
    }

    public void setBusiNoTag(String busiNoTag) {
        this.busiNoTag = busiNoTag;
    }

    public enum BusiNoTag {
        QR_CODE("QR_CODE"),  //标准busi号
        CODE_39("CODE_39"),
        CODE_128("CODE_128");

        BusiNoTag(String code) {
            this.code = code;
        }

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
