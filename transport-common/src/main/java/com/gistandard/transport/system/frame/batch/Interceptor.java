package com.gistandard.transport.system.frame.batch;

import com.gistandard.transport.system.frame.batch.bean.BaseBatchResult;

/**
 * 拦截过滤组件，用于检验过滤掉无法处理的数据
 * Created by m on 2016/12/14.
 */
public interface Interceptor<T, S> {
    BaseBatchResult<S> filter(T request);
}
