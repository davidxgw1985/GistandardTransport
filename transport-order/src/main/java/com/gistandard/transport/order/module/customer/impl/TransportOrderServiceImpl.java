package com.gistandard.transport.order.module.customer.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gistandard.dubbo.CouponDubboService;
import com.gistandard.dubbo.bean.DataResultBean;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.genno.GenerateOrderNumberService;
import com.gistandard.transport.app.dubbo.wechat.bean.AddressInfo;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.gps.service.GpsService;
import com.gistandard.transport.order.module.customer.TransportOrderService;
import com.gistandard.transport.order.module.customer.bean.*;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileStationOrderService;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.RecordMobileStationOrderRequest;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.RevUserBean;
import com.gistandard.transport.order.webservice.server.mobilestation.constant.RecordOrderType;
import com.gistandard.transport.system.common.address.dao.CustomerMobileAddressInfoDao;
import com.gistandard.transport.system.common.bean.AddressQueryRes;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.tools.util.HeadAuthentication;
import com.gistandard.transport.tools.util.HttpClientUtil;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 同城运输(用户)业务接口实现类
 * @author 员伟
 */
@Service
public class TransportOrderServiceImpl implements TransportOrderService {


    private static final Logger logger = LoggerFactory.getLogger(TransportOrderServiceImpl.class);

    @Autowired
    private MobileAddressInfoDaoEx mobileAddressInfoDaoEx;

    @Autowired
    private CustomerMobileAddressInfoDao customerMobileAddressInfoDao;

    @Autowired
    private MobileAddressInfoDao mobileAddressInfoDao;

    @Autowired
    private BookingFormDao bookingFormDao;

    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Autowired
    private BillingFormSalmDao billingFormSalmDao;

    @Autowired
    private ComWaybillTraceDao comWaybillTraceDao;

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

    @Autowired
    private ComProvinceService comProvinceService;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private ComCountyService comCountyService;

    @Autowired
    private MobileStationOrderService mobileStationOrderService;

    @Autowired
    private ComGoodsTypeDaoEx comGoodsTypeDaoEx;

    @Autowired
    private ComUnitService comUnitService;

    @Autowired
    private ComAccountService comAccountService;

    @Autowired
    private GenerateOrderNumberService generateOrderNumberService;

    @Autowired
    private GpsService gpsService;

    @Autowired
    private MobileValueAddService mobileValueAddService;

    @Autowired
    private ComVehicleTypeService comVehicleTypeService;

    @Autowired
    private MobileMyOrderService mobileMyOrderService;

    @Autowired
    private MobileFleetBiddingDao mobileFleetBiddingDao;

    @Autowired
    private MobileFleetBiddingDaoEx mobileFleetBiddingDaoEx;

    @Autowired
    private MobileValueAddDaoEx mobileValueAddDaoEx;

    @Autowired
    private BillingFormSalmDaoEx billingFormSalmDaoEx;

    @Autowired
    private MobileGoodsDtlDaoEx mobileGoodsDtlDaoEx;

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;

    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;

    @Autowired
    private CouponDubboService couponDubboService;

    @Autowired
    private GpsLogService gpsLogService;

    @Value("#{spring.user}")
    private String user;

    @Value("#{spring.passwd}")
    private String passwd;

    @Value("#{customerOrderIM.imContentSystemUrl}")
    private String imContentSystemUrl;

    @Value("#{customerOrderIM.imPushUrl}")
    private String imPushUrl;

    @Value("#{customerOrderIM.customerOrderPredictValue}")
    private String customerOrderPredictValue;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public PlaceAnOrderRes placeAnOrder(PlaceAnOrderReq req) throws MobileStationBizException {

        checkPlaceOrderParamValid(req);//校验参数
        //TODO 同城运输用户可能是企业用户下单
        if (req.getAppLoginInfo() != null && req.getAppLoginInfo().getCurrentComCustomer() != null) {
            if (req.getAppLoginInfo().getCurrentComCustomer().getAccountId() != null) {
                req.setStaffAccountId(req.getAppLoginInfo().getCurrentComCustomer().getAccountId());
            }
        }
        PlaceAnOrderRes placeAnOrderRes = new PlaceAnOrderRes();
        ComAccount comAccount = req.getAppLoginInfo().getComAccount();
        final BookingForm bookingForm = getBookingForm(req, comAccount);
        //订单写入数据库
        bookingFormDao.insertSelective(bookingForm);
        //增值服务
        insertMobileValueAddInfo(req, bookingForm);
        //保存货物信息
        saveGoodsInfo(req.getGoodsInfos(), bookingForm);
        //TODO 同城运输指派单,以后需求中添加
        //doSubOrder(bookingForm);
        //运单跟踪日志
        ComWaybillTrace tmp = new ComWaybillTrace();
        tmp.setAcctUsername(req.getAcctUsername());
        if (comAccount != null && comAccount.getRealName() != null) {
            tmp.setRealName(comAccount.getRealName());
        } else {
            tmp.setRealName(bookingForm.getShipCustlinkMan());
        }
        tmp.setBusiBookNo(bookingForm.getBusiBookNo());
        tmp.setGrade(0);
        tmp.setRemark(BusinessDefine.ORDER);
        tmp.setExecCode(WayBillStatusDefine.ORDER.getIntValue());
        tmp.setStaDate(new Date());
        tmp.setRoleId(1);
        comWaybillTraceDao.insert(tmp);
        //设置返回结果的订单号
        placeAnOrderRes.setBusiBookNo(bookingForm.getBusiBookNo());
        //设置返回结果中订单信息是否报价
        placeAnOrderRes.setInsured(req.getInsured());
        //设置返回结果的订单id
        placeAnOrderRes.setOrderId(bookingForm.getId());
        placeAnOrderRes.setProductType(bookingForm.getTransportType());
        //TODO 直接下单到订单池，参与竞价
        logger.debug("同城运输下单返回结果-placeAnOrderRes={}", JSONObject.toJSONString(placeAnOrderRes));
        return placeAnOrderRes;
    }


    private void insertMobileValueAddInfo(PlaceAnOrderReq req, BookingForm bookingForm) {
        List<MobileValueAdd> mobileValueAddList = req.getMobileValueAddList();
        if (CollectionUtils.isNotEmpty(mobileValueAddList)) {
            List<MobileValueAddRel> mobileValueAddRelList = new ArrayList<>();
            Map<Integer, MobileValueAdd> mvaCacheMap = mobileValueAddService.queryForMap();
            logger.debug("同城运输下单用户选择增值服务为-{}", JSONObject.toJSONString(mobileValueAddList));
            for (MobileValueAdd mobileValueTemp : req.getMobileValueAddList()) {
                //TODO 增值服务创建
                MobileValueAdd mva = mvaCacheMap.get(mobileValueTemp.getId());
                if (mva != null) {
                    //TODO 增值服务关联类创建
                    MobileValueAddRel mobileValueAddRel = new MobileValueAddRel();
                    mobileValueAddRel.setCreateDate(bookingForm.getBookingDate());
                    mobileValueAddRel.setCreateUser(bookingForm.getCreateUser());
                    mobileValueAddRel.setCreateUserId(bookingForm.getCreateUserId());
                    mobileValueAddRel.setBookingFormId(bookingForm.getId());
                    mobileValueAddRel.setValueAddId(mobileValueTemp.getId());
                    mobileValueAddRelList.add(mobileValueAddRel);
                }
            }
            if (CollectionUtils.isNotEmpty(mobileValueAddRelList)) {
                //mobileValueAddDao.batchInsert(mobileValueAddList);
                mobileValueAddDaoEx.batchInsert(mobileValueAddRelList);
            }
            logger.debug("同城运输下单用户选择增值服务插入数据库结束---end");
        }
    }


    private BookingForm getBookingForm(PlaceAnOrderReq req, ComAccount comAccount) throws MobileStationBizException {
        logger.debug("同城运输下单-创建订单表信息--start");
        BookingForm bookingForm = new BookingForm();
        //保存发件人地址信息
        saveSenderAddress(req, bookingForm);
        //保存收件人地址信息
        saveReceiverAddress(req, bookingForm);
        //TODO 第一步 注入订单参数信息到Bean
        injectParam2BookFormFirst(req, comAccount, bookingForm);
        //TODO 第二步 注入订单参数信息到Bean
        injectParam2BookFormSecond(req, bookingForm);
        //TODO 第三步 注入订单参数信息到Bean
        //设置报价单类型参数
        injectParam2BookFormThird(req, bookingForm);
        //TODO 第四步 注入订单参数信息到Bean
        injectParam2BookFormFourth(req, bookingForm);
        //TODO 最后一步 注入订单参数信息到Bean
        injectParam2BookFormLast(req, bookingForm);
        logger.debug("同城运输下单-创建订单表信息-end,bookingForm-{}", JSON.toJSONString(bookingForm));
        return bookingForm;
    }


    /**
     * 给订单bean属性设值操作第五步(下面的p标签中是注入的部分参数)
     * <p/>
     * 发件人关注标识、收件人关注标识、运输路线
     * <p/>
     * @param req req
     * @param bookingForm bookingForm
     * @throws MobileStationBizException MobileStationBizException
     */
    private void injectParam2BookFormLast(PlaceAnOrderReq req, BookingForm bookingForm) {
        //TODO 发件人关注  senderFollow;//发件人关注 0未关注 , 1已关注
        bookingForm.setSenderFollow(CustomerDefine.UNFOLLOW);
        //TODO receiverFollow;//收件人关注 0未关注 , 1已关注 2,等待关注确认
        bookingForm.setReceiverFollow(CustomerDefine.UNFOLLOW);
        //TODO receiverFollow;//是否关注收件时间
        bookingForm.setFocusAccessTime(req.getFocusAccessTime());
        //TODO receiverFollow;//是否关注取件时间
        bookingForm.setFocusDeliveryTime(req.getFocusDeliveryTime());
        //设置 同城运输订单运输路线 例如江苏省 南京市- 江苏省南京市
        String tempRouteBookNa;
        if (StringUtil.isEmpty(bookingForm.getCarriageReceiCounty()) || "0".equals(bookingForm.getCarriageReceiCounty())) {
            tempRouteBookNa = getCity(bookingForm.getCarriageReceiCity());
        } else {
            tempRouteBookNa = getCity(bookingForm.getCarriageReceiCounty());
        }
        tempRouteBookNa = tempRouteBookNa + "-";
        if (StringUtil.isEmpty(bookingForm.getCarriageDelivCounty()) || "0".equals(bookingForm.getCarriageDelivCounty())) {
            tempRouteBookNa = tempRouteBookNa + getCity(bookingForm.getCarriageDelivCity());
        } else {
            tempRouteBookNa = tempRouteBookNa + getCity(bookingForm.getCarriageDelivCounty());
        }
        bookingForm.setRouteBookNa(tempRouteBookNa);
        //设置接单商户名称,同城运输默认无，采用竞价模式
        if (!StringUtil.isEmpty(bookingForm.getTeamComsixName())) {
            bookingForm.setStartLocusNa(bookingForm.getTeamComsixName());
        }
        //设置接单商户代码,同城运输默认无, 采用竞价模式
        if (!StringUtil.isEmpty(bookingForm.getTeamComsixNo())) {
            bookingForm.setStartLocus(bookingForm.getTeamComsixNo());
        }
        //TODO 企业账户ID设置,只有企业用户设置
        if (req.getStaffAccountId() != null && req.getStaffAccountId() > 0) {
            bookingForm.setCreateCompanyId(req.getStaffAccountId());
            ComAccount companyAccount = comAccountDao.selectByPrimaryKey(req.getStaffAccountId());
            if (companyAccount != null) {
                bookingForm.setPayUser(companyAccount.getAcctUsername());
                bookingForm.setPayUserRealName(companyAccount.getRealName());
                bookingForm.setPayUserTelephone(companyAccount.getTelephone());
            }
        }
        //嗨付券参数
        DataResultBean dataResultBean = couponDubboService.checkHaveCoupons(req.getAcctUsername());
        logger.info("Hi voucher interface call dataResultBean-{}", JSON.toJSONString(dataResultBean));
        if (dataResultBean == null) {
            logger.error("Hi voucher interface call failed res is null");
            bookingForm.setCouponFlag(0);
        } else {
            if (dataResultBean.getSucceed()) {
                logger.info("Hi voucher interface call successful");
                if (Boolean.TRUE.equals(dataResultBean.getRefObject())) {
                    logger.info("Hi voucher interface response result is true");
                    bookingForm.setCouponFlag(1);
                }
                if (Boolean.FALSE.equals(dataResultBean.getRefObject())) {
                    logger.info("Hi voucher interface response result is false");
                    bookingForm.setCouponFlag(0);
                }
            } else {
                logger.error("Hi voucher interface call failed");
                bookingForm.setCouponFlag(0);
            }
        }

    }

    /**
     * 给订单bean属性设值操作第四步(下面的p标签中是注入的部分参数)
     * <p/>
     * 运输类型(产品代码)、长宽高、公里数、总包装件数
     * 用户自报价、用户自报价币制、货物信息、
     * <p/>
     * @param req req
     * @param bookingForm bookingForm
     * @throws MobileStationBizException MobileStationBizException
     */
    private void injectParam2BookFormFourth(PlaceAnOrderReq req, BookingForm bookingForm) {
        //设置订单的运输类型(来自产品代码)此处是同城运输  OTCYS
        bookingForm.setTransportType(req.getItemCode());
        //TODO 这几个参数未知
        bookingForm.setWhtGrosswht(new BigDecimal(0));
        bookingForm.setWhtVolcbm(new BigDecimal(0));
        bookingForm.setWhtVolwht(new BigDecimal(0));
        //设置订单公里数
        bookingForm.setMileage(req.getMileage());
        //TODO 货物数量
        bookingForm.setGoodsQty(new BigDecimal(0));
        //TODO 设置总包装件数
        bookingForm.setGoodsPackageQty(req.getGoodPackageQty());
        //设置同城运输下单用户自报价
        bookingForm.setSelfQuoteValue(req.getSelfQuoteValue());
        //设置同城运输下单用户自报价币制
        bookingForm.setSelfQuoteCurr(req.getSelfQuoteCurr());
        List<GoodsInfo> goodsInfoList = req.getGoodsInfos();
        if (CollectionUtils.isNotEmpty(goodsInfoList)) {
            //保存货物信息
            for (GoodsInfo goodsInfo : goodsInfoList) {
                if (goodsInfo.getWeight() != null) {
                    //毛重
                    bookingForm.setWhtGrosswht(bookingForm.getWhtGrosswht().add(BigDecimal.valueOf(goodsInfo.getWeight())));
                    bookingForm.setWhtFeewht(bookingForm.getWhtGrosswht());
                }
                if (null != goodsInfo.getHeight() && null != goodsInfo.getLength() && null != goodsInfo.getWidth()) {
                    //体积
                    bookingForm.setWhtVolcbm(bookingForm.getWhtVolcbm().add(BigDecimal.valueOf(goodsInfo.getHeight() * goodsInfo.getLength() * goodsInfo.getWidth()).setScale(4, BigDecimal.ROUND_HALF_UP)));
                    BigDecimal goodsFeewht = new BigDecimal(goodsInfo.getLength() * goodsInfo.getWidth() * goodsInfo.getHeight())
                            .divide(new BigDecimal(6000), 8, BigDecimal.ROUND_HALF_EVEN);
                    bookingForm.setWhtVolwht(bookingForm.getWhtVolwht().add(goodsFeewht));
                }
                //TODO 获取详细数量
                if (null != goodsInfo.getQty()) {
                    bookingForm.setGoodsQty(bookingForm.getGoodsQty().add(goodsInfo.getQty()));
                }
                if (bookingForm.getWhtFeewht() != null && bookingForm.getWhtVolwht() != null) {
                    if (bookingForm.getWhtVolwht().compareTo(bookingForm.getWhtFeewht()) > 0) {
                        bookingForm.setWhtFeewht(bookingForm.getWhtVolwht());
                    }
                }
            }
        }


    }


    /**
     * 给订单bean属性设值操作第三步(下面的p标签中是注入的部分参数)
     * <p/>
     * 报价单类型、提货时间、订单状态、零担设置、订单编号
     * 支付订单编号、预估费用(目前无)、代收贷款(目前无)
     * 代收贷款(目前无)、结算状态、可删除标识、短信通知标识、币制
     * <p/>
     * @param req req
     * @param bookingForm bookingForm
     * @throws MobileStationBizException MobileStationBizException
     */
    private void injectParam2BookFormThird(PlaceAnOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        //报价单类型 10 同城运输
        if (null != req.getQuotedType()) {
            bookingForm.setQuotedType(req.getQuotedType());
        }
        //取货方式：1-客户自送，2-上门接货(同城运输此处为上门接货)
        bookingForm.setCarriAgerecei(req.getAccessMethod() + "");
        //设置提货时间
        if (StringUtils.isNotEmpty(req.getAccessTime())) {
            bookingForm.setAccesstime(req.getAccessTime());
        }
        //设置订单状态,同城运输下单,初始化是待报价
        bookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TO_QUOTE);
        //TODO 整车：1,   零担：0,   自拼车：2
        bookingForm.setCargoLoader(req.getTransportType().toString());
        bookingForm.setVehicleTypeId(req.getVehicleTypeId());
        //利用先前保存的地址信息中收货地址的城市信息生成订单编号
        String busiNo = generateOrderNumberService.getOrderNumberByCityId(NumberUtils.toInt(bookingForm.getCarriageReceiCity()));
        if (StringUtil.isEmpty(busiNo)) {
            throw new MobileStationBizException("获取订单号异常!");
        }
        //设置业务订单编号
        bookingForm.setBusiBookNo(busiNo);
        //设置支付订单编号
        bookingForm.setWaybillNo(busiNo);
        //TODO 同城运输没有预估费用 只有用户的自报价和车队的竞价
        if (null != req.getPredictValue()) {
            bookingForm.setPredictValue(req.getPredictValue());
        }
        //TODO 代收贷款 目前不用
        if (null != req.getGoodsPayment()) {
            bookingForm.setGoodsPayment(req.getGoodsPayment());
        }
        //TODO 代收贷款 币制 目前不用
        if (null != req.getGoodsPaymentCurr()) {
            bookingForm.setGoodsPaymentCurr(req.getGoodsPaymentCurr());
        }
        //设置订单的结算状态 0待结算 1已结算（可以发起支付）2已对账 3已支付 4支付失败
        bookingForm.setIsJs(0);
        //设置订单是否可删除 0不可删 1可删  初始为0
        bookingForm.setIsDel(0);
        //TODO 同城快递设置下单是否短信通知  默认不通知
        if (null != req.getSmsNoty()) {
            bookingForm.setSmsNoty(req.getSmsNoty());
        }
        //TODO 币制是否校验 142 人民币
        if (StringUtils.isNotBlank(req.getPredictCurr())) {
            bookingForm.setPredictCurr(req.getPredictCurr());
        }
    }

    /**
     * 指派单相关操作,暂时不做
     * @param req req
     * @param bookingForm bookingForm
     */
    private void injectRevUser2BookForm(PlaceAnOrderReq req, BookingForm bookingForm) {

        if (null != req.getCourierId()) {
            bookingForm.setRevUserId(req.getCourierId());
            if (null != req.getCourierAcctUsername()) {
                bookingForm.setRevUser(req.getCourierAcctUsername());
            }
            if (null != req.getCourierAcctUserRealName()) {
                bookingForm.setRevUserName(req.getCourierAcctUserRealName());
            } else {
                if (null != req.getCourierAcctUsername()) {
                    bookingForm.setRevUser(req.getCourierAcctUsername());
                    ComAccount account = comAccountService.queryAccountByAcctUsername(req.getCourierAcctUsername());
                    if (null != account) {
                        if (StringUtils.isNotBlank(account.getRealName())) {
                            bookingForm.setRevUserName(account.getRealName());
                        }
                    }
                }
            }
        }
    }

    /**
     * 给订单bean属性设值操作第二步(下面的p标签中是注入的部分参数)
     * <p/>
     * 货值、保险费用、付款人、付款人真实姓名、付款人电话号码
     * <p/>
     * @param req req
     * @param bookingForm bookingForm
     * @throws MobileStationBizException MobileStationBizException
     */
    private void injectParam2BookFormSecond(PlaceAnOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        if (null != req.getGoodsValue()) {
            bookingForm.setGoodsValue(BigDecimal.valueOf(req.getGoodsValue()));
        }
        //保险费用,目前不做
        if (null != req.getPremiumValue()) {
            bookingForm.setPremiumValue(req.getPremiumValue());
        }
        //TODO 支付方式
        bookingForm.setPayType(req.getPaymentType());//1-到付 2-在线支付 3-现金支付
        if (req.getPaymentType() == MobileStationDefine.PAYTYPE_COLLECT) {
            if (req.getReceiverAcctUsername() == null) {
                throw new MobileStationBizException("收件人不是平台用户");
            }
            //到付设置付款人为收件人的平台帐号o2id
            bookingForm.setPayUser(req.getReceiverAcctUsername());
            if (StringUtils.isNotBlank(req.getReceiverAcctUsername())) {
                ComAccount receiveAccount = comAccountDaoEx.queryByAccount(req.getReceiverAcctUsername());
                if (receiveAccount != null) {
                    bookingForm.setPayUserRealName(receiveAccount.getRealName());
                    bookingForm.setPayUserTelephone(receiveAccount.getTelephone());
                }
            }
        } else if (req.getPaymentType() == MobileStationDefine.PAYTYPE_GENERALACCT) {
            //平台支付，付款人设置为下单人
            if (StringUtil.isEmpty(req.getCompanyAccountId())) {
                bookingForm.setPayUser(req.getAcctUsername());
                bookingForm.setPayUserRealName(req.getAppLoginInfo().getComAccount().getRealName());
                bookingForm.setPayUserTelephone(req.getAppLoginInfo().getComAccount().getTelephone());
            } else {
                ComAccount companyAccount = comAccountDao.selectByPrimaryKey(req.getCompanyAccountId());
                bookingForm.setPayUser(companyAccount.getAcctUsername());
                bookingForm.setPayUserRealName(companyAccount.getRealName());
                bookingForm.setPayUserTelephone(companyAccount.getTelephone());
            }
        } else if (req.getPaymentType() == MobileStationDefine.PAYTYPE_CASH) {
            //现金支付，在快递员收件时设置为快递员
            bookingForm.setPayUser(req.getAcctUsername());
            bookingForm.setPayUserRealName(req.getAppLoginInfo().getComAccount().getRealName());
            bookingForm.setPayUserTelephone(req.getAppLoginInfo().getComAccount().getTelephone());
        }
        injectRevUser2BookForm(req, bookingForm);
    }

    /**
     * 给订单bean属性设值操作第一步(下面的p标签中是注入的部分参数)
     * <p/>
     * 订单状态、收件人账号、下单人ID
     * 下单人账号、操作要求、订单来源、送货上门标识
     * 下单时间、订单类型、增值服务标识、总包装件数
     * <p/>
     * @param req req
     * @param comAccount comAccount
     * @param bookingForm bookingForm
     * @throws MobileStationBizException MobileStationBizException
     */
    private void injectParam2BookFormFirst(PlaceAnOrderReq req, ComAccount comAccount, BookingForm bookingForm) throws MobileStationBizException {

        //订单状态(取决于角色Id)
        if (req.getRoleId() != null) {
            bookingForm.setBookingCtrl(req.getRoleId());
        }
        //收件人账号
        if (null != req.getReceiverAcctUsername()) {
            bookingForm.setReceiverUser(req.getReceiverAcctUsername());
        }
        if (null != comAccount) {
            bookingForm.setCreateUserId(comAccount.getId());
            req.setAccountId(comAccount.getId());
        }
        //同城运输下单人用户账号
        if (null != req.getAcctUsername()) {
            bookingForm.setCreateUser(req.getAcctUsername());
        }
        //操作要求
        if (null != req.getNarrate()) {
            bookingForm.setNarrate(req.getNarrate());
        }
        //订单来源
        if (null != req.getOrderForm()) {
            bookingForm.setOrderForm(req.getOrderForm());
        } else {
            bookingForm.setOrderForm(CustomerDefine.IM_ORDERFORM_APP);
        }
        //送货上门
        bookingForm.setCarriageDeiv("2");
        //下单时间
        bookingForm.setBookingDate(new Date());//申请订单日期
        // 1运输  2快递
        bookingForm.setOrderType(req.getTransType());
        //是否需要价格和增值服务
        bookingForm.setNeedInsure(req.getInsured());
        //总包装件数
        bookingForm.setGoodsPackageQty(req.getGoodPackageQty());
    }


    /**
     * 校验同城运输新增订单参数
     * @param req 请求的参数
     * @throws MobileStationBizException 手机业务异常
     */
    private void checkPlaceOrderParamValid(PlaceAnOrderReq req) throws MobileStationBizException {

        //用户信息校验
        if (StringUtils.isEmpty(req.getAcctUsername())) {
            throw new MobileStationBizException("操作用户不存在");
        }
        ComAccount comAccount = comAccountService.queryAccountByAcctUsername(req.getAcctUsername());
        if (comAccount == null) {
            throw new MobileStationBizException("操作用户不存在");
        }
        //自报价校验
        if (req.getSelfQuoteValue() != null && (req.getSelfQuoteValue().compareTo(new BigDecimal(0)) < 0)) {
            throw new MobileStationBizException("自报价不得为空或者小于等于零");
        }
        //运输方式校验  0物流、1运输、2快递 此时为同城运输 必须为1
        Integer transType = req.getTransType();
        if (transType == null || (transType != 1)) {
            throw new MobileStationBizException("运输方式入参错误");
        }

        if (StringUtil.isEmpty(req.getItemCode())) {
            throw new MobileStationBizException("同城运输产品代码入参为空");
        }
        if (!MobileStationDefine.PRODUCT_TYPE_TCYS.equals(req.getItemCode())) {
            throw new MobileStationBizException("同城运输产品代码入参错误");
        }
        checkAddress(req);
        Integer payType = req.getPaymentType();
        //TODO 支付方式 只有线上支付和线下支付  没有到付(1到付2平台支付3现金支付)
        if (payType == null) {
            throw new MobileStationBizException("客户下单付款方式入参错误");
        }
        if (req.getPaymentType() != 1 && req.getPaymentType() != 2 && req.getPaymentType() != 3) {
            throw new MobileStationBizException("客户下单付款方式入参错误");
        }
        //1-客户自送，2-上门接货
        Integer accessMethod = req.getAccessMethod();
        if (accessMethod == null || (accessMethod != 1 && accessMethod != 2)) {
            throw new MobileStationBizException("客户下单取件方式入参错误");
        }
        //TODO 增值服务的校验
        List<MobileValueAdd> mobileValueAddList = req.getMobileValueAddList();
        if (CollectionUtils.isNotEmpty(mobileValueAddList)) {
            Map<Integer, MobileValueAdd> mvaMap = mobileValueAddService.queryForMap();
            for (MobileValueAdd mobileValueAdd : mobileValueAddList) {
                MobileValueAdd mvaTemp = mvaMap.get(mobileValueAdd.getId());
                if (mvaTemp == null) {
                    throw new MobileStationBizException("客户下单增值服务编号入参错误");
                }
                if (!mvaTemp.getValueAddCode().equals(mobileValueAdd.getValueAddCode())) {
                    throw new MobileStationBizException("客户下单增值服务编码入参错误");
                }
                if (!mvaTemp.getValueAddName().equals(mobileValueAdd.getValueAddName())) {
                    throw new MobileStationBizException("客户下单增值服务名称入参错误");
                }
            }
        }
        //TODO 装件数校验
        /*if (null == req.getGoodPackageQty() || req.getGoodPackageQty().compareTo(new BigDecimal(0)) <= 0) {
            throw new MobileStationBizException("装件数入参错误");
        }*/
        //TODO 收件人发件人地址信息校验
        if (null == req.getReceiverAddr() && null == req.getReceiverAddrId()) {
            throw new MobileStationBizException("收件人信息入参错误");
        }
        if (null == req.getSenderAddr() && null == req.getSenderAddrId()) {
            throw new MobileStationBizException("发件人信息入参错误");
        }
        //同城运输运输方式(整车,零担)
        if (req.getTransportType() == null || (req.getTransportType() != 1 && req.getTransportType() != 0)) {
            throw new MobileStationBizException("选择车型入参错误");
        }
        Map<Integer, ComVehicleType> map = comVehicleTypeService.queryForMap();
        if (req.getVehicleTypeId() != null) {
            if (!map.keySet().contains(req.getTransportType())) {
                throw new MobileStationBizException("运输方式选车入参错误");
            }
        }

    }

    /**
     * 同城运输下单地址校验
     * @param req 同城运输下单请求参数
     * @throws MobileStationBizException 手机业务异常
     */
    private void checkAddress(PlaceAnOrderReq req) throws MobileStationBizException {
        Integer senderAddrId = req.getSenderAddrId();
        AddressInfo senderAddr = req.getSenderAddr();
        if (senderAddrId == null && senderAddr == null) {
            throw new MobileStationBizException("发件人地址入参错误");
        }
        if (senderAddrId == null && senderAddr != null) {
            if (StringUtils.isEmpty(senderAddr.getName())) {
                throw new MobileStationBizException("发件人姓名入参错误");
            }
            if (StringUtils.isEmpty(senderAddr.getTelephone())) {
                throw new MobileStationBizException("发件人手机号码入参错误");
            }
            setSelctCityId(senderAddr.getAreaIdSel() + "", req.getSenderAddr());
            if (StringUtils.isEmpty(req.getSenderAddr().getProvince()) || StringUtils.isEmpty(req.getSenderAddr().getCity())) {
                throw new MobileStationBizException("发件人地址入参错误");
            }
        }
        Integer receiverAddrId = req.getReceiverAddrId();
        AddressInfo receiverAddr = req.getReceiverAddr();
        if (receiverAddrId == null && receiverAddr == null) {
            throw new MobileStationBizException("收件人地址入参错误");
        }
        if (receiverAddrId == null && receiverAddr != null) {
            if (StringUtils.isEmpty(receiverAddr.getName())) {
                throw new MobileStationBizException("收件人姓名入参错误");
            }
            if (StringUtils.isEmpty(receiverAddr.getTelephone())) {
                throw new MobileStationBizException("收件人手机号码入参错误");
            }
            setSelctCityId(receiverAddr.getAreaIdSel() + "", req.getReceiverAddr());
            if (StringUtils.isEmpty(req.getReceiverAddr().getProvince()) || StringUtils.isEmpty(req.getReceiverAddr().getCity())) {
                throw new MobileStationBizException("收件人地址入参错误");
            }
        }
    }


    private void setSelctCityId(String cityId, AddressInfo addressInfo) {
        if (null != cityId) {

            ComCounty tCounty = comCountyService.queryForMap().get(cityId);
            if (null != tCounty) {
                addressInfo.setCounty(tCounty.getId().toString());
                addressInfo.setCity(tCounty.getCityId().toString());
                ComCity tCtiy = comCityService.queryForMap().get(tCounty.getCityId().toString());
                if (null != tCtiy) {
                    addressInfo.setProvince(StringUtil.getObjStr(tCtiy.getProvinceId()));
                }
            } else {
                ComCity tCtiy = comCityService.queryForMap().get(cityId);

                if (null != tCtiy) {
                    addressInfo.setCity(tCtiy.getId().toString());
                    addressInfo.setProvince(StringUtil.getObjStr(tCtiy.getProvinceId()));
                } else {
                    ComProvince comProvince = comProvinceService.queryForMap().get(cityId);
                    if (null != comProvince) {
                        addressInfo.setProvince(comProvince.getId().toString());
                    }
                }
            }

        }
    }


    /**
     * 保存获取信息
     * @param goodsInfos goodsInfos
     * @param bookingForm bookingForm
     */
    private void saveGoodsInfo(List<GoodsInfo> goodsInfos, BookingForm bookingForm) {
        for (GoodsInfo goodsInfoReq : goodsInfos) {
            BillingFormSalm billingFormSalm = new BillingFormSalm();
            billingFormSalm.setBusiBooknoId(bookingForm.getId());
            if (goodsInfoReq.getGoodsName() != null) {
                billingFormSalm.setHscodeNachs(goodsInfoReq.getGoodsName());
            }
            if (goodsInfoReq.getGoodsType() != null) {
                billingFormSalm.setHscodeSpecs(goodsInfoReq.getGoodsType());
            }
            billingFormSalm.setGoodsQty(goodsInfoReq.getQty());
            billingFormSalm.setGoodsQtyUnitCo(goodsInfoReq.getQtyUnit());
            if (goodsInfoReq.getWeight() != null) {
                billingFormSalm.setGoodsGrosswht(BigDecimal.valueOf(goodsInfoReq.getWeight()));
            }
            if (goodsInfoReq.getWeightUnit() != null) {
                billingFormSalm.setWeightUnitCo(goodsInfoReq.getWeightUnit());
            }
            //体积存储
            if (goodsInfoReq.getLength() != null) {
                billingFormSalm.setGoodsLength(goodsInfoReq.getLength());
            }
            if (goodsInfoReq.getWidth() != null) {
                billingFormSalm.setGoodsWidth(goodsInfoReq.getWidth());
            }
            if (goodsInfoReq.getHeight() != null) {
                billingFormSalm.setGoodsHeight(goodsInfoReq.getHeight());
            }
            logger.debug("同城运输下单保存的货物信息-billingFormSalm--{}", JSON.toJSONString(billingFormSalm));
            billingFormSalmDao.insertSelective(billingFormSalm);
        }
    }


    /**
     * 保存收货地址
     * @param req 请求参数
     * @param bookingForm 同城运输订单信息
     * @throws MobileStationBizException 手机业务异常
     */
    private void saveReceiverAddress(PlaceAnOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        Integer receiverAddrId = req.getReceiverAddrId();
        AddressInfo receiverAddr = req.getReceiverAddr();
        StringBuffer sb = new StringBuffer();
        //如果只传递了是地址id,代表是选择的已有地址
        if (receiverAddrId != null && receiverAddr == null) {
            AddressQueryRes addressQueryRes = customerMobileAddressInfoDao.queryByPrimaryKey(receiverAddrId);
            if (addressQueryRes == null) {
                throw new MobileStationBizException("地址不存在");
            }
            bookingForm.setCneeCustAddr(addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress());
            bookingForm.setCneeCustHouseNumber(addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress());
            bookingForm.setCneeCustLinkMan(addressQueryRes.getName());
            bookingForm.setCneeCustLinkTel(addressQueryRes.getTelephone());
            /*收件人地址*/
            if (StringUtils.isNotBlank(addressQueryRes.getProvinceName())) {
                sb.append(addressQueryRes.getProvinceName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCityName())) {
                sb.append(addressQueryRes.getCityName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCountyName())) {
                sb.append(addressQueryRes.getCountyName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getAddress())) {
                sb.append(addressQueryRes.getAddress());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getDetailAddress())) {
                sb.append(addressQueryRes.getDetailAddress());
            }
            bookingForm.setCarriageDelivAddr(sb.toString());
            //bookingForm.setCarriageDelivAddr((addressQueryRes.getProvinceName() == null ? "" : addressQueryRes.getProvinceName()) + (addressQueryRes.getCityName() == null ? "" : addressQueryRes.getCityName()) + (addressQueryRes.getCountyName() == null ? "" : addressQueryRes.getCountyName()) + (addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress()) + (addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress()));
            bookingForm.setCarriageDelivLongitude(addressQueryRes.getAddressLongitude());
            bookingForm.setCarriageDelivLatitude(addressQueryRes.getAddressLatitude());
            if (!StringUtil.isEmpty(addressQueryRes.getCounty()) && !"0".equals(addressQueryRes.getCounty())) {
                bookingForm.setCarriageDelivCounty(addressQueryRes.getCounty());
            }
            if (addressQueryRes.getCity() != null) {
                bookingForm.setCarriageDelivCity(addressQueryRes.getCity());
            }
            if (addressQueryRes.getProvince() != null) {
                bookingForm.setCarriageDelivProvince(addressQueryRes.getProvince());
            }
            if (addressQueryRes.getCompanyName() != null) {
                bookingForm.setCneeCustName(addressQueryRes.getCompanyName());
            }
            if (addressQueryRes.getZipCode() != null) {
                bookingForm.setReceiverZipCode(addressQueryRes.getZipCode());
            }
        } else {
            bookingForm.setCneeCustName(req.getReceiverAddr().getName());
            bookingForm.setCneeCustHouseNumber(req.getReceiverAddr().getDetailAddress() == null ? "" : req.getReceiverAddr().getDetailAddress());
            bookingForm.setCneeCustAddr(req.getReceiverAddr().getAddress() == null ? "" : req.getReceiverAddr().getAddress());
            bookingForm.setCneeCustLinkMan(req.getReceiverAddr().getName());
            bookingForm.setCneeCustLinkTel(req.getReceiverAddr().getTelephone());
            if (req.getReceiverAddrSaveFlag() != null && req.getReceiverAddrSaveFlag() == CustomerDefine.SAVE_ADDRES) {
                if (receiverAddr.getAccountId() == null) {
                    receiverAddr.setAccountId(req.getAccountId());
                }
                saveAddress(receiverAddr, 1);//1收货地址，2发货地址
            }
            /*收件人地址*/
            if (StringUtils.isNotBlank(req.getReceiverAddr().getProvinceName())) {
                sb.append(req.getReceiverAddr().getProvinceName());
            }
            if (StringUtils.isNotBlank(req.getReceiverAddr().getCityName())) {
                sb.append(req.getReceiverAddr().getCityName());
            }
            if (StringUtils.isNotBlank(req.getReceiverAddr().getCountyName())) {
                sb.append(req.getReceiverAddr().getCountyName());
            }
            if (StringUtils.isNotBlank(req.getReceiverAddr().getAddress())) {
                sb.append(req.getReceiverAddr().getAddress());
            }
            if (StringUtils.isNotBlank(req.getReceiverAddr().getDetailAddress())) {
                sb.append(req.getReceiverAddr().getDetailAddress());
            }
            bookingForm.setCarriageDelivAddr(sb.toString());
            /*县级单位*/
            if (!StringUtil.isEmpty(req.getReceiverAddr().getCounty()) && !"0".equals(req.getReceiverAddr().getCounty())) {
                bookingForm.setCarriageDelivCounty(req.getReceiverAddr().getCounty());
            }
            if (req.getReceiverAddr().getCity() != null) {
                bookingForm.setCarriageDelivCity(req.getReceiverAddr().getCity());
            }
            if (req.getReceiverAddr().getProvince() != null) {
                bookingForm.setCarriageDelivProvince(req.getReceiverAddr().getProvince());
            }
            if (req.getReceiverAddr().getCompanyName() != null) {
                bookingForm.setCneeCustName(req.getReceiverAddr().getCompanyName());
            }
            if (req.getReceiverAddr().getZipCode() != null) {
                bookingForm.setReceiverZipCode(req.getReceiverAddr().getZipCode());
            }
            if (req.getReceiverAddr() != null && (req.getReceiverAddr().getAddressLatitude() == null || req.getReceiverAddr().getAddressLongitude() == null)) {
                //获取只有地址的信息
                String address = bookingForm.getCarriageDelivAddr();
                if (bookingForm.getCarriageDelivAddr() != null && bookingForm.getCneeCustHouseNumber() != null
                        && bookingForm.getCarriageDelivAddr().lastIndexOf(bookingForm.getCneeCustHouseNumber()) > 0) {
                    address = bookingForm.getCarriageDelivAddr().substring(0, bookingForm.getCarriageDelivAddr().lastIndexOf(bookingForm.getCneeCustHouseNumber()));
                }
                GiPoint giPoint = gpsService.getGiPointByNearBy(address, bookingForm.getCneeCustHouseNumber());
                req.getReceiverAddr().setAddressLatitude(BigDecimal.valueOf(giPoint.getLatitude()));
                req.getReceiverAddr().setAddressLongitude(BigDecimal.valueOf(giPoint.getLongitude()));
            }
            bookingForm.setCarriageDelivLongitude(req.getReceiverAddr().getAddressLongitude());
            bookingForm.setCarriageDelivLatitude(req.getReceiverAddr().getAddressLatitude());

        }
        //logger.debug("同城运输下单-保存收件人信息后,bookingForm={}", JSON.toJSONString(bookingForm));
    }

    /**
     * 保存发货地址相关参数的信息
     * @param req 请求参数
     * @param bookingForm 订单bean
     * @throws MobileStationBizException 通用手机业务异常
     */
    private void saveSenderAddress(PlaceAnOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        Integer senderAddrId = req.getSenderAddrId();
        AddressInfo senderAddr = req.getSenderAddr();
        StringBuffer sb = new StringBuffer();
        //如果只传递了发货地址ID,代表是选择的已有地址
        if (senderAddrId != null && senderAddr == null) {
            AddressQueryRes addressQueryRes = customerMobileAddressInfoDao.queryByPrimaryKey(senderAddrId);
            if (addressQueryRes == null) {
                throw new MobileStationBizException("地址不存在");
            }
            bookingForm.setShipCustaDdr(addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress());
            bookingForm.setShipCustHouseNumber(addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress());
            bookingForm.setShipCustlinkMan(addressQueryRes.getName());
            bookingForm.setShipCustlinkTel(addressQueryRes.getTelephone());
            /*发件人地址*/
            if (StringUtils.isNotBlank(addressQueryRes.getProvinceName())) {
                sb.append(addressQueryRes.getProvinceName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCityName())) {
                sb.append(addressQueryRes.getCityName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCountyName())) {
                sb.append(addressQueryRes.getCountyName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getAddress())) {
                sb.append(addressQueryRes.getAddress());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getDetailAddress())) {
                sb.append(addressQueryRes.getDetailAddress());
            }
            bookingForm.setCarriageReceiAddr(sb.toString());
            //bookingForm.setCarriageReceiAddr((addressQueryRes.getProvinceName() == null ? "" : addressQueryRes.getProvinceName()) + (addressQueryRes.getCityName() == null ? "" : addressQueryRes.getCityName()) + (addressQueryRes.getCountyName() == null ? "" : addressQueryRes.getCountyName()) + (addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress()) + (addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress()));
            bookingForm.setCarriageReceiLongitude(addressQueryRes.getAddressLongitude());
            bookingForm.setCarriageReceiLatitude(addressQueryRes.getAddressLatitude());
            if (!StringUtil.isEmpty(addressQueryRes.getCounty()) && !"0".equals(addressQueryRes.getCounty())) {
                bookingForm.setCarriageReceiCounty(addressQueryRes.getCounty());
            }
            if (addressQueryRes.getCity() != null) {
                bookingForm.setCarriageReceiCity(addressQueryRes.getCity());
            }
            if (addressQueryRes.getProvince() != null) {
                bookingForm.setCarriageReceiProvince(addressQueryRes.getProvince());
            }
            if (addressQueryRes.getCompanyName() != null) {
                bookingForm.setShipCustName(addressQueryRes.getCompanyName());
            }
            if (addressQueryRes.getZipCode() != null) {
                bookingForm.setSenderZipCode(addressQueryRes.getZipCode());
            }
        } else {

            bookingForm.setShipCustaDdr(req.getSenderAddr().getAddress() == null ? "" : req.getSenderAddr().getAddress());
            bookingForm.setShipCustHouseNumber(req.getSenderAddr().getDetailAddress() == null ? "" : req.getSenderAddr().getDetailAddress());
            bookingForm.setShipCustlinkMan(req.getSenderAddr().getName());
            bookingForm.setShipCustlinkTel(req.getSenderAddr().getTelephone());
             /*发件人地址*/
            if (StringUtils.isNotBlank(req.getSenderAddr().getProvinceName())) {
                sb.append(req.getSenderAddr().getProvinceName());
            }
            if (StringUtils.isNotBlank(req.getSenderAddr().getCityName())) {
                sb.append(req.getSenderAddr().getCityName());
            }
            if (StringUtils.isNotBlank(req.getSenderAddr().getCountyName())) {
                sb.append(req.getSenderAddr().getCountyName());
            }
            if (StringUtils.isNotBlank(req.getSenderAddr().getAddress())) {
                sb.append(req.getSenderAddr().getAddress());
            }
            if (StringUtils.isNotBlank(req.getSenderAddr().getDetailAddress())) {
                sb.append(req.getSenderAddr().getDetailAddress());
            }
            bookingForm.setCarriageReceiAddr(sb.toString());
            //bookingForm.setCarriageReceiAddr((req.getSenderAddr().getProvinceName() == null ? "" : req.getSenderAddr().getProvinceName()) + (req.getSenderAddr().getCityName() == null ? "" : req.getSenderAddr().getCityName()) + (req.getSenderAddr().getCountyName() == null ? "" : req.getSenderAddr().getCountyName()) + (req.getSenderAddr().getAddress() == null ? "" : req.getSenderAddr().getAddress()) + (req.getSenderAddr().getDetailAddress() == null ? "" : req.getSenderAddr().getDetailAddress()));
            if (!StringUtil.isEmpty(req.getSenderAddr().getCounty()) && !"0".equals(req.getSenderAddr().getCounty())) {
                bookingForm.setCarriageReceiCounty(req.getSenderAddr().getCounty());
            }
            if (req.getSenderAddr().getCity() != null) {
                bookingForm.setCarriageReceiCity(req.getSenderAddr().getCity());
            }
            if (req.getSenderAddr().getProvince() != null) {
                bookingForm.setCarriageReceiProvince(req.getSenderAddr().getProvince());
            }
            if (req.getSenderAddr().getCompanyName() != null) {
                bookingForm.setShipCustName(req.getSenderAddr().getCompanyName());
            }
            if (req.getSenderAddr().getZipCode() != null) {
                bookingForm.setSenderZipCode(req.getSenderAddr().getZipCode());
            }
            if (req.getSenderAddr() != null && (req.getSenderAddr().getAddressLatitude() == null || req.getSenderAddr().getAddressLongitude() == null)) {
                //获取只有地址的信息
                String address = bookingForm.getCarriageReceiAddr();
                if (bookingForm.getCarriageReceiAddr() != null && bookingForm.getShipCustHouseNumber() != null
                        && bookingForm.getCarriageReceiAddr().lastIndexOf(bookingForm.getShipCustHouseNumber()) > 0) {
                    address = bookingForm.getCarriageReceiAddr().substring(0, bookingForm.getCarriageReceiAddr().lastIndexOf(bookingForm.getShipCustHouseNumber()));
                }
                GiPoint giPoint = gpsService.getGiPointByNearBy(address, bookingForm.getShipCustHouseNumber());
                req.getSenderAddr().setAddressLatitude(BigDecimal.valueOf(giPoint.getLatitude()));
                req.getSenderAddr().setAddressLongitude(BigDecimal.valueOf(giPoint.getLongitude()));
            }
            bookingForm.setCarriageReceiLongitude(req.getSenderAddr().getAddressLongitude());
            bookingForm.setCarriageReceiLatitude(req.getSenderAddr().getAddressLatitude());

            if (req.getSenderAddrSaveFlag() != null && req.getSenderAddrSaveFlag() == CustomerDefine.SAVE_ADDRES) {
                if (senderAddr.getAccountId() == null) {
                    senderAddr.setAccountId(req.getAccountId());
                }
                //判断是否需要保存该地址
                saveAddress(senderAddr, 2);//1收货地址，2发货地址
            }
        }
        //logger.debug("同城运输下单-保存发件人信息后,bookingForm={}", JSON.toJSONString(bookingForm));
    }


    /**
     * 保存地址数据库操作
     * @param addressReq 地址信息
     * @param addressType 地址类型
     * @throws MobileStationBizException 手机异常异常
     */
    private void saveAddress(AddressInfo addressReq, int addressType) throws MobileStationBizException {
        //判断是否需要保存
        Boolean isDefaultAddr = addressReq.isDefaultAddress();
        MobileAddressInfo mobileAddressInfo = new MobileAddressInfo();
        try {
            BeanUtils.copyProperties(mobileAddressInfo, addressReq);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new MobileStationBizException(e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new MobileStationBizException(e.getMessage());
        }
        if (mobileAddressInfo.getCounty() != null && "0".equals(mobileAddressInfo.getCounty())) {
            mobileAddressInfo.setCounty(null);
        }
        mobileAddressInfo.setStatus(1);
        mobileAddressInfo.setCreateTime(new Date());
        mobileAddressInfo.setAddressType(addressType);
        //判断是否默认
        if (isDefaultAddr != null && isDefaultAddr) {
            mobileAddressInfo.setDefaultAddress(isDefaultAddr ? 1 : 0);
        }

        if (mobileAddressInfoDaoEx.querySameAddressInfo(mobileAddressInfo) >= 1) {
            //要是存在姓名、手机、地址一样的就不保存了。
        } else {
            mobileAddressInfoDao.insert(mobileAddressInfo);
        }
    }


    private String sendMessageIM(String realName, String busibookno, String acctUsername, String remindCode, Integer status) {
        String url = imContentSystemUrl;
        Map<String, Object> req = createRequest(realName, busibookno, acctUsername, remindCode, status);
        logger.debug("req={}", JSONObject.toJSONString(req));
        String resultStr = HttpClientUtil.URLPost(url, req,
                HeadAuthentication.setIMHead(CustomerDefine.APP_TAG));
        logger.debug("result={}", resultStr);
        return resultStr;
    }

    private Map<String, Object> createRequest(String realName, String busibookno, String acctUsername, String remindCode, Integer status) {
        Map<String, Object> req = new HashMap<>();
        req.put("sysCode", CustomerDefine.IM_SYS_DEFINE);
        req.put("platAccounts", acctUsername);
        req.put("sysTag", CustomerDefine.IM_MS_DEFINE);
        Map<String, String> mapObject = new HashMap<>();
        mapObject.put("realName", realName);
        mapObject.put("busiBookNo", busibookno);
        if (remindCode.equals(CustomerDefine.IM_FOLLOW_CODE)) {
            mapObject.put("msg", new StringBuffer("您的好友").append(realName).append("给您推送了一个订单").append(busibookno).append(",关注，确认接受本次关注？").toString());
        } else if (remindCode.equals(CustomerDefine.IM_FOLLOW_NOTIFY_CODE)) {
            if (status == 1) {
                mapObject.put("msg", new StringBuffer("您的好友").append(realName).append("已同意了关注").append(busibookno).append("订单").toString());
            } else if (status == 0) {
                mapObject.put("msg", new StringBuffer("您的好友").append(realName).append("已拒绝了关注").append(busibookno).append("订单").toString());
            }
            req.put("status", status);
        }
        req.put("body", JSONObject.toJSON(mapObject).toString());
        req.put("remindCode", remindCode);
        return req;
    }


    private String getItemCode(OrderPolicyBean req) {
        if (req == null && req.getGoodsInfos() == null) {
            return "";
        }
        ComGoodsType comGoodsType = comGoodsTypeDaoEx.selectByTypeCode(req.getGoodsInfos().get(0).getGoodsType());
        if (comGoodsType != null) {
            return comGoodsType.getInsuranceCode();
        } else {
            return "";
        }
    }


    private String getItem(OrderPolicyBean req) {
        if (req == null && req.getGoodsInfos() == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (GoodsInfo goodsInfos : req.getGoodsInfos()) {
            sb.append(goodsInfos.getGoodsName() + " ");
        }
        return sb.toString();
    }

    private String getQuantity(OrderPolicyBean req) {
        if (req == null && req.getGoodsInfos() == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (GoodsInfo goodsInfos : req.getGoodsInfos()) {
            ComUnit comUnit = comUnitService.getComUnitByCode(goodsInfos.getQtyUnit());
            sb.append(goodsInfos.getQty()).append(comUnit.getUnitCh() != null ? comUnit.getUnitCh() : "").append(" ");
        }
        return sb.toString();
    }


    private String getCity(String cityCode) {
        StringBuilder routeFullName = new StringBuilder();
        //区县
        ComCounty tCounty = comCountyService.queryForMap().get(cityCode);
        if (null != tCounty) {
            routeFullName.append(tCounty.getAreaName());
            cityCode = StringUtil.getObjStr(tCounty.getCityId());
        }
        ComCity tCtiy = comCityService.queryForMap().get(cityCode);
        if (null != tCtiy) {
            routeFullName.insert(0, tCtiy.getName() + " ");
            cityCode = StringUtil.getObjStr(tCtiy.getProvinceId());
        }
        ComProvince comProvince = comProvinceService.queryForMap().get(cityCode);
        if (null != comProvince) {
            routeFullName.insert(0, comProvince.getProvinceName() + " ");
        }
        return routeFullName.toString();
    }


    private void doSubOrder(BookingForm bookingForm) throws MobileStationBizException {

        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        mobileBookingForm.setShipCustProvide(bookingForm.getCarriageReceiProvince());
        mobileBookingForm.setShipCustCity(bookingForm.getCarriageReceiCity());
        if (null != bookingForm.getCarriageReceiCounty()) {
            mobileBookingForm.setShipCustCounty(bookingForm.getCarriageReceiCounty());
        }
        mobileBookingForm.setShipCustAddr((bookingForm.getShipCustaDdr() == null ? "" : bookingForm.getShipCustaDdr()) + (bookingForm.getShipCustHouseNumber() == null ? "" : bookingForm.getShipCustHouseNumber()));
        mobileBookingForm.setShipCustLinkMan(bookingForm.getShipCustlinkMan());
        mobileBookingForm.setShipCustLinkTel(bookingForm.getShipCustlinkTel());
        mobileBookingForm.setShipLatitude(bookingForm.getCarriageReceiLatitude());
        mobileBookingForm.setShipLongitude(bookingForm.getCarriageReceiLongitude());

        mobileBookingForm.setCneeCustProvide(bookingForm.getCarriageDelivProvince());
        mobileBookingForm.setCneeCustCity(bookingForm.getCarriageDelivCity());
        if (null != bookingForm.getCarriageDelivCounty()) {
            mobileBookingForm.setCneeCustCounty(bookingForm.getCarriageDelivCounty());
        }
        mobileBookingForm.setCneeCustAddr((bookingForm.getCneeCustAddr() == null ? "" : bookingForm.getCneeCustAddr()) + (bookingForm.getCneeCustHouseNumber() == null ? "" : bookingForm.getCneeCustHouseNumber()));
        mobileBookingForm.setCneeCustLinkMan(bookingForm.getCneeCustLinkMan());
        mobileBookingForm.setCneeCustLinkTel(bookingForm.getCneeCustLinkTel());
        mobileBookingForm.setCneeLatitude(bookingForm.getCarriageDelivLatitude());
        mobileBookingForm.setCneeLongitude(bookingForm.getCarriageDelivLongitude());
        if (null != bookingForm.getPredictValue()) {
            mobileBookingForm.setPredictValue(bookingForm.getPredictValue());
        }
        mobileBookingForm.setBookingFormId(bookingForm.getId());
        mobileBookingForm.setCreateUserId(bookingForm.getCreateUserId());
        mobileBookingForm.setCreateUser(bookingForm.getCreateUser());
        mobileBookingForm.setBusiBookNo(bookingForm.getBusiBookNo());
        mobileBookingForm.setBookingDate(bookingForm.getBookingDate());
        //TODO 运输来源
        mobileBookingForm.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU);
        mobileBookingForm.setTransportType(bookingForm.getOrderType());
        mobileBookingForm.setRevUserId(bookingForm.getRevUserId());
        mobileBookingForm.setRevUser(bookingForm.getRevUser());
        mobileBookingForm.setComQuoteId(bookingForm.getDocno());
        mobileBookingForm.setPayType(bookingForm.getPayType());
        if (!StringUtil.isEmpty(bookingForm.getGoodsPayment())) {
            mobileBookingForm.setDestPayment(bookingForm.getGoodsPayment());
            mobileBookingForm.setGoodsPaymentCurr(bookingForm.getGoodsPaymentCurr());
        }
        if (!StringUtil.isEmpty(bookingForm.getGoodsPayment())) {
            mobileBookingForm.setGoodsPayment(bookingForm.getGoodsPayment());
            mobileBookingForm.setGoodsPaymentCurr(bookingForm.getGoodsPaymentCurr());
        }
        if (null != bookingForm.getPredictCurr()) {
            mobileBookingForm.setPredictCurr(bookingForm.getPredictCurr());
        }
        if (!StringUtil.isEmpty(bookingForm.getCarriAgerecei())) {
            mobileBookingForm.setOrderType(Integer.parseInt(bookingForm.getCarriAgerecei()));
        }
        if (null != bookingForm.getNeedInsure()) {
            mobileBookingForm.setNeedInsure(bookingForm.getNeedInsure());
        }
        //订单状态设置为已接单
        mobileBookingForm.setIsJs(0);
        mobileBookingForm.setProductType(bookingForm.getTransportType());
        if (null != bookingForm.getGoodsValue()) {
            mobileBookingForm.setGoodsValue(bookingForm.getGoodsValue());
        }
        mobileBookingForm.setBookingDate(bookingForm.getBookingDate());
        mobileBookingForm.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL);
        mobileBookingForm.setTransportType(bookingForm.getOrderType());
        mobileBookingForm.setRevUserId(bookingForm.getRevUserId());
        mobileBookingForm.setRevUser(bookingForm.getRevUser());
        mobileBookingForm.setRevCompanyId(bookingForm.getRevCompanyId());
        mobileBookingForm.setPayType(bookingForm.getPayType());
        mobileBookingForm.setRevDate(new Date());
        mobileBookingForm.setBusiCtrl(1);
        mobileBookingForm.setCreateDate(new Date());
        List<MobileGoodsDtl> recordList = new ArrayList<>();
        List<BillingFormSalm> billingFormSalms = billingFormSalmDaoEx.queryByBusiBooknoId(bookingForm.getId());
        for (BillingFormSalm billingFormSalm : billingFormSalms) {
            MobileGoodsDtl mobileGoodsDtl = createMobileGoodsDtl(mobileBookingForm, billingFormSalm);
            recordList.add(mobileGoodsDtl);
        }

        if (null != bookingForm.getNarrate()) {
            mobileBookingForm.setNarrate(bookingForm.getNarrate());
        }
        //4:小(M)站,5:快递员,6:司机
        if (mobileBookingForm.getTransportType() == 1) {//运输
            mobileBookingForm.setRoleId(3);//司机
        }
        if (bookingForm.getCreateCompanyId() != null && bookingForm.getCreateCompanyId() > 0) {
            mobileBookingForm.setCreateCompanyId(bookingForm.getCreateCompanyId());
        }
        //数据备份mobilestation
        AppBaseResult recordMoblieStationOrder = recordMoblieStationOrder(mobileBookingForm, recordList);
        if (recordMoblieStationOrder.getRetCode() == SystemDefine.FAILURE) {
            throw new MobileStationBizException("下单数据备份mobilestation失败");
        }

    }


    private MobileGoodsDtl createMobileGoodsDtl(MobileBookingForm mobileBookingForm, BillingFormSalm billingFormSalm) {
        MobileGoodsDtl mobileGoodsDtl = new MobileGoodsDtl();
        mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
        mobileGoodsDtl.setCreateDate(new Date());
        mobileGoodsDtl.setCreateUser(mobileBookingForm.getCreateUser().toString());
        if (billingFormSalm.getGoodsHeight() != null) {
            mobileGoodsDtl.setGoodsHeight(BigDecimal.valueOf(billingFormSalm.getGoodsHeight()));
        }
        if (billingFormSalm.getGoodsLength() != null) {
            mobileGoodsDtl.setGoodsLenght(BigDecimal.valueOf(billingFormSalm.getGoodsLength()));
        }
        if (billingFormSalm.getGoodsWidth() != null) {
            mobileGoodsDtl.setGoodsWide(BigDecimal.valueOf(billingFormSalm.getGoodsWidth()));
        }
        if (billingFormSalm.getHscodeNachs() != null) {
            mobileGoodsDtl.setGoodsName(billingFormSalm.getHscodeNachs());
        }
        if (billingFormSalm.getGoodsQty() != null) {
            mobileGoodsDtl.setGoodsQty(billingFormSalm.getGoodsQty());
        }
        if (billingFormSalm.getHscodeSpecs() != null) {
            mobileGoodsDtl.setGoodsType(billingFormSalm.getHscodeSpecs());
        }
        if (billingFormSalm.getGoodsQtyUnitCo() != null) {
            mobileGoodsDtl.setGoodsQtyUnit(billingFormSalm.getGoodsQtyUnitCo());
        }
        if (billingFormSalm.getGoodsGrosswht() != null) {
            mobileGoodsDtl.setGoodsWeight(billingFormSalm.getGoodsGrosswht());
        }
        mobileGoodsDtl.setGoodsWeightUnit(billingFormSalm.getWeightUnitCo());
        if (billingFormSalm.getGoodsHeight() != null && billingFormSalm.getGoodsLength() != null && billingFormSalm.getGoodsWidth() != null) {
            mobileGoodsDtl.setGoodsVolume(BigDecimal.valueOf(billingFormSalm.getGoodsHeight() * billingFormSalm.getGoodsLength() * billingFormSalm.getGoodsWidth()).setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        mobileGoodsDtl.setGoodsVolumeUnit("164");
        return mobileGoodsDtl;
    }


    /**
     * 将订单与货物信息备份到mobilestation
     * @param mobileBookingForm mobileBookingForm
     * @param recordList recordList
     * @return AppBaseResult
     * @throws MobileStationBizException
     */
    private AppBaseResult recordMoblieStationOrder(MobileBookingForm mobileBookingForm, List<MobileGoodsDtl> recordList)
            throws MobileStationBizException {
        /**
         * 组装请求mobilestation下单接口参数
         */
        RecordMobileStationOrderRequest recordMobileStationOrderRequest = convertRecordMobileStationOrderRequest(mobileBookingForm);
        recordMobileStationOrderRequest.setRecordOrderType(RecordOrderType.ORDER_O.getValue());
        //组装接收人信息
        ArrayList<RevUserBean> revUserList = new ArrayList<RevUserBean>();
        RevUserBean revUserBean = new RevUserBean();
        revUserBean.setRevUserId(mobileBookingForm.getRevUserId());
        revUserBean.setRevUser(mobileBookingForm.getRevUser());
        revUserList.add(revUserBean);

        //设置站点信息
        recordMobileStationOrderRequest.setStartLocus(MobileStationDefine.POP);
        recordMobileStationOrderRequest.setDestnLocus(MobileStationDefine.POD);

        recordMobileStationOrderRequest.setRevUserList(revUserList);
        //赋值订单货物信息
        recordMobileStationOrderRequest.setMobileGoodDtlList(recordList);
        //调用mobilestation下单接口：备份订单以及货物信息-->mobilestation
        AppBaseResult recordMobileStationOrder = mobileStationOrderService.recordMobileStationOrder(recordMobileStationOrderRequest);
        return recordMobileStationOrder;
    }

    /**
     * 组装booking_form数据
     * @param mobileBookingForm mobileBookingForm
     * @return RecordMobileStationOrderRequest
     * @throws MobileStationBizException MobileStationBizException
     */
    private RecordMobileStationOrderRequest convertRecordMobileStationOrderRequest(MobileBookingForm mobileBookingForm)
            throws MobileStationBizException {

        RecordMobileStationOrderRequest recordMobiStaOrderRequest = new RecordMobileStationOrderRequest();
        try {
            PropertyUtils.copyProperties(recordMobiStaOrderRequest, mobileBookingForm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MobileStationBizException("参数格式异常");
        }
        return recordMobiStaOrderRequest;
    }


    /**
     * 处理用户确认报价
     * @param req req
     * @return QuotePriceRes
     * @throws MobileStationBizException 手机订单业务异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuotePriceRes handleQuotePrice(QuotePriceReq req) throws MobileStationBizException {
        logger.debug("用户确认报价开始--req:{}", JSON.toJSONString(req));
        validateQuotePriceReq(req);
        QuotePriceRes res = new QuotePriceRes();
        //订单信息
        BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(req.getOrderId());
        List<MobileFleetBidding> mfbList = mobileFleetBiddingDaoEx.queryAllFleetBiddingInfo(bookingForm.getBusiBookNo());
        if (CollectionUtils.isEmpty(mfbList) || mfbList.size() > 1) {
            logger.debug("该订单找不到竞价信息或者有多条竞价信息");
            throw new MobileStationBizException("该订单竞价信息异常");
        }
        if (bookingForm != null && bookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER) {
            logger.debug("该订单--{},重复确认竞价", bookingForm.getBusiBookNo());
            throw new MobileStationBizException("重复确认竞价");
        }
        logger.debug("确认报价中车队竞价表的信息:mfbList-{}", JSON.toJSONString(mfbList));
        //用户确认报价,设置为已接单
        /*if (req.getQuoted()) {*/
        logger.debug("确认报价操作开始--start");
        MobileFleetBidding mfb = mfbList.get(0);
        bookingForm.setRevCompanyId(mfb.getFleetUserId());
        bookingForm.setRevUserName(mfb.getDriverName());
        bookingForm.setRevUser(mfb.getDriverUser());
        bookingForm.setRevUserId(mfb.getDriverUserId());
        bookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);//已接单
        bookingForm.setBookingCtrl(SysAccountRole.OPERATOR_CAR_OWNER.getValue());
        bookingForm.setRevDate(new Date());
        try {
            //1. 更新订单状态
            bookingFormDaoEx.updateBookingFormAfterQuoted(bookingForm);
            //2. 新增mobileBookingForm
            doSubOrder(bookingForm);
            //3. 通知中层GPS 用户确认报价
            sendCfmPriceGpsMsg(bookingForm);
            logger.debug("确认报价操作完成--end");
        } catch (Exception e) {
            res.setData(e.getMessage());
            res.setRetCode(-1);
            res.setRetMsg("确认报价操作失败");
            return res;
        }
        logger.debug("确认报价操作返回值:res--{}", JSON.toJSONString(res));
        return res;
    }

    /**
     * 确认报价校验参数
     * @param req req
     * @throws MobileStationBizException MobileStationBizException
     */
    private void validateQuotePriceReq(QuotePriceReq req) throws MobileStationBizException {
        logger.debug("用户确认或者取消报价校验参数--start");
        if (req == null) {
            throw new MobileStationBizException("请求参数为空");
        }
        if (!MobileStationDefine.PRODUCT_TYPE_TCYS.equals(req.getProductType())) {

            throw new MobileStationBizException("产品类型为空");
        }
        if (StringUtils.isBlank(req.getBusiBookNo())) {
            throw new MobileStationBizException("订单编号为空");
        }
        if (req.getOrderId() == null) {
            throw new MobileStationBizException("订单id为空");
        }
        BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(req.getOrderId());
        if (bookingForm == null) {
            throw new MobileStationBizException("订单信息不存在");
        }
        logger.debug("用户确认或者取消报价校验参数--end");
    }

    /**
     * 通知中层GPS 用户取消报价
     * @param bookingForm bookingForm
     */
    private void sendClePriceGpsMsg(BookingForm bookingForm) {
        // 发送消息给车队,用户取消报价
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(bookingForm.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(bookingForm.getTransportType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
        giOrderTraceResynced.setTsAct(new Date());
        if (bookingForm.getCreateCompanyId() != null) {
            ComAccount companyAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
            if (companyAccount != null) {
                giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
            } else {
                giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
            }
        } else {
            giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
        }
        giOrderTraceResynced.setLoginCode(bookingForm.getCreateUser());
        giOrderTraceResynced.setRoleCode(SysAccountRole.NORMAL_PERSONAL.getRoleCode());
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
    }

    /**
     * 通知中层GPS 用户确认报价
     * @param bookingForm
     */
    private void sendCfmPriceGpsMsg(BookingForm bookingForm) {
        //发送中层GPS消息,用户确认报价
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(bookingForm.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(bookingForm.getTransportType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_FleetAccept);
        giOrderTraceResynced.setTsAct(new Date());
        if (bookingForm.getCreateCompanyId() != null) {
            ComAccount companyAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
            if (companyAccount != null) {
                giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
            } else {
                giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
            }
        } else {
            giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
        }
        giOrderTraceResynced.setLoginCode(bookingForm.getCreateUser());
        giOrderTraceResynced.setRoleCode(SysAccountRole.NORMAL_PERSONAL.getRoleCode());
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
    }


    /**
     * 确认竞价后取消竞价
     * @param bookingForm bookingForm
     * @param mfbList mfbList
     */
    private void cancleQuotePrice(BookingForm bookingForm, List<MobileFleetBidding> mfbList) {
        logger.debug("确认竞价后取消竞价:订单-bookingForm--{}", JSON.toJSONString(bookingForm));
        logger.debug("确认竞价后取消竞价:竞价信息集合-mfbList--{}", JSON.toJSONString(mfbList));
        MobileFleetBidding mobileFleetBidding = mfbList.get(0);
        mobileFleetBidding.setChooseFlag(-1);
        mobileFleetBiddingDao.updateByPrimaryKey(mobileFleetBidding);
        logger.debug("确认竞价后取消竞价,更新mobileFleetBidding完成-successful");
        bookingForm.setRevCompanyId(null);
        bookingForm.setRevUserName(null);
        bookingForm.setRevUser(null);
        bookingForm.setRevUserId(null);
        bookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_QUO_FAIL);//确认后取消报价
        bookingForm.setRevDate(null);
        bookingFormDao.updateByPrimaryKey(bookingForm);
        logger.debug("确认竞价后取消竞价,更新bookingForm完成-successful");
        List<MobileBookingForm> paramList = new ArrayList<>();
        List<MobileBookingForm> mbfList = mobileBookingFormDaoEx.selectMobileOrderByBookBusiNo(bookingForm.getBusiBookNo());
        List<MobileGoodsDtl> mgdAllList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(mbfList)) {
            for (MobileBookingForm mobileBookingForm : mbfList) {
                List<MobileGoodsDtl> mgdList = mobileGoodsDtlDaoEx.selectByMobileBookingFormId(mobileBookingForm.getId());
                if (CollectionUtils.isNotEmpty(mgdList)) {
                    mgdAllList.addAll(mgdList);
                }
                mobileBookingForm.setBusiCtrl(-12);
                paramList.add(mobileBookingForm);
            }
            /*if (CollectionUtils.isNotEmpty(mgdAllList)) {
                mobileGoodsDtlDaoEx.batchDeleteMobileGoodsDtl(mgdAllList);
                logger.debug("确认竞价后取消竞价,批量删除MobileGoodsDtl完成-successful");
            }*/
            mobileBookingFormDaoEx.batchUpdateOrderBusi(mbfList);
            logger.debug("确认竞价后取消竞价,批量删除MobileBookingForm完成-successful");
        }
        /*mobileBookingForm.setRevUserId(null);
          mobileBookingForm.setRevUser(null);
          mobileBookingForm.setRevCompanyId(null);
          mobileBookingForm.setRevDate(null);
          mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_GIVEUP);
          mobileBookingForm.setCreateDate(null);
          mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);*/
        // 发送消息
        sendClePriceGpsMsg(bookingForm);
        logger.debug("确认竞价后取消竞价,通知车队gps完成-successful");
    }


    /**
     * 用户或者咪站取消订单--运输单(OTCYSEX|ITCYS)
     * @param cancleOrderReq cancleOrderReq
     * @return CancleOrderRes
     * @throws MobileStationBizException MobileStationBizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CancleOrderRes handleCancleOrder(CancleOrderReq cancleOrderReq) throws MobileStationBizException {
        logger.debug("咪站或用户取消订单--cancleOrderReq:{}", JSON.toJSONString(cancleOrderReq));
        validateCancleOrderReq(cancleOrderReq);
        CancleOrderRes res = new CancleOrderRes();
        //取消订单信息 OTCYSEX
        if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(cancleOrderReq.getProductType())) {
            try {
                //1. 更新BOOKING_FORM表订单状态为已失效
                BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(cancleOrderReq.getOrderId());
                if (bookingForm == null) {
                    res.setRetMsg("订单信息不存在");
                    res.setRetCode(SystemDefine.FAILURE);
                    return res;
                }
                if (bookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_QUOTE) {
                    logger.debug("handleCancleOrder--更改订单状态--由待确认转成已失效报价");
                    //更新订单的状态
                    bookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_QUO_FAIL);
                    bookingForm.setBusiAbort(cancleOrderReq.getAcctUsername());
                    bookingForm.setBusiAbortCaus(cancleOrderReq.getReason());
                    bookingForm.setBusiAbortDate(new Date());
                    cancelPriceOrder4OTCYSEX(bookingForm);
                } else if (bookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TO_QUOTE) {
                    logger.debug("handleCancleOrder--更改订单状态--由待报价转成已失效订单");
                    //更新订单的状态
                    bookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_DOC_FAIL);
                    bookingForm.setBusiAbort(cancleOrderReq.getAcctUsername());
                    bookingForm.setBusiAbortCaus(cancleOrderReq.getReason());
                    bookingForm.setBusiAbortDate(new Date());
                    cancelPriceOrder4OTCYSEX(bookingForm);
                } else {
                    res.setRetMsg("订单状态错误");
                    res.setRetCode(-1);
                    return res;
                }
                cancelOrder4BillTrace(cancleOrderReq, bookingForm);
            } catch (Exception ex) {
                logger.error("OTCYS取消订单出现异常-{}", ex.getMessage());
                res.setRetCode(SystemDefine.FAILURE);
                res.setData(ex.getMessage());
                res.setRetMsg("同城运输取消订单异常");
                return res;
            }
        } else {  //取消订单信息 ITCYS
            try {
                MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(cancleOrderReq.getOrderId());
                if (mobileBookingForm == null) {
                    logger.error("{}'s order in table MOBILE_BOOKING_FORM does not exist", cancleOrderReq.getBusiBookNo());
                    res.setRetMsg("订单不存在");
                    res.setRetCode(-1);
                    return res;
                }
                Integer oriBusiCtrl = mobileBookingForm.getBusiCtrl();
                if (oriBusiCtrl == MobileStationDefine.MOBILE_ORDER_STATUS_QUOTE) {
                    logger.debug("handleCancelOrder--更改订单状态--由待确认-转成-取件成功");
                    //更新订单的状态
                    mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                    mobileBookingForm.setBidBy(null);
                    mobileBookingForm.setBidUser(null);
                    mobileBookingForm.setBidUserId(null);
                    cancelPriceOrder4ITCYS(mobileBookingForm, oriBusiCtrl);
                } else if (oriBusiCtrl == MobileStationDefine.MOBILE_ORDER_STATUS_TO_QUOTE) {
                    logger.debug("handleCancelOrder--更改订单状态--由待报价-转成-取件成功");
                    //更新订单的状态
                    mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                    mobileBookingForm.setBidBy(null);
                    mobileBookingForm.setBidUser(null);
                    mobileBookingForm.setBidUserId(null);
                    cancelPriceOrder4ITCYS(mobileBookingForm, oriBusiCtrl);
                } else {
                    res.setRetMsg("订单状态错误");
                    res.setRetCode(-1);
                    return res;
                }
                cancelOrder4BillTrace(cancleOrderReq, mobileBookingForm);
            } catch (Exception ex) {
                logger.error("ITCYS取消订单出现异常-{}", ex.getMessage());
                res.setRetCode(SystemDefine.FAILURE);
                res.setData(ex.getMessage());
                res.setRetMsg("同城运输取消订单异常");
                return res;
            }

        }

        logger.debug("确认或取消报价操作返回值:res--{}", JSON.toJSONString(res));
        return res;
    }


    /**
     * 用户取消订单记录追踪日志
     * @param req req
     * @param obj obj
     */
    private void cancelOrder4BillTrace(CancleOrderReq req, Object obj) {
        logger.info("用户取消订单记录追踪日志-开始");
        //1. 记录追踪日志
        ComWaybillTrace tmp = new ComWaybillTrace();
        tmp.setExecCode(WayBillStatusDefine.CANCLE_ORDER.getIntValue());
        tmp.setRemark(WayBillStatusDefine.CANCLE_ORDER.getName());
        tmp.setAcctUsername(req.getAcctUsername());
        ComAccount comAccount = comAccountService.queryAccountByAcctUsername(req.getAcctUsername());
        BookingForm bf;
        if (obj != null && obj.getClass() == BookingForm.class) {
            bf = (BookingForm) obj;
            tmp.setGrade(0);
            tmp.setRoleId(1);
            tmp.setRemark(BusinessDefine.ORDER_BROADCAST_CACEL);
            tmp.setExecCode(WayBillStatusDefine.CANCLE_ORDER.getIntValue());
            tmp.setBusiBookNo(bf.getBusiBookNo());
            if (comAccount != null && comAccount.getRealName() != null) {
                tmp.setRealName(comAccount.getRealName());
            } else {
                tmp.setRealName(bf.getShipCustlinkMan());
            }
            tmp.setStaDate(new Date());
        }
        MobileBookingForm mbf;
        if (obj != null && obj.getClass() == MobileBookingForm.class) {
            mbf = (MobileBookingForm) obj;
            tmp.setGrade(BusinessDefine.GRADE);
            tmp.setRoleId(23);
            tmp.setRemark(BusinessDefine.ORDER_BROADCAST_CACEL);
            tmp.setExecCode(WayBillStatusDefine.CANCLE_ORDER.getIntValue());
            tmp.setBusiBookNo(mbf.getBusiBookNo());
            if (comAccount != null) {
                tmp.setRealName(comAccount.getRealName());
            }
            tmp.setStaDate(new Date());
        }
        comWaybillTraceDao.insert(tmp);
        //1. 记录追踪日志
        logger.info("用户取消订单记录追踪日志-结束-数据库中追踪的对象是-{}", JSON.toJSONString(tmp));
    }


    /**
     * 用户或者咪站取消订单
     * @param cancleOrderReq 请求参数
     * @throws MobileStationBizException MobileStationBizException
     */
    private void validateCancleOrderReq(CancleOrderReq cancleOrderReq) throws MobileStationBizException {
        logger.debug("用户或者咪站取消订单--运输单(OTCYSEX|ITCYS)--start");
        if (cancleOrderReq == null) {
            throw new MobileStationBizException("请求参数为空");
        }
        if (StringUtils.isBlank(cancleOrderReq.getBusiBookNo())) {
            throw new MobileStationBizException("订单号为空");
        }
        if (StringUtil.isEmpty(cancleOrderReq.getOrderId())) {
            throw new MobileStationBizException("订单Id为空");
        }
        if (StringUtils.isBlank(cancleOrderReq.getProductType())) {
            throw new MobileStationBizException("产品类型为空");
        }
        if (!MobileStationDefine.PRODUCT_TYPE_TCYS.equals(cancleOrderReq.getProductType())
                && !MobileStationDefine.PRODUCT_TYPE_ITCYS.equals(cancleOrderReq.getProductType())) {
            throw new MobileStationBizException("产品类型参数无效");
        }
        logger.debug("用户确认或者取消报价校验参数--end-successful");
    }

    /**
     * 咪站取消订单时,通知GPS中层消息
     * @param mobileBookingForm mobileBookingForm
     */
    private void sendMiCancleOrderGpsMsg(MobileBookingForm mobileBookingForm) {
        //发送消息
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(mobileBookingForm.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
        giOrderTraceResynced.setTypeCancelOrdered(0);//咪站(用户)取消
        giOrderTraceResynced.setTsAct(new Date());
        if (mobileBookingForm.getRevCompanyId() != null) {
            ComAccount companyAccount = comAccountDao.selectByPrimaryKey(mobileBookingForm.getRevCompanyId());
            if (companyAccount != null) {
                giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
            } else {
                giOrderTraceResynced.setUserCode(mobileBookingForm.getRevUser());
            }
        } else {
            giOrderTraceResynced.setUserCode(mobileBookingForm.getRevUser());
        }
        giOrderTraceResynced.setLoginCode(mobileBookingForm.getRevUser());
        giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
        logger.info("cancleOrder sendGpsLogMessage ={}", JSON.toJSONString(giOrderTraceResynced));
        //通知Mi站gps日志消息
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
    }

    /**
     * 用户取消订单时,通知消息给中层GPS
     * @param bookingForm bookingForm
     */
    private void sendUserCancleOrderGpsMsg(BookingForm bookingForm) {
        // 发送消息
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(bookingForm.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(bookingForm.getTransportType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
        giOrderTraceResynced.setTypeCancelOrdered(0);//用户取消
        giOrderTraceResynced.setTsAct(new Date());
        if (bookingForm.getCreateCompanyId() != null) {
            ComAccount companyAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
            if (companyAccount != null) {
                giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
            } else {
                giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
            }
        } else {
            giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
        }
        giOrderTraceResynced.setLoginCode(bookingForm.getCreateUser());
        giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
        logger.info("cancleOrder sendGpsLogMessage ={}", JSON.toJSONString(giOrderTraceResynced));
        //通知用户GPS消息消息
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
    }


    /**
     * 推送消息给车队,暂时不做
     * @param obj bookingForm/mobileBookingForm
     * @param mfb MobileFleetBidding(竞价信息)
     */
    private void sendOpMsg2Fleet(Object obj, MobileFleetBidding mfb) {
        //推送消息Bean
        if (obj.getClass() == BookingForm.class) {
            BookingForm bookingForm = (BookingForm) obj;
            MsgIMReq msgIMReq = new MsgIMReq();
            msgIMReq.setAccountId(mfb.getCreateUserId());//账户ID
            msgIMReq.setAcctUsername(mfb.getCreateUser());//登录账号
            msgIMReq.setBusiBookNo(bookingForm.getBusiBookNo());//订单编号
            msgIMReq.setOrderId(bookingForm.getId());//BOOKING_FORM表订单主键
            msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_GIVEUP_ORDER);//业务标识,用户放弃订单(定义的消息类别)
            msgIMReq.setMsgTo(1); // 推送消息给用户
            msgIMReq.setCreateUser(bookingForm.getCreateUser());
            msgIMReq.setOrderFrom(2);//2运输指派单，4个人指派单，5运输广播单6个人广播单，7MS指派单，8MS广播单
            mobileMyOrderService.sendMsg(msgIMReq);
            return;
        }
        if (obj.getClass() == MobileBookingForm.class) {
            MobileBookingForm mobileBookingForm = (MobileBookingForm) obj;
            //推送消息Bean
            MsgIMReq msgIMReq = new MsgIMReq();
            msgIMReq.setAccountId(mfb.getCreateUserId());//账户ID
            msgIMReq.setAcctUsername(mfb.getCreateUser());//登录账号
            msgIMReq.setBusiBookNo(mobileBookingForm.getBusiBookNo());//订单编号
            msgIMReq.setScheducarno(mobileBookingForm.getBusiBookNo());//实派车单号
            msgIMReq.setOrderId(mobileBookingForm.getId());//MOBIL_BOOKING_FORM表订单主键
            msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_GIVEUP_ORDER);//业务标识,用户放弃订单(定义的消息类别)
            msgIMReq.setMsgTo(2); // 推送消息给Mi站
            msgIMReq.setCreateUser(mobileBookingForm.getBidUser());
            msgIMReq.setOrderFrom(7);//2运输指派单，4个人指派单，5运输广播单6个人广播单，7MS指派单，8MS广播单
            mobileMyOrderService.sendMsg(msgIMReq);
        }
    }


    /**
     * ITCYS单取消报价或者取消订单具体实现细节
     * @param mobileBookingForm mobileBookingForm
     * @param oriBusiCtrl oriBusiCtrl订单先前状态
     */
    private void cancelPriceOrder4ITCYS(MobileBookingForm mobileBookingForm, Integer oriBusiCtrl) {
        mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
        //删除增值服务 I单需要删除
        mobileValueAddDaoEx.delMVAByMobileBookingFormId(mobileBookingForm.getId());
        MobileFleetBidding mfb = mobileFleetBiddingDaoEx.queryFleetBidding(mobileBookingForm.getBusiBookNo(), mobileBookingForm.getScheducarno());
        if (mfb != null) {
            mfb.setChooseFlag(-1);//未选中
            //更新竞价表中的选中状态
            mobileFleetBiddingDao.updateByPrimaryKey(mfb);
        }
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(mobileBookingForm.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
        if (oriBusiCtrl == MobileStationDefine.MOBILE_ORDER_STATUS_QUOTE) {
            giOrderTraceResynced.setTypeCancelOrdered(1);//报价后咪站用户取消
        }
        if (oriBusiCtrl == MobileStationDefine.MOBILE_ORDER_STATUS_TO_QUOTE) {
            giOrderTraceResynced.setTypeCancelOrdered(0);//咪站用户主动取消
        }
        giOrderTraceResynced.setTsAct(new Date());
        if (mobileBookingForm.getRevCompanyId() != null) {
            ComAccount companyAccount = comAccountDao.selectByPrimaryKey(mobileBookingForm.getRevCompanyId());
            if (companyAccount != null) {
                giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
            } else {
                giOrderTraceResynced.setUserCode(mobileBookingForm.getRevUser());
            }
        } else {
            giOrderTraceResynced.setUserCode(mobileBookingForm.getRevUser());
        }
        giOrderTraceResynced.setLoginCode(mobileBookingForm.getRevUser());
        giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
        logger.info("cancelPriceOrder4ITCYS sendGpsLogMessage ={}", JSONArray.toJSONString(giOrderTraceResynced));
        //通知Mi站gps日志消息
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
    }

    /**
     * OTCYSEX单取消报价或者取消订单具体实现细节
     * @param bookingForm bookingForm
     */
    private void cancelPriceOrder4OTCYSEX(BookingForm bookingForm) {
        bookingFormDao.updateByPrimaryKey(bookingForm);
        //删除增值服务 O单暂不删除,app端再来一单
        //mobileValueAddDaoEx.delMVAByBookingFormId(bookingForm.getId());
        MobileFleetBidding mfb = mobileFleetBiddingDaoEx.selectByOrderId(bookingForm.getId());
        if (mfb != null) {
            mfb.setChooseFlag(-1);//未选中
            //更新竞价表中的选中状态
            logger.debug("cancelPriceOrder4OTCYSEX--更改竞价表选中状态为-1");
            mobileFleetBiddingDao.updateByPrimaryKey(mfb);
        }
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(bookingForm.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(bookingForm.getTransportType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
        if (bookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_QUO_FAIL) {
            giOrderTraceResynced.setTypeCancelOrdered(1);//报价后用户取消
        }
        if (bookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_DOC_FAIL) {
            giOrderTraceResynced.setTypeCancelOrdered(0);//用户主动取消
        }
        giOrderTraceResynced.setTsAct(new Date());
        if (bookingForm.getCreateCompanyId() != null) {
            ComAccount companyAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
            if (companyAccount != null) {
                giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
            } else {
                giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
            }
        } else {
            giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
        }
        giOrderTraceResynced.setLoginCode(bookingForm.getCreateUser());
        giOrderTraceResynced.setRoleCode(SysAccountRole.NORMAL_PERSONAL.getRoleCode());
        logger.info("cancelPriceOrder4OTCYSEX sendGpsLogMessage ={}", JSONArray.toJSONString(giOrderTraceResynced));
        //通知用户GPS消息消息
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
    }
}
