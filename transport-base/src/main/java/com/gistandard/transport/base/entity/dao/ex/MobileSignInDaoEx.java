package com.gistandard.transport.base.entity.dao.ex;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileSignIn;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@MyBatisRepository
public interface MobileSignInDaoEx {

    MobileSignIn selectMsiByModel(MobileSignIn recode);

    MobileSignIn queryMobileSignIn(@Param("acctUserName") String acctUserName, @Param("destAccountId") Integer destAccountId);

    MobileSignIn getUser2UserSignInfo(@Param("acctUserName") String acctUserName, @Param("stationAcctUser") String stationAcctUser, @Param("signInTime") Date signInTime);

    MobileSignIn getUser2CompanySignInfo(@Param("acctUserName") String acctUserName, @Param("stationAcctUser") String stationAcctUser,@Param("signInTime") Date signInTime);

    MobileSignIn getCompany2CompanySignInfo(@Param("acctUserName") String acctUserName, @Param("stationAcctUser") String stationAcctUser, @Param("signInTime") Date signInTime);

    MobileSignIn getCompany2UserSignInfo(@Param("acctUserName") String acctUserName, @Param("stationAcctUser") String stationAcctUser, @Param("signInTime") Date signInTime);


}