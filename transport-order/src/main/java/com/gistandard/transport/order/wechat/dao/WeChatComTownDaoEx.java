package com.gistandard.transport.order.wechat.dao;

import com.gistandard.transport.app.dubbo.wechat.bean.WeChatComTown;
import com.gistandard.transport.base.annotation.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface WeChatComTownDaoEx {

    List<WeChatComTown> selectByCountyId(@Param(value = "countyId") Integer countyId);

}