package com.gistandard.transport.system.frame.batch;

import com.gistandard.transport.system.frame.batch.bean.BaseBatchResult;

/**
 * 结果处理组件
 * Created by m on 2016/12/14.
 */
public interface Completor<T, S> {
    BaseBatchResult<S> complete(T request, BaseBatchResult<S> result);
}
