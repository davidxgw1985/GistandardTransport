package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: RewardReq
 * @Date: 2017/5/31
 * @Description: 打赏获取结算对账单请求对象
 */
@ApiModel(value = "打赏获取结算对账单请求对象", parent = AppBaseRequest.class)
public class RewardReq extends AppBaseRequest{

    @ApiModelProperty(value = "收款客户编号",required = true, position = 1)
    private String gfUserFromCode;
    @ApiModelProperty(value = "收款客户姓名",required = true, position = 2)
    private String gfUserFromName;
    @ApiModelProperty(value = "付款客户编号",required = true, position = 3)
    private String gfUserToCode;
    @ApiModelProperty(value = "付款客户姓名",required = true, position = 4)
    private String gfUserToName;
    @ApiModelProperty(value = "打赏金额",required = true, position = 5)
    private BigDecimal amount;
    @ApiModelProperty(value = "币制编码",required = true, position = 6)
    private String currencyCode;
    @ApiModelProperty(value = "支付方式 GeneralAcct为通用账户支付",required = true, position = 7)
    private String paymentTerm;
    @ApiModelProperty(value = "订单号",required = true, position = 7)
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGfUserFromCode() {
        return gfUserFromCode;
    }

    public void setGfUserFromCode(String gfUserFromCode) {
        this.gfUserFromCode = gfUserFromCode;
    }

    public String getGfUserFromName() {
        return gfUserFromName;
    }

    public void setGfUserFromName(String gfUserFromName) {
        this.gfUserFromName = gfUserFromName;
    }

    public String getGfUserToCode() {
        return gfUserToCode;
    }

    public void setGfUserToCode(String gfUserToCode) {
        this.gfUserToCode = gfUserToCode;
    }

    public String getGfUserToName() {
        return gfUserToName;
    }

    public void setGfUserToName(String gfUserToName) {
        this.gfUserToName = gfUserToName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }
}
