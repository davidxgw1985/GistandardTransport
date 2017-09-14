package com.gistandard.transport.order.module.mobilestation.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.CustomerDefine;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.entity.bean.BatchUpMBF;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.bean.MobileSignIn;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.MobileSignInDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComAccountRoleRelDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileBookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileSignInDaoEx;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.bean.QRCodeReq;
import com.gistandard.transport.order.module.customer.bean.QRCodeRes;
import com.gistandard.transport.order.module.customer.bean.SignInReq;
import com.gistandard.transport.order.module.customer.bean.SignInRes;
import com.gistandard.transport.order.module.mobilestation.bean.SerialNoRedisBean;
import com.gistandard.transport.order.module.mobilestation.service.MobileSignInService;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.tools.util.DateUtil;
import com.gistandard.transport.tools.util.ImPushUtil;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 客户端-签到业务实现类
 * <p>咪站、快递员、司机签到服务接口实现<p/>
 * <p>生成签到二维码内容接口实现<p/>
 * @author 员伟
 */
@Service
public class MobileSignInServiceImpl implements MobileSignInService {

    private static final Logger logger = LoggerFactory.getLogger(MobileSignInServiceImpl.class);

    private static final String REDIS_SIGN_IN_KEY = "redisSignIn";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

    private static DecimalFormat serialFormat2 = new DecimalFormat("00");

    private static DecimalFormat serialFormat3 = new DecimalFormat("000");

    private static DecimalFormat serialFormat4 = new DecimalFormat("0000");

    private static DecimalFormat serialFormat5 = new DecimalFormat("00000");

    @Autowired
    private MobileSignInDao mobileSignInDao;

    @Autowired
    private MobileSignInDaoEx mobileSignInDaoEx;

    @Autowired
    private ComAccountRoleRelDaoEx comAccountRoleRelDaoEx;

    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;

    @Autowired
    private GpsLogService gpsLogService;

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 司机、快递员、咪站、洼站签到操作
     * @param req 签到请求
     * @return 签到结果
     * @throws MobileStationBizException 业务异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public SignInRes addSignInfo(SignInReq req) throws MobileStationBizException {
        //1. 检验参数
        validateSIParam4Req(req);
        //2. 构造返回
        SignInRes res = new SignInRes(req);
        MobileSignIn mobileSignIn = new MobileSignIn();
        List<Integer> destAcctIdRoleIds = getDestAcctIdRoleId(req.getQrCodeInfo(), mobileSignIn);
        int destAccountId = destAcctIdRoleIds.get(0);
        List<MobileBookingForm> mbfList;
        //3. 根据角色创建签到内容
        switch (req.getRoleId()) {
            case 3: //如果是司机
                mobileSignIn.setAcctUsername(req.getAcctUsername());
                mobileSignIn.setCompanyAcctUsername(req.getCompanyAcctUsername());
                mobileSignIn.setFleetAcct(req.getCompanyAcctUsername());
                mobileSignIn.setScanTime(new Date());
                mobileSignIn.setQrCodeInfo(req.getQrCodeInfo());
                mobileSignIn.setDestAccountId(destAccountId);
                mbfList = mobileBookingFormDaoEx.querySignInOrderList(req.getAcctUsername(), 20, 3, destAccountId);
                break;
            case 5: //如果是洼站
                mobileSignIn.setAcctUsername(req.getAcctUsername());
                mobileSignIn.setCompanyAcctUsername(req.getCompanyAcctUsername());
                mobileSignIn.setwStationNo(req.getwStationNo());
                mobileSignIn.setScanTime(new Date());
                mobileSignIn.setQrCodeInfo(req.getQrCodeInfo());
                mobileSignIn.setDestAccountId(destAccountId);
                mbfList = mobileBookingFormDaoEx.querySignInOrderList(req.getAcctUsername(), 40, 5, destAccountId);
                break;
            case 7: //如果是快递员
                mobileSignIn.setAcctUsername(req.getAcctUsername());
                mobileSignIn.setCompanyAcctUsername(req.getCompanyAcctUsername());
                mobileSignIn.setCompanyAcct(req.getCompanyAcctUsername());
                mobileSignIn.setScanTime(new Date());
                mobileSignIn.setQrCodeInfo(req.getQrCodeInfo());
                mobileSignIn.setDestAccountId(destAccountId);
                mbfList = mobileBookingFormDaoEx.querySignInOrderList(req.getAcctUsername(), 40, 7, destAccountId);
                break;
            case 23: //如果是咪站
                mobileSignIn.setAcctUsername(req.getAcctUsername());
                mobileSignIn.setCompanyAcctUsername(req.getCompanyAcctUsername());
                mobileSignIn.setMiStationNo(req.getMiStationNo());
                mobileSignIn.setScanTime(new Date());
                mobileSignIn.setQrCodeInfo(req.getQrCodeInfo());
                mobileSignIn.setDestAccountId(destAccountId);
                mbfList = mobileBookingFormDaoEx.querySignInOrderList(req.getAcctUsername(), 40, 23, destAccountId);
                break;
            default:
                res.setRetCode(-1);
                res.setRetMsg("角色非法");
                return res;
        }
        //4. 数据库操作
        signIn4DataSource(mobileSignIn, destAccountId, mbfList);
        //5. 通知destAccountId推送消息Bean 备注:destAcctRoles.get(1)为产生二维码的账户的角色Id
        sendMsg2MiOrWa(destAccountId, destAcctIdRoleIds.get(1), req);
        return res;
    }


    /**
     * 发型消息到咪站或者洼站(二维码产生点)
     * @param destAccountId 目的地(咪站或者洼站)账号Id
     * @param roleId 目的地(咪站或者洼站)账号Id
     * @param req 签到请求
     * @throws MobileStationBizException MobileStationBizException
     */
    private void sendMsg2MiOrWa(Integer destAccountId, Integer roleId, SignInReq req) throws MobileStationBizException {
        logger.info("发送消息目的地账户id--{}", destAccountId);
        logger.info("发送消息目的地roleId--{}", roleId);
        logger.info("签到人员签到请求req---{}", JSON.toJSONString(req));
        ComAccount destAcct = comAccountDao.selectByPrimaryKey(destAccountId);//接收方账户
        MsgIMReq msgIMReq = new MsgIMReq();
        msgIMReq.setAccountId(req.getAccountId());//登录账户ID,扫码人账户Id
        msgIMReq.setAcctUsername(req.getAcctUsername());//登录账号,扫码人账号
        msgIMReq.setRemindCode("PUSH_MS_000004");//签到业务标识,特定
        msgIMReq.setCreateUser(destAcct.getAcctUsername());//接收方账号
        Map<String, String> mapObject = new HashMap<>();
        String sysCode;
        String sysTag;
        try {
            if (null != destAcct) {
                if (!StringUtil.isEmpty(destAcct.getRealName())) {
                    mapObject.put("realName", destAcct.getRealName());
                } else {
                    throw new MobileStationBizException("推送人姓名为空");
                }
                mapObject.put("acctUsername", destAcct.getAcctUsername());
                mapObject.put("userImg", destAcct.getUserImg());
                mapObject.put("tel", destAcct.getTelephone());
                mapObject.put("accountId", destAcct.getId().toString());
            } else {
                throw new MobileStationBizException("推送人账户不存在");
            }
            mapObject.put("msgTime", DateUtil.formatDate2Str(new Date(), DateUtil.C_TIME_PATTON_DEFAULT));
            // 根据角色推送消息(23:咪站,5:洼站)
            if (roleId == 23) {
                sysTag = CustomerDefine.IM_MS_DEFINE;//"0001"代表 咪站标识
                sysCode = "SYS_1006"; // "SYS_1006"代表 咪站系统码
                mapObject.put("registerType", MobileStationDefine.OPERATOR_MSTATION);//注册类型咪站
            } else if (roleId == 5) {
                sysTag = CustomerDefine.IM_WA_DEFINE;//"0009"代表 洼站标识
                sysCode = "SYS_1008";//"SYS_1008"代表 洼站系统码
                mapObject.put("registerType", MobileStationDefine.OPERATOR_WSTATION);//注册类型咪站
            } else {
                throw new MobileStationBizException("推送人角色出错");
            }
            msgIMReq.setMapObject(mapObject);
            logger.info("签到人员发送消息消息体-mapObject{}", JSON.toJSONString(mapObject));
            ImPushUtil.sendMessageIM(sysCode, destAcct.getAcctUsername(), sysTag,
                    msgIMReq.getRemindCode(), msgIMReq.getMapObject());
        } catch (MobileStationBizException e) {
            logger.info("推送IM消息参数错误：" + e.getMessage());
            throw new MobileStationBizException("推送IM消息出错");
        }

    }


    /**
     * 签到操作中数据库操作
     * @param mobileSignIn 签到数据Bean
     * @param destAccountId 目的地账户id
     * @param mbfList 订单信息集合
     * @throws MobileStationBizException msbe(业务异常)
     */
    private void signIn4DataSource(MobileSignIn mobileSignIn, Integer destAccountId, List<MobileBookingForm> mbfList) throws MobileStationBizException {
        logger.info("签到操作中数据库操作签到mobileSignIn:{}", JSON.toJSONString(mobileSignIn));
        logger.info("签到操作中数据库操作--目的地destAccountId{}", destAccountId);
        logger.info("签到操作中数据库操作--mbfList{}", JSON.toJSONString(mbfList));
        //1. 查询是否已签到
        MobileSignIn mobileSignInModel = mobileSignInDaoEx.selectMsiByModel(mobileSignIn);
        if (mobileSignInModel != null) {
            throw new MobileStationBizException("二维码失效，请重新生成签到二维码");
        }
        //2. 插入签到信息
        mobileSignInDao.insert(mobileSignIn);
        //3. 批量更新mobileBookingForm插入签到时间
        if (CollectionUtils.isNotEmpty(mbfList)) {
            BatchUpMBF batchUpMBF = new BatchUpMBF();
            List<Integer> idList = new ArrayList<>();
            for (MobileBookingForm mbf : mbfList) {
                idList.add(mbf.getId());
            }
            batchUpMBF.setIdList(idList);
            batchUpMBF.setSignInTime(new Date());
            mobileBookingFormDaoEx.batchUpdateSignInOrder(batchUpMBF);
            logger.info("签到操作中,订单表更新完成-successful!!");
        }
        //4.更新流水号
        try {
            SerialNoRedisBean snrb = (SerialNoRedisBean) redisTemplate.opsForHash().get(REDIS_SIGN_IN_KEY, destAccountId);
            if (snrb != null && StringUtils.isNotBlank(snrb.getSerialNumber())) {
                snrb.setSerialNumber(getSerialRedisNumber(snrb.getSerialNumber()));
                redisTemplate.opsForHash().put(REDIS_SIGN_IN_KEY, destAccountId, snrb);
                logger.info("签到操作中redis数据库操作--SerialNumber{}", snrb.getSerialNumber());
            }
        } catch (Exception e) {
            logger.error("流水号自增操作出现异常-{}", e.getMessage());
            throw new MobileStationBizException("流水号自增出错");
        }
        logger.info("签到操作中数据库操作,mysql,redis (to the end)-successful!");
    }


    /**
     * 获取目的地账户id查询可以签到的订单列表
     * @param qrCodeInfo 二维码信息
     * @return 目的地账户id和目的地角色id
     * @throws MobileStationBizException MobileStationBizException
     */
    private List<Integer> getDestAcctIdRoleId(String qrCodeInfo, MobileSignIn mobileSignIn) throws MobileStationBizException {
        List<Integer> destAcctRoles = new ArrayList<>();
        logger.info("二维码信息内容-qrCodeInfo:-{}", qrCodeInfo);
        String qrCodeAcctIdStr;//二维码内容中的账户id
        String qrCodeRoleIdStr;//二维码内容中的账户roleId
        int destAccountId;//更新mobileBookingForm表需要的账户id
        int destRoleId;//通知消息需要的角色id
        int acctIndex = qrCodeInfo.indexOf("-");
        if (acctIndex == -1) {
            logger.error("二维码内容格式出错,qrCodeInfo-{}", qrCodeInfo);
            throw new MobileStationBizException("签到出错");
        }
        int roleIndex = qrCodeInfo.indexOf("|");
        if (roleIndex == -1) {
            logger.error("二维码内容格式出错,qrCodeInfo-{}", qrCodeInfo);
            throw new MobileStationBizException("签到出错");
        }
        try {
            if (qrCodeInfo.indexOf("-") == qrCodeInfo.lastIndexOf("-")) {
                qrCodeAcctIdStr = qrCodeInfo.substring(++acctIndex);
            } else {
                qrCodeAcctIdStr = qrCodeInfo.substring(++acctIndex, qrCodeInfo.lastIndexOf("-"));
            }
            logger.info("二维码信息中-qrCodeAcctIdStr-{}", qrCodeAcctIdStr);
            destAccountId = Integer.parseInt(qrCodeAcctIdStr);
            qrCodeRoleIdStr = qrCodeInfo.substring(++roleIndex, --acctIndex);
            destRoleId = Integer.parseInt(qrCodeRoleIdStr);
            destAcctRoles.add(destAccountId);
            destAcctRoles.add(destRoleId);
        } catch (Exception e) {
            logger.error("获取二维码源账户id出错-e:{}", e.getMessage());
            throw new MobileStationBizException("签到出错");
        }
        SerialNoRedisBean snrb = (SerialNoRedisBean) redisTemplate.opsForHash().get(REDIS_SIGN_IN_KEY, destAccountId);
        logger.error("获取redis中签到信息-SerialNoRedisBean:{}", JSON.toJSONString(snrb));
        if (snrb == null || StringUtils.isBlank(snrb.getSerialNumber())) {
            logger.error("获取二维码redis出错");
            throw new MobileStationBizException("二维码失效，请重新生成签到二维码");
        }
        if (qrCodeInfo.substring(6, 6 + snrb.getSerialNumber().length()).indexOf(snrb.getSerialNumber()) == -1) {
            logger.info("二维码失效，请重新生成签到二维码-{}", destAccountId);
            throw new MobileStationBizException("二维码失效，请重新生成签到二维码");
        }
        String qrCompanyAcct = qrCodeInfo.substring(6 + snrb.getSerialNumber().length(), qrCodeInfo.indexOf("|"));
        ComAccount companyComAccount = comAccountDaoEx.queryByAccount(qrCompanyAcct);
        if (companyComAccount != null) {
            mobileSignIn.setMwcompanyAcctUsername(companyComAccount.getAcctUsername());
        }
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(destAccountId);
        if (comAccount != null) {
            mobileSignIn.setMwAcctUsername(comAccount.getAcctUsername());
        }
        logger.info("二维码信息中-destAccountId-{}", destAccountId);
        logger.info("二维码信息中-destRoleId----{}", destRoleId);
        return destAcctRoles;
    }


    /**
     * 签到成功后,主动将流水号加1
     * @param serialNumber serialNumber
     * @return 新的流水号
     * @throws MobileStationBizException MobileStationBizException
     */
    private String getSerialRedisNumber(String serialNumber) throws MobileStationBizException {
        logger.info("签到成功主动将流水号加1,流水号初始-{}", serialNumber);
        String serialRedisNumber;
        Integer number = Integer.parseInt(serialNumber);
        if (number < 99) {
            serialRedisNumber = serialFormat2.format(++number);
        } else if (number < 999) {
            serialRedisNumber = serialFormat3.format(++number);
        } else if (number < 9999) {
            serialRedisNumber = serialFormat4.format(++number);
        } else if (number < 99999) {
            serialRedisNumber = serialFormat5.format(++number);
        } else {
            throw new MobileStationBizException("签到次数达到上限");
        }
        logger.info("签到成功主动将流水号加1,流水号最终-{}", serialRedisNumber);
        return serialRedisNumber;
    }

    /**
     * 校验签到的参数信息
     * @param req signInReq
     */
    private void validateSIParam4Req(SignInReq req) throws MobileStationBizException {

        logger.info("签到校验参数开始--req--begin");
        if (req == null) {
            throw new MobileStationBizException("请求参数非法");
        }
        if (req.getRoleId() == null) {
            throw new MobileStationBizException("操作人员角色参数非法");
        }
        if (StringUtils.isBlank(req.getAcctUsername())) {
            throw new MobileStationBizException("操作人员账号参数非法");
        }
        if (StringUtils.isBlank(req.getQrCodeInfo())) {
            throw new MobileStationBizException("二维码参数非法");
        }
        logger.info("签到校验参数结束--req--end--successful");
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public QRCodeRes getQRCodeInfo(QRCodeReq req) throws MobileStationBizException {
        validateQRParam4Req(req);
        logger.info("获取二维码内容信息--req-start");
        QRCodeRes res = new QRCodeRes(req);
        Object obj = redisTemplate.opsForHash().get(REDIS_SIGN_IN_KEY, req.getAccountId());
        String serialNumber;
        SerialNoRedisBean serialNoRedisBean;
        try {
            if (obj == null) {
                serialNoRedisBean = new SerialNoRedisBean();
                serialNoRedisBean.setAcctUserName(req.getAcctUsername());
                serialNoRedisBean.setAccountId(req.getAccountId());
                serialNoRedisBean.setSerialNumber("00");
                serialNoRedisBean.setSerialDate(sdf.format(Calendar.getInstance().getTime()));
                serialNumber = "00";
                redisTemplate.opsForHash().put(REDIS_SIGN_IN_KEY, serialNoRedisBean.getAccountId(), serialNoRedisBean);
            } else {
                serialNoRedisBean = (SerialNoRedisBean) obj;
                if (StringUtils.isNotBlank(serialNoRedisBean.getSerialDate()) && !serialNoRedisBean.getSerialDate().equals(sdf.format(Calendar.getInstance().getTime()))) {
                    redisTemplate.opsForHash().delete(REDIS_SIGN_IN_KEY, serialNoRedisBean.getAccountId());
                    serialNoRedisBean = new SerialNoRedisBean();
                    serialNoRedisBean.setAcctUserName(req.getAcctUsername());
                    serialNoRedisBean.setAccountId(req.getAccountId());
                    serialNoRedisBean.setSerialNumber("00");
                    serialNumber = "00";
                    serialNoRedisBean.setSerialDate(sdf.format(Calendar.getInstance().getTime()));
                    redisTemplate.opsForHash().put(REDIS_SIGN_IN_KEY, serialNoRedisBean.getAccountId(), serialNoRedisBean);
                } else {
                    serialNumber = serialNoRedisBean.getSerialNumber();
                }
            }
        } catch (Exception e) {
            logger.info("获取二维码内容信息--出现异常-{}", e.getMessage());
            res.setRetCode(-1);
            res.setRetMsg("获取二维码失败");
            return res;
        }
        String acctUserName = req.getAcctUsername();
        String dateFormat = sdf.format(Calendar.getInstance().getTime());
        Integer accountId = req.getAccountId();
        ComAccount comAccount = null;
        if (req.getRoleId() == 5) {
            comAccount = comAccountDao.selectByPrimaryKey(req.getCompanyAccountId());
        }
        if (req.getRoleId() == 23) {
            comAccount = comAccountDao.selectByPrimaryKey(accountId);
        }
        res.setQrCodeAccountId(accountId);
        res.setQrCodeAcctUserName(acctUserName);
        res.setDateFormat(dateFormat);
        res.setSerialNumber(serialNumber);
        StringBuffer sb = new StringBuffer();
        sb.append(dateFormat).append(serialNumber).append(acctUserName).append("|").append(req.getRoleId()).append("-").append(accountId);
        if (comAccount != null && StringUtils.isNotBlank(comAccount.getRealName())) {
            sb.append("-").append(comAccount.getRealName());
        }
        res.setQrCodeInfo(sb.toString());
        try {
            res.setData(sb.toString());
        } catch (Exception e) {
            logger.error("EncryptUtil.aesEncrypt出现异常-{}", e.getMessage());
            res.setRetCode(-1);
            res.setRetMsg("获取二维码失败");
            return res;
        }
        logger.info("获取二维码内容信息---end-二维码内容是-{}", sb.toString());
        return res;
    }


    /**
     * 生成二维码请求参数
     * @param req QRCodeReq
     */
    private void validateQRParam4Req(QRCodeReq req) throws MobileStationBizException {

        logger.info("生成二维码请求校验参数开始--req--begin");
        if (req == null) {
            throw new MobileStationBizException("请求参数非法");
        }
        if (req.getRoleId() == null) {
            throw new MobileStationBizException("操作人员角色参数非法");
        }
        if (StringUtils.isBlank(req.getAcctUsername())) {
            throw new MobileStationBizException("操作人员账号参数非法");
        }
        if (req.getAccountId() == null) {
            throw new MobileStationBizException("操作人员账号Id参数非法");
        }
        ComAccount comAccount = comAccountDaoEx.queryByAccount(req.getAcctUsername());
        if (comAccount == null) {
            throw new MobileStationBizException("操作人员账号不存在");
        }
        if (req.getRoleId() != 5 && req.getRoleId() != 23) {
            throw new MobileStationBizException("操作人员角色参数非法");
        }
        logger.info("生成二维码请求校验参数结束--req--end--successful");
    }


}
