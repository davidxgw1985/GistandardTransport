package com.gistandard.transport.applytobe.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.applytobe.bean.*;
import com.gistandard.transport.applytobe.define.ApplyState;
import com.gistandard.transport.applytobe.define.RequestTypeEnum;
import com.gistandard.transport.applytobe.service.ApplyToBeService;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SysState;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.BizAttachmentService;
import com.gistandard.transport.register.define.RegisterDefine;
import com.gistandard.transport.register.util.UploadToPsc;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.system.upload.define.UploadFileType;
import com.gistandard.transport.system.upload.service.UploadService;
import com.gistandard.transport.tools.util.StringUtil;
import com.gistandard.transport.webdubbo.auditAccount.bean.AddRequestBean;
import com.valueplus.psc.dubbo.service.common.bean.*;
import com.valueplus.psc.dubbo.service.common.define.RecordOperatorTypeEnum;
import com.valueplus.psc.dubbo.service.record.PscRecordService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by m on 2016/4/18.
 */
@Service
public class ApplyToBeServiceImpl implements ApplyToBeService {

    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(ApplyToBeServiceImpl.class);

    @Autowired
    private ComAccountRequestDaoEx comAccountRequestDaoEx;

    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private BizAttachmentRecordDao bizAttachmentRecordDao;

    @Autowired
    private ComVehicleInfoDaoEx comVehicleInfoDaoEx;

    @Autowired
    private ComUserinfoRecordDao comUserinfoRecordDao;

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private ComUserinfoDao comUserinfoDao;

    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;

    @Autowired
    private ComVehicleInfoRecordDao comVehicleInfoRecordDao;

    @Autowired
    private ComVehicleInfoRecordDaoEx comVehicleInfoRecordDaoEx;

    @Autowired
    private ComDriverInfoRecordDao comDriverInfoRecordDao;

    @Autowired
    private ComVehicleCarriageRecordDao comVehicleCarriageRecordDao;

    @Autowired
    private ComVehicleTrayRecordDao comVehicleTrayRecordDao;

    @Autowired
    private ComAccountRequestDao comAccountRequestDao;

    @Autowired
    private ComCustomerRecordDao comCustomerRecordDao;

    @Autowired
    private ComCustomerRecordDaoEx comCustomerRecordDaoEx;

    @Autowired
    private PscRecordService pscRecordService;

    @Autowired
    private BizAttachmentDao bizAttachmentDao;

    @Autowired
    private BizAttachmentRecordDaoEx bizAttachmentRecordDaoEx;

    @Autowired
    private ComAccessoryRelationDaoEx comAccessoryRelationDaoEx;

    @Value("#{spring.msAppUrl}")
    private String msAppUrl;

    @Value("#{spring.fastDFSUrl}")
    private String fastDFSUrl;

    /**
     * 根据帐户ID查询是否存在申请请求
     * @param accountId
     * @return
     */
    @Override
    public List<ComAccountRequest> queryApplyReqByAccountId(Integer accountId){
        return comAccountRequestDaoEx.queryApplyReqByAccountId(accountId);
    }

    /**
     * 根据企业简称 或 企业编号查找企业信息
     * @param custTtl 企业简称
     * @param customNo 企业编号
     * @return
     */
    @Override
    public List<ComCustomer> queryCompanyByParams(String custTtl, String customNo) {
        return comCustomerDaoEx.queryCompanyByParams(custTtl, customNo);
    }

    /**
     * 企业简称唯一性校验
     * @param custTtl 企业简称
     * @return
     */
    @Override
    public List<ComCustomerRecord> checkCustTtlUnique(String custTtl){
        return comCustomerRecordDaoEx.checkCustTtlUnique(custTtl);
    }

    /**
     * 企业编号唯一性校验
     * @param customNo 企业编号
     * @return
     */
    @Override
    public List<ComCustomerRecord> checkCustomNoUnique(String customNo){
        return comCustomerRecordDaoEx.checkCustomNoUnique(customNo);
    }

    /**
     * 校验车牌是否重复
     * @param truckCode
     * @return
     */
    @Override
    public boolean checkTruckCodeUnique(String truckCode) {
        return CollectionUtils.isEmpty(comVehicleInfoDaoEx.queryVehicleByTruckCode(truckCode));
    }

    /**
     * 备案信息表校验车牌是否重复
     * @param truckCode
     * @return
     */
    @Override
    public boolean checkRecordTruckCodeUnique(String truckCode) {
        return CollectionUtils.isEmpty(comVehicleInfoRecordDaoEx.queryVehicleByTruckCode(truckCode));
    }

    /**
     * 上传文件
     * @param multipartFile
     * @param fileId
     * @return
     * @throws Exception
     */
    @Override
    public ResultBean registerFileUpload(MultipartFile multipartFile, String fileId) throws Exception {
        ResultBean resultBean = new ResultBean();
        int fileType = -1;
        if (RegisterDefine.IDENTITY_POSITIVE_FILE_ID.equals(fileId)
                || RegisterDefine.DRIVER_IDENTITY_POSITIVE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.IDENTITY_POSITIVE.getValue();
        } else if (RegisterDefine.IDENTITY_NEGATIVE_FILE_ID.equals(fileId)
                || RegisterDefine.DRIVER_IDENTITY_NEGATIVE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.IDENTITY_NEGATIVE.getValue();
        } else if (RegisterDefine.PORTRAIT_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.PORTRAIT.getValue();
        } else if (RegisterDefine.LICENSE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.OPERATE_LICENSE.getValue();
        } else if (RegisterDefine.DRIVER_LICENSE_FILE_ID.equals(fileId)
                || RegisterDefine.SECOND_DRIVER_LICENSE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.DRIVER_LICENSE.getValue();
        } else if (RegisterDefine.FIRST_CAR_IMAGE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.CAR_IMAGE.getValue();
        } else if (RegisterDefine.SECOND_CAR_IMAGE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.CAR_IMAGE_2.getValue();
        } else if (RegisterDefine.DRIVING_LICENSE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.DRIVING_IMAGE.getValue();
        } else if (RegisterDefine.TAX_REGISTER_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.TAX_REGISTER.getValue();
        } else if (RegisterDefine.TRANSPORT_AGREE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.TRANSPORT_AGREE.getValue();
        } else if (RegisterDefine.TRANSPORT_BUSINESS_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.TRANSPORT_BUSINESS.getValue();
        }else if (RegisterDefine.ORG_CODE_LILCENSE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.ORG_CODE_LILCENSE.getValue();
        }else if (RegisterDefine.IDENTITY_HALF_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.IDENTITY_HALF.getValue();
        }

        if (fileType == -1) {
            resultBean.setMessage("文件上传异常");
            return resultBean;
        }
        BizAttachment bizAttachment = uploadService.uploadDfsFile(multipartFile, fileType);
        if (bizAttachment == null) {
            resultBean.setMessage("文件上传失败");
            return resultBean;
        }
        BizAttachmentRecord bizAttachmentRecord = new BizAttachmentRecord();
        PropertyUtils.copyProperties(bizAttachmentRecord, bizAttachment);

        bizAttachmentRecord.setAttachId(null);
        bizAttachmentRecord.setAttachState(SysState.USABLE.getValue());
        bizAttachmentRecord.setAttachServerIp(StringUtil.getLocalServerIp());
        bizAttachmentRecord.setUploadTime(new Date());
        bizAttachmentRecord.setAttachDomain(fastDFSUrl);
        bizAttachmentRecordDao.insert(bizAttachmentRecord);

        resultBean.setState(bizAttachmentRecord.getAttachId() != null);
        resultBean.setData(bizAttachmentRecord.getAttachId());
        resultBean.setMessage(fastDFSUrl+bizAttachmentRecord.getAttachPath());

        return resultBean;
    }

    /**
     * 申请成为车主
     * @param carOwnerBean
     * @param accountId
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBean carOwner(MerchantCarOwnerBean carOwnerBean,Integer accountId,AddRequestBean addRequestBean) throws Exception {
        ResultBean resultBean = new ResultBean();
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(accountId);
        ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(accountId);

        // 申请表
        Integer result = insertAccountRequest(accountId, SysAccountRole.OPERATOR_CAR_OWNER.getValue(),addRequestBean,carOwnerBean);
        if(result != null){
            resultBean.setState(true);
            resultBean.setData(result);

            // 用户信息备案表
            ComUserinfoRecord comUserinfoRecord = new ComUserinfoRecord();
            PropertyUtils.copyProperties(comUserinfoRecord, carOwnerBean);
            comUserinfoRecord.setId(null);
            comUserinfoRecord.setAccountId(accountId);
            comUserinfoRecord.setUrgentLinkUser(carOwnerBean.getUrgentLinkUser());
            comUserinfoRecord.setUrgentLinkTel(carOwnerBean.getUrgentLinkTel());
            if (StringUtil.isEmpty(comAccount.getAuthStatus())){
                comUserinfoRecord.setIdentno(carOwnerBean.getIdentno());
                comUserinfoRecord.setRealName(carOwnerBean.getRealName());
            }
            comUserinfoRecordDao.insert(comUserinfoRecord);
            Logger.info("帐号：{},用户信息备案表数据：{}", comAccount.getAcctUsername(),JSON.toJSONString(comUserinfoRecord));

            // 司机信息备案表
            ComDriverInfoRecord comDriverInfoRecord = new ComDriverInfoRecord();
            comDriverInfoRecord.setDriver(comAccount.getRealName());
            comDriverInfoRecord.setIdcardno(comUserinfo.getIdentno());
            comDriverInfoRecord.setAccountId(comAccount.getId());
            comDriverInfoRecord.setDriverCustomsCode(carOwnerBean.getDriverCustomsCode());
            comDriverInfoRecord.setDrivingcard(carOwnerBean.getDriverCard());
            comDriverInfoRecordDao.insert(comDriverInfoRecord);

            List<Integer> attachmentIdList = new ArrayList<Integer>();
            attachmentIdList.add(carOwnerBean.getDriverLicenseFileId());
            if (!StringUtil.isEmpty(carOwnerBean.getIdentityPositiveFileId())){
                attachmentIdList.add(carOwnerBean.getIdentityPositiveFileId());
                attachmentIdList.add(carOwnerBean.getIdentityNegativeFileId());
                attachmentIdList.add(carOwnerBean.getIdentityHalfFileId());
            }
            for(Integer attachmentId : attachmentIdList){
                if(attachmentId != null){
                    BizAttachmentRecord attachmentRecord = bizAttachmentRecordDao.selectByPrimaryKey(attachmentId);
                    attachmentRecord.setUploadUser(comAccount.getId());
                    bizAttachmentRecordDao.updateByPrimaryKey(attachmentRecord);
                }
            }
        } else{
            resultBean.setState(false);
        }
        return resultBean;
    }

    /**
     * 申请成为快递员
     * @param accountId
     * @throws Exception
     */
    @Override
    @Transactional
    public ResultBean courier(MerchantCourierBean merchantCourierBean,Integer accountId,AddRequestBean addRequestBean) throws Exception{
        ResultBean resultBean = new ResultBean();
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(accountId);

        // 申请表
        Integer result = insertAccountRequest(accountId, SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue(),addRequestBean,merchantCourierBean);
        if(result != null) {
            // 用户信息备案表
            ComUserinfoRecord comUserinfoRecord = new ComUserinfoRecord();
            comUserinfoRecord.setId(null);
            comUserinfoRecord.setAccountId(accountId);
            comUserinfoRecord.setUrgentLinkUser(merchantCourierBean.getUrgentLinkUser());
            comUserinfoRecord.setUrgentLinkTel(merchantCourierBean.getUrgentLinkTel());
            if (!StringUtil.isEmpty(merchantCourierBean.getIdentityPositiveFileId())){
                if(StringUtil.isEmpty(comAccount.getAuthStatus())){
                    comUserinfoRecord.setIdentno(merchantCourierBean.getIdentno());
                    comUserinfoRecord.setRealName(merchantCourierBean.getRealName());
                }
                List<Integer> attachmentIdList = new ArrayList<Integer>();
                attachmentIdList.add(merchantCourierBean.getIdentityPositiveFileId());
                attachmentIdList.add(merchantCourierBean.getIdentityNegativeFileId());
                attachmentIdList.add(merchantCourierBean.getIdentityHalfFileId());
                for(Integer attachmentId : attachmentIdList){
                    if(attachmentId != null){
                        BizAttachmentRecord attachmentRecord = bizAttachmentRecordDao.selectByPrimaryKey(attachmentId);
                        attachmentRecord.setUploadUser(comAccount.getId());
                        bizAttachmentRecordDao.updateByPrimaryKey(attachmentRecord);
                    }
                }
            }
            comUserinfoRecordDao.insert(comUserinfoRecord);
            Logger.info("帐号：{},用户信息备案表数据：{}", comAccount.getAcctUsername(), JSON.toJSONString(comUserinfoRecord));

            resultBean.setState(true);
            resultBean.setData(result);
        }else{
            resultBean.setState(false);
        }
        return resultBean;
    }

    /**
     * 申请成为咪站
     * @param accountId
     * @throws Exception
     */
    @Override
    @Transactional
    public ResultBean mstation(MerchantMstationBean mstationBean,Integer accountId,AddRequestBean addRequestBean) throws Exception{
        ResultBean resultBean = new ResultBean();
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(accountId);

        // 申请表
        Integer result = insertAccountRequest(accountId, SysAccountRole.OPERATOR_MSTATION.getValue(),addRequestBean,mstationBean);
        if(result != null) {
            // 用户信息备案表
            ComUserinfoRecord comUserinfoRecord = new ComUserinfoRecord();
            PropertyUtils.copyProperties(comUserinfoRecord, mstationBean);
            comUserinfoRecord.setId(null);
            comUserinfoRecord.setAccountId(accountId);
            comUserinfoRecord.setUrgentLinkUser(mstationBean.getUrgentLinkUser());
            comUserinfoRecord.setUrgentLinkTel(mstationBean.getUrgentLinkTel());
            if (!StringUtil.isEmpty(mstationBean.getIdentityPositiveFileId())){
                if(StringUtil.isEmpty(comAccount.getAuthStatus())){
                    comUserinfoRecord.setIdentno(mstationBean.getIdentno());
                    comUserinfoRecord.setRealName(mstationBean.getRealName());
                }
                List<Integer> attachmentIdList = new ArrayList<Integer>();
                attachmentIdList.add(mstationBean.getIdentityPositiveFileId());
                attachmentIdList.add(mstationBean.getIdentityNegativeFileId());
                attachmentIdList.add(mstationBean.getIdentityHalfFileId());
                for(Integer attachmentId : attachmentIdList){
                    if(attachmentId != null){
                        BizAttachmentRecord attachmentRecord = bizAttachmentRecordDao.selectByPrimaryKey(attachmentId);
                        attachmentRecord.setUploadUser(comAccount.getId());
                        bizAttachmentRecordDao.updateByPrimaryKey(attachmentRecord);
                    }
                }
            }
            comUserinfoRecordDao.insert(comUserinfoRecord);
            Logger.info("帐号：{},用户信息备案表数据：{}", comAccount.getAcctUsername(), JSON.toJSONString(comUserinfoRecord));

            resultBean.setState(true);
            resultBean.setData(result);
        }else{
            resultBean.setState(false);
        }
        return resultBean;
    }

    /**
     * 申请成为车队
     * @param fleetBean
     * @param accountId
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBean fleet(MerchantFleetBean fleetBean,Integer accountId,AddRequestBean addRequestBean) throws Exception{
        ResultBean resultBean = new ResultBean();

        // 申请表
        Integer result = insertAccountRequest(accountId, SysAccountRole.OPERATOR_COMPANY_FLEET.getValue(),addRequestBean,null);
        if(result != null) {
            resultBean.setState(true);
            resultBean.setData(result);

            // 企业信息备案表
            ComCustomerRecord comCustomerRecord = new ComCustomerRecord();
            PropertyUtils.copyProperties(comCustomerRecord, fleetBean);
            comCustomerRecord.setId(null);
            comCustomerRecord.setAccountId(accountId);
            if (StringUtils.isNotEmpty(fleetBean.getFoundedYearsStr())) {
                comCustomerRecord.setFoundedYears(DateUtils.parseDate(fleetBean.getFoundedYearsStr(), "yyyy-MM-dd"));
            }
            comCustomerRecordDao.insert(comCustomerRecord);

            Logger.info("企业信息备案表数据：{}", JSON.toJSONString(comCustomerRecord));
        }else{
            resultBean.setState(false);
        }
        return resultBean;
    }

    /**
     * 申请成为站点
     * @param stationBean
     * @param accountId
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBean station(MerchantStationBean stationBean,Integer accountId,AddRequestBean addRequestBean) throws Exception {
        ResultBean resultBean = new ResultBean();
        // 申请表
        Integer result = insertAccountRequest(accountId, SysAccountRole.OPERATOR_COMPANY_STATION.getValue(),addRequestBean,null);
        if(result != null) {
            resultBean.setState(true);
            resultBean.setData(result);

            // 企业信息备案表
            ComCustomerRecord comCustomerRecord = new ComCustomerRecord();
            PropertyUtils.copyProperties(comCustomerRecord, stationBean);
            comCustomerRecord.setId(null);
            comCustomerRecord.setAccountId(accountId);
            if (StringUtils.isNotEmpty(stationBean.getFoundedYearsStr())) {
                comCustomerRecord.setFoundedYears(DateUtils.parseDate(stationBean.getFoundedYearsStr(), "yyyy-MM-dd"));
            }
            comCustomerRecordDao.insert(comCustomerRecord);

            Logger.info("企业信息备案表数据：{}", JSON.toJSONString(comCustomerRecord));
        }else{
            resultBean.setState(false);
        }
        return resultBean;
    }

    /**
     * 申请成为业务中心
     * @param serviceCenterBean
     * @param accountId
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBean serviceCenter(ServiceCenterBean serviceCenterBean,Integer accountId,AddRequestBean addRequestBean) throws Exception {
        /*ResultBean resultBean = new ResultBean();
        // 申请表
        Integer result = insertAccountRequest(accountId, SysAccountRole.SERVICE_CENTER.getValue(),addRequestBean,null);
        if(result != null) {
            resultBean.setState(true);
            resultBean.setData(result);

            // 企业信息备案表
            ComCustomerRecord comCustomerRecord = new ComCustomerRecord();
            PropertyUtils.copyProperties(comCustomerRecord, serviceCenterBean);
            comCustomerRecord.setId(null);
            comCustomerRecord.setAccountId(accountId);
            if (StringUtils.isNotEmpty(serviceCenterBean.getFoundedYearsStr())) {
                comCustomerRecord.setFoundedYears(DateUtils.parseDate(serviceCenterBean.getFoundedYearsStr(), "yyyy-MM-dd"));
            }
            comCustomerRecordDao.insert(comCustomerRecord);

            Logger.info("企业信息备案表数据：{}", JSON.toJSONString(comCustomerRecord));
        }else{
            resultBean.setState(false);
        }
        return resultBean;*/
        return null;
    }

    /**
     * 申请成为业务中心或者商业中心
     * @param accountRole
     * @param accountId
     * @throws Exception
     */
    @Override
    @Transactional
    public ResultBean center(String accountRole,Integer accountId,AddRequestBean addRequestBean) throws Exception{
        ResultBean resultBean = new ResultBean();
        // 申请表
        Integer result = insertAccountRequest(accountId, SysAccountRole.getRoleIdByCode(accountRole),addRequestBean,null);
        if(result != null) {
            resultBean.setState(true);
            resultBean.setData(result);
        }else{
            resultBean.setState(false);
        }
        return resultBean;
    }

    /**
     * 查看申请结果
     */
    @Override
    public void viewApplyResult(ComAccountRequest comAccountRequest){
        if(ApplyState.APPLY_THROUGH.getValue() == comAccountRequest.getReqStatus()){
            comAccountRequest.setReqStatus(ApplyState.THROUGH_HAS_VIEW.getValue());
        }else{
            comAccountRequest.setReqStatus(ApplyState.REJECTED_HAS_VIEW.getValue());
        }
        comAccountRequestDao.updateByPrimaryKey(comAccountRequest);
    }

    /**
     * 新增申请数据
     * @param accountId
     * @param roleId
     * @return
     */
    private Integer insertAccountRequest(Integer accountId,Integer roleId,AddRequestBean addRequestBean,Object obj){
        // 申请表
        ComAccountRequest comAccountRequest = new ComAccountRequest();
        comAccountRequest.setAccountId(accountId);
        comAccountRequest.setRoleId(roleId);
        comAccountRequest.setReqTime(new Date());
        comAccountRequest.setReqStatus(ApplyState.APPLY.getValue());
        comAccountRequest.setReqType(RequestTypeEnum.UPGRADE_TYPE.getTypeCode());
        if(addRequestBean != null){
            comAccountRequest.setRecommendFlag(addRequestBean.getRecommendFlag());
            comAccountRequest.setInvestigator(addRequestBean.getInvestigator());
            comAccountRequest.setReferee(addRequestBean.getReferences());
        }
        if (null != obj){
            MerchantPersonalBean merchantPersonalBean = (MerchantPersonalBean) obj;
            if (StringUtils.isNotEmpty(merchantPersonalBean.getIdentno())){
                comAccountRequest.setAuthRealName(merchantPersonalBean.getRealName());
                comAccountRequest.setAuthIdentityno(merchantPersonalBean.getIdentno());
            }
        }
        int result = comAccountRequestDao.insert(comAccountRequest);
        if(result > 0){
            return comAccountRequest.getId();
        }else{
            return null;
        }
    }

    @Override
    public ResultBean userAuthentication(MerchantPersonalBean merchantPersonalBean,AppLoginInfo appLoginInfo) throws Exception {
        RecordInfoBean recordInfoBean = new RecordInfoBean();
        int accountId=appLoginInfo.getAccountId();
        // 帐号相关信息
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setRealName(merchantPersonalBean.getRealName().trim());
        accountInfo.setAcctUsername(appLoginInfo.getComAccount().getAcctUsername());
        accountInfo.setAuthStatus(1);
        accountInfo.setAuthType(1);
        recordInfoBean.setAccountInfo(accountInfo);

        //用户信息
        List<RecordProperty> userPropertyList = new ArrayList<RecordProperty>();
        userPropertyList.add(buildRecordProperty("realName", merchantPersonalBean != null ?
                StringUtil.getNotNullStr(merchantPersonalBean.getRealName(), "") : ""));
        userPropertyList.add(buildRecordProperty("identno", merchantPersonalBean != null ?
                StringUtil.getNotNullStr(merchantPersonalBean.getIdentno(), "") : ""));
        recordInfoBean.setUserPropertyList(userPropertyList);

        recordInfoBean.setRecordType(RecordOperatorTypeEnum.UPDATE.getType());
        recordInfoBean.setAcctType(SysAccountRole.NORMAL_PERSONAL.getRoleCode());
        recordInfoBean.setAcctUsername(appLoginInfo.getComAccount().getAcctUsername());
        recordInfoBean.setIdentityNo(merchantPersonalBean.getIdentno());
        ServiceResult result = pscRecordService.record(RegisterDefine.buildTransportAuthInfo(), recordInfoBean);
        Logger.info("实名认证返回结果：{}", JSON.toJSONString(result));
        if (result.isResult()){
            ComAccount comAccount = new ComAccount();
            comAccount.setId(accountId);
            comAccount.setRealName(merchantPersonalBean.getRealName());
            comAccount.setAuthStatus(1);
            comAccount.setAuthType(1);
            comAccountDao.updateByPrimaryKeySelective(comAccount);

            ComUserinfo comUserinfo1 = new ComUserinfo();
            // 复制请求对象中属性到人员信息对象中
            comUserinfo1.setId(appLoginInfo.getComUserinfo().getId());
            comUserinfo1.setRealName(merchantPersonalBean.getRealName());
            comUserinfo1.setIdentno(merchantPersonalBean.getIdentno());
            comUserinfoDao.updateByPrimaryKeySelective(comUserinfo1);
            Logger.info("运输平台数据库实名认证记录：{}", JSON.toJSONString(comUserinfo1));
        }
        ResultBean resultBean = new ResultBean();
        resultBean.setState(result.isResult());
        resultBean.setMessage(result.getMsg());
        return resultBean;
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
     * 设置附件关联表数据
     * @param relationList
     * @param attachList
     * @param relId
     * @param tableName
     */
    private void bindRelIdAndAttachRelation(List<ComAccessoryRelation> relationList, List<Integer> attachList,
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
     * 上传文件
     * @param multipartFile
     * @param fileId
     * @return
     * @throws Exception
     */
    @Override
    public ResultBean registerFileUpload(MultipartFile multipartFile, String fileId, int accountId) throws Exception {
        ResultBean resultBean = new ResultBean();
        int fileType = -1;
        if (RegisterDefine.IDENTITY_POSITIVE_FILE_ID.equals(fileId)
                || RegisterDefine.DRIVER_IDENTITY_POSITIVE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.IDENTITY_POSITIVE.getValue();
        } else if (RegisterDefine.IDENTITY_NEGATIVE_FILE_ID.equals(fileId)
                || RegisterDefine.DRIVER_IDENTITY_NEGATIVE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.IDENTITY_NEGATIVE.getValue();
        } else if (RegisterDefine.PORTRAIT_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.PORTRAIT.getValue();
        } else if (RegisterDefine.LICENSE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.OPERATE_LICENSE.getValue();
        } else if (RegisterDefine.DRIVER_LICENSE_FILE_ID.equals(fileId)
                || RegisterDefine.SECOND_DRIVER_LICENSE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.DRIVER_LICENSE.getValue();
        } else if (RegisterDefine.FIRST_CAR_IMAGE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.CAR_IMAGE.getValue();
        } else if (RegisterDefine.SECOND_CAR_IMAGE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.CAR_IMAGE_2.getValue();
        } else if (RegisterDefine.DRIVING_LICENSE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.DRIVING_IMAGE.getValue();
        } else if (RegisterDefine.TAX_REGISTER_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.TAX_REGISTER.getValue();
        } else if (RegisterDefine.TRANSPORT_AGREE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.TRANSPORT_AGREE.getValue();
        } else if (RegisterDefine.TRANSPORT_BUSINESS_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.TRANSPORT_BUSINESS.getValue();
        }else if (RegisterDefine.ORG_CODE_LILCENSE_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.ORG_CODE_LILCENSE.getValue();
        }else if (RegisterDefine.IDENTITY_HALF_FILE_ID.equals(fileId)) {
            fileType = UploadFileType.IDENTITY_HALF.getValue();
        }

        if (fileType == -1) {
            resultBean.setMessage("文件上传异常");
            return resultBean;
        }
        BizAttachment bizAttachment = uploadService.uploadDfsFile(multipartFile, fileType);
        if (bizAttachment == null) {
            resultBean.setMessage("文件上传失败");
            return resultBean;
        }
        BizAttachmentRecord bizAttachmentRecord = new BizAttachmentRecord();
        PropertyUtils.copyProperties(bizAttachmentRecord, bizAttachment);
        bizAttachmentRecordDaoEx.deleteByAcctIdAndFileType(accountId, fileType);
        bizAttachmentRecord.setUploadUser(accountId);
        bizAttachmentRecord.setAttachId(null);
        bizAttachmentRecord.setAttachState(SysState.USABLE.getValue());
        bizAttachmentRecord.setAttachServerIp("");
        bizAttachmentRecord.setUploadTime(new Date());
        bizAttachmentRecord.setAttachDomain(fastDFSUrl);
        bizAttachmentRecordDao.insert(bizAttachmentRecord);

        resultBean.setState(bizAttachmentRecord.getAttachId() != null);
        resultBean.setData(bizAttachmentRecord.getAttachId());
        String AttachPath =  bizAttachmentRecord.getAttachPath();
        String path = fastDFSUrl + AttachPath.substring(0, AttachPath.lastIndexOf("."))+"_150x174_"+AttachPath.substring(AttachPath.lastIndexOf("."));
        resultBean.setMessage(path);

        return resultBean;
    }
}
