package com.gistandard.transport.app.module.station.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.station.bean.*;
import com.gistandard.transport.app.module.station.dao.StationDao;
import com.gistandard.transport.app.module.station.service.StationService;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.dao.ex.ComCustomerDaoEx;
import com.gistandard.transport.base.entity.service.ComAccountService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by m on 2016/10/8.
 */
@Service
public class StationServiceImpl implements StationService {
    private static final Logger logger = LoggerFactory.getLogger(StationServiceImpl.class);

    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;

    @Autowired
    private ComAccountService comAccountService;

    @Autowired
    private StationDao customerStationDao;

    @Override
    public List<CustomerListBean> queryMandWstation(QueryMandWstationReq req) throws MobileStationBizException {
        //查询哇站\米站列表，调用签派接口，传递订单号，类型（暂时没用），当前操作人帐号ID
        logger.info("快递员 指派查询 咪站蛙站列表queryMandWstation 请求{}", JSON.toJSONString(req));
        String customerListJson;
        if (SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode().equals(req.getType())) {
            customerListJson = mobileRecOrderWebService.getAllWaMiStationByRouteAndExpressCode(req.getBusiNo(), req.getUserId());
        } else {
            customerListJson = mobileRecOrderWebService.selectOrderCustomerList(req.getBusiNo(), req.getType(), req.getUserId());
        }
        if (customerListJson != null) {
            SelectOrderCustomerListRes res = JSON.parseObject(customerListJson, SelectOrderCustomerListRes.class);
            if (res.getStatus() == 1) {
                String jsonStr = (String) res.getData();
                List<CustomerListBean> customerList = JSON.parseArray(jsonStr, CustomerListBean.class);
                boolean flag = req.getItemCode().equals(MobileStationDefine.PRODUCT_TYPE_ITCYS);
                Iterator<CustomerListBean> iterator = customerList.iterator();
                List<String> accountList = new ArrayList<>();
                HashMap<String, CustomerListBean> tempMap = new HashMap<>();
                List<CustomerListBean> resultList = new ArrayList<>();
                while (iterator.hasNext()) {
                    CustomerListBean customerListBean = iterator.next();
                    if (StringUtils.isBlank(customerListBean.getId())) {
                        iterator.remove();
                        continue;
                    }
                    if (!StringUtils.isBlank(req.getMwType())) {
                        if (!req.getMwType().equals(customerListBean.getType())) {
                            iterator.remove();
                            continue;
                        }
                    }
                    if (req.getStationCategoryAttr() != null) {
                        if (req.getStationCategoryAttr() != customerListBean.getStationCategoryAttr()) {
                            iterator.remove();
                            continue;
                        }
                    }
                    if (flag && customerListBean.getType() != null) {  //同程运输，只要洼站
                        if (customerListBean.getType().equals(CustomerListBean.M)) {
                            iterator.remove();
                            continue;
                        }
                    }
                    tempMap.put(customerListBean.getUserCode(), customerListBean);
                    accountList.add(customerListBean.getUserCode());
                }
                if (accountList.size() >= 1) {
                    List<StationInfo> stationInfoList = customerStationDao.batchQueryStationInfo(accountList);
                    for (String userCode : accountList) {
                        CustomerListBean customerListBean = tempMap.get(userCode);
                        if (customerListBean != null) {
                            for (StationInfo stationInfo : stationInfoList) {
                                if (userCode.equals(stationInfo.getAcctUsername())) {
                                    customerListBean.setUserAccountId(stationInfo.getAccountId());
                                    customerListBean.setUserImg(stationInfo.getUserImg());
                                    customerListBean.setCustTtl(stationInfo.getCustTtl());
                                    break;
                                }
                            }
                            resultList.add(customerListBean);
                        }
                    }

                }
                logger.info("queryMandWstation {}", JSON.toJSONString(resultList));
                return resultList;
            } else {
                throw new MobileStationBizException(res.getMesasge());
            }
        } else {
            throw new MobileStationBizException("网络超时");
        }
    }

    public List<CustomerListBean> getAllMiMovingByMiStopId(GetAllMiMovingByMiStopIdReq req) throws MobileStationBizException {
        String customerListJson = mobileRecOrderWebService.getAllMiMovingByMiStopId(req.getIdGiMiRoutePlan(), req.getMiStopId());
        if (customerListJson != null) {
            SelectOrderCustomerListRes res = JSON.parseObject(customerListJson, SelectOrderCustomerListRes.class);
            if (res.getStatus() == 1) {
                String jsonStr = (String) res.getData();
                List<CustomerListBean> customerList = JSON.parseArray(jsonStr, CustomerListBean.class);
                Iterator<CustomerListBean> iterator = customerList.iterator();
                while (iterator.hasNext()) {
                    CustomerListBean customerListBean = iterator.next();
                    if (StringUtils.isBlank(customerListBean.getId())) {
                        iterator.remove();
                        continue;
                    }
                    //如果comAccount不存在则此M\W账户有问题，也去除
                    ComAccount comAccount = comAccountService.queryAccountByAcctUsername(customerListBean.getUserCode());
                    if (comAccount == null) {
                        iterator.remove();
                        continue;
                    } else {
                        customerListBean.setUserAccountId(comAccount.getId());
                        customerListBean.setUserImg(comAccount.getUserImg());
                    }
                }
                return customerList;
            } else {
                throw new MobileStationBizException(res.getMesasge());
            }
        } else {
            throw new MobileStationBizException("网络超时");
        }
    }
}
