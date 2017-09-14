package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.ComWaybillTrace;
import com.gistandard.transport.base.entity.dao.ComWaybillTraceDao;
import com.gistandard.transport.base.entity.dao.ex.ComWaybillTraceDaoEx;
import com.gistandard.transport.base.entity.service.ComWaybillTraceService;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.base.exception.MobileStationBizException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yujie
 * @ClassName ComWaybillTraceService
 * @Description
 * @Version 1.0
 * @Date 2015-12-18
 */
@Service
public class ComWaybillTraceServiceImpl implements ComWaybillTraceService {

    private static final Logger logger = LoggerFactory.getLogger(ComWaybillTraceServiceImpl.class);

    @Autowired
    private ComWaybillTraceDao comWaybillTraceDao;
    @Autowired
    private ComWaybillTraceDaoEx comWaybillTraceDaoEx;


    /**
     * 插入方法，返回插入数据的ID，如果为空，说明插入失败
     *
     * @param comWaybillTrace
     * @return
     */
    public Integer insert(ComWaybillTrace comWaybillTrace) {
        Integer traceId = null;
        if (comWaybillTrace == null) {
            logger.error("insert comWaybillTrace failed,comWaybillTrace is null");
            return traceId;
        }
        if (StringUtils.isEmpty(comWaybillTrace.getBusiBookNo()) || StringUtils.isEmpty(comWaybillTrace.getAcctUsername())
                || comWaybillTrace.getExecCode() == null || comWaybillTrace.getGrade() == null) {
            logger.error("insert comWaybillTrace failed, some param is null, BusiBookNo:{}，AcctUsername：{}, ExecCode:{}, Grade :{}",
                    comWaybillTrace.getBusiBookNo(), comWaybillTrace.getAcctUsername(), comWaybillTrace.getExecCode(), comWaybillTrace.getGrade());
            return traceId;
        }
        try {
            comWaybillTraceDao.insert(comWaybillTrace);
            traceId = comWaybillTrace.getId();
        } catch (Exception e) {
            logger.error("insert comWaybillTrace error : ", e.toString());
        } finally {
            return traceId;
        }
    }

    /**
     * 根据条件获取订单跟踪信息
     *
     * @param comWaybillTrace
     * @return
     */
    @Override
    public List<ComWaybillTrace> queryWaybillListByCondition(ComWaybillTrace comWaybillTrace) throws MobileStationBizException {
        if (!StringUtils.isEmpty(comWaybillTrace.getHubNo())) {
            return comWaybillTraceDaoEx.queryWaybillListByCondition(comWaybillTrace);
        } else {
            throw new MobileStationBizException("订单号为空");
        }
    }


}
