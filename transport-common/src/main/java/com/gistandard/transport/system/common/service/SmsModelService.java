package com.gistandard.transport.system.common.service;

import com.gistandard.transport.base.entity.bean.SmsModel;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: SmsModelServiceImpl
 * @Date: 2016/2/17
 * @Description: 短信模板
 */
public interface SmsModelService {
    /**
     * 查询所有短信模板
     * @return
     */
    List<SmsModel> querySmsModelAll();
}
