package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.ComCurrency;


import java.util.List;
import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComCurrencyService
 * @Date: 2015/7/28
 * @Description:
 */
public interface ComCurrencyService {
    Map<String, ComCurrency> queryForMap();

    ComCurrency getComCurrencyByCode(String code);

    List<ComCurrency> queryAllComCurrencyList();

    Map<String, ComCurrency> queryForNameMap();

    Map<String, ComCurrency> queryForCurrencyCodeMap();

    Map<String, ComCurrency> queryForCurrencyEnMap();


}
