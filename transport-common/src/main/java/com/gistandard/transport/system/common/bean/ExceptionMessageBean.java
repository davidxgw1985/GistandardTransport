package com.gistandard.transport.system.common.bean;

/**
 * @author yujie
 * @ClassName ExceptionMessageBean
 * @Description
 * @Version 1.0
 * @Date 2015-06-29
 */
public class ExceptionMessageBean {

    private int exCode;

    private Integer exNo;

    private String message;

    public int getExCode() {
        return exCode;
    }

    public void setExCode(int exCode) {
        this.exCode = exCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getExNo() {
        return exNo;
    }

    public void setExNo(Integer exNo) {
        this.exNo = exNo;
    }
}
