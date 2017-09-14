package com.gistandard.transport.app.module.im.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by yujie on 2016/9/30.
 */
public class QueryContactsDetailParam extends AppBaseRequest {

    //联系人账号ID
    private int contactAccountId;

    public int getContactAccountId() {
        return contactAccountId;
    }

    public void setContactAccountId(int contactAccountId) {
        this.contactAccountId = contactAccountId;
    }
}
