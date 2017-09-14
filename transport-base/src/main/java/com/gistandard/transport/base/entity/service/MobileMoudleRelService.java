package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.MobileMoudleRel;

import java.util.List;


public interface MobileMoudleRelService {

    /**
     * 查询所有kpp模块
     * @param mobileMoudleRel 查询参数对象
     * @return
     */
    List<MobileMoudleRel> queryAllModule(MobileMoudleRel mobileMoudleRel);

    /**
     * 根据名称查询kpp模块
     * @param mobileMoudleRel 查询参数对象
     * @return
     */
    List<MobileMoudleRel> queryMobileMoudleRel(MobileMoudleRel mobileMoudleRel);

    /**
     * 更新kpp模块信息
     * @param mobileMoudleRel
     */
    void updateMobileMoudleRel(MobileMoudleRel mobileMoudleRel);

    /**
     * 更新kpp模块状态
     * @param acctUsername 帐号名称
     * @param companyAcctUserName 企业帐号名称
     */
    void updateMobileMoudleRelStatus(String acctUsername, String companyAcctUserName);

    /**
     * 删除kpp模块
     * @param mobileMoudleRel
     */
    void deleteMobileMoudleRel(MobileMoudleRel mobileMoudleRel);
}
