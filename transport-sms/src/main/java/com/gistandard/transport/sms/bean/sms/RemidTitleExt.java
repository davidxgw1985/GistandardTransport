package com.gistandard.transport.sms.bean.sms;

import java.io.Serializable;

/**
 * Created by m on 2017/6/7.
 */
public class RemidTitleExt implements Serializable {
    private static final long serialVersionUID = 4151421745372420956L;
    private String title;
    private String body;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
