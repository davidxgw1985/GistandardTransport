package com.gistandard.transport.dubbo.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.dubbo.register.bean.*;
import com.gistandard.transport.app.dubbo.register.service.RegisterDubboService;
import com.gistandard.transport.applytobe.define.ApplyState;
import com.gistandard.transport.applytobe.define.RequestTypeEnum;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountryService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.dubbo.dao.RequestDubboDao;
import com.gistandard.transport.register.define.RegisterDefine;
import com.gistandard.transport.register.util.UploadToPsc;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.common.bean.*;
import com.valueplus.psc.dubbo.service.common.define.RecordOperatorTypeEnum;
import com.valueplus.psc.dubbo.service.ocr.OcrService;
import com.valueplus.psc.dubbo.service.ocr.bean.IdentityBean;
import com.valueplus.psc.dubbo.service.ocr.bean.OcrParamBean;
import com.valueplus.psc.dubbo.service.ocr.define.OcrType;
import com.valueplus.psc.dubbo.service.record.PscRecordService;
import com.valueplus.psc.dubbo.service.record.bean.ManagerInfoRelativeBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuming on 2017/4/6.
 */
public class RegisterDubboServiceImpl implements RegisterDubboService {
    private final static Logger LOG = LoggerFactory.getLogger(RegisterDubboServiceImpl.class);

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private PscRecordService pscRecordService;

    @Autowired
    private ComUserinfoDao comUserinfoDao;

    @Autowired
    private ComAccountRoleRelDao comAccountRoleRelDao;

    @Autowired
    private ComAccessoryRelationDaoEx comAccessoryRelationDaoEx;

    @Autowired
    private BizAttachmentDao bizAttachmentDao;

    @Autowired
    private BizAttachmentDaoEx bizAttachmentDaoEx;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private ComCountyService comCountyService;

    @Autowired
    private ComCountryService countryService;

    @Autowired
    private ComProvinceService provinceService;

    @Autowired
    private ComCityService cityService;

    @Autowired
    private ComCountyService countyService;

    @Autowired
    private OcrService ocrService;

    @Autowired
    private BizAttachmentRecordDao bizAttachmentRecordDao;

    @Autowired
    private BizAttachmentRecordDaoEx bizAttachmentRecordDaoEx;

    @Autowired
    private ComCustomerDao comCustomerDao;

    @Autowired
    private ComCustomerRelationDao comCustomerRelationDao;

    @Autowired
    private ComAccountRequestDao comAccountRequestDao;

    @Autowired
    private RequestDubboDao requestDubboDao;

    @Autowired
    private ComCompanyStaffDaoEx comCompanyStaffDaoEx;

    @Autowired
    private ComAccountRequestDaoEx comAccountRequestDaoEx;

    @Value("#{spring.fastDFSUrl}")
    private String fastDFSUrl;

    private final String WeChat ="0900e0298e024f6aadcec1e53673698c";

    /**
     * 上传文件
     * @param regBizAttachmentRecord
     * @return
     * @throws Exception
     */
    @Override
    public RegResultBean registerFileUpload(RegBizAttachmentRecord regBizAttachmentRecord) throws Exception {
        RegResultBean resultBean = new RegResultBean();
        BizAttachmentRecord bizAttachmentRecord = new BizAttachmentRecord();
        BeanUtils.copyProperties(bizAttachmentRecord, regBizAttachmentRecord);
        if (!StringUtil.isEmpty(regBizAttachmentRecord.getUploadUser())){
            bizAttachmentRecordDaoEx.deleteByUploadUser(regBizAttachmentRecord.getUploadUser());
        }
        bizAttachmentRecordDao.insert(bizAttachmentRecord);
        resultBean.setState(bizAttachmentRecord.getAttachId() != null);
        resultBean.setData(bizAttachmentRecord.getAttachId());
        return resultBean;
    }

    /**
     * 个人注册
     * @param accountRegisterBean
     * @param personalRegister
     * @param o2IdRegisterBean
     * @throws Exception
     */
    @Override
    public RegResultBean normalPersonalSave(RegAccountRegisterBean accountRegisterBean,RegNormalPersonalRegisterBean personalRegister,
                                            RegO2IdRegisterBean o2IdRegisterBean) throws Exception {
        // 注册相关数据
        RegResultBean resultBean = new RegResultBean();
        RecordInfoBean recordInfoBean = new RecordInfoBean();
        recordInfoBean.setRecordType(RecordOperatorTypeEnum.ADD.getType());
        recordInfoBean.setAcctType(SysAccountRole.NORMAL_PERSONAL.getRoleCode());
        //recordInfoBean.setIdentityNo(personalRegister.getIdentno().trim());
        // 用于生成帐号的数据
        GistandardNoBean gistandardNoBean = new GistandardNoBean();
        gistandardNoBean.setCountryCode(accountRegisterBean.getArea());
        gistandardNoBean.setTelCode(accountRegisterBean.getTelCode());
        gistandardNoBean.setCityId(NumberUtils.toInt(accountRegisterBean.getProvinceAndCity_sel()));
        gistandardNoBean.setUserNo(o2IdRegisterBean.getUserNo());
        gistandardNoBean.setAccountVerifyUUID(o2IdRegisterBean.getAccountId());
        recordInfoBean.setGistandardNoBean(gistandardNoBean);

        // 帐号相关信息
        AccountInfo accountInfo = new AccountInfo();
        // 转换省市区县信息
        transAddress(accountRegisterBean, accountInfo);
        accountInfo.setAcctType(accountRegisterBean.getAcctType());
        //accountInfo.setRealName(personalRegister.getRealName().trim());
        accountInfo.setTelPrefix(accountRegisterBean.getTelPrefix());
        accountInfo.setTelephone(accountRegisterBean.getTelephone().trim());
        accountInfo.setAccountTypes(SysAccountRole.NORMAL_PERSONAL.getValue() + "");
        accountInfo.setRegisterType(RegisterDefine.REGISTER_USER_TYPE);
        accountInfo.setAcctPassword(o2IdRegisterBean.getAcctPassword());
        accountInfo.setShareStatus(true);
        if(accountRegisterBean.getRecommendAccountId() != null){
            ComAccount recommendAccount = comAccountDao.selectByPrimaryKey(accountRegisterBean.getRecommendAccountId());
            if(recommendAccount!=null && !StringUtil.isEmpty(recommendAccount.getId())){
                accountInfo.setRecommendAccountId(recommendAccount.getId());
                accountInfo.setRecommendFrom(recommendAccount.getRecommendFrom());
            }
        }
        accountInfo.setRecommendAccountId(accountRegisterBean.getRecommendAccountId());
        accountInfo.setRecommendFrom(accountRegisterBean.getRecommendFrom());
        recordInfoBean.setAccountInfo(accountInfo);

        // 用户信息数据

        recordInfoBean.setUserPropertyList(buildRecordUserInfo(accountRegisterBean, personalRegister));
        // 附件
        /*List<Integer> idList = new ArrayList<Integer>();
        idList.add(personalRegister.getIdentityPositiveFileId());
        idList.add(personalRegister.getIdentityNegativeFileId());
        idList.add(personalRegister.getIdentityHalfFileId());
        recordInfoBean.setAttachmentList(buildAttachmentInfo(idList));*/
        ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
        serviceAuthBean.setAuthPwd(RegisterDefine.TRANSPORT_AUTH_PWD);
        serviceAuthBean.setAuthUser(RegisterDefine.TRANSPORT_AUTH_USER);
        serviceAuthBean.setSysFlag(WeChat);
        ServiceResult result = pscRecordService.recordOnce(serviceAuthBean, recordInfoBean);
        LOG.info("个人注册返回结果：{}", JSON.toJSONString(result));
        resultBean.setState(result.isResult());
        resultBean.setMessage(result.getMsg());
        resultBean.setData(result.getData());
        if(result.isResult()){
            ComAccount comAccount = new ComAccount();
            AccountInfo accountInfoResult = (AccountInfo)result.getData();
            PropertyUtils.copyProperties(comAccount, accountInfoResult);
            comAccount.setId(accountInfoResult.getAccountId());
            comAccount.setCountryId(Integer.valueOf(accountRegisterBean.getCountry()));
            comAccount.setProvinceId(Integer.valueOf(accountRegisterBean.getProvince()));
            comAccount.setCityId(Integer.valueOf(accountRegisterBean.getCity()));
            comAccount.setCountyId(Integer.valueOf(accountRegisterBean.getCounty()));
            comAccount.setShareStatus(true);
            try {
                comAccountDao.insert(comAccount);
                ComUserinfo comUserinfo = new ComUserinfo();
                // 复制请求对象中属性到人员信息对象中
                comUserinfo.setAccountId(comAccount.getId());
                comUserinfo.setTelephone(accountRegisterBean.getTelephone().trim());
                //comUserinfo.setIdentno(personalRegister.getIdentno().trim());
                //comUserinfo.setRealName(comAccount.getRealName().trim());
                comUserinfoDao.insert(comUserinfo);
                LOG.info("微信注册，运输平台数据库普通人员记录：{}", JSON.toJSONString(comUserinfo));

                // 账号角色关联表
                ComAccountRoleRel comAccountRoleRel = new ComAccountRoleRel();
                comAccountRoleRel.setAccountId(comAccount.getId());
                comAccountRoleRel.setRoleId(SysAccountRole.NORMAL_PERSONAL.getValue());
                comAccountRoleRel.setRelId(comUserinfo.getId());
                comAccountRoleRelDao.insert(comAccountRoleRel);
                //insertBizAttachment(comAccount.getId(),idList);
            }catch (Exception e){
                LOG.error("微信注册异常：{}",e.getMessage());
            }finally {
                return resultBean;
            }
        }
        return resultBean;
    }

    /**
     * 插入附件信息
     * @throws Exception
     */
    private void insertBizAttachment(Integer accountId,List<Integer> idList) throws Exception{
        List<ComAccessoryRelation> accessoryRelationList = new ArrayList<ComAccessoryRelation>();
        List<Integer> attachmentList = new ArrayList<Integer>();
        for (Integer id : idList){
            BizAttachmentRecord attachmentRecord = bizAttachmentRecordDao.selectByPrimaryKey(id);
            BizAttachment attachment = new BizAttachment();
            PropertyUtils.copyProperties(attachment, attachmentRecord);
            attachment.setAttachId(null);
            attachment.setUploadUser(accountId);
            bizAttachmentDao.insert(attachment);
            attachmentList.add(attachment.getAttachId());
            bizAttachmentRecordDao.deleteByPrimaryKey(id);
        }

        if (accessoryRelationList.size() > 0) {
            comAccessoryRelationDaoEx.deleteByCond(accountId,"COM_ACCOUNT",null);
            bindRelIdAndAttachRelation(accessoryRelationList, attachmentList, accountId, "COM_ACCOUNT");
            // 插入数据到附件关联表
            comAccessoryRelationDaoEx.batchInsert(accessoryRelationList);
        }
    }

    /**
     * 身份证验证正面
     * @param identityPositiveFileId
     * @param area
     */
    @Override
    public RegResultBean ocrRetriveIdentityInfo(Integer identityPositiveFileId,String area) {
        BizAttachmentRecord bizAttachmentRecord = bizAttachmentRecordDao.selectByPrimaryKey(identityPositiveFileId);
        RegResultBean resultBean = new RegResultBean();
        try{
            URL url = new URL(bizAttachmentRecord.getAttachDomain() + bizAttachmentRecord.getAttachPath());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            int len = 0;
            while( (len=inStream.read(buffer)) != -1 ){
                outStream.write(buffer, 0, len);
            }
            inStream.close();
            OcrParamBean ocrParamBean = new OcrParamBean();
            ocrParamBean.setPicArr(outStream.toByteArray());
            if (area.contains("CN")){
                ocrParamBean.setTypeId(OcrType.SECOND_IDENTITY_CARD_POSITIVE.getValue());
            } else if (area.contains("TW")) {
                ocrParamBean.setTypeId(OcrType.TW_IDENTITY_CARD_POSITIVE.getValue());
            } else if(area.contains("HK")){
                ocrParamBean.setTypeId(OcrType.HK_IDENTITY_CARD.getValue());
            }
            ocrParamBean.setExtensionType(bizAttachmentRecord.getAttachName().substring(bizAttachmentRecord.getAttachName().indexOf(".")));

            ServiceResult<IdentityBean> result = ocrService.ocrRetriveIdentityInfo(RegisterDefine.buildTransportAuthInfo(), ocrParamBean);
            LOG.info("身份证正面识别结果：{}", result.isResult());
            if(result.isResult()){
                resultBean.setState(true);
                RegOcrResultBean ocrResultBean = new RegOcrResultBean();
                IdentityBean identityBean = (IdentityBean)result.getData();
                ocrResultBean.setIdentno(identityBean.getIdentityNo());
                ocrResultBean.setRealName(identityBean.getUserName());
                LOG.info("身份证正面识别结果：身份证号码{}，姓名{}", identityBean.getIdentityNo(), identityBean.getUserName());
                resultBean.setData(ocrResultBean);
            }else{
                resultBean.setState(false);
            }
        }catch(Exception e){
            resultBean.setState(false);
        }
        return resultBean;
    }

    /**
     * 身份证验证反面
     * @param identityNegativeFileId
     * @param area
     */
    @Override
    public RegResultBean ocrVerifyIdentityBack(Integer identityNegativeFileId,String area) {
        BizAttachmentRecord bizAttachmentRecord = bizAttachmentRecordDao.selectByPrimaryKey(identityNegativeFileId);
        RegResultBean resultBean = new RegResultBean();
        try{
            URL url = new URL(bizAttachmentRecord.getAttachDomain() + bizAttachmentRecord.getAttachPath());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            int len = 0;
            while( (len=inStream.read(buffer)) != -1 ){
                outStream.write(buffer, 0, len);
            }
            inStream.close();

            OcrParamBean ocrParamBean = new OcrParamBean();
            ocrParamBean.setPicArr(outStream.toByteArray());
            ocrParamBean.setExtensionType(bizAttachmentRecord.getAttachName().substring(bizAttachmentRecord.getAttachName().indexOf(".")));
            if (area.contains("CN")){
                ocrParamBean.setTypeId(OcrType.SECOND_IDENTITY_CARD_NEGATIVE.getValue());
                ServiceResult<IdentityBean> result = ocrService.ocrVerifyIdentityBack(RegisterDefine.buildTransportAuthInfo(), ocrParamBean);
                LOG.info("身份证反面识别结果：{}",JSON.toJSONString(result));
                resultBean.setState(result.isResult());
            } else if (area.contains("TW")) {
                ocrParamBean.setTypeId(OcrType.TW_IDENTITY_CARD_NEGATIVE.getValue());
                ServiceResult<IdentityBean> result = ocrService.ocrVerifyIdentityBack(RegisterDefine.buildTransportAuthInfo(), ocrParamBean);
                LOG.info("身份证反面识别结果：{}",JSON.toJSONString(result));
                resultBean.setState(result.isResult());
            } else if(area.contains("HK")){
                resultBean.setState(true);
            }
        }catch(Exception e){
            resultBean.setState(false);
        }
        return resultBean;
    }

    @Override
    public RegResultBean normalCompanySave(RegAccountRegisterBean accountRegisterBean, RegNormalCompanyRegisterBean companyRegister, RegO2IdRegisterBean o2IdRegisterBean) throws Exception {
        // 注册相关数据
        RecordInfoBean recordInfoBean = new RecordInfoBean();
        recordInfoBean.setRecordType(RecordOperatorTypeEnum.ADD.getType());
        recordInfoBean.setAcctType(SysAccountRole.NORMAL_COMPANY.getRoleCode());
        recordInfoBean.setCompanyBizNo(companyRegister.getCustComno().trim());

        // 用于生成帐号的数据
        GistandardNoBean gistandardNoBean = new GistandardNoBean();
        gistandardNoBean.setCountryCode(accountRegisterBean.getArea());
        gistandardNoBean.setTelCode(accountRegisterBean.getTelCode());
        gistandardNoBean.setCityId(NumberUtils.toInt(accountRegisterBean.getProvinceAndCity_sel()));
        gistandardNoBean.setUserNo(o2IdRegisterBean.getUserNo());
        gistandardNoBean.setAccountVerifyUUID(o2IdRegisterBean.getAccountId());
        recordInfoBean.setGistandardNoBean(gistandardNoBean);

        // 帐号相关信息
        AccountInfo accountInfo = new AccountInfo();
        // 转换省市区县信息
        transAddress(accountRegisterBean, accountInfo);
        accountInfo.setAcctType(accountRegisterBean.getAcctType());
        accountInfo.setUserImg(companyRegister.getUserImg());
        accountInfo.setRealName(companyRegister.getRealName().trim());
        accountInfo.setEmail(accountRegisterBean.getRegEmail());
        if(accountRegisterBean.getRecommendAccountId() != null){
            ComAccount recommendAccount = comAccountDao.selectByPrimaryKey(accountRegisterBean.getRecommendAccountId());
            if(recommendAccount!=null && !StringUtil.isEmpty(recommendAccount.getId())){
                accountInfo.setRecommendAccountId(recommendAccount.getId());
                accountInfo.setRecommendFrom(recommendAccount.getRecommendFrom());
            }
        }
        accountInfo.setAccountTypes(SysAccountRole.NORMAL_COMPANY.getValue()+"");
        accountInfo.setRegisterType(accountRegisterBean.getRegisterType());
        accountInfo.setAcctPassword(o2IdRegisterBean.getAcctPassword());
        recordInfoBean.setAccountInfo(accountInfo);

        if(accountRegisterBean.getParentAccountId() != null){
            SubAccountInfo subAccountInfo = new SubAccountInfo();
            subAccountInfo.setParentAccountId(accountRegisterBean.getParentAccountId());
            subAccountInfo.setCreateAccountId(accountRegisterBean.getCreateAccountId());
            recordInfoBean.setSubAccountInfo(subAccountInfo);
        }

        // 企业信息数据
        recordInfoBean.setCompanyPropertyList(buildRecordCompanyInfo(accountRegisterBean, companyRegister));

        // 附件
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(companyRegister.getLicenseFileId());
        idList.add(companyRegister.getPortraitFileId());
        recordInfoBean.setAttachmentList(buildAttachmentInfo(idList));

        recordInfoBean.setManagerInfoRelativeBeanList(companyRegister.getManagers());

        ServiceResult result = pscRecordService.recordOnce(RegisterDefine.buildTransportAuthInfo(),recordInfoBean);
        if(RegisterDefine.REGISTER_SERVICE_CENTER_TYPE.equals(accountRegisterBean.getRegisterType())){
            LOG.info("业务中心注册返回结果：{}", JSON.toJSONString(result));
        }else{
            LOG.info("企业注册返回结果：{}", JSON.toJSONString(result));
        }
        if(result.isResult()) {
            ComAccount comAccount = new ComAccount();
            AccountInfo accountInfoResult = (AccountInfo)result.getData();
            PropertyUtils.copyProperties(comAccount, result.getData());
            comAccount.setId(accountInfoResult.getAccountId());
            comAccountDao.insert(comAccount);

            ComCustomer comCustomer = new ComCustomer();
            PropertyUtils.copyProperties(comCustomer, accountRegisterBean);
            comCustomer.setAccountId(comAccount.getId());
            comCustomer.setCustComno(companyRegister.getCustComno().trim());
            comCustomer.setCustName(companyRegister.getRealName().trim());
            comCustomerDao.insert(comCustomer);
            if(RegisterDefine.REGISTER_SERVICE_CENTER_TYPE.equals(accountRegisterBean.getRegisterType())){
                LOG.info("运输平台数据库业务中心企业记录：{}", JSON.toJSONString(comCustomer));
            }else{
                LOG.info("运输平台数据库企业记录：{}", JSON.toJSONString(comCustomer));
            }

            // 账号角色关联表
            ComAccountRoleRel comAccountRoleRel = new ComAccountRoleRel();
            comAccountRoleRel.setAccountId(comAccount.getId());
            comAccountRoleRel.setRoleId(SysAccountRole.NORMAL_COMPANY.getValue());
            comAccountRoleRel.setRelId(comCustomer.getId());
            comAccountRoleRelDao.insert(comAccountRoleRel);

            // 企业父子关系关联表
            ComCustomerRelation comCustomerRelation = new ComCustomerRelation();
            comCustomerRelation.setCustomId(comCustomer.getId());
            comCustomerRelation.setParentCustomId(RegisterDefine.CUSTOMER_PARENT_ROOT_ID);
            comCustomerRelation.setParentPath(StringUtil.getObjStr(comCustomer.getId()));
            comCustomerRelationDao.insert(comCustomerRelation);

            List<ComAccessoryRelation> relationList = new ArrayList<ComAccessoryRelation>();
            List<Integer> attachIdList = new ArrayList<Integer>();
            if (companyRegister.getLicenseFileId() != null) {
                attachIdList.add(companyRegister.getLicenseFileId());
            }
            if (companyRegister.getPortraitFileId() != null) {
                attachIdList.add(companyRegister.getPortraitFileId());
            }
            // 附件表关联的表是企业信息表
            if (attachIdList.size() > 0) {
                comAccessoryRelationDaoEx.deleteByCond(comAccount.getId(),"COM_ACCOUNT",null);
                bindRelIdAndAttachRelation(relationList, attachIdList, comAccount.getId(), "COM_ACCOUNT");
                comAccessoryRelationDaoEx.batchInsert(relationList);
                bizAttachmentDaoEx.batchUpdateUploadUser(comAccount.getId(), attachIdList);
            }

            if(CollectionUtils.isNotEmpty(companyRegister.getManagers())){
                for(ManagerInfoRelativeBean manager : companyRegister.getManagers()){
                    ComAccount comAccountManager = comAccountDao.queryByAccount(manager.getManagerO2Id());
                    if(comAccountManager != null){
                        ComUserinfo comUserinfo = comUserinfoDao.queryByAcctId(comAccountManager.getId());
                        comUserinfo.setCustomerId(comCustomer.getId());
                        comUserinfoDao.updateByPrimaryKey(comUserinfo);
                    }
                }
            }
        }

        RegResultBean resultBean = new RegResultBean();
        resultBean.setState(result.isResult());
        resultBean.setMessage(result.getMsg());
        resultBean.setData(result.getData());
        return resultBean;
    }

    @Override
    public RegResultBean getTelCode(String selectCityId) {
        String TelCode = null;
        if (!StringUtil.isEmpty(selectCityId)) {
            Map<String, ComCity> cityMap = comCityService.queryForMap();
            ComCity city = cityMap.get(selectCityId);
            if(city != null && !StringUtil.isEmpty(city.getTelCode())){
                TelCode = city.getTelCode();
            }
        }
        if(!StringUtil.isEmpty(TelCode)){
            int length = TelCode.length();
            StringBuffer prefix = new StringBuffer();
            for(int i = 0;i < 5 - length;i++){
                prefix.append("0");
            }
            prefix.append(TelCode);
            TelCode = prefix.toString();
        }
        RegResultBean regResultBean = new RegResultBean();
        regResultBean.setData(TelCode);
        return regResultBean;
    }

    @Override
    public RegResultBean applytoWechatOrOnlineBus(RegComAccountRequest regComAccountRequest) {
        RegResultBean regResultBean = new RegResultBean();
        ComAccountRequest record = new ComAccountRequest();
        if (regComAccountRequest.getRoleId() == 24){
            record.setWechatMerchantAccount(regComAccountRequest.getWechatMerchantAccount());
        }else if (regComAccountRequest.getRoleId() == 25){
            record.setAuthRealName(regComAccountRequest.getAuthRealName());
            record.setAuthIdentityno(regComAccountRequest.getAuthIdentityno());
            record.setShopName(regComAccountRequest.getShopName());
            record.setShopLink(regComAccountRequest.getShopLink());
        }
        record.setRoleId(regComAccountRequest.getRoleId());
        record.setAccountId(regComAccountRequest.getAccountId());
        record.setReqTime(new Date());
        record.setReqStatus(ApplyState.APPLY.getValue());
        record.setReqType(RequestTypeEnum.UPGRADE_TYPE.getTypeCode());
        int result = comAccountRequestDao.insert(record);
        if (result>0)
            regResultBean.setState(true);
        else
            regResultBean.setState(false);
        return regResultBean;
    }

    @Override
    public RegResultBean queryApplytoRequest(Integer accountId) {
        RegResultBean regResultBean = new RegResultBean();
        try {

            ComAccountRequest query = new ComAccountRequest();
            query.setAccountId(accountId);
            query.setReqType(RequestTypeEnum.UPGRADE_TYPE.getTypeCode());
            ComAccountRequest comAccountRequest = comAccountRequestDaoEx.selectByAccountIdAndRoleIdAndStatus(query);
            if (comAccountRequest != null && comAccountRequest.getRoleId() !=24 && comAccountRequest.getRoleId() !=25
                    && comAccountRequest.getReqStatus()!=4 && comAccountRequest.getReqStatus()!=5){
                RegComAccountRequest regComAccountRequest = new RegComAccountRequest();
                BeanUtils.copyProperties(regComAccountRequest, comAccountRequest);
                regResultBean.setState(true);
                regResultBean.setData(regComAccountRequest);
                return regResultBean;
            }
            List<RegComAccountRequest> list = requestDubboDao.queryApplytoByAccountId(accountId);
            if (list.size()>0){
                regResultBean.setState(true);
                regResultBean.setData(list.get(0));
            }else {
                regResultBean.setState(false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return regResultBean;
    }

    @Override
    public RegResultBean queryMangerO2ID(Integer accountId) {
        List<ComAccount> list = comCompanyStaffDaoEx.queryMangerInfo(accountId);
        RegResultBean regResultBean = new RegResultBean();
        if (list.size()>0){
            regResultBean.setState(true);
            ComAccount comAccount = list.get(0);
            RegComAccount regComAccount = new RegComAccount();
            try {
                BeanUtils.copyProperties(regComAccount, comAccount);
            } catch (Exception e) {
                LOG.info("BeanUtils.copyProperties转化失败：{}", JSON.toJSONString(e));
            }
            regResultBean.setData(regComAccount);
        }else {
            regResultBean.setState(false);
        }
        return regResultBean;
    }

    /**
     * 绑定企业数据
     * @param accountRegisterBean
     * @param companyRegister
     * @return
     */
    private List<RecordProperty> buildRecordCompanyInfo(RegAccountRegisterBean accountRegisterBean, RegNormalCompanyRegisterBean companyRegister){
        // 人员备案信息
        List<RecordProperty> companyPropertyList = new ArrayList<RecordProperty>();
        companyPropertyList.add(buildRecordProperty("companyName", companyRegister.getRealName()));
        companyPropertyList.add(buildRecordProperty("companyBizNo", companyRegister.getCustComno()));
        buildAreaProperty(accountRegisterBean, companyPropertyList);
        return companyPropertyList;
    }

    /**
     * 设置附件关联表数据
     * @param relationList
     * @param attachList
     * @param relId
     * @param tableName
     */
    private void bindRelIdAndAttachRelation(java.util.List<ComAccessoryRelation> relationList, java.util.List<Integer> attachList,
                                            Integer relId, String tableName) {
        if (attachList != null && attachList.size() > 0) {
            for (Integer attachId : attachList) {
                ComAccessoryRelation comAccessoryRelation = new ComAccessoryRelation();
                comAccessoryRelation.setAttachId(attachId);
                comAccessoryRelation.settTab(tableName);
                comAccessoryRelation.setRelaId(relId);
                relationList.add(comAccessoryRelation);
            }
        }
    }

    /**
     * 解析省市区
     * @param accountRegisterBean
     */
    private void transAddress(RegAccountRegisterBean accountRegisterBean,AccountInfo accountInfo) {
        // 设置默认国家
        accountRegisterBean.setCountry(RegisterDefine.REGISTER_DEFAULT_COUNTRY);
        accountInfo.setCountryId(RegisterDefine.REGISTER_DEFAULT_COUNTRY_INT);
        // 设置省市区
        if (StringUtils.isEmpty(accountRegisterBean.getProvinceAndCity())) {
            return;
        }
        String[] tempArray = accountRegisterBean.getProvinceAndCity().split("-");
        if (tempArray != null && tempArray.length > 0) {
            // 只选择到城市
            if (tempArray.length == 2) {
                Map<String, ComCity> cityMap = comCityService.queryForMap();
                if (cityMap != null && cityMap.get(accountRegisterBean.getProvinceAndCity_sel()) != null) {
                    ComCity comCity = cityMap.get(accountRegisterBean.getProvinceAndCity_sel());
                    accountRegisterBean.setCity(accountRegisterBean.getProvinceAndCity_sel());
                    accountRegisterBean.setProvince(comCity.getProvinceId().toString());

                    accountInfo.setCityId(comCity.getId());
                    accountInfo.setProvinceId(comCity.getProvinceId());
                }
            } else if (tempArray.length == 3) {
                // 选择到区县
                Map<String, ComCounty> countyMap = comCountyService.queryForMap();
                if (countyMap != null && countyMap.get(accountRegisterBean.getProvinceAndCity_sel()) != null) {
                    ComCounty comCounty = countyMap.get(accountRegisterBean.getProvinceAndCity_sel());
                    accountRegisterBean.setCounty(comCounty.getId().toString());
                    accountRegisterBean.setCity(comCounty.getCityId().toString());
                    accountRegisterBean.setProvince(comCounty.getProvinceId().toString());

                    accountInfo.setCountyId(comCounty.getId());
                    accountInfo.setCityId(comCounty.getCityId());
                    accountInfo.setProvinceId(comCounty.getProvinceId());
                }
            }
        }
    }

    /**
     * 绑定用户数据
     * @param accountRegisterBean
     * @param personalRegister
     * @return
     */
    private java.util.List<RecordProperty> buildRecordUserInfo(RegAccountRegisterBean accountRegisterBean, RegNormalPersonalRegisterBean personalRegister){
        // 人员备案信息
        java.util.List<RecordProperty> userPropertyList = new ArrayList<RecordProperty>();
        /*userPropertyList.add(buildRecordProperty("realName", personalRegister != null ?
                StringUtil.getNotNullStr(personalRegister.getRealName(), "") : ""));
        userPropertyList.add(buildRecordProperty("identno", personalRegister != null ?
                StringUtil.getNotNullStr(personalRegister.getIdentno(), "") : ""));*/
        userPropertyList.add(buildRecordProperty("telephone", accountRegisterBean != null ?
                StringUtil.getNotNullStr(accountRegisterBean.getTelephone(),"") : ""));
        buildAreaProperty(accountRegisterBean, userPropertyList);
        return userPropertyList;
    }

    /**
     * 绑定附件
     * @param idList
     * @return
     */
    private List<AttachmentInfo> buildAttachmentInfo(List<Integer> idList) throws Exception{
        List<AttachmentInfo> attachmentList = new ArrayList<AttachmentInfo>();
        for (Integer id :idList){
            BizAttachmentRecord record = bizAttachmentRecordDao.selectByPrimaryKey(id);
            AttachmentInfo attachmentInfo = UploadToPsc.uploadToPscByRecord(record);
            attachmentInfo.setId(null);
            attachmentInfo.setUploadDomain(fastDFSUrl);
            attachmentList.add(attachmentInfo);

        }
        return attachmentList;
    }

    /**
     * 绑定参数
     * @param propertyCode
     * @param value
     * @return
     */
    private RecordProperty buildRecordProperty(String propertyCode, String value) {
        RecordProperty recordProperty = new RecordProperty();
        recordProperty.setPropertyCode(propertyCode);
        recordProperty.setPropertyValue(value);
        return recordProperty;
    }

    /**
     * 组装国家省市区县信息
     * @param accountRegisterBean
     */
    private void buildAreaProperty(RegAccountRegisterBean accountRegisterBean, java.util.List<RecordProperty> propertyList) {
        // 将国家代码标志转成中文显示
        Map<String, ComCountry> countryMap = countryService.queryForCountryCodeMap();
        if (countryMap != null && countryMap.size() > 0 && countryMap.containsKey(accountRegisterBean.getCountry())) {
            propertyList.add(buildRecordProperty("country",countryMap.get(accountRegisterBean.getCountry()).getCountryNameCh()));
        }

        // 转换省份
        if (StringUtils.isNotEmpty(accountRegisterBean.getProvince())) {
            ComProvince province = provinceService.queryForMap().get(accountRegisterBean.getProvince());
            if (province != null) {
                propertyList.add(buildRecordProperty("province", province.getProvinceName()));
            }
        }

        // 转换城市
        if (StringUtils.isNotEmpty(accountRegisterBean.getCity())) {
            ComCity city = cityService.queryForMap().get(accountRegisterBean.getCity());
            if (city != null) {
                propertyList.add(buildRecordProperty("city", city.getName()));
            }
        }

        // 转换县
        if (StringUtils.isNotEmpty(accountRegisterBean.getCounty())) {
            ComCounty county = countyService.queryForMap().get(accountRegisterBean.getCounty());
            if (county != null) {
                propertyList.add(buildRecordProperty("county", county.getAreaName()));
            }
        }
    }
}
