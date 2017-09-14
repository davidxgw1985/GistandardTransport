package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCurrency;

import java.util.List;

@MyBatisRepository
public interface ComCurrencyDaoEx {
    List<ComCurrency> queryAll();
}