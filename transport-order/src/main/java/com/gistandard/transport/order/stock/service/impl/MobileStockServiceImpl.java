package com.gistandard.transport.order.stock.service.impl;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.app.BaseResPageBean;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.MobileStockDao;
import com.gistandard.transport.base.entity.dao.MobileStockDetailDao;
import com.gistandard.transport.base.entity.service.ComUnitService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.MobileOrderOperateBean;
import com.gistandard.transport.order.module.mobilestation.bean.stock.*;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.stock.MobileStationStockDao;
import com.gistandard.transport.order.stock.bean.QueryStockListResult;
import com.gistandard.transport.order.stock.service.MobileStockService;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MobileStockServiceImpl implements MobileStockService {
    @Autowired
    private MobileStationStockDao mobileStationStockDao;
    @Autowired
    private ComUnitService comUnitService;
    @Autowired
    private MobileStockDao mobileStockDao;
    @Autowired
    private MobileStockDetailDao mobileStockDetailDao;
    @Autowired
    private MobileStationOrderDao mobileStationOrderDao;
    @Autowired
    private ComAccountDao comAccountDao;

    /**
     * 派车单出入库
     *
     * @param stockInOutReq
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult stockInOut(StockInOutReq stockInOutReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(stockInOutReq);
        if (StringUtil.isEmpty(stockInOutReq.getStockType())) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("出入库参数不能为空！");
            return baseResBean;
        }
        if (StringUtils.isBlank(stockInOutReq.getBusiBookNo())){
            stockInOutReq.setBusiBookNo(stockInOutReq.getScheducarno());
        }
        //查询订单货物
        List<MobileGoodsDtl> goodsDtlList = mobileStationStockDao.queryMobileGoodsDtlListTwo(
                stockInOutReq.getBusiBookNo(), stockInOutReq.getAcctUsername(), stockInOutReq.getCompanyAccountId());
        if (goodsDtlList.size() > 0) {
            BigDecimal totalQty = getOrderGoodsTotalPackageQty(goodsDtlList);
            stockInOutReq.setGoodsQty(totalQty.intValue());
            stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
            stockInOutReq.setGoodsName(goodsDtlList.get(0).getGoodsName());
            stockInOutReq.setGoodsType(goodsDtlList.get(0).getGoodsType());
            //操作库存
            stockInOutOperate(stockInOutReq);
        } else {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("没有货物！");
        }

        return baseResBean;
    }

    //获取订单总包装数
    private BigDecimal getOrderGoodsTotalPackageQty(List<MobileGoodsDtl> goodsDtlList) {
        //查询订单的货物总数
        BigDecimal totalPackageQty = new BigDecimal("0");
        for (MobileGoodsDtl mobileGoodsDtl : goodsDtlList) {
            if (mobileGoodsDtl.getGoodsQty() != null) {
                totalPackageQty = totalPackageQty.add(mobileGoodsDtl.getGoodsQty());
            }
        }
        return totalPackageQty;
    }

    //获取当前库存总包装数
    @Override
    public BigDecimal getOrderStockTotalPackageQty(Integer subOrderId, Integer accountId) {
        //查询订单的货物总数
        List<MobileStock> stockList = mobileStationStockDao.querySubOrderMobileStockListBySubOrderId(subOrderId, accountId);
        BigDecimal totalPackageQty = new BigDecimal("0");
        for (MobileStock stock : stockList) {
            totalPackageQty = totalPackageQty.add(stock.getGoodsQty());
        }
        return totalPackageQty;
    }

    //MS扫描收货确认码，代另一个MS出库
    private void otherStockOutOperate(StockInOutReq stockInOutReq) {
        //给送货MS的库存做出库，修改当前库存，插入出库历史
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(stockInOutReq.getOtherAccountId());
        stockInOutOperate(stockInOutReq);
        //修改送货MS的订单状态，改为确认送达
        MobileOrderOperateBean orderOutOperateBean = new MobileOrderOperateBean(stockInOutReq.getOtherAccountId(), stockInOutReq.getBusiBookNo(),
                comAccount.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN, MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
        mobileStationOrderDao.updateBookingFormStatusByBusiNo(orderOutOperateBean);
        //TODO，是否需要更细子订单
        //出入库 插入出入库历史明细表

        if (stockInOutReq.getGoodsQty() != 0) {
            MobileStockDetail mobileStockDetail = new MobileStockDetail();
            mobileStockDetail.setBusiBookNo(stockInOutReq.getBusiBookNo());
            mobileStockDetail.setCreateDate(new Date());
            mobileStockDetail.setCreateUser(stockInOutReq.getOtherAccountId());
            mobileStockDetail.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
            mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
            mobileStockDetail.setStockType(MobileStationDefine.STOCK_OUT);
            mobileStockDetail.setScheducarno(stockInOutReq.getScheducarno());
            mobileStockDetailDao.insert(mobileStockDetail);
            //查询当前库存 同一类型的库存，如果有，进行汇总，没有，插入
            List<MobileStock> mobileStockList = mobileStationStockDao.queryMobileStockInfo(stockInOutReq);
            if (mobileStockList != null && mobileStockList.size() > 0) {
                MobileStock mobileStock = mobileStockList.get(0);
                if (mobileStock == null || StringUtil.isEmpty(mobileStock.getBusiBookNo())) {
                    //插入当前库存表
                    mobileStock = new MobileStock();
                    mobileStock.setAccountId(stockInOutReq.getOtherAccountId());
                    mobileStock.setOperDate(new Date());
                    mobileStock.setBusiBookNo(stockInOutReq.getBusiBookNo());
                    mobileStock.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                    mobileStock.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                    mobileStock.setScheducarno(stockInOutReq.getScheducarno());
                    mobileStockDao.insert(mobileStock);
                } else {
                    //对库存进行汇总
                    //出库，库存相减
                    if (mobileStock.getGoodsQty().intValue() < stockInOutReq.getGoodsQty()) {
                        //如果当前库存小于要出库的库存,上面要给出提示
                        mobileStock.setGoodsQty(new BigDecimal(0));
                    } else {
                        mobileStock.setGoodsQty(mobileStock.getGoodsQty().subtract(BigDecimal.valueOf(stockInOutReq.getGoodsQty())));
                    }
                    mobileStock.setOperDate(new Date());
                    mobileStockDao.updateByPrimaryKey(mobileStock);
                }
            }
        }
    }

    //进出库操作库存
    private void stockInOutOperate(StockInOutReq stockInOutReq) {
        //出入库 插入出入库历史明细表
        if (stockInOutReq.getGoodsQty() != 0) {
            MobileStockDetail mobileStockDetail = new MobileStockDetail();
            mobileStockDetail.setBusiBookNo(stockInOutReq.getBusiBookNo());
            mobileStockDetail.setCreateDate(new Date());
            mobileStockDetail.setCreateUser(stockInOutReq.getAccountId());
            mobileStockDetail.setGoodsName(stockInOutReq.getGoodsName());
            mobileStockDetail.setGoodsType(stockInOutReq.getGoodsType());
            mobileStockDetail.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
            if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
            }
            mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
            mobileStockDetail.setStockType(stockInOutReq.getStockType());
            mobileStockDetail.setOrderFrom(stockInOutReq.getOrderFrom());
            if (!StringUtil.isEmpty(stockInOutReq.getBoxNum())) {
                mobileStockDetail.setBoxNum(Integer.valueOf(stockInOutReq.getBoxNum()));
            }

            mobileStockDetail.setScheducarno(stockInOutReq.getScheducarno());
            mobileStockDetailDao.insert(mobileStockDetail);

            if (stockInOutReq.getStockType() == MobileStationDefine.STOCK_IN) {
                List<MobileStock> mobileStockList = mobileStationStockDao.queryMobileStockInfo(stockInOutReq);
                if (mobileStockList != null && mobileStockList.size() > 0) {
                    MobileStock stock = mobileStockList.get(0);
                    if (null != stock) {
                        stock.setOperDate(new Date());
                        stock.setGoodsType(stockInOutReq.getGoodsType());
                        stock.setGoodsName(stockInOutReq.getGoodsName());
                        stock.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                        stock.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                        stock.setOrderFrom(stockInOutReq.getOrderFrom());
                        stock.setScheducarno(stockInOutReq.getScheducarno());
                        mobileStockDao.updateByPrimaryKey(stock);
                    }
                } else {
                    //插入当前库存表
                    MobileStock mobileStock = new MobileStock();
                    mobileStock.setAccountId(stockInOutReq.getAccountId());
                    mobileStock.setOperDate(new Date());
                    mobileStock.setBusiBookNo(stockInOutReq.getBusiBookNo());
                    mobileStock.setGoodsType(stockInOutReq.getGoodsType());
                    mobileStock.setGoodsName(stockInOutReq.getGoodsName());
                    mobileStock.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                    mobileStock.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                    mobileStock.setOrderFrom(stockInOutReq.getOrderFrom());
                    mobileStock.setScheducarno(stockInOutReq.getScheducarno());
                    mobileStockDao.insert(mobileStock);
                }
            } else {
                //查询当前库存 同一类型的库存
                List<MobileStock> mobileStockList = mobileStationStockDao.queryMobileStockInfo(stockInOutReq);
                if (mobileStockList != null && mobileStockList.size() > 0) {
                    MobileStock mobileStock = mobileStockList.get(0);
                    if (null != mobileStock) {
                        //出库，库存相减
                        if (mobileStock.getGoodsQty().intValue() < stockInOutReq.getGoodsQty()) {
                            //如果当前库存小于要出库的库存,上面要给出提示
                            mobileStock.setGoodsQty(new BigDecimal(0));
                        } else {
                            mobileStock.setGoodsQty(mobileStock.getGoodsQty().subtract(BigDecimal.valueOf(stockInOutReq.getGoodsQty())));
                        }
                        mobileStock.setOperDate(new Date());
                        mobileStockDao.updateByPrimaryKey(mobileStock);
                    }
                }
            }
        }
    }

    /**
     * 查询当前库存
     *
     * @param queryStockListReq
     * @throws Exception
     */
    @Override
    public QueryStockListResult queryStockList(QueryStockListReq queryStockListReq) {
        QueryStockListResult baseResBean = new QueryStockListResult(queryStockListReq);
        //查询账面库存与当前实际库存
        List<QueryStockListBean> list = mobileStationStockDao.queryStockList(queryStockListReq);
        Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
        for (QueryStockListBean stockBean : list) {
            if (comUnitMap.get(stockBean.getGoodsUnit()) != null) {
                stockBean.setGoodsUnitName(comUnitMap.get(stockBean.getGoodsUnit()).getUnitCh());
            }
        }
        baseResBean.setData(list);
        return baseResBean;
    }

    /**
     * 查询出入库历史
     *
     * @param queryStockDetailListReq
     * @throws Exception
     */
    @Override
    public BaseResPageBean queryStockDetailList(QueryStockDetailListReq queryStockDetailListReq) {
        BaseResPageBean baseResPageBean = new BaseResPageBean();
        //查询符合条件的记录总数
        int recordCount = mobileStationStockDao.queryStockDetailListCount(queryStockDetailListReq);
        if (recordCount > 0) {
            //根据条件查询出入库历史
            List<QueryStockDetailListBean> detailListBeans = mobileStationStockDao.queryStockDetailList(queryStockDetailListReq);
            Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
            for (QueryStockDetailListBean detailBean : detailListBeans) {
                if (detailBean.getGoodsQtyUnit() != null && comUnitMap.get(detailBean.getGoodsQtyUnit()) != null) {
                    detailBean.setGoodsQtyUnitName(comUnitMap.get(detailBean.getGoodsQtyUnit()).getUnitCh());
                }
            }
            baseResPageBean.setData(detailListBeans);
        }
        baseResPageBean.setRecordCount(recordCount);
        baseResPageBean.setReqId(queryStockDetailListReq.getReqId());
        return baseResPageBean;
    }
}
