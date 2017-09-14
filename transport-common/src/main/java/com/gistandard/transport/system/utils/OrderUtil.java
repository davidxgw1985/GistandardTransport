package com.gistandard.transport.system.utils;

import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import com.gistandard.transport.base.entity.dao.ex.ComCustomerDaoEx;
import com.gistandard.transport.base.exception.MobileStationBizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by m on 2016/10/21.
 */
@Component
public class OrderUtil {
    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;

    /**
     * 获取平台报价所需的startaccountid和endaccountid
     *
     * @return
     */
    public String getAccountId(String locus, Integer roleId) throws MobileStationBizException {
        String accountId = null;
        if (locus.equals(MobileStationDefine.POP) || locus.equals(MobileStationDefine.POD)) {
            if (roleId == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                accountId = null;
            }else {
                accountId = "-1";
            }
        } else if (locus.equals(MobileStationDefine.M)) {
            accountId = "23";
        } else {
            //洼站是简称
            ComCustomer comCustomer = comCustomerDaoEx.getComCustomerByCustTtl(locus);
            if (comCustomer == null)
                throw new MobileStationBizException("蛙站" + locus + "账号信息不存在");
            accountId = String.valueOf(comCustomer.getAccountId());
        }
        return accountId;
    }
}
