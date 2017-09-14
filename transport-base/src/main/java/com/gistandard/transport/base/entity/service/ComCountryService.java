package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.ComCountry;

import java.util.List;
import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComCountryService
 * @Date: 2015/7/24
 * @Description:
 */
public interface ComCountryService {

    Map<String, ComCountry> queryForMap();

    Map<String, ComCountry> queryForNameMap();

    Map<String, ComCountry> queryForCountryCodeMap();

    List<ComCountry> queryComCountryList();
}
