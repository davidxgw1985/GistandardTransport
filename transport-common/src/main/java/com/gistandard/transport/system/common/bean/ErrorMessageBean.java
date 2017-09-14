package com.gistandard.transport.system.common.bean;

/**
 * @author yujie
 * @ClassName ErrorMessageBean
 * @Description
 * @Version 1.0
 * @Date 2015-06-09
 */
public class ErrorMessageBean {

    private String errorField;

    private String errorMessage;

    private String errorObject;

    public String getErrorObject() {
        return errorObject;
    }

    public void setErrorObject(String errorObject) {
        this.errorObject = errorObject;
    }

    public String getErrorField() {
        return errorField;
    }

    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
