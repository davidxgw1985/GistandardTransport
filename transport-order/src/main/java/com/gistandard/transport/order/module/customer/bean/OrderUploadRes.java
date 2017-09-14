package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBasePagerResult;

import java.util.List;

/**
 * 订单上传文件结果
 * @author 员伟
 */
public class OrderUploadRes extends AppBasePagerResult {

    public OrderUploadRes(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<String> data;//订单上传图片的列表

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}