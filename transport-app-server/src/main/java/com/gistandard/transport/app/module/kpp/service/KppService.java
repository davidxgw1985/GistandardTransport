package com.gistandard.transport.app.module.kpp.service;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.module.kpp.bean.QueryKppParam;
import com.gistandard.transport.base.entity.bean.MobileMoudleRel;
import com.gistandard.transport.base.exception.MobileStationBizException;

/**
 * Created by yujie on 2016/9/30.
 */
public interface KppService {

    AppBaseResult addMobileMoudleRel(MobileMoudleRel mobileMoudleRel) throws MobileStationBizException;

    AppBaseResult updateMobileMoudleRel(QueryKppParam queryKppParam) throws MobileStationBizException;

    /**
     * ����GPS��ǰ�û���Ϣ
     * ����λ����Ϣ��ģ����Ϣ����ɫ��Ϣ
     * @param accountId
     * @param companyAcctUsername
     */
    void sendGpsLoginMsg(Integer accountId, String companyAcctUsername);
}
