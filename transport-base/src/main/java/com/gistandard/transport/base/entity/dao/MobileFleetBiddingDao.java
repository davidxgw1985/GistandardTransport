package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileFleetBidding;

@MyBatisRepository
public interface MobileFleetBiddingDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileFleetBidding record);

    int insertSelective(MobileFleetBidding record);

    MobileFleetBidding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileFleetBidding record);

    int updateByPrimaryKey(MobileFleetBidding record);

}