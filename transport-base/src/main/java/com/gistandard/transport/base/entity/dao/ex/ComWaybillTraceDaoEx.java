package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.bean.CustomerOrderTaskBean;
import com.gistandard.transport.base.entity.bean.ComWaybillTrace;

import java.util.List;

/**
 * Created by m on 2016/9/30.
 */
@MyBatisRepository
public interface ComWaybillTraceDaoEx {
    /**
     * 根据busi号查运单历史
     * @param busiBookNo
     * @return
     */
    List<ComWaybillTrace> queryListByBusiBookNo(String busiBookNo);

    /**
     * 获取
     * @param condtion
     * @return
     */
    List<CustomerOrderTaskBean> queryOrderWaybill(String condtion);

    List<ComWaybillTrace> queryWaybillListByCondition(ComWaybillTrace record);

    int batchInsert(List<ComWaybillTrace> comWaybillTraceList);

}
