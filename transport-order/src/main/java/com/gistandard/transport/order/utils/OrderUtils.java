package com.gistandard.transport.order.utils;

import com.gistandard.transport.base.entity.bean.BookingForm;
import com.gistandard.transport.base.entity.bean.ComWaybillTrace;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.bean.MobileScheduSubOrder;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileScheduSubOrderDaoEx;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.stock.BatchStockInReq;
import com.gistandard.transport.order.module.mobilestation.bean.stock.BatchStockInReqBean;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockInReq;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDao;
import com.gistandard.transport.system.frame.disruptor.DisruptorHelper;
import com.gistandard.transport.tools.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单模块业务工具类
 * Created by m on 2016/11/26.
 */
@Component
public class OrderUtils {
    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;
    @Autowired
    private MobileStationOrderDao mobileStationOrderDao;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;


    /**
     * 根据派车单查询子订单,并修改为指定状态
     */
    @Transactional(rollbackFor = MobileStationBizException.class)
    public void querySubOrderbyScheno(MobileBookingForm mobileBookingForm, Integer oldStatus, Integer destStatus){
        List<MobileScheduSubOrder> mobileScheduSubOrders = mobileScheduSubOrderDaoEx
                .selectByScheducarno(mobileBookingForm.getScheducarno(), mobileBookingForm.getRevUser(),
                        mobileBookingForm.getRoleId());
        for (MobileScheduSubOrder mobileScheduSubOrder : mobileScheduSubOrders){
            MobileOrderOperateBean2 orderOutOperateBean2 = new MobileOrderOperateBean2(
                    mobileScheduSubOrder.getBusiBookNo(), mobileBookingForm.getRevUser(),
                    mobileBookingForm.getRevCompanyId(), oldStatus, destStatus, null);
            mobileStationOrderDao.updateBookingFormStatus2(orderOutOperateBean2);
        }
    }

    /**
     * 批量插入跟踪日志
     *
     * @param waybillTraceOperateBeans
     * @throws Exception
     */
    public void batchInsertWaybillTrace(List<WaybillTraceOperateBean> waybillTraceOperateBeans){
        List<ComWaybillTrace> tmps = new ArrayList<>();
        for (WaybillTraceOperateBean waybillTraceOperateBean : waybillTraceOperateBeans) {
            ComWaybillTrace tmp;
            if (waybillTraceOperateBean != null) {
                if (StringUtil.isEmpty(waybillTraceOperateBean.getScheducarno())) {
                    // 订单，根据订单号获取订单信息，直接插入订单的跟踪日志
                    tmp = new ComWaybillTrace();
                    tmp.setAcctUsername(waybillTraceOperateBean.getAcctUsername());
                    tmp.setBusiBookNo(waybillTraceOperateBean.getBusiBookNo());
                    tmp.setStartLocus(waybillTraceOperateBean.getStartLocus());
                    tmp.setDestnLocus(waybillTraceOperateBean.getDestnLocus());
                    tmp.setGrade(waybillTraceOperateBean.getGrade());
                    tmp.setRemark(waybillTraceOperateBean.getRemark());
                    tmp.setExecCode(waybillTraceOperateBean.getExecCode());
                    tmp.setStaDate(new Date());
                    if (waybillTraceOperateBean.getRoleId() != null) {
                        tmp.setRoleId(waybillTraceOperateBean.getRoleId());
                    }
                    if (waybillTraceOperateBean.getRealName() != null) {
                        tmp.setRealName(waybillTraceOperateBean.getRealName());
                    }
                    tmps.add(tmp);
//                comWaybillTraceService.insert(tmp);
                } else {
                    // 派车单，需要插入派车单和所有子订单的跟踪日志
                    // 插入主单日志
                    tmp = new ComWaybillTrace();
                    tmp.setAcctUsername(waybillTraceOperateBean.getAcctUsername());
                    tmp.setBusiBookNo(waybillTraceOperateBean.getScheducarno());
                    tmp.setStartLocus(waybillTraceOperateBean.getStartLocus());
                    tmp.setDestnLocus(waybillTraceOperateBean.getDestnLocus());
                    tmp.setGrade(waybillTraceOperateBean.getGrade());
                    tmp.setRemark(waybillTraceOperateBean.getRemark());
                    tmp.setExecCode(waybillTraceOperateBean.getExecCode());
                    tmp.setHubNo(waybillTraceOperateBean.getScheducarno());
                    tmp.setStaDate(new Date());
                    if (waybillTraceOperateBean.getRoleId() != null) {
                        tmp.setRoleId(waybillTraceOperateBean.getRoleId());
                    }
                    if (waybillTraceOperateBean.getRealName() != null) {
                        tmp.setRealName(waybillTraceOperateBean.getRealName());
                    }
                    tmps.add(tmp);
//                comWaybillTraceService.insert(tmp);
                    // 插入子弹日志
                    List<MobileScheduSubOrder> bookingForms = mobileMyOrderDao
                            .selectMobileSubOrderByMobileId(waybillTraceOperateBean.getDispatchId());
                    for (int i = 0; i < bookingForms.size(); i++) {
                        ComWaybillTrace sontmp = new ComWaybillTrace();
                        sontmp.setAcctUsername(waybillTraceOperateBean.getAcctUsername());
                        sontmp.setBusiBookNo(bookingForms.get(i).getBusiBookNo());
                        // sontmp.setWaybillNo(bookingForms.get(i).getWaybillNo());
                        sontmp.setStartLocus(waybillTraceOperateBean.getStartLocus());
                        sontmp.setDestnLocus(waybillTraceOperateBean.getDestnLocus());
                        sontmp.setGrade(waybillTraceOperateBean.getGrade());
                        sontmp.setRemark(waybillTraceOperateBean.getRemark());
                        sontmp.setExecCode(waybillTraceOperateBean.getExecCode());
                        sontmp.setStaDate(new Date());
                        sontmp.setHubNo(waybillTraceOperateBean.getScheducarno());
//                    comWaybillTraceService.insert(tmp);
                        tmps.add(sontmp);
                    }
                }
            }
        }
        if (tmps.size() > 0) {
            //使用disruptor框架做生产消费者模式
            DisruptorHelper.getInstance().producer(tmps);
        }
    }

    /**
     *通过四通一达单号查询busibookno
     * @param no4T1D
     * @return
     * @throws MobileStationBizException
     */
    public String getBusiNoFrom4T1Dno(String no4T1D) throws MobileStationBizException {
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByExpressOrderNo(no4T1D);
        if (bookingForm == null)
            throw new MobileStationBizException("此单未导入系统");
        return bookingForm.getBusiBookNo();
    }

    /**
     * 入参对象拼装
     * @param batchStockInReq
     * @param batchStockInReqBean
     * @return
     */
    public static StockInReq createStockInReq(BatchStockInReq batchStockInReq, BatchStockInReqBean batchStockInReqBean){
        StockInReq stockInReq = new StockInReq();
        stockInReq.setScanBusiBookNo(batchStockInReqBean.getBusiBookNo());
        stockInReq.setBusiNoTag(batchStockInReqBean.getBusiNoTag());
        stockInReq.setAppLoginInfo(batchStockInReq.getAppLoginInfo());
        stockInReq.setAcctUsername(batchStockInReq.getAcctUsername());
        stockInReq.setAccountId(batchStockInReq.getAccountId());
        stockInReq.setRoleId(batchStockInReq.getRoleId());
        return stockInReq;
    }

    /**
     * 入参对象拼装
     * @param batchMobileStatusOperateReq
     * @param batchMobileStatusOperateReqBean
     * @param queryOrderDetailBean
     * @return
     */
    public static MobileStatusOperateReq createMobileStatusOperateReq(BatchMobileStatusOperateReq batchMobileStatusOperateReq,
                                                          BatchMobileStatusOperateReqBean batchMobileStatusOperateReqBean, QueryOrderDetailBean queryOrderDetailBean){
        MobileStatusOperateReq mobileStatusOperateReq = new MobileStatusOperateReq();
        mobileStatusOperateReq.setBusiBookNo(batchMobileStatusOperateReqBean.getBusiBookNo());
        mobileStatusOperateReq.setOrderFrom(queryOrderDetailBean.getOrderFrom());
        mobileStatusOperateReq.setBusiNoTag(batchMobileStatusOperateReqBean.getBusiNoTag());
        mobileStatusOperateReq.setScheducarno(queryOrderDetailBean.getScheducarno());
        mobileStatusOperateReq.setAppLoginInfo(batchMobileStatusOperateReq.getAppLoginInfo());
        mobileStatusOperateReq.setAcctUsername(batchMobileStatusOperateReq.getAcctUsername());
        mobileStatusOperateReq.setAccountId(batchMobileStatusOperateReq.getAccountId());
        mobileStatusOperateReq.setRoleId(batchMobileStatusOperateReq.getRoleId());
        mobileStatusOperateReq.setOrderId(queryOrderDetailBean.getOrderId());
        return mobileStatusOperateReq;
    }
}
