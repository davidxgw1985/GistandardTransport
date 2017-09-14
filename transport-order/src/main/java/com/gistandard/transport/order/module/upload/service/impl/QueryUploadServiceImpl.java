package com.gistandard.transport.order.module.upload.service.impl;

import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.dao.ex.ExpreessFileUploadRecordDaoEx;
import com.gistandard.transport.order.module.customer.bean.OrderQueryReq;
import com.gistandard.transport.order.module.customer.bean.OrderUploadRes;
import com.gistandard.transport.order.module.upload.service.QueryUploadService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询订单上传信息业务接口实现类
 * @author 员伟
 */
@Service
public class QueryUploadServiceImpl implements QueryUploadService {

    private final Logger logger = LoggerFactory.getLogger(QueryUploadServiceImpl.class);

    @Autowired
    private ExpreessFileUploadRecordDaoEx efurDaoEx;

    /**
     * 运输平台地址
     */
    @Value("#{spring.msAppUrl}")
    private String msAppUrl;


    @Override
    public void queryOrderUploadPath(OrderQueryReq req, OrderUploadRes res) {
        if (validateReq(req, res)) {
            logger.error("查询订单上传信息请求不通过");
            return;
        }
        List<String> data = efurDaoEx.queryOrderUploadPath(req.getBusiBookNo(), req.getAcctUsername());
        List<String> pathList = null;
        if (CollectionUtils.isNotEmpty(data)) {
            pathList = new ArrayList<>();
            for (String picPath : data) {
                String path = msAppUrl + picPath;
                pathList.add(path);
            }
        }
        res.setData(pathList);
    }


    /**
     * 查询订单上传信息校验参数
     * @param req 请求
     * @param res 返回
     * @return 校验结果 false:通过 true:不通过
     */
    private boolean validateReq(OrderQueryReq req, OrderUploadRes res) {
        logger.info("查询订单上传信息校验参数-开始");
        if (req == null) {
            logger.error("查询订单上传信息请求为空");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("查询订单上传信息请求非法-空请求");
            return true;
        }
        if (StringUtils.isBlank(req.getBusiBookNo())) {
            logger.error("查询订单上传信息订单号为空");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("查询订单上传信息订单号不存在");
            return true;
        }
        if (StringUtils.isBlank(req.getAcctUsername())) {
            logger.error("查询订单上传信息操作账号不存在");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("查询订单上传信息操作账号不存在");
            return true;
        }
        logger.info("查询订单上传信息校验参数-结束-successful!");
        return false;
    }


}
