package com.gistandard.transport.app.module.mistation.operate.action;

import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.operate.service.MobileOrderOperateService;
import com.gistandard.transport.order.module.mobilestation.bean.AssignOrderforbatchSuccess;
import com.gistandard.transport.order.module.mobilestation.bean.BatchMobileOrderAssignReq;
import com.gistandard.transport.order.module.mobilestation.bean.CheckAssignOrderforbatchResult;
import com.gistandard.transport.system.frame.batch.Interceptor;
import com.gistandard.transport.system.frame.batch.bean.BaseBatchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 批量指派校验
 * Created by m on 2016/12/27.
 */
@Component
public class BatchMiOrderAssignInterceptor implements Interceptor<BatchMobileOrderAssignReq, AssignOrderforbatchSuccess> {
    @Autowired
    private MobileOrderOperateService mobileOrderOperateService;

    @Override
    public BaseBatchResult<AssignOrderforbatchSuccess> filter(BatchMobileOrderAssignReq request) {
        BaseBatchResult res = new BaseBatchResult();
        try {
            CheckAssignOrderforbatchResult result = mobileOrderOperateService.checkAssignOrderforbatch(request);
            res.setRetCode(result.getRetCode());
            res.setFaildBeans(result.getCheckAssignOrderforbatchFaileds());
            res.setSuccessBeans(result.getCheckAssignOrderforbatchSuccesses());
        } catch (MobileStationBizException e) {
            e.printStackTrace();
        }
        return res;
    }
}
