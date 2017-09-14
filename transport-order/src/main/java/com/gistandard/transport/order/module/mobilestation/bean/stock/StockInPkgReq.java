package com.gistandard.transport.order.module.mobilestation.bean.stock;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/11/1.
 */
@ApiModel(description = "查询集包入参", parent = AppBaseRequest.class)
public class StockInPkgReq extends AppBaseRequest implements ValidTokenMark{
    @ApiModelProperty(value = "集包号", required = true, position = 1)
    private String pkgNo;

    public String getPkgNo() {
        return pkgNo;
    }

    public void setPkgNo(String pkgNo) {
        this.pkgNo = pkgNo;
    }
}
