package com.gistandard.transport.order.module.customer.bean;

import java.io.Serializable;

/**
 * Created by m on 2016/7/14.
 */
public class QueryPolicyRes implements Serializable {
    private static final long serialVersionUID = -8891235287161892126L;
    private String statusEpolicy;//电子保单状态 0 创建失败 1 创建成功 2 未创建
    private String fileEpolicy;//电子保单内容  BASE64 转换的二进制流文件
    private String status;//状态
    private String policyno;


    public String getStatusEpolicy() {
        return statusEpolicy;
    }

    public void setStatusEpolicy(String statusEpolicy) {
        this.statusEpolicy = statusEpolicy;
    }

    public String getFileEpolicy() {
        return fileEpolicy;
    }

    public void setFileEpolicy(String fileEpolicy) {
        this.fileEpolicy = fileEpolicy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPolicyno() {
        return policyno;
    }

    public void setPolicyno(String policyno) {
        this.policyno = policyno;
    }
}
