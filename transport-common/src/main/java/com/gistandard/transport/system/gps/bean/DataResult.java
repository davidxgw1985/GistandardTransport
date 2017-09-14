package com.gistandard.transport.system.gps.bean;

/**
 * @author: xgw
 * @ClassName: DataResult
 * @Date: 2017/4/7
 * @Description:
 */
public class DataResult {
    /**
     * 是否成功, true:成功 false:失败
     */
    private Boolean succeed;

    /**
     * 异常值
     */
    private Integer errNum;

    /**
     * 结果描述
     */
    private String message;

    /**
     * 返回对象
     */
    private Object refObject;

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

    public Object getRefObject() {
        return refObject;
    }

    public void setRefObject(Object refObject) {
        this.refObject = refObject;
    }
}
