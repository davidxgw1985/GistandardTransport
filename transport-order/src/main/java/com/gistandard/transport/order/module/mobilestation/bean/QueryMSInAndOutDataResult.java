package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;

/**
 * Created by yjf on 2016/4/29.
 */
public class QueryMSInAndOutDataResult implements Serializable {

    /**
     * 执行成功，失败标记
     */
    private Boolean succeed;

    /**
     * 执行异常次数
     */
    private Integer errNum;

    /**
     * 执行结果描述
     */
    private String message;

    /**
     * 执行结果
     */
    private QueryMSBean refObject;

    public Boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public Integer getErrNum() {
        return errNum;
    }

    public void setErrNum(Integer errNum) {
        this.errNum = errNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public QueryMSBean getRefObject() {
        return refObject;
    }

    public void setRefObject(QueryMSBean refObject) {
        this.refObject = refObject;
    }
}
