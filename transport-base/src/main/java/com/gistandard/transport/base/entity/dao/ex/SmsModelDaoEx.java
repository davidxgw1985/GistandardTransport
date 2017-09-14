package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.SmsModel;

import java.util.List;

/**
 * Created by m on 2016/9/29.
 */
@MyBatisRepository
public interface SmsModelDaoEx {
    List<SmsModel> querySmsModelAll();
}
