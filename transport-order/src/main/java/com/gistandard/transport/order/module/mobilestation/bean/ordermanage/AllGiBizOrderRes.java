package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.transport.system.gps.bean.GiBizOrder;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: AllGiBizOrderRes
 * @Date: 2017/2/4
 * @Description:
 */
public class AllGiBizOrderRes implements Serializable{
    private static final long serialVersionUID = 2165589315501474321L;
    private int count;
    private List<GiBizOrder> allGiBizOrder;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<GiBizOrder> getAllGiBizOrder() {
        return allGiBizOrder;
    }

    public void setAllGiBizOrder(List<GiBizOrder> allGiBizOrder) {
        this.allGiBizOrder = allGiBizOrder;
    }
}
