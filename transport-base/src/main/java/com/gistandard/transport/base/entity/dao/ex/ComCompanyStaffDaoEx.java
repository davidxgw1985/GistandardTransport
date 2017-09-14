package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComCompanyStaff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by m on 2016/12/7.
 */
@MyBatisRepository
public interface ComCompanyStaffDaoEx {
    ComCompanyStaff queryByAccountAndCompany(@Param("staffaccountid") Integer staffaccountid,
                                                   @Param("companyaccountid") Integer companyaccountid);

    List<ComAccount> queryMangerInfo(@Param("companyaccountid") Integer companyaccountid);

    ComCompanyStaff queryCompanyByAccount(@Param("staffaccountid") Integer staffaccountid);

    List<ComCompanyStaff> queryCompanyByAccountRole(@Param("staffaccountid") Integer staffaccountid);
}
