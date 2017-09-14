package com.gistandard.transport.system.center.pay.service.impl;

import com.gistandard.transport.base.entity.bean.BookingForm;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import com.gistandard.transport.base.entity.bean.ComUserinfo;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.ComCustomerDao;
import com.gistandard.transport.base.entity.dao.ComUserinfoDao;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.system.center.pay.bean.SafePayInfoRes;
import com.gistandard.transport.system.center.pay.service.SenderCenterPayService;
import com.gistandard.transport.system.webservice.client.payinfo.QueryCalcManagerWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by m on 2016/10/8.
 */
@Service
public class SenderCenterPayServiceImpl implements SenderCenterPayService {
    @Autowired
    private QueryCalcManagerWebService queryCalcManagerWebService;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private ComUserinfoDao comUserinfoDao;
    @Autowired
    private ComCustomerDao comCustomerDao;

    private String url;

    @Value("#{spring[\"pickupUrl.transportOrders\"]}")
    private String pickupUrl_transportOrders;

    @Value("#{spring[\"pickupUrl.expressOrders\"]}")
    private String pickupUrl_expressOrders;

    @Value("#{spring[\"receiveUrl.bookingForm\"]}")
    private String receiveUrl_bookingForm;


    @Override
    public SafePayInfoRes safePayInfo(String busiBookNo) {
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(busiBookNo);
        ComUserinfo comUserinfo = null;
        ComCustomer comCustomer = null;
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateUserId());
        if (comAccount != null) {
            comUserinfo = comUserinfoDao.queryByAcctId(comAccount.getId());
            if (null == comUserinfo) {
                comCustomer = comCustomerDao.queryCustomerInfoByAcctId(comAccount.getId());
            }
        }
        SafePayInfoRes safePayInfoRes = new SafePayInfoRes();
        safePayInfoRes.setApplyName(null != comUserinfo ? comUserinfo.getRealName() : comCustomer.getCustName());
        safePayInfoRes.setApplyCartType(null != comUserinfo ? "1170010001" : "2170010001");
        safePayInfoRes.setApplyCarCode(null != comUserinfo ? comUserinfo.getIdentno() : comCustomer.getOrgCodeLilcenseNo());
        safePayInfoRes.setInsurantName(bookingForm.getShipCustlinkMan());
        safePayInfoRes.setInsurantTel(bookingForm.getShipCustlinkTel());
        safePayInfoRes.setGoodsValue(bookingForm.getGoodsValue());
        safePayInfoRes.setPremiumValue(bookingForm.getPremiumValue());
        safePayInfoRes.setBusiBookNo(bookingForm.getBusiBookNo());
        return safePayInfoRes;
    }
}
