package com.gistandard.transport.system.frame.batch.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;

import java.util.List;

/**
 * Created by m on 2016/12/14.
 */
public class BaseBatchResult<T> extends AppBaseResult {
    private List<BaseFaildBean> faildBeans;
    private List<BaseSuccessBean<T>> successBeans;

    public List<BaseFaildBean> getFaildBeans() {
        return faildBeans;
    }

    public void setFaildBeans(List<BaseFaildBean> faildBeans) {
        this.faildBeans = faildBeans;
    }

    public List<BaseSuccessBean<T>> getSuccessBeans() {
        return successBeans;
    }

    public void setSuccessBeans(List<BaseSuccessBean<T>> successBeans) {
        this.successBeans = successBeans;
    }
}
