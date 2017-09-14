package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by m on 2017/2/9.
 */
public class WeChatPlatFormOutModel implements Serializable {
    private static final long serialVersionUID = -5512872886402262585L;
    private String status;

    private String message;

    private Integer quoteCount;

    private List<WeChatPlatFormDetailModel> quoteDetailList;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getQuoteCount() {
        return quoteCount;
    }

    public void setQuoteCount(Integer quoteCount) {
        this.quoteCount = quoteCount;
    }

    public List<WeChatPlatFormDetailModel> getQuoteDetailList() {
        return quoteDetailList;
    }

    public void setQuoteDetailList(List<WeChatPlatFormDetailModel> quoteDetailList) {
        this.quoteDetailList = quoteDetailList;
    }
}
