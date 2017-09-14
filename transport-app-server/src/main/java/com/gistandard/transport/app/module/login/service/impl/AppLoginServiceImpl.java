package com.gistandard.transport.app.module.login.service.impl;

import com.gistandard.transport.app.module.kpp.service.KppService;
import com.gistandard.transport.app.module.login.action.AppLoginController;
import com.gistandard.transport.app.module.login.bean.*;
import com.gistandard.transport.app.module.login.dao.AppLoginDao;
import com.gistandard.transport.app.module.login.service.AppLoginService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SysResult;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.ComCustomerDao;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.app.module.kpp.bean.QueryKppParam;
import com.gistandard.transport.base.entity.service.MobileMoudleRelService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.oauth2.SecurityUser;
import com.gistandard.transport.oauth2.bean.AuthTokenInfo;
import com.gistandard.transport.oauth2.bean.AuthTokenInfoResult;
import com.gistandard.transport.oauth2.service.OAuth2TokenService;
import com.gistandard.transport.system.gps.bean.GiPositionAcct;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.system.logToPsc.define.OperateResultType;
import com.gistandard.transport.system.logToPsc.define.OperateType;
import com.gistandard.transport.system.logToPsc.service.LogToPscService;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.authorize.CompanyStaffRelativeService;
import com.valueplus.psc.dubbo.service.authorize.bean.QueryUseFlagInCompanyParam;
import com.valueplus.psc.dubbo.service.common.bean.CheckLoginBean;
import com.valueplus.psc.dubbo.service.common.bean.CheckLoginResultBean;
import com.valueplus.psc.dubbo.service.common.bean.ServiceAuthBean;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import com.valueplus.psc.dubbo.service.login.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppLoginServiceImpl implements AppLoginService {

    private static final Logger logger = LoggerFactory.getLogger(AppLoginServiceImpl.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

    @Autowired
    private ComAccountRoleRelDaoEx comAccountRoleRelDaoEx;

    @Autowired
    private AppLoginDao appLoginDao;

    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;

    @Autowired
    private ComCustomerDao comCustomerDao;

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private OAuth2TokenService oAuth2TokenService;

    @Autowired
    private MobileMoudleRelService mobileMoudleRelService;

    @Autowired
    private MobileMoudleRelDaoEx mobileMoudleRelDaoEx;

    @Autowired
    private ComAccountCategoryDaoEx comAccountCategoryDaoEx;

    @Autowired
    private GpsOrderService gpsOrderService;

    @Autowired
    private LogToPscService logToPscService;

    @Autowired
    private KppService kppService;

    @Autowired
    private CompanyStaffRelativeService companyStaffRelativeService;


    /**
     * 登录信息校验接口
     *
     * @param checkLoginParam 登录信息参数对象
     * @return 登录结果
     */
    @Override
    public CheckLoginResult checkAppLoginInfo(CheckLoginParam checkLoginParam) {
        CheckLoginResult baseResBean = new CheckLoginResult(checkLoginParam);
        ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
        serviceAuthBean.setSysFlag(AppServerDefine.MOBILE_STATION_SYS_FLAG);
        CheckLoginBean checkLoginBean = new CheckLoginBean();
        checkLoginBean.setAcctUsername(checkLoginParam.getLoginAccount());
        if(!StringUtil.isEmpty(checkLoginParam.getLoginType()) && 2 == checkLoginParam.getLoginType()){
            checkLoginBean.setLoginType(2);
            checkLoginBean.setTokenId(checkLoginParam.getTokenId());
            checkLoginBean.setValidateCode(checkLoginParam.getValidateCode());
        }else {
            checkLoginBean.setAcctPassword(checkLoginParam.getLoginPwd());
        }
        ServiceResult<CheckLoginResultBean> result = accountService.checkLogin(serviceAuthBean, checkLoginBean);
        if (result.isResult()) {
            CheckLoginData res = new CheckLoginData();
            CheckLoginResultBean checkLoginResultBean = result.getData();
            res.setPscId(checkLoginResultBean.getAccountId());
            res.setSystemFlag(AppServerDefine.MOBILE_STATION_SYS_FLAG);
            //查询账号是否存在，如果存在就获取时间戳标识
            ComAccount comAccount = comAccountDaoEx.queryByAccount(checkLoginResultBean.getAcctUsername());
            if (comAccount == null) {
                baseResBean.setRetCode(AppServerDefine.FAILURE);
                baseResBean.setRetMsg("账号不存在");
                return baseResBean;
            }
            int acctId = comAccount.getId();
            //根据账号ID和角色Id查找用户信息
            String roleIds = SysAccountRole.NORMAL_PERSONAL.getValue() + "," + SysAccountRole.OPERATOR_CAR_OWNER.getValue() + "," + SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue() + "," + SysAccountRole.OPERATOR_MSTATION.getValue();
            ComUserinfo comUserinfo = appLoginDao.queryUserinfoByAcctId(acctId, roleIds);
            if (comUserinfo == null) {
                baseResBean.setRetCode(AppServerDefine.FAILURE);
                baseResBean.setRetMsg("没有权限登录");
                return baseResBean;
            }
            //根据账号查询对应的车辆信息
            ComVehicleInfo comVehicleInfo = appLoginDao.queryVehicleByAcctId(acctId);
            if (comVehicleInfo != null) {
                res.setComVehicleInfo(comVehicleInfo);
            }
            //获取角色列表
            List<ComAccountRoleRel> roleRelList = comAccountRoleRelDaoEx.queryByAccountId(acctId);
            List<Integer> roleList = new ArrayList<>();
            for (ComAccountRoleRel roleRel : roleRelList) {
                roleList.add(roleRel.getRoleId());
            }
            //获取用户所属企业信息
            List<CompanyInfo> companyInfoList = appLoginDao.queryCompanyInfoListByAcctId(acctId);
            //获取用户是否为移动咪站
            ComAccountCategory comAccountCategory = comAccountCategoryDaoEx.getMiStationStatus(acctId);
            if (comAccountCategory != null) {
                res.setMobileMiFlag(true);
            }
            res.setComUserinfo(comUserinfo);
            res.setComAccount(comAccount);
            res.setRoleList(roleList);
            res.setCompanyInfoList(companyInfoList);
            baseResBean.setData(res);
            //推送GPS当前用户信息
            kppService.sendGpsLoginMsg(acctId, null);
            logToPscService.sendOperateLogMessage(comAccount.getAcctUsername(), OperateType.LOGIN.getOpType(),
                    comAccount.getAcctUsername() + "成功登录通用快递App", OperateResultType.SUCCESS.getValue(),
                    null, SystemDefine.MOBILE_STATION_SYS_FLAG);
        } else {
            baseResBean.setRetCode(AppServerDefine.FAILURE);
            baseResBean.setRetMsg(result.getMsg());
            return baseResBean;
        }
        return baseResBean;
    }

    /**
     * 获取站点信息
     *
     * @param appBaseRequest 请求基类信息
     * @return 登录结果
     * @throws MobileStationBizException
     */
    @Override
    public CheckLoginResult getStationInfo(AppBaseRequest appBaseRequest) throws MobileStationBizException {
        CheckLoginResult baseResBean = new CheckLoginResult(appBaseRequest);
        if (appBaseRequest == null || StringUtils.isEmpty(appBaseRequest.getAcctUsername())) {
            throw new MobileStationBizException("账号信息不能为空");
        }
        CheckLoginData res = new CheckLoginData();

        //用户信息，获取所在站点信息
        ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(appBaseRequest.getAccountId());
        if (comUserinfo == null || StringUtil.isEmpty(comUserinfo.getCustomerId())) {
            throw new MobileStationBizException("获取不到企业信息");
        } else {
            //根据customerId 获取企业信息
            ComCustomer comCustomer = comCustomerDao.selectByPrimaryKey(comUserinfo.getCustomerId());
            if (comCustomer == null) {
                throw new MobileStationBizException("获取不到企业信息");
            } else {
                res.setPscId(comCustomer.getAccountId());
                ComAccount comAccount = comAccountDao.selectByPrimaryKey(comCustomer.getAccountId());
                if (comAccount == null) {
                    throw new MobileStationBizException("获取不到企业信息");
                } else {
                    res.setComAccount(comAccount);
                }
                //获取角色列表
                List<ComAccountRoleRel> roleRelList = comAccountRoleRelDaoEx.queryByAccountId(comCustomer.getAccountId());
                List<Integer> roleList = new ArrayList<>();
                for (ComAccountRoleRel roleRel : roleRelList) {
                    roleList.add(roleRel.getRoleId());
                }
                res.setRoleList(roleList);
                res.setComCustomer(comCustomer);
            }
        }
        res.setSystemFlag(AppServerDefine.MOBILE_STATION_SYS_FLAG);
        baseResBean.setData(res);
        return baseResBean;
    }

    /**
     * 根据登录信息更新企业数据
     *
     * @param appLoginInfo 登录信息
     * @param principal
     * @param request      企业id
     * @return 授权信息
     */
    @Override
    public UpdateSelectCompanyResult updateSelectCompany(AppLoginInfo appLoginInfo, Principal principal, SelectCompanyRequest request) {
        UpdateSelectCompanyResult result = new UpdateSelectCompanyResult();
        result.setRetCode(SysResult.FAILED.getValue());
        List<CompanyInfo> companyInfoList = appLoginDao.queryCompanyInfoListByAcctId(appLoginInfo.getAccountId());

        if (!containsCompany(request.getCompanyId(), companyInfoList)) {
            result.setRetCode(SysResult.FAILED.getValue());
            result.setRetMsg("不能选择该企业！");
            return result;
        }

        //所属企业
        ComCustomer comCustomer = comCustomerDao.selectByPrimaryKey(request.getCompanyId());
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(comCustomer.getAccountId());


        //获取用户在企业下是否有通用账户权限
        ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
        serviceAuthBean.setSysFlag(AppServerDefine.MOBILE_STATION_SYS_FLAG);
        QueryUseFlagInCompanyParam queryUseFlagInCompanyParam = new QueryUseFlagInCompanyParam();
        queryUseFlagInCompanyParam.setCompanyAccountId(comAccount.getId());
        queryUseFlagInCompanyParam.setStaffAccountId(appLoginInfo.getAccountId());
        queryUseFlagInCompanyParam.setSysFlag(SystemDefine.ACCOUNT_SYS_FLAG);
        ServiceResult<Boolean> serviceResult = companyStaffRelativeService.queryUseFlagInCompany(serviceAuthBean, queryUseFlagInCompanyParam);
        if (serviceResult.isResult()) {
            UpdateSelectCompanyResBean resBean = new UpdateSelectCompanyResBean();
            resBean.setAccountAuth(serviceResult.getData());
            result.setData(resBean);
        } else {
            result.setRetCode(SystemDefine.FAILURE);
            result.setRetMsg(serviceResult.getMsg());
            return result;
        }
        //更新授权信息
        Authentication client = (Authentication) principal;
        OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) client.getDetails();
        OAuth2Authentication oAuth2Authentication = oAuth2TokenService.getAuthentication(auth2AuthenticationDetails.getTokenValue());

        //设置选择企业信息
        SecurityUser<AppLoginInfo> userInfo = (SecurityUser) oAuth2Authentication.getPrincipal();
        userInfo.getInfo().setCurrentComCustomer(comCustomer);
        userInfo.getInfo().setCurrentComCustomerAccount(comAccount);

        //更新缓存授权信息
        oAuth2TokenService.updateAuthentication(oAuth2Authentication);

        result.setRetCode(SysResult.SUCCESS.getValue());

        QueryKppParam queryKppParam = new QueryKppParam();
        queryKppParam.setAppLoginInfo(appLoginInfo);
        mobileMoudleRelService.updateMobileMoudleRelStatus(queryKppParam.getAcctUsername(), queryKppParam.getCompanyAcctUsername());

        //推送GPS当前用户信息
        kppService.sendGpsLoginMsg(appLoginInfo.getAccountId(), comAccount.getAcctUsername());
        return result;
    }

    @Override
    public AuthTokenInfoResult getToken(TokenRequest tokenRequest) {
        AuthTokenInfoResult authTokenInfoResult = oAuth2TokenService.sendRefreshTokenRequest(tokenRequest.getRefreshToke(), tokenRequest.getClientId(), tokenRequest.getClientSecret());
        if(authTokenInfoResult.getRetCode()==1){
            AuthTokenInfo authTokenInfo = authTokenInfoResult.getData();
            OAuth2Authentication oAuth2Authentication = oAuth2TokenService.getAuthentication(authTokenInfo.getValue());
            SecurityUser<AppLoginInfo> userInfo = (SecurityUser) oAuth2Authentication.getPrincipal();
            if(!StringUtil.isEmpty(tokenRequest.getCompanyId())){
                ComCustomer comCustomer = comCustomerDao.selectByPrimaryKey(tokenRequest.getCompanyId());
                ComAccount comAccount = comAccountDao.selectByPrimaryKey(comCustomer.getAccountId());
                    userInfo.getInfo().setCurrentComCustomer(comCustomer);
                    userInfo.getInfo().setCurrentComCustomerAccount(comAccount);

                    //更新缓存授权信息
                    oAuth2TokenService.updateAuthentication(oAuth2Authentication);
                    logToPscService.sendOperateLogMessage(userInfo.getUsername(), OperateType.LOGIN.getOpType(),
                            userInfo.getUsername()+"以"+comAccount.getAcctUsername() + "成功登录通用快递App", OperateResultType.SUCCESS.getValue(),
                            null, SystemDefine.MOBILE_STATION_SYS_FLAG);
                    logToPscService.sendOperateLogMessage(comAccount.getAcctUsername(), OperateType.LOGIN.getOpType(),
                            userInfo.getUsername()+"以"+comAccount.getAcctUsername() + "成功登录通用快递App", OperateResultType.SUCCESS.getValue(),
                            null, SystemDefine.MOBILE_STATION_SYS_FLAG);
            }else{
                logToPscService.sendOperateLogMessage(userInfo.getUsername(), OperateType.LOGIN.getOpType(),
                        userInfo.getUsername() + "成功登录通用快递App", OperateResultType.SUCCESS.getValue(),
                        null, SystemDefine.MOBILE_STATION_SYS_FLAG);
            }

        }
        logger.debug("刷新token:{}", authTokenInfoResult.getData());
        return authTokenInfoResult;
    }

    /**
     * 是否包含目标企业
     *
     * @param companyID
     * @param companyInfoList
     * @return
     */
    private boolean containsCompany(Integer companyID, List<CompanyInfo> companyInfoList) {
        for (CompanyInfo companyInfo : companyInfoList) {
            if (companyInfo.getCompanyID() != null && companyInfo.getCompanyID().equals(companyID)) {
                return true;
            }
        }
        return false;
    }
}
