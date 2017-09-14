package com.gistandard.transport.order.stock.bean;

import com.gistandard.transport.app.dubbo.order.bean.MobileSubOrder;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileOrderBean
 * @Date: 2017/5/22
 * @Description:
 */
public class MobileOrderBean extends MobileBookingForm{
    private static final long serialVersionUID = 1645736618205894188L;

    private List<MobileSubOrder> subOrderList;

    public List<MobileSubOrder> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<MobileSubOrder> subOrderList) {
        this.subOrderList = subOrderList;
    }
}
