package com.gistandard.transport.app.dubbo.sign.bean;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import java.util.Date;


/**
 * @author: 员伟
 * @ClassName: SignInTimeDubboRes
 * @Date: 2017/6/21
 * @Description: 签到时间Dubbo服务模型
 */
public class SignInTimeDubboRes extends MsDubboResBean {

    private Date signInTime;

    private SignInRecord signInRecord;


    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public SignInRecord getSignInRecord() {
        return signInRecord;
    }

    public void setSignInRecord(SignInRecord signInRecord) {
        this.signInRecord = signInRecord;
    }
}