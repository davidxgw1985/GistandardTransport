package com.gistandard.transport.app.dubbo.order.service;

import com.gistandard.transport.app.dubbo.order.bean.AssistApplyOrderReq;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 * 订单援助dubbo接口
 * @author 员伟
 * @date 2017-09-01
 */
public interface AssistDubboService {

    /**
     * 驳回援助申请接口
     * @param req 调度管理系统驳回申请请求
     * @return 返回结果
     */
    MsDubboResBean overruleAssistApply(AssistApplyOrderReq req);


    /**
     * 同意援助申请接口
     * @param req 调度管理系统同意申请请求
     * @return 返回结果
     */
    MsDubboResBean approvalAssistApply(AssistApplyOrderReq req);






}
