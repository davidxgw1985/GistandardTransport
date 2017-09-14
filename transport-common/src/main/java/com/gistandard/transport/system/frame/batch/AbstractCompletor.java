package com.gistandard.transport.system.frame.batch;

import com.gistandard.transport.system.frame.batch.bean.BaseBatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础结果处理组件，具体业务的结果处理组件扩展于它
 * Created by m on 2016/12/14.
 */
public abstract class AbstractCompletor<T, S> implements Completor<T, S> {
    private Logger logger = LoggerFactory.getLogger(AbstractCompletor.class);

    @Override
    public BaseBatchResult<S> complete(T requset, BaseBatchResult<S> result) {
        preComplete(requset, result);
        BaseBatchResult<S> res = doComplete(requset, result);
        proComplete(requset, result);
        return res;
    }

    /**
     * 后处理
     * @param requset
     * @param result
     * @return
     */
    protected void proComplete(T requset, BaseBatchResult<S> result) {
        logger.info("proComplete");
    }

    /**
     * 结果处理方法
     * @param requset
     * @param result
     * @return
     */
    protected abstract BaseBatchResult<S> doComplete(T requset, BaseBatchResult<S> result);

    /**
     * 预处理
     * @param requset
     * @param result
     * @return
     */
    protected void preComplete(T requset, BaseBatchResult<S> result) {
        logger.info("preComplete");
    }
}
