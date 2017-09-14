package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCity;
import com.gistandard.transport.base.entity.bean.ex.ComCityEx;

import java.util.List;

@MyBatisRepository
public interface ComCityDaoEx {

	int batchInsert(List<ComCity> list);

	List<ComCity> queryAll();

	/**
	 * 根据条件查询城市数据
	 */
	List<ComCityEx> queryCity();
}