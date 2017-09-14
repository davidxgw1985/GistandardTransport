package com.gistandard.platform.pojo.res;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.io.Serializable;

/**
 * Created by yujie on 2016/9/30.
 */
public class AppBasePagerResult extends AppBaseResult implements Serializable{

    private static final long serialVersionUID = -3658502875278432715L;
    private int recordCount;//总条数

    
    public AppBasePagerResult() {
    	
	}

    public AppBasePagerResult(AppBasePagerRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public AppBasePagerResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

}
