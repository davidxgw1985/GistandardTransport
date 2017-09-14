package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: QueryOrdersCalcListRes
 * @Date: 2017/5/18
 * @Description: I单结算批量查询返回Bean
 */
public class QueryOrdersCalcListRes implements Serializable {
    private static final long serialVersionUID = -3252148763364266314L;

    private List<QueryOrdersCalcListResBean> calcList;//结算列表

    public List<QueryOrdersCalcListResBean> getCalcList() {
        return calcList;
    }

    public void setCalcList(List<QueryOrdersCalcListResBean> calcList) {
        this.calcList = calcList;
    }
}
