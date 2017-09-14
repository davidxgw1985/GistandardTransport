package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;

/**
 * Created by m on 2017/2/6.
 */
public class OrderTraceDetail  implements Serializable {
    private static final long serialVersionUID = 7732656402171833366L;
    private String stadate;   //时间
    private String remark;//日志备注
    private Integer id;
    public String getStadate() {
        return stadate;
    }

    public void setStadate(String stadate) {
        this.stadate = stadate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
