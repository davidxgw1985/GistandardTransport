package com.gistandard.transport.system.common.baseinfo.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.entity.bean.BizAttachment;

/**
 * @author kongxm
 * @ClassName ChangeAvatarReq
 * @Description 客户下单APP，修改用户头像
 * @Version 1.0
 * @Date 2016/1/26
 */
public class ChangeAvatarReq extends AppBaseRequest {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    private BizAttachment bizAttachment;

    public BizAttachment getBizAttachment() {
        return bizAttachment;
    }

    public void setBizAttachment(BizAttachment bizAttachment) {
        this.bizAttachment = bizAttachment;
    }
}