package com.gistandard.transport.system.common.baseinfo.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author kongxm
 * @ClassName ChangeNickNameReq
 * @Description 客户下单APP，修改昵称请求
 * @Version 1.0
 * @Date 2016/1/26
 */
public class ChangeNickNameReq extends AppBaseRequest {
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private String nickName;

}