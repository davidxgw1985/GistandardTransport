package com.gistandard.transport.system.frame.batch;

import com.gistandard.transport.system.frame.batch.bean.BatchRequest;
import com.gistandard.transport.system.frame.batch.bean.BatchResponse;

/**
 * Created by m on 2016/12/15.
 */
public interface BatchInterceptor<T, S> {
    BatchResponse<S> batchFilter(BatchRequest<T> request);
}