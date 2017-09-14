package com.gistandard.transport.app.module.message.service.impl;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.module.message.bean.AddCallInfoReq;
import com.gistandard.transport.app.module.message.service.CallInfoService;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.MobileCallInfo;
import com.gistandard.transport.base.entity.dao.MobileCallInfoDao;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xgw
 * @ClassName CallInfoServiceImpl
 * @Description 通话信息接口
 * @Version 1.0
 * @Date 2017/2/14
 */
@Service
public class CallInfoServiceImpl implements CallInfoService {
    @Autowired
    private MobileCallInfoDao mobileCallInfoDao;

    @Override
    public AppBaseResult addCallInfo(AddCallInfoReq addCallInfoReq) {
        AppBaseResult appBaseResult = new AppBaseResult(addCallInfoReq);
        MobileCallInfo mobileCallInfo = new MobileCallInfo();
        mobileCallInfo.setCreateUser(addCallInfoReq.getAcctUsername());
        mobileCallInfo.setCreateUserId(addCallInfoReq.getAccountId());
        try {
            BeanUtils.copyProperties(mobileCallInfo, addCallInfoReq);
            mobileCallInfoDao.insert(mobileCallInfo);
        } catch (Exception e) {
            appBaseResult.setRetCode(SystemDefine.FAILURE);
            appBaseResult.setRetMsg("记录通话信息失败！");
        }
        return appBaseResult;
    }
}
