package com.gistandard.transport.order.module.upload.service;

import com.gistandard.transport.order.module.customer.bean.OrderQueryReq;
import com.gistandard.transport.order.module.customer.bean.OrderUploadRes;

/**
 * 查询订单上传业务接口类
 * @author 员伟
 * @date 2017-09-08
 */
public interface QueryUploadService {

    void queryOrderUploadPath(OrderQueryReq req,OrderUploadRes res);

}
