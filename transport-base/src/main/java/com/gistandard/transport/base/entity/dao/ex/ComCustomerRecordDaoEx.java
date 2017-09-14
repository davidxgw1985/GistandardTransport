package com.gistandard.transport.base.entity.dao.ex;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCustomerRecord;

import java.util.List;

@MyBatisRepository
public interface ComCustomerRecordDaoEx {

    /**
     * 根据帐户ID查询
     * @return
     */
    List<ComCustomerRecord> queryByAccountId(Integer accountId);

    /**
     * 根据帐户ID删除
     * @param accountId
     * @return
     */
    Integer deleteByAccountId(Integer accountId);

    /**
     * 企业简称唯一性校验
     * @param custTtl 企业简称
     * @return
     */
    List<ComCustomerRecord> checkCustTtlUnique(String custTtl);

    /**
     * 企业编号唯一性校验
     * @param customNo 企业编号
     * @return
     */
    List<ComCustomerRecord> checkCustomNoUnique(String customNo);

    /**
     * 根据企业主键查询该企业的GISNO账号
     *
     * @param customerId
     *            企业主键
     */
    String queryGisNoByCustomerId(Integer customerId);
}