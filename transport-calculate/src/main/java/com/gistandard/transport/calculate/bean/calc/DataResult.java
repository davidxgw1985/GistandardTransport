package com.gistandard.transport.calculate.bean.calc;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: DataResult
 * @Date: 2016/12/2
 * @Description:
 */
public class DataResult {
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
    private List<CalcDataResult> refObject;

    public Boolean getSucceed() {
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

    public List<CalcDataResult> getRefObject() {
        return refObject;
    }

    public void setRefObject(List<CalcDataResult> refObject) {
        this.refObject = refObject;
    }
}
