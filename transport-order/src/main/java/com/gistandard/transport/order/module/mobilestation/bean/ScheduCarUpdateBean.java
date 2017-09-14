package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: ScheduCarUpdateBean
 * @Date: 2016/3/25
 * @Description:
 */
public class ScheduCarUpdateBean implements Serializable{
    private static final long serialVersionUID = -2828077148416081545L;

    private String scheducarno;//实派车单号
    private Integer scheduStatus;//派车单状态
    private Integer oldStatus;//派车单旧状态
    private Date startDate;//发车时间
    private Date arrivalDate;//实际到达时间

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getScheduStatus() {
        return scheduStatus;
    }

    public void setScheduStatus(Integer scheduStatus) {
        this.scheduStatus = scheduStatus;
    }

    public Integer getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
