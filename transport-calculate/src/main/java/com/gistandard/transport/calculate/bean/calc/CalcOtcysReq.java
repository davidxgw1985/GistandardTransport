package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: CalcOtcysReq
 * @Date: 2017/6/21
 * @Description: O单同城运输结算请求对象
 */
public class CalcOtcysReq extends AppBaseRequest{
    private static final long serialVersionUID = -8122747838706446479L;

    @ApiModelProperty(value = "付款客户编号",required = true, position = 3)
    private String gfUserToCode;
    @ApiModelProperty(value = "付款客户姓名",required = true, position = 4)
    private String gfUserToName;
    @ApiModelProperty(value = "mobileBookingForm主键",required = true, position = 5)
    private Integer orderId;//mobileBookingForm主键
    @ApiModelProperty(value = "税前金额",required = true, position = 7)
    protected BigDecimal amount;
    @ApiModelProperty(value = "币制",required = true, position = 8)
    protected String currencyCode;
    @ApiModelProperty(value = "税率",required = true, position = 9)
    protected BigDecimal tax;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
}
