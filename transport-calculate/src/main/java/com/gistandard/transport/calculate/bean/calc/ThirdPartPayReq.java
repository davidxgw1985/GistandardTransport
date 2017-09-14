package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: ThirdPartPayReq
 * @Date: 2017/3/28
 * @Description: 第三方代付接口请求对象
 */
@ApiModel(value = "第三方代付接口请求对象", parent = AppBaseRequest.class)
public class ThirdPartPayReq extends AppBaseRequest{
    @ApiModelProperty(value = "对账单号",required = true, position = 1)
    private String validBillno;
    @ApiModelProperty(value = "第三方支付账户",required = true, position = 2)
    private String gFUser3FToCode;
    @ApiModelProperty(value = "第三方支付账户名",required = true, position = 3)
    private String gFUser3FToName;
    @ApiModelProperty(value = "1设置支付，2取消设置支付",required = true, position = 4)
    private Integer type;

    public String getValidBillno() {
        return validBillno;
    }

    public void setValidBillno(String validBillno) {
        this.validBillno = validBillno;
    }

    public String getgFUser3FToCode() {
        return gFUser3FToCode;
    }

    public void setgFUser3FToCode(String gFUser3FToCode) {
        this.gFUser3FToCode = gFUser3FToCode;
    }

    public String getgFUser3FToName() {
        return gFUser3FToName;
    }

    public void setgFUser3FToName(String gFUser3FToName) {
        this.gFUser3FToName = gFUser3FToName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ThirdPartPayReq{");
        sb.append("validBillno='").append(validBillno).append('\'');
        sb.append(", gFUser3FToCode='").append(gFUser3FToCode).append('\'');
        sb.append(", gFUser3FToName='").append(gFUser3FToName).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
