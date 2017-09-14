package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.MobileMoudleRel;
import com.gistandard.transport.base.entity.dao.ex.MobileMoudleRelDaoEx;
import com.gistandard.transport.base.entity.service.MobileMoudleRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MobileMoudleRelServiceImpl implements MobileMoudleRelService {

    @Autowired
    private MobileMoudleRelDaoEx mobileMoudleRelDaoEx;

    /**
     * 查询所有kpp模块
     * @param mobileMoudleRel 查询参数对象
     * @return
     */
    @Override
    public List<MobileMoudleRel> queryAllModule(MobileMoudleRel mobileMoudleRel) {
        return mobileMoudleRelDaoEx.queryAllModule(mobileMoudleRel);
    }

    /**
     * 根据名称查询kpp模块
     * @param mobileMoudleRel 查询参数对象
     * @return
     */
    @Override
    public List<MobileMoudleRel> queryMobileMoudleRel(MobileMoudleRel mobileMoudleRel) {
        return mobileMoudleRelDaoEx.queryMobileMoudleRelByCondition(mobileMoudleRel);
    }

    /**
     * 更新kpp模块信息
     * @param mobileMoudleRel
     */
    @Override
    public void updateMobileMoudleRel(MobileMoudleRel mobileMoudleRel) {
        mobileMoudleRelDaoEx.updateByPrimaryKeySelective(mobileMoudleRel);

    }

    /**
     * 更新kpp模块状态
     * @param acctUsername 帐号名称
     * @param companyAcctUserName 企业帐号名称
     */
    @Override
    @Transactional
    public void updateMobileMoudleRelStatus(String acctUsername, String companyAcctUserName) {
        MobileMoudleRel mobileMoudleRel = new MobileMoudleRel();
        mobileMoudleRel.setAcctUsername(acctUsername);
        mobileMoudleRel.setIsOn(0);
        mobileMoudleRelDaoEx.updateMobileMoudleRelStatus(mobileMoudleRel);
        mobileMoudleRel.setCompanyCode(companyAcctUserName);
        mobileMoudleRel.setIsOn(1);
        mobileMoudleRelDaoEx.updateMobileMoudleRelStatus(mobileMoudleRel);
    }

    /**
     * 删除kpp模块
     * @param mobileMoudleRel
     */
    @Override
    public void deleteMobileMoudleRel(MobileMoudleRel mobileMoudleRel) {
        mobileMoudleRelDaoEx.deleteByPrimaryKeySelective(mobileMoudleRel);
    }
}
