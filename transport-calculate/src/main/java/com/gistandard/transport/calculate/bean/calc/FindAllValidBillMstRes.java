package com.gistandard.transport.calculate.bean.calc;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: FindAllValidBillMstBean
 * @Date: 2016/3/15
 * @Description: 查询待支付任务返回Bean
 */
public class FindAllValidBillMstRes implements Serializable {
    private static final long serialVersionUID = -8967123648251067462L;
    private boolean succeed;//执行状态
    private String message;//如果失败，失败原因
    private Integer totalRecords;//总条数
    private List<ValidBillMst> allValidBillMst;//应付对账单列表信息；如果succeed==true, 才会有ValidBillMst

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<ValidBillMst> getAllValidBillMst() {
        return allValidBillMst;
    }

    public void setAllValidBillMst(List<ValidBillMst> allValidBillMst) {
        this.allValidBillMst = allValidBillMst;
    }
}
