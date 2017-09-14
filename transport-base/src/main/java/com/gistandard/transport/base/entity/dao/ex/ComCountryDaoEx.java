package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCountry;

import java.util.List;

@MyBatisRepository
public interface ComCountryDaoEx {

    List<ComCountry> queryAll();
}