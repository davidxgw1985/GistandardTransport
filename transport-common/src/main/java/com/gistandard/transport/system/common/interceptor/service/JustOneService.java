package com.gistandard.transport.system.common.interceptor.service;

/**
 * Created by m on 2016/9/5.
 */
public interface JustOneService {
    boolean checkReqIdOne(String reqId, String seqId, String methodName);
}
