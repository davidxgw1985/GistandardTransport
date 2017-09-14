package com.gistandard.transport.app.module.login.dao;

import com.gistandard.transport.app.module.login.bean.CompanyInfo;
import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComUserinfo;
import com.gistandard.transport.base.entity.bean.ComVehicleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface AppLoginDao {
    /**
     * 根据帐号id和角色信息查询人员
     * @param acctId 帐号id
     * @param roleIds 角色id
     * @return 人员信息对象
     */
    ComUserinfo queryUserinfoByAcctId(@Param(value = "acctId") Integer acctId, @Param(value = "roleIds") String roleIds);

    /**
     * 根据帐号id 查询车辆信息
     * @param acctId
     * @return 车辆信息对象
     */
    ComVehicleInfo queryVehicleByAcctId(Integer acctId);

    /**
     * 查询用户所属企业信息列表
     * @param acctId 帐号id
     * @return 企业信息列表
     */
    List<CompanyInfo> queryCompanyInfoListByAcctId(@Param(value = "acctId") Integer acctId);
}
