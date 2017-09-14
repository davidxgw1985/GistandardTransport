package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: OrderPicUploadResult
 * @Date: 2016/12/23
 * @Description:
 */
@ApiModel(description = "订单图片批量上传返回对象", parent = AppBaseResult.class)
public class OrderPicUploadResult extends AppBaseResult{
    private static final long serialVersionUID = -4443193292545568212L;

    @ApiModelProperty(value = "订单图片上传返回对象")
    private List<OrderPicUploadResBean> list;

    public List<OrderPicUploadResBean> getList() {
        return list;
    }

    public void setList(List<OrderPicUploadResBean> list) {
        this.list = list;
    }
}
