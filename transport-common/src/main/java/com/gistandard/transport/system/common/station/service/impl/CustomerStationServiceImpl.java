package com.gistandard.transport.system.common.station.service.impl;


import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.AttentionInfo;
import com.gistandard.transport.base.entity.bean.BizAttachment;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import com.gistandard.transport.base.entity.dao.AttentionInfoDao;
import com.gistandard.transport.base.entity.dao.ComCustomerDao;
import com.gistandard.transport.base.entity.service.BizAttachmentService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.station.bean.*;
import com.gistandard.transport.system.common.station.dao.CustomerAttentionInfoDao;
import com.gistandard.transport.system.common.station.dao.CustomerStationDao;
import com.gistandard.transport.system.common.station.service.CustomerStationService;
import com.gistandard.transport.system.upload.service.AccountUploadFileType;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.accountBusiness.AccountBusinessService;
import com.valueplus.psc.dubbo.service.accountBusiness.bean.AccountBusinessBean;
import com.valueplus.psc.dubbo.service.accountBusiness.bean.AccountRecommendBean;
import com.valueplus.psc.dubbo.service.accountBusiness.bean.AccountResultBean;
import com.valueplus.psc.dubbo.service.common.bean.ServiceAuthBean;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kongxm
 * @ClassName CustomerStationServiceImpl
 * @Description 服务商service
 * @Version 1.0
 * @Date 2016/1/28
 */
@Service
public class CustomerStationServiceImpl implements CustomerStationService {
    @Autowired
    private CustomerStationDao stationDao;

    @Autowired
    private AttentionInfoDao attentionInfoDao;

    @Autowired
    private CustomerAttentionInfoDao customerAttentionInfoDao;

    @Autowired
    private ComCustomerDao comCustomerDao;

    @Autowired
    private BizAttachmentService bizAttachmentService;

    @Autowired
    private AccountBusinessService accountBusinessService;

    @Override
    public List<Station> query(StationQueryReq req) {
        List<Station> stationList = stationDao.query(req);
        if (stationList != null && stationList.size() > 0) {
            for (Station station : stationList) {
                List<BizAttachment> attachList = bizAttachmentService.queryAttachByAcctId(station.getAccountId());
                if (station.getRoleId() != null) {
                    station.setRoleName(SysAccountRole.getName(station.getRoleId().intValue()));
                }
                if (attachList != null && attachList.size() > 0) {
                    for (BizAttachment bizAttachment : attachList) {
                        if (bizAttachment.getFileType() != null && bizAttachment.getFileType() == AccountUploadFileType.OPERATE_LICENSE.getValue()) {
                            station.setBizAttachment(bizAttachment);
                            break;
                        }
                    }
                }
            }
        }
        return stationList;
    }

    @Override
    public int queryCount(StationQueryReq req) {
        return stationDao.queryCount(req);
    }

    @Override
    public List<Station> queryNearby(StationQueryNearbyReq req) {
        List<Station> stationList = stationDao.queryNearby(req);
        return stationList;
    }

    @Override
    public int queryNearbyCount(StationQueryNearbyReq req) {
        return stationDao.queryNearbyCount(req);
    }

    @Override
    public void follow(StationFollowReq req) throws MobileStationBizException {
        Integer stationId = req.getStationId();
        Integer accountId = req.getAccountId();
        if (stationId == null) {
            throw new MobileStationBizException("供应商(站点)id不能为空");
        }
        if (accountId == null) {
            throw new MobileStationBizException("accountId不能为空");
        }
        //根据id查询站点
        ComCustomer comCustomer = comCustomerDao.selectByPrimaryKey(stationId);
        if (comCustomer == null) {
            throw new MobileStationBizException("供应商(站点)不存在");
        }
        //组装数据
        AttentionInfo attentionInfo = new AttentionInfo();
        attentionInfo.setAccountId(accountId);
        attentionInfo.setTransportType(2);//1是运输，2快递
        attentionInfo.setAttentionType(1);//1=站点,2快递员3司机
        attentionInfo.setStationId(stationId);
        attentionInfo.setAttentionStatus(1);//0=无效，1=有效
        attentionInfo.setAttentionTime(new Date());
        attentionInfo.setRoleId(req.getRoleId() == null ? 5 : req.getRoleId());
        //TODO:ATTENTION_SYSTEM是什么字段？设置的客户下单app
        attentionInfo.setAttentionSystem("customerApp");
        //判断是否关注过
        int count = customerAttentionInfoDao.count(attentionInfo);
        if (count > 0) {
            customerAttentionInfoDao.updateStatus(attentionInfo);
        } else {
            //插入数据
            attentionInfoDao.insert(attentionInfo);
        }
    }

    @Override
    public void unfollow(StationUnFollowReq req) throws MobileStationBizException {
        Integer stationId = req.getStationId();
        if (stationId == null) {
            throw new MobileStationBizException("stationId参数错误");
        }
        Integer accountId = req.getAccountId();
        if (accountId == null) {
            throw new MobileStationBizException("accountId参数错误");
        }
        AttentionInfo attentionInfo = new AttentionInfo();
        attentionInfo.setAccountId(accountId);
        attentionInfo.setStationId(stationId);
        attentionInfo.setAttentionStatus(0);
        attentionInfo.setRoleId(req.getRoleId() == null ? 5 : req.getRoleId());
        customerAttentionInfoDao.updateStatus(attentionInfo);
    }

    @Override
    public List<Station> queryFollow(AppBasePagerRequest req) throws MobileStationBizException {
        Integer accountId = req.getAccountId();
        if (accountId == null) {
            throw new MobileStationBizException("accountId不能为空");
        }
        List<Station> stationList = stationDao.queryFollow(req);
        if (stationList != null && stationList.size() > 0) {
            for (Station station : stationList) {
                List<BizAttachment> attachList = bizAttachmentService.queryAttachByAcctId(station.getAccountId());
                if (station.getRoleId() != null) {
                    station.setRoleName(SysAccountRole.getName(station.getRoleId().intValue()));
                }
                if (attachList != null && attachList.size() > 0) {
                    for (BizAttachment bizAttachment : attachList) {
                        if (bizAttachment.getFileType() != null && bizAttachment.getFileType() == AccountUploadFileType.OPERATE_LICENSE.getValue()) {
                            station.setBizAttachment(bizAttachment);
                            break;
                        }
                    }
                }
            }
        }
        return stationList;
    }

    @Override
    public int queryFollowCount(AppBasePagerRequest req) {
        return stationDao.queryFollowCount(req);
    }

    @Override
    public QueryBusinessRelationResult queryBusinessRelation(QueryBusinessRelationReq req) {
        QueryBusinessRelationResult queryResult = new QueryBusinessRelationResult(req);
        AccountBusinessBean accountBusinessBean = new AccountBusinessBean();
        if (!StringUtil.isEmpty(req.getAreaId())) {
            if (req.getAreaId().startsWith("3"))
                accountBusinessBean.setCityId(Integer.parseInt(req.getAreaId()));
            else {
                accountBusinessBean.setCountyId(Integer.parseInt(req.getAreaId()));
            }
        }
        ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
        serviceAuthBean.setAuthPwd(SystemDefine.CUSTOMER_AUTH_PWD);
        serviceAuthBean.setAuthUser(SystemDefine.CUSTOMER_AUTH_USER);
        serviceAuthBean.setSysFlag(SystemDefine.MOBILE_STATION_SYS_FLAG);
        accountBusinessBean.setStartRow(req.getStartRecord());
        accountBusinessBean.setSize(req.getPageSize());
        accountBusinessBean.setOrdersStatus(1);
        ServiceResult serviceResult = accountBusinessService.queryBusinessRelation(serviceAuthBean, accountBusinessBean);
        if (serviceResult.isResult()) {
            AccountResultBean accountResultBean = (AccountResultBean) serviceResult.getData();
            queryResult.setRecordCount(accountResultBean.getCount());
            List<BusinessRelation> relationList = new ArrayList<>();
            for (AccountRecommendBean recommendBean : accountResultBean.getList()) {
                BusinessRelation businessRelation = new BusinessRelation();
                businessRelation.setAccountId(recommendBean.getAccountId());
                businessRelation.setAcctUsername(recommendBean.getAcctUsername());
                businessRelation.setAddress(recommendBean.getAddress());
                businessRelation.setRealname(recommendBean.getRealName());
                businessRelation.setTelephone(recommendBean.getTelephone());
                relationList.add(businessRelation);
            }
            queryResult.setData(relationList);
        }
        return queryResult;
    }
}
