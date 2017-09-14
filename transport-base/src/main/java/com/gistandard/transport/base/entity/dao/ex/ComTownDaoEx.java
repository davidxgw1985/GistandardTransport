package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComTown;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ComTownDaoEx {

    List<ComTown> selectByCountyId(@Param(value = "countyId") Integer countyId);

}