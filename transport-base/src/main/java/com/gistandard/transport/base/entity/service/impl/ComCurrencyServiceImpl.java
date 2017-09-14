package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.ComCurrency;
import com.gistandard.transport.base.entity.dao.ex.ComCurrencyDaoEx;
import com.gistandard.transport.base.entity.service.ComCurrencyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComCurrencyServiceImpl
 * @Date: 2015/7/28
 * @Description:
 */
@Service
public class ComCurrencyServiceImpl implements ComCurrencyService {

	@Autowired
	private ComCurrencyDaoEx comCurrencyDaoEx;

	@Override
	@Cacheable(value = "transport_app_queryComCurrencyForMap")
	public Map<String, ComCurrency> queryForMap() {
		List<ComCurrency> list = comCurrencyDaoEx.queryAll();
		Map<String, ComCurrency> comCurrencyMap = new HashMap<String, ComCurrency>();
		for (ComCurrency comCurrency : list) {
			comCurrencyMap.put(comCurrency.getCurrencyCode(), comCurrency);
		}
		return comCurrencyMap;
	}

	/**
	 * 根据编码 获取 货币信息
	 * @param code
	 * @return
	 */
	@Override
	public ComCurrency getComCurrencyByCode(String code) {
		if(StringUtils.isEmpty(code)){
			return new ComCurrency();
		}
		return queryForMap().get(code);
	}

	@Override
	@Cacheable(value = "transport_app_queryComCurrencyForNameMap")
	public Map<String, ComCurrency> queryForNameMap() {
		List<ComCurrency> list = comCurrencyDaoEx.queryAll();
		Map<String, ComCurrency> comCurrencyMap = new HashMap<String, ComCurrency>();
		for (ComCurrency comCurrency : list) {
			comCurrencyMap.put(comCurrency.getCurrencyCh(), comCurrency);
		}
		return comCurrencyMap;
	}

	@Override
	@Cacheable(value = "transport_app_queryAllComCurrencyList")
	public List<ComCurrency> queryAllComCurrencyList() {
		return comCurrencyDaoEx.queryAll();
	}

	@Override
	@Cacheable(value = "transport_app_queryForCurrencyCodeMap")
	public Map<String, ComCurrency> queryForCurrencyCodeMap() {
		List<ComCurrency> list = comCurrencyDaoEx.queryAll();
		Map<String, ComCurrency> comCurrencyMap = new HashMap<String, ComCurrency>();
		for (ComCurrency comCurrency : list) {
			comCurrencyMap.put(comCurrency.getCurrencyCode(), comCurrency);
		}
		return comCurrencyMap;
	}

	@Override
	@Cacheable(value = "transport_app_queryForCurrencyEnMap")
	public Map<String, ComCurrency> queryForCurrencyEnMap() {
		List<ComCurrency> list = comCurrencyDaoEx.queryAll();
		Map<String, ComCurrency> comCurrencyMap = new HashMap<String, ComCurrency>();
		for (ComCurrency comCurrency : list) {
			comCurrencyMap.put(comCurrency.getCurrencyEn(), comCurrency);
		}
		return comCurrencyMap;
	}

}
