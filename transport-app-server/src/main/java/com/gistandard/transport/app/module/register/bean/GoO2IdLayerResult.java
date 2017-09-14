package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.valueplus.psc.dubbo.service.record.bean.AccountSeqBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by m on 2016/8/18.
 */
@ApiModel(value = "根据所选城市随机生成10个帐号结果对象")
public class GoO2IdLayerResult extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "根据所选城市随机生成10个帐号接口返回对象", required = true)
    private List<AccountSeqBean> data;

    public GoO2IdLayerResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public List<AccountSeqBean> getData() {
        return data;
    }

    public void setData(List<AccountSeqBean> data) {
        this.data = data;
    }
}
