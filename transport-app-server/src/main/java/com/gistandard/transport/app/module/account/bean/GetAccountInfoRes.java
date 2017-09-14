package com.gistandard.transport.app.module.account.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: GetAccountInfoRes
 * @Date: 2017/3/28
 * @Description: 通过手机号码查询出账户信息
 */
@ApiModel(description = "通过手机号码查询出账户信息返回对象", parent = AppBaseResult.class)
public class GetAccountInfoRes extends AppBaseResult {
    public GetAccountInfoRes(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "账号名称", required = true)
    private String acctUserName;
    @ApiModelProperty(value = "姓名", required = true)
    private String realName;

    public String getAcctUserName() {
        return acctUserName;
    }

    public void setAcctUserName(String acctUserName) {
        this.acctUserName = acctUserName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
