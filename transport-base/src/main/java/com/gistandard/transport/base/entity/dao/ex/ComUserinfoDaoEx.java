package com.gistandard.transport.base.entity.dao.ex;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComUserinfo;

import java.util.List;

@MyBatisRepository
public interface ComUserinfoDaoEx {

    /**
     * 根据身份证号，查找人员
     * @param identityNo
     * @return
     */
    ComUserinfo queryByIdentityNo(String identityNo);

    ComUserinfo queryByTelephone(String telephone);

    int batchInsert(List<ComUserinfo> list);

    /**
     * 根据帐号ID，查找人员信息
     * @param acctId
     * @return
     */
    ComUserinfo queryByAcctId(Integer acctId);

    /**
     * 根据账户删除用户信息
     *
     * @param accountId
     *            账户ID
     */
    Integer deleteByAccountId(Integer accountId);

    /**
     * 与企业解绑
     * @param id
     * @return
     */
    Integer updateCustomerIdToNullById(Integer id);
}