package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.transport.base.bean.app.BaseReqBean;
import com.gistandard.transport.system.webservice.client.calcWebService.ValidBean;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: OrderThawStatusReq
 * @Date: 2016/8/13
 * @Description: 批量请求 待支付列表 支付前，判断是否需要解冻接口请求Bean
 */
public class OrderThawStatusReq extends BaseReqBean {
    private static final long serialVersionUID = -7857634192884485726L;

    private List<ValidBean> allValidBillMst;

    public List<ValidBean> getAllValidBillMst() {
        return allValidBillMst;
    }

    public void setAllValidBillMst(List<ValidBean> allValidBillMst) {
        this.allValidBillMst = allValidBillMst;
    }
}
