package com.gistandard.transport.system.common.interceptor.service.impl;

import com.gistandard.transport.system.common.interceptor.service.JustOneService;
import com.gistandard.transport.tools.util.JedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by m on 2016/9/5.
 */
@Service
public class JustOneServiceImpl implements JustOneService {
    @Autowired
    private JedisManager jedisManager;

    @Override
    public boolean checkReqIdOne(String reqId, String seqId, String methodName) {
        return jedisManager.saveValueNX(reqId + seqId, methodName, 60);
    }
}
