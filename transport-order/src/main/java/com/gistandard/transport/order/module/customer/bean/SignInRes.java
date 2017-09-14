package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

import java.io.Serializable;

/**
 * 签到Bean
 * <p>保存接口-返回模型<p/>
 * @author 员伟
 */
public class SignInRes  extends AppBaseResult implements Serializable {


    private static final long serialVersionUID = 6386825292273354235L;

    private Object data;//返回内容

    public SignInRes() {
        super();
    }

    public SignInRes(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
