package com.gistandard.transport.app.module.kpp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.module.kpp.bean.QueryKppParam;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComCompanyStaff;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.ex.ComCompanyStaffDaoEx;
import com.gistandard.transport.base.define.MobileStationRetCode;
import com.gistandard.transport.system.gps.bean.GiPositionAcct;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.tools.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gistandard.transport.app.module.kpp.service.KppService;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.entity.bean.ComAccountRoleRel;
import com.gistandard.transport.base.entity.bean.MobileMoudleRel;
import com.gistandard.transport.base.entity.dao.MobileMoudleRelDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccountRoleRelDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileMoudleRelDaoEx;
import com.gistandard.transport.base.exception.MobileStationBizException;

/**
 * Created by yujie on 2016/9/30.
 */
@Service
public class KppServiceImpl implements KppService {

    private static final Logger logger = LoggerFactory.getLogger(KppServiceImpl.class);
    @Autowired
    private ComAccountRoleRelDaoEx comAccountRoleRelDaoEx;

    @Autowired
    private MobileMoudleRelDaoEx mobileMoudleRelDaoEx;

    @Autowired
    private MobileMoudleRelDao mobileMoudleRelDao;

    @Autowired
    private ComCompanyStaffDaoEx comCompanyStaffDaoEx;

    @Autowired
    private GpsOrderService gpsOrderService;

    @Autowired
    private ComAccountDao comAccountDao;

    @Override
    public AppBaseResult addMobileMoudleRel(MobileMoudleRel mobileMoudleRel) throws MobileStationBizException {
        AppBaseResult appBaseResult = null;
        MobileMoudleRel query = new MobileMoudleRel();
        List<ComAccountRoleRel> comAccountRoleRels = null;
        ComCompanyStaff comCompanyStaff = null;
        comAccountRoleRels = comAccountRoleRelDaoEx.queryByAccountId(mobileMoudleRel.getAccountId());
        if (mobileMoudleRel.getCompanyId() != null) {
            comCompanyStaff = comCompanyStaffDaoEx.queryByAccountAndCompany(mobileMoudleRel.getAccountId(), mobileMoudleRel.getCompanyId());
        }
        if (mobileMoudleRel.getMoudleCode().equals(MobileStationDefine.PRODUCT_TYPE_TCYS)) {
            appBaseResult = checkKppRole(comCompanyStaff, SysAccountRole.OPERATOR_CAR_OWNER.getValue(), comAccountRoleRels);
        } else if (mobileMoudleRel.getMoudleCode().equals(MobileStationDefine.PRODUCT_TYPE_TCKD)
                || mobileMoudleRel.getMoudleCode().equals(MobileStationDefine.PRODUCT_TYPE_0TCWM)) {
            appBaseResult = checkKppRole(comCompanyStaff, SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue(), comAccountRoleRels);
        } else if (mobileMoudleRel.getMoudleCode().equals(MobileStationDefine.PRODUCT_TYPE_OTCKDM)) {
            appBaseResult = checkKppRole(comCompanyStaff, SysAccountRole.OPERATOR_MSTATION.getValue(), comAccountRoleRels);
        }
        if (appBaseResult != null) {
            return appBaseResult;
        }
        query.setAcctUsername(mobileMoudleRel.getAcctUsername());
        query.setCompanyCode(mobileMoudleRel.getCompanyCode());
        query.setMoudleCode(mobileMoudleRel.getMoudleCode());
        List<MobileMoudleRel> mobileMoudleRels = mobileMoudleRelDaoEx.queryMobileMoudleRelByCondition(query);
        if (mobileMoudleRels != null && mobileMoudleRels.size() > 0) {
            appBaseResult = new AppBaseResult();
            appBaseResult.setRetCode(MobileStationRetCode.KPP_REPEAT_ADD.getValue());
            appBaseResult.setRetMsg(MobileStationRetCode.KPP_REPEAT_ADD.getName());
            return appBaseResult;
        }
        mobileMoudleRel.setCreateDate(new Date());
        mobileMoudleRel.setIsOn(1);
        int record = mobileMoudleRelDao.insert(mobileMoudleRel);
        if (record > 0) {
            appBaseResult = new AppBaseResult();
            appBaseResult.setRetCode(MobileStationRetCode.SUCCESS.getValue());
            appBaseResult.setRetMsg(MobileStationRetCode.SUCCESS.getName());
        }
        return appBaseResult;
    }

    @Override
    public AppBaseResult updateMobileMoudleRel(QueryKppParam queryKppParam) throws MobileStationBizException {
        AppBaseResult appBaseResult = new AppBaseResult(queryKppParam);
        if (StringUtil.isEmpty(queryKppParam.getMoudleCode())) {
            //修改共享位置状态
            ComAccount comAccount = new ComAccount();
            comAccount.setId(queryKppParam.getAccountId());
            comAccount.setShareStatus(queryKppParam.isShareStatus());
            comAccountDao.updateByPrimaryKeySelective(comAccount);
        } else {
            //修改模块接单状态
            MobileMoudleRel mobileMoudleRel = new MobileMoudleRel();
            BeanUtils.copyProperties(queryKppParam, mobileMoudleRel);
            mobileMoudleRel.setCompanyCode(queryKppParam.getCompanyAcctUsername());
            mobileMoudleRel.setCompanyId(queryKppParam.getCompanyAccountId());
            mobileMoudleRelDaoEx.updateByPrimaryKeySelective(mobileMoudleRel);
        }
        sendGpsLoginMsg(queryKppParam.getAccountId(), queryKppParam.getCompanyAcctUsername());
        return appBaseResult;
    }

    public AppBaseResult checkKppRole(ComCompanyStaff comCompanyStaff, int needRoleId, List<ComAccountRoleRel> comAccountRoleRels) {
        AppBaseResult appBaseResult = null;
        boolean hasRole = false;
        for (ComAccountRoleRel comAccountRoleRel : comAccountRoleRels) {
            if (comAccountRoleRel.getRoleId() == needRoleId) {
                hasRole = true;
                break;
            }
        }
        if (!hasRole) {
            //没有对应的角色
            appBaseResult = new AppBaseResult();
            appBaseResult.setRetCode(MobileStationRetCode.KPP_ROLE_NOT_SUPPORT.getValue());
            appBaseResult.setRetMsg(MobileStationRetCode.KPP_ROLE_NOT_SUPPORT.getName());
            return appBaseResult;
        }
        if (comCompanyStaff != null) {
            boolean hasRoleInCompany = false;
            String roleIds = comCompanyStaff.getRoleIds();
            if (roleIds != null) {
                String[] split = roleIds.split(",");
                for (String roldId : split) {
                    if (roldId.equals(needRoleId + "")) {
                        hasRoleInCompany = true;
                        break;
                    }
                }
            }
            if (!hasRoleInCompany) {
                appBaseResult = new AppBaseResult();
                appBaseResult.setRetCode(MobileStationRetCode.KPP_ROLE_NOT_IN_COMPANY.getValue());
                appBaseResult.setRetMsg(MobileStationRetCode.KPP_ROLE_NOT_IN_COMPANY.getName());
                return appBaseResult;
            }
        }
        return appBaseResult;
    }

    /**
     * 推送GPS当前用户信息
     * 共享位置信息、模块信息、角色信息
     *
     * @param accountId
     * @param companyAcctUsername
     */
    @Override
    public void sendGpsLoginMsg(Integer accountId, String companyAcctUsername) {
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(accountId);
        GiPositionAcct giPositionAcct = new GiPositionAcct();
        giPositionAcct.setUserCode(comAccount.getAcctUsername());
        giPositionAcct.setCompanyCode(companyAcctUsername);
        //获取模块列表
        MobileMoudleRel mobileMoudleRel = new MobileMoudleRel();
        mobileMoudleRel.setAccountId(accountId);
        if (!StringUtil.isEmpty(companyAcctUsername)) {
            mobileMoudleRel.setCompanyCode(companyAcctUsername);
            List<String> allModuleCode = new ArrayList<>();
            List<String> allRoleCode = new ArrayList<>();
            List<MobileMoudleRel> mobileMoudleRelList = mobileMoudleRelDaoEx.queryMobileMoudleRelByCondition(mobileMoudleRel);
            if (mobileMoudleRelList != null && mobileMoudleRelList.size() > 0) {
                for (MobileMoudleRel rel : mobileMoudleRelList) {
                    allModuleCode.add(rel.getMoudleCode());
                    if (rel.getMoudleStatus() != null && rel.getMoudleStatus() == 1) {
                        if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(rel.getMoudleCode())) {
                            allRoleCode.add(SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode());
                        } else if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(rel.getMoudleCode())) {
                            allRoleCode.add(SysAccountRole.OPERATOR_CAR_OWNER.getRoleCode());
                        } else if (MobileStationDefine.PRODUCT_TYPE_OTCKDM.equals(rel.getMoudleCode())) {
                            allRoleCode.add(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
                        }
                    }
                }
            }
            giPositionAcct.setAllModuleCode(allModuleCode);
            giPositionAcct.setAllRoleCode(allRoleCode);
        } else {
            //如果是个人，需要判断企业下是否有相同模块，如果有过滤掉
            List<MobileMoudleRel> companyMoudleRelList = mobileMoudleRelDaoEx.queryMobileMoudleRelCompanyList(mobileMoudleRel);
            List<MobileMoudleRel> mobileMoudleRelList = mobileMoudleRelDaoEx.queryMobileMoudleRelByCondition(mobileMoudleRel);
            List<String> allModuleCode = new ArrayList<>();
            List<String> allRoleCode = new ArrayList<>();
            if (mobileMoudleRelList != null && mobileMoudleRelList.size() > 0) {
                for (MobileMoudleRel rel : mobileMoudleRelList) {
                    if (rel.getMoudleCode() == null) {
                        continue;
                    }
                    if (companyMoudleRelList != null && companyMoudleRelList.size() > 0) {
                        boolean flag = true;
                        for (MobileMoudleRel companyRel : companyMoudleRelList) {
                            if (companyRel.getMoudleCode() != null && rel.getMoudleCode().equals(companyRel.getMoudleCode())) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            allModuleCode.add(rel.getMoudleCode());
                            if (rel.getMoudleStatus() != null && rel.getMoudleStatus() == 1) {
                                if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(rel.getMoudleCode())) {
                                    allRoleCode.add(SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode());
                                } else if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(rel.getMoudleCode())) {
                                    allRoleCode.add(SysAccountRole.OPERATOR_CAR_OWNER.getRoleCode());
                                } else if (MobileStationDefine.PRODUCT_TYPE_OTCKDM.equals(rel.getMoudleCode())) {
                                    allRoleCode.add(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
                                }
                            }
                        }
                    } else {
                        allModuleCode.add(rel.getMoudleCode());
                        if (rel.getMoudleStatus() != null && rel.getMoudleStatus() == 1) {
                            if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(rel.getMoudleCode())) {
                                allRoleCode.add(SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode());
                            } else if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(rel.getMoudleCode())) {
                                allRoleCode.add(SysAccountRole.OPERATOR_CAR_OWNER.getRoleCode());
                            } else if (MobileStationDefine.PRODUCT_TYPE_OTCKDM.equals(rel.getMoudleCode())) {
                                allRoleCode.add(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
                            }
                        }
                    }
                }
            }
            giPositionAcct.setAllModuleCode(allModuleCode);
            giPositionAcct.setAllRoleCode(allRoleCode);
        }

        giPositionAcct.setSharingPosition(comAccount.isShareStatus());
        logger.info("sendUserLoginCompanyMessage {}", JSON.toJSONString(giPositionAcct));
        gpsOrderService.sendUserLoginCompanyMessage(giPositionAcct);
    }
}
