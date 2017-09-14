package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.ComCountry;
import com.gistandard.transport.base.entity.dao.ex.ComCountryDaoEx;
import com.gistandard.transport.base.entity.service.ComCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComCountryServiceImpl
 * @Date: 2015/7/24
 * @Description:
 */
@Service
public class ComCountryServiceImpl implements ComCountryService {

	@Autowired
	private ComCountryDaoEx comCountryDaoEx;

	@Override
	@Cacheable(value = "transport_app_queryComCountryForMap")
	public Map<String, ComCountry> queryForMap() {
		List<ComCountry> list = comCountryDaoEx.queryAll();
		Map<String, ComCountry> comCountryMap = new HashMap<String, ComCountry>();
		for (ComCountry comCountry : list) {
			comCountryMap.put(comCountry.getCountryCode(), comCountry);
		}
		return comCountryMap;
	}

	@Override
	@Cacheable(value = "transport_app_queryComCountryForNameMap")
	public Map<String, ComCountry> queryForNameMap() {
		List<ComCountry> list = comCountryDaoEx.queryAll();
		Map<String, ComCountry> comCountryMap = new HashMap<String, ComCountry>();
		for (ComCountry comCountry : list) {
			comCountryMap.put(comCountry.getCountryNameCh(), comCountry);
		}
		return comCountryMap;
	}

	@Override
	public List<ComCountry> queryComCountryList() {
		return comCountryDaoEx.queryAll();
	}

	/**
	 * 注解中value表示该查询结果缓存的空间位置<br>
	 * key是指缓存的key，value+key的配置说明，该key的数据缓存在该value的位置上，下次获取会到该位置获取该key对应的值<br>
	 * condition是缓存条件，spring的spEL表达式<br>
	 * Spring的缓存有个限制是只能单点缓存，不支持分布式，所以不如redis等使用广泛
	 */
	@Override
	@Cacheable(value = "transport_app_queryComCountryForCodeMap")
	public Map<String, ComCountry> queryForCountryCodeMap() {
		List<ComCountry> list = comCountryDaoEx.queryAll();
		Map<String, ComCountry> comCountryMap = new HashMap<String, ComCountry>();
		for (ComCountry comCountry : list) {
			comCountryMap.put(comCountry.getCountryCode(), comCountry);
		}
		return comCountryMap;
	}

}
