package com.gistandard.transport.quote.system.database.services;

import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.quote.system.database.bean.QueryBatchPlatformQuote2Result;
import com.gistandard.transport.quote.system.database.bean.QueryPlatformQuote2Req;
import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuote;
import com.gistandard.transport.system.webservice.client.payinfo.Exception_Exception;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormInModel;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormOutModWrap;

import java.util.List;

/**
 * Created by m on 2016/10/21.
 */
public interface ExpressService {
    /**
     *查询平台报价（新）
     * @param systemFlag
     * @param busibookno
     * @param startAccountId  起始AccountId，咪站为23, 快递员-1, 洼站简称
     * @param endAccountId   终点AccountId，咪站为23, 快递员-1, 洼站简称
     * @return
     */
    PlatformQuote queryPlatformQuote2(String systemFlag, String busibookno, String startAccountId, String endAccountId, Integer roleId) throws MobileStationBizException;

    /**
     * 批量查询平台报价（新）
     * @param queryPlatformQuote2Reqs
     * @return
     * @throws MobileStationBizException
     */
    QueryBatchPlatformQuote2Result queryBatchPlatformQuote2(List<QueryPlatformQuote2Req> queryPlatformQuote2Reqs);

    /**
     * 比价操作实现细节
     * @param platFormInModel
     * @return
     * @throws Exception_Exception
     * @throws CustomerBizException
     */
    PlatFormOutModWrap priceCompareOperate(PlatFormInModel platFormInModel) throws Exception_Exception, CustomerBizException;
}
