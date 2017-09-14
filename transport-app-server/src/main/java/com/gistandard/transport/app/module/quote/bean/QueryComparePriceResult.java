package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormOutModWrap;

/**
 * @author: 员伟
 * @ClassName: 比价处理结果类
 * @Date: 2017/6/1
 * @Description: 不改变原先业务处理类返回数据的data
 * <p> 由于QueryPlatFormExpressPriceResult中在其他业务场景返回数据，
 *     现在新需求比价操作，运输平台返回数据的json的key一律为data,而
 *     现在需要封装，data新对象，故而新增添此类。
 * <p/>
 */
public class QueryComparePriceResult extends AppBasePagerResult {

    public QueryComparePriceResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private PlatFormOutModWrap data;

    public PlatFormOutModWrap getData() {
        return data;
    }

    public void setData(PlatFormOutModWrap data) {
        this.data = data;
    }

}
