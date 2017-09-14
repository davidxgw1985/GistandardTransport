package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: FindAllValidBillMstResult
 * @Date: 2016/10/21
 * @Description:
 */
public class FindAllValidBillMstResult extends AppBasePagerResult{

    public FindAllValidBillMstResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }
    private List<FindAllValidBillMstBean> data;

    public List<FindAllValidBillMstBean> getData() {
        return data;
    }

    public void setData(List<FindAllValidBillMstBean> data) {
        this.data = data;
    }
}
