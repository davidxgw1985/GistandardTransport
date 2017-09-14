package com.gistandard.transport.system.im;

import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.im.MsgIMReq;

/**
 * Created by yujie on 2016/10/6.
 */
public interface ImMessageService {

    BaseResBean pushMessageIM(MsgIMReq msgIMReq);
}
