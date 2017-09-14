package com.gistandard.transport.app.module.applytobe.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.system.common.bean.ResultBean;

import java.io.Serializable;


/**
 * Created by m on 2016/8/18.
 */
public class FileUploadResult extends AppBaseResult implements Serializable {

    private ResultBean data;

    public ResultBean getData() {
        return data;
    }

    public void setData(ResultBean data) {
        this.data = data;
    }

}
