package com.gistandard.transport.order.module.workcenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.dubbo.order.bean.MobileGoodsInfo;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.BookingFormDao;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationOrderListBean;
import com.gistandard.transport.order.module.workcenter.bean.*;
import com.gistandard.transport.order.module.workcenter.dao.WorkCenterDao;
import com.gistandard.transport.order.module.workcenter.service.WorkCenterService;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xgw
 * @ClassName: WorkCenterServiceImpl
 * @Date: 2017/4/6
 * @Description: 工作中心模块
 */
@Service
public class WorkCenterServiceImpl implements WorkCenterService {

    private static final Logger logger = LoggerFactory.getLogger(WorkCenterServiceImpl.class);

    @Autowired
    private WorkCenterDao workCenterDao;
    @Autowired
    private BookingFormDao bookingFormDao;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private PnWebService pnWebService;
    @Autowired
    private ComProvinceService comProvinceService;
    @Autowired
    private ComCityService comCityService;
    @Autowired
    private ComCountyService comCountyService;
    @Autowired
    private ComCurrencyService comCurrencyService;
    @Autowired
    private ComUnitService comUnitService;
    @Autowired
    private ComGoodsTypeService comGoodsTypeService;
    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;

    /**
     * 工作中心列表查询(地图)
     *
     * @param workCenterMapListReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public WorkCenterMapListResult queryMapOrderList(WorkCenterMapListReq workCenterMapListReq) throws MobileStationBizException {
        WorkCenterMapListResult workCenterListResult = new WorkCenterMapListResult(workCenterMapListReq);
        List<WorkCenterMapListBean> workCenterListBeanList;
        List roleList = new ArrayList();
        roleList.add(SysAccountRole.getRoleCode(workCenterMapListReq.getRoleId()));
        if (workCenterMapListReq.getBusiCtrl() != null && workCenterMapListReq.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER
                && workCenterMapListReq.getOrderType() != null && workCenterMapListReq.getOrderType() == 2) {
            //待接广播单，不需要查表
            workCenterListBeanList = new ArrayList<>();
        } else {
            workCenterListBeanList = workCenterDao.queryMapOrderList(workCenterMapListReq);

            if (workCenterListBeanList.size() > 0) {
                List<Long> schemaIds = new ArrayList<>();
                for (WorkCenterMapListBean workCenterMapListBean : workCenterListBeanList) {
                    if (workCenterMapListBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                            && workCenterMapListBean.getRouteSchemaId() != null) {
                        schemaIds.add(workCenterMapListBean.getRouteSchemaId().longValue());
                    }
                }
                if (schemaIds != null && schemaIds.size() > 0) {
                    String res = mobileRecOrderWebService.getRouteTimeBySchemaId(schemaIds);
                    RouteTimeResBean routeTimeResBean = JSON.parseObject(res, RouteTimeResBean.class);

                    if (routeTimeResBean != null && routeTimeResBean.getStatus() == 1 && routeTimeResBean.getData() != null && routeTimeResBean.getData().size() > 0) {
                        Map<String, RouteTime> map = new HashMap();
                        for (RouteTime routeTime : routeTimeResBean.getData()) {
                            map.put(routeTime.getRouteSchemaId().toString(), routeTime);
                        }

                        for (WorkCenterMapListBean workCenterMapListBean : workCenterListBeanList) {
                            if (workCenterMapListBean.getRouteSchemaId() != null && map.get(workCenterMapListBean.getRouteSchemaId().toString()) != null) {
                                workCenterMapListBean.setRouteTime(map.get(workCenterMapListBean.getRouteSchemaId().toString()).getTimeControl());
                            }
                        }
                    }
                }
            }
        }

        if (workCenterMapListReq.getBusiCtrl() == null || workCenterMapListReq.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER) {
            if (workCenterMapListReq.getOrderType() == null || workCenterMapListReq.getOrderType() == 2) {
                Long radius = 0L;
                if (workCenterMapListReq.getRadius() != null) {
                    radius = workCenterMapListReq.getRadius();
                }
                //获取待接单列表
                String gpsRes = pnWebService.getAllGiBizOrderForCluster(workCenterMapListReq.getLongitude().doubleValue(), workCenterMapListReq.getLatitude().doubleValue(),
                        JSON.toJSONString(roleList), workCenterMapListReq.getIsOnlyMyFence(), radius);
                List<GiBizOrder> broadcastOrderList = JSON.parseArray(gpsRes, GiBizOrder.class);
                WorkCenterMapListBean workCenterMapListBean;
                if (broadcastOrderList != null && broadcastOrderList.size() > 0) {
                    for (GiBizOrder giBizOrder : broadcastOrderList) {
                        workCenterMapListBean = new WorkCenterMapListBean();
                        workCenterMapListBean.setBusiBookNo(giBizOrder.getDocNo());
                        workCenterMapListBean.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
                        workCenterMapListBean.setOrderFrom(Integer.parseInt(giBizOrder.getDocFrom()));
                        workCenterMapListBean.setTransportType(giBizOrder.getProductType());
                        workCenterMapListBean.setOrderDate(giBizOrder.getTsClientPushed());
                        workCenterMapListBean.setPushDate(giBizOrder.getTsClientPushed());
                        workCenterMapListBean.setScheducarno(giBizOrder.getScheducarno());
                        workCenterMapListBean.setProductType(giBizOrder.getProductType());
                        if (giBizOrder.getPointSource() != null) {
                            workCenterMapListBean.setLongitude(BigDecimal.valueOf(giBizOrder.getPointSource().getLongitude()));
                            workCenterMapListBean.setLatitude(BigDecimal.valueOf(giBizOrder.getPointSource().getLatitude()));
                        }
                        workCenterListBeanList.add(workCenterMapListBean);
                    }
                }
            }
        }

        //获取待收货、待派送单列表
        workCenterListResult.setData(workCenterListBeanList);
        return workCenterListResult;
    }

    /**
     * 工作中心列表查询(根据订单ID和订单号查询)
     *
     * @param workCenterListReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public WorkCenterListResult queryOrderList(WorkCenterListReq workCenterListReq) throws MobileStationBizException {
        WorkCenterListResult workCenterListResult = new WorkCenterListResult(workCenterListReq);
        List<WorkCenterListBean> workCenterListBeanList = new ArrayList<>();
        List<Integer> orderIdList = new ArrayList<>();
        List<String> busiBookNoList = new ArrayList<>();
        if (workCenterListReq.getWorkCenterListReqBeanList() != null && workCenterListReq.getWorkCenterListReqBeanList().size() > 0) {
            //获取指派单、待收件、待送达订单列表
            for (WorkCenterListReqBean workCenterListReqBean : workCenterListReq.getWorkCenterListReqBeanList()) {
                if (workCenterListReqBean.getOrderId() != null) {
                    orderIdList.add(workCenterListReqBean.getOrderId());
                } else {
                    busiBookNoList.add(workCenterListReqBean.getBusiBookNo());
                }
            }

            WorkCenterListBean workCenterListBean;
            BookingForm bookingForm;
            ComAccount comAccount;
            if (orderIdList != null && orderIdList.size() > 0) {
                Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                Map<String, ComCity> comCityMap = comCityService.queryForMap();
                Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
                Map<String, ComCurrency> comCurrencyMap = comCurrencyService.queryForMap();
                Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
                Map<String, ComGoodsType> comGoodsTypeMap = comGoodsTypeService.queryForMap();
                try {
                    List<MobileStationOrderListBean> orderList = workCenterDao.queryOrderList(orderIdList);
                    if (orderList != null && orderList.size() > 0) {
                        for (MobileStationOrderListBean orderListBean : orderList) {
                            workCenterListBean = new WorkCenterListBean();
                            BeanUtils.copyProperties(workCenterListBean, orderListBean);
                            // 根据省市区设置地址
                            String startAddress = "";
                            String destAddress = "";
                            if (!StringUtil.isEmpty(orderListBean.getStartProvide())
                                    && comProvinceMap.get(orderListBean.getStartProvide()) != null) {
                                // 判断地址信息是否包含省
                                if (orderListBean.getStartAddress().indexOf(
                                        comProvinceMap.get(orderListBean.getStartProvide()).getProvinceName()) == -1) {
                                    startAddress += comProvinceMap.get(orderListBean.getStartProvide()).getProvinceName();
                                    if (orderListBean.getStartAddress().indexOf(
                                            comCityMap.get(orderListBean.getStartCity()).getName()) == -1) {
                                        if (!StringUtil.isEmpty(orderListBean.getStartCity())
                                                && comCityMap.get(orderListBean.getStartCity()) != null) {
                                            startAddress += comCityMap.get(orderListBean.getStartCity()).getName();
                                        }
                                        if (!StringUtil.isEmpty(orderListBean.getStartCounty())
                                                && comCountyMap.get(orderListBean.getStartCounty()) != null) {
                                            startAddress += comCountyMap.get(orderListBean.getStartCounty()).getAreaName();
                                        }
                                    }
                                }
                            }
                            workCenterListBean.setStartAddress(startAddress + orderListBean.getStartAddress());

                            if (!StringUtil.isEmpty(orderListBean.getDestProvide())
                                    && comProvinceMap.get(orderListBean.getDestProvide()) != null) {
                                // 判断地址信息是否包含省
                                if (orderListBean.getDestAddress().indexOf(
                                        comProvinceMap.get(orderListBean.getDestProvide()).getProvinceName()) == -1) {
                                    destAddress += comProvinceMap.get(orderListBean.getDestProvide()).getProvinceName();
                                    if (orderListBean.getStartAddress().indexOf(
                                            comCityMap.get(orderListBean.getDestCity()).getName()) == -1) {
                                        if (!StringUtil.isEmpty(orderListBean.getDestCity())
                                                && comCityMap.get(orderListBean.getDestCity()) != null) {
                                            destAddress += comCityMap.get(orderListBean.getDestCity()).getName();
                                        }
                                        if (!StringUtil.isEmpty(orderListBean.getDestCounty())
                                                && comCountyMap.get(orderListBean.getDestCounty()) != null) {
                                            destAddress += comCountyMap.get(orderListBean.getDestCounty()).getAreaName();
                                        }
                                    }
                                }
                            }
                            workCenterListBean.setDestAddress(destAddress + orderListBean.getDestAddress());

                            String description = "";
                            String weightUtil = "";
                            for (MobileGoodsInfo goodsInfo : orderListBean.getGoodsInfoList()) {
                                if (goodsInfo.getGoodsType() != null) {
                                    String typeName = goodsInfo.getGoodsType();
                                    if (comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                                        typeName = comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh();
                                    }
                                    description += typeName + "-";
                                }
                                if (goodsInfo.getGoodsName() != null) {
                                    description += goodsInfo.getGoodsName();
                                }
                                if (goodsInfo.getGoodsWeight() != null) {
                                    if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit())
                                            && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                                        weightUtil = comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh();
                                    }

                                    description += "重量：" + goodsInfo.getGoodsWeight().setScale(2, BigDecimal.ROUND_HALF_UP)
                                            + weightUtil + " ";
                                }
                                if (goodsInfo.getGoodsLength() != null && goodsInfo.getGoodsWide() != null
                                        && goodsInfo.getGoodsHeight() != null) {
                                    description += "体积：" + goodsInfo.getGoodsLength().setScale(0, BigDecimal.ROUND_HALF_UP)
                                            + "*" + goodsInfo.getGoodsWide().setScale(0, BigDecimal.ROUND_HALF_UP) + "*"
                                            + goodsInfo.getGoodsHeight().setScale(0, BigDecimal.ROUND_HALF_UP) + "立方厘米";
                                }
                                description += " ";
                                //货物类型名称
                                if (!StringUtil.isEmpty(goodsInfo.getGoodsType()) && comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                                    goodsInfo.setGoodsTypeName(comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh());
                                }
                                if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit()) && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                                    goodsInfo.setGoodsQtyUnitName(comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh());
                                }
                                if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit()) && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                                    goodsInfo.setGoodsWeightUnitName(comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh());
                                }
                                if (!StringUtil.isEmpty(goodsInfo.getGoodsVolumeUnit()) && comUnitMap.get(goodsInfo.getGoodsVolumeUnit()) != null) {
                                    goodsInfo.setGoodsVolumeUnitName(comUnitMap.get(goodsInfo.getGoodsVolumeUnit()).getUnitCh());
                                }
                            }
                            workCenterListBean.setDescription(description);
                            // 设置币制
                            if (!StringUtil.isEmpty(orderListBean.getPredictCurr())
                                    && comCurrencyMap.get(orderListBean.getPredictCurr()) != null) {
                                workCenterListBean.setPredictCurr(comCurrencyMap.get(orderListBean.getPredictCurr())
                                        .getCurrencyCh());
                            }

                            bookingForm = bookingFormDao.selectByPrimaryKey(orderListBean.getBookingFormId());
                            //设置实名制状态
                            if (bookingForm != null && bookingForm.getCreateUserId() != null) {
                                comAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateUserId());
                                if (comAccount != null && !StringUtil.isEmpty(comAccount.getRealName())) {
                                    workCenterListBean.setIsRealName(true);
                                } else {
                                    workCenterListBean.setIsRealName(false);
                                }
                            }

                            workCenterListBeanList.add(workCenterListBean);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            //获取广播单列表
            if (busiBookNoList != null && busiBookNoList.size() > 0) {
                String gpsRes = pnWebService.getAllGiBizOrderByAllDocNo(JSON.toJSONString(busiBookNoList));
                List<GiBizOrder> broadcastOrderList = JSON.parseArray(gpsRes, GiBizOrder.class);
                if (broadcastOrderList != null && broadcastOrderList.size() > 0) {
                    for (GiBizOrder giBizOrder : broadcastOrderList) {
                        workCenterListBean = new WorkCenterListBean();
                        workCenterListBean.setBusiBookNo(giBizOrder.getDocNo());
                        workCenterListBean.setScheducarno(giBizOrder.getScheducarno());
                        workCenterListBean.setTransportType(giBizOrder.getBizType() + "");
                        workCenterListBean.setOrderFrom(Integer.parseInt(giBizOrder.getDocFrom()));
                        workCenterListBean.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
                        workCenterListBean.setNarrate(giBizOrder.getDescription());
                        workCenterListBean.setPredictValue(giBizOrder.getTransportCost());
                        workCenterListBean.setPredictCurr(giBizOrder.getPredictCurr());
                        workCenterListBean.setProductType(giBizOrder.getProductType());
                        workCenterListBean.setPushDate(giBizOrder.getTsClientPushed());
                        workCenterListBean.setStartProvide(giBizOrder.getSourceProvince());
                        workCenterListBean.setStartCity(giBizOrder.getSourceCity());
                        workCenterListBean.setStartCounty(giBizOrder.getSourceDistrict());
                        workCenterListBean.setStartAddress(giBizOrder.getSourceAddress());
                        if (giBizOrder.getPointSource() != null) {
                            workCenterListBean.setStartLatitude(BigDecimal.valueOf(giBizOrder.getPointSource().getLatitude()));
                            workCenterListBean.setStartLongitude(BigDecimal.valueOf(giBizOrder.getPointSource().getLongitude()));
                        }
                        workCenterListBean.setDestProvide(giBizOrder.getDestProvince());
                        workCenterListBean.setDestCity(giBizOrder.getDestCity());
                        workCenterListBean.setDestCounty(giBizOrder.getDestDistrict());
                        workCenterListBean.setDestAddress(giBizOrder.getDestAddress());

                        if (giBizOrder.getPointDest() != null) {
                            workCenterListBean.setDestLatitude(BigDecimal.valueOf(giBizOrder.getPointDest().getLatitude()));
                            workCenterListBean.setDestLongitude(BigDecimal.valueOf(giBizOrder.getPointDest().getLongitude()));
                        }
                        workCenterListBean.setIsRealName(giBizOrder.getIsRealName());
                        workCenterListBeanList.add(workCenterListBean);
                    }
                }
            }
        }

        workCenterListResult.setData(workCenterListBeanList);
        return workCenterListResult;
    }
}
