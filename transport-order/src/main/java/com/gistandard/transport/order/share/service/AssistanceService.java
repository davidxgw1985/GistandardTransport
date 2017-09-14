package com.gistandard.transport.order.share.service;

import com.gistandard.transport.order.share.bean.AssistanceReq;
import com.gistandard.transport.order.share.bean.AssistanceRes;

/**
 * 快递员或者咪站援助请求业务接口
 * @author 员伟
 * @date 2017-08-30
 */
public interface AssistanceService {

    /**
     * 为咪站或者快递员提供援助接口
     * @param req 援助请求
     * @return 援助结果
     */
    AssistanceRes assistance4MiOrCourier(AssistanceReq req);

}
