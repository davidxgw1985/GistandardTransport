package com.gistandard.transport.order.module.mobilestation.bean.stock;

import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by m on 2016/12/2.
 */
@ApiModel(description = "批量入库返回对象", parent = AppBaseResult.class)
public class BatchStockInResult extends AppBaseResult {
    @ApiModelProperty(value = "批量收件失败列表", required = true, position = 1)
    private List<BatchStockInResultBean> errorBeans;

    @ApiModelProperty(value = "批量收件成功列表", required = true, position = 2)
    private List<BatchStockInResultBean> successBeans;

    public List<BatchStockInResultBean> getSuccessBeans() {
        return successBeans;
    }

    public void setSuccessBeans(List<BatchStockInResultBean> successBeans) {
        this.successBeans = successBeans;
    }

    public List<BatchStockInResultBean> getErrorBeans() {
        return errorBeans;
    }

    public void setErrorBeans(List<BatchStockInResultBean> errorBeans) {
        this.errorBeans = errorBeans;
    }
}
