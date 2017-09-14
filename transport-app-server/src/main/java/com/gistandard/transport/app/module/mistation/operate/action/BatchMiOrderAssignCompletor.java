package com.gistandard.transport.app.module.mistation.operate.action;

import com.gistandard.transport.order.module.mistation.operate.service.MobileOrderOperateService;
import com.gistandard.transport.order.module.mobilestation.bean.AssignOrderforbatchSuccess;
import com.gistandard.transport.order.module.mobilestation.bean.BatchMobileOrderAssignReq;
import com.gistandard.transport.order.module.mobilestation.bean.CheckAssignOrderforbatchResult;
import com.gistandard.transport.order.module.mobilestation.bean.CheckAssignOrderforbatchSuccess;
import com.gistandard.transport.system.frame.batch.AbstractCompletor;
import com.gistandard.transport.system.frame.batch.bean.BaseBatchResult;
import com.gistandard.transport.system.frame.batch.bean.BaseSuccessBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量指派校验完成之后的统一操作
 * Created by m on 2016/12/28.
 */
@Component
public class BatchMiOrderAssignCompletor extends AbstractCompletor<BatchMobileOrderAssignReq, AssignOrderforbatchSuccess> {
    @Autowired
    private MobileOrderOperateService mobileOrderOperateService;

    @Override
    protected BaseBatchResult<AssignOrderforbatchSuccess> doComplete(BatchMobileOrderAssignReq requset, BaseBatchResult<AssignOrderforbatchSuccess> result) {
        List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> list = new ArrayList<>();
        for (BaseSuccessBean<AssignOrderforbatchSuccess> baseSuccessBean : result.getSuccessBeans()){
            CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess> checkAssignOrderforbatchSuccess = new CheckAssignOrderforbatchSuccess<>();
            checkAssignOrderforbatchSuccess.setReq(baseSuccessBean.getReq());
            list.add(checkAssignOrderforbatchSuccess);
        }
        try {
            CheckAssignOrderforbatchResult result1 = mobileOrderOperateService.doBatchMiOrderAssign(list, requset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
