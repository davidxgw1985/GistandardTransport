package com.gistandard.transport.system.common.service.impl;

import com.gistandard.transport.base.entity.bean.SmsModel;
import com.gistandard.transport.base.entity.dao.ex.SmsModelDaoEx;
import com.gistandard.transport.system.common.service.SmsModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: SmsModelServiceImpl
 * @Date: 2016/2/17
 * @Description: 短信模板
 */
@Service
public class SmsModelServiceImpl implements SmsModelService {
    @Autowired
    private SmsModelDaoEx smsModelDaoEx;

    @Override
    public List<SmsModel> querySmsModelAll() {
        return smsModelDaoEx.querySmsModelAll();
    }
}
