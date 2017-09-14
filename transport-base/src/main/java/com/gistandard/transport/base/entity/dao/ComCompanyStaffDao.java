package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCompanyStaff;

@MyBatisRepository
public interface ComCompanyStaffDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComCompanyStaff record);

    int insertSelective(ComCompanyStaff record);

    ComCompanyStaff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComCompanyStaff record);

    int updateByPrimaryKey(ComCompanyStaff record);
}