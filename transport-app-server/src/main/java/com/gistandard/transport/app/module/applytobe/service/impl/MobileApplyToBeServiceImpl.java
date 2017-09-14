package com.gistandard.transport.app.module.applytobe.service.impl;

import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.app.module.applytobe.bean.CheckRoleResult;
import com.gistandard.transport.app.module.applytobe.bean.MobileApplyDefault;
import com.gistandard.transport.app.module.applytobe.bean.MobileRoleBean;
import com.gistandard.transport.app.module.applytobe.service.MobileApplyToBeService;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.applytobe.define.ApplyState;
import com.gistandard.transport.applytobe.define.RequestTypeEnum;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComAccountRequest;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.ComAccountRequestDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComAccountRequestDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComAccountRoleRelDaoEx;
import com.gistandard.transport.oauth2.SecurityUser;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author zxnui
 * @Date 8/16/16.
 */
@Service
public class MobileApplyToBeServiceImpl implements MobileApplyToBeService {

    @Autowired
    private ComAccountRequestDao comAccountRequestDao;

    @Autowired
    private ComAccountRequestDaoEx comAccountRequestDaoEx;

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

    @Autowired
    private ComAccountRoleRelDaoEx comAccountRoleRelDaoEx;

    @Override
    public CheckRoleResult check(AppBaseRequest appBaseRequest) {
        CheckRoleResult checkRoleResult = new CheckRoleResult(appBaseRequest);
        checkRoleResult.setReqId(appBaseRequest.getReqId());

        ComAccount comAccount = comAccountDao.selectByPrimaryKey(appBaseRequest.getAccountId());
        checkRoleResult.setData(MobileApplyDefault.URL_BASE_HTTP + "?accountId=" + comAccount.getId());

        ComAccountRequest query = new ComAccountRequest();
        query.setAccountId(appBaseRequest.getAccountId());
        query.setReqType(RequestTypeEnum.UPGRADE_TYPE.getTypeCode());

        ComAccountRequest comAccountRequest = comAccountRequestDaoEx.selectByAccountIdAndRoleIdAndStatus(query);
        if (comAccountRequest != null){
            if (comAccountRequest.getReqStatus() != ApplyState.REJECTED_HAS_VIEW.getValue()
                    && comAccountRequest.getReqStatus() != ApplyState.THROUGH_HAS_VIEW.getValue()){
                checkRoleResult.setData(MobileApplyDefault.URL_SHOW_HTTP + "?account=" + comAccount.getAcctUsername());
            }
        }
        return checkRoleResult;
    }

    /**
     * 获取结果页面展示文言
     * @param account
     * @return
     */
    @Override
    public AppBaseResult getShowMessage(String account){
        AppBaseResult res = new AppBaseResult();
        res.setRetCode(-1);
        ComAccount comAccount = comAccountDaoEx.queryByAccount(account);

        ComAccountRequest query = new ComAccountRequest();
        query.setAccountId(comAccount.getId());
        query.setReqType(RequestTypeEnum.UPGRADE_TYPE.getTypeCode());
        ComAccountRequest comAccountRequest = comAccountRequestDaoEx.selectByAccountIdAndRoleIdAndStatus(query);
        // 升级角色名称
        String roleName = SysAccountRole.getName(comAccountRequest.getRoleId());
        if (comAccountRequest.getReqStatus() == ApplyState.APPLY.getValue()
                || comAccountRequest.getReqStatus() == ApplyState.WAITTING_FOR_CHECK.getValue()
                || comAccountRequest.getReqStatus() == ApplyState.WAITTING_FOR_APPROVE.getValue()){
            // 已申请
            res.setRetMsg(MobileApplyDefault.MSG_APPLY_WAIT.replace("{0}", roleName));
            res.setRetCode(ApplyState.APPLY.getValue());
        } else if (comAccountRequest.getReqStatus() == ApplyState.APPLY_THROUGH.getValue()){
            // 申请通过
            res.setRetMsg(MobileApplyDefault.MSG_APPLY_SUCCESS.replace("{0}", roleName));
            res.setRetCode(ApplyState.APPLY_THROUGH.getValue());

            comAccountRequest.setReqStatus(ApplyState.THROUGH_HAS_VIEW.getValue());
            comAccountRequestDao.updateByPrimaryKey(comAccountRequest);
        } else if (comAccountRequest.getReqStatus() == ApplyState.APPLY_REJECTED.getValue()){
            // 申请拒绝
            res.setRetMsg(MobileApplyDefault.MSG_APPLY_FAIL.replace("{0}", roleName) + comAccountRequest.getReqResultDesc());
            res.setRetCode(ApplyState.APPLY_REJECTED.getValue());

            comAccountRequest.setReqStatus(ApplyState.REJECTED_HAS_VIEW.getValue());
            comAccountRequestDao.updateByPrimaryKey(comAccountRequest);
        }

        return res;
    }

    /**
     * 获取结果页面展示文言
     * @param accountId
     * @return
     */
    @Override
    public MobileRoleBean getRoleName(Integer accountId){
        MobileRoleBean mobileRoleBean = new MobileRoleBean();
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(accountId);
        //当前用户现有角色
        List<Integer> roleList = comAccountRoleRelDaoEx.getUserRoleIdByAccountId(comAccount.getId());

        Map<Integer,String> roleMap = new HashMap<Integer,String>();
        roleMap.put(SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue(),SysAccountRole.OPERATOR_DELIVERY_OWNER.getName());
        roleMap.put(SysAccountRole.OPERATOR_CAR_OWNER.getValue(),SysAccountRole.OPERATOR_CAR_OWNER.getName());
        roleMap.put(SysAccountRole.OPERATOR_MSTATION.getValue(),SysAccountRole.OPERATOR_MSTATION.getName());

        String hasRole = "";
        String noRole = "";
        Map<String,Boolean> hasRoleMap = new HashMap<String,Boolean>();
        if (CollectionUtils.isNotEmpty(roleList)){
            for (int roleId : roleList){
                if(roleMap.containsKey(roleId)){
                    hasRoleMap.put(SysAccountRole.getRoleCode(roleId),true);
                    if (roleMap.containsKey(roleId)){
                        hasRole = hasRole + "、" + SysAccountRole.getName(roleId);
                    }
                }
            }
        }

        for (Map.Entry<Integer,String> entry : roleMap.entrySet()) {
            if(!hasRoleMap.containsKey(SysAccountRole.getRoleCode(entry.getKey()))){
                hasRoleMap.put(SysAccountRole.getRoleCode(entry.getKey()),false);
                noRole = noRole + "、" + SysAccountRole.getName(entry.getKey());
            }
        }
        mobileRoleBean.setHasRole(StringUtil.isEmpty(hasRole) ? "" : hasRole.substring(1));
        mobileRoleBean.setNoRole(StringUtil.isEmpty(noRole) ? "" : noRole.substring(1));
        mobileRoleBean.setHasRoleMap(hasRoleMap);
        return mobileRoleBean;
    }

    @Override
    public ModelAndView checkPage(SecurityUser<AppLoginInfo> currentUser) {
        ModelAndView modelAndView = new ModelAndView();
        ComAccountRequest query = new ComAccountRequest();
        query.setAccountId(currentUser.getInfo().getAccountId());
        query.setReqType(RequestTypeEnum.UPGRADE_TYPE.getTypeCode());
        ComAccountRequest comAccountRequest = comAccountRequestDaoEx.selectByAccountIdAndRoleIdAndStatus(query);
        if (comAccountRequest != null){
            if (comAccountRequest.getReqStatus() != ApplyState.REJECTED_HAS_VIEW.getValue()
                    && comAccountRequest.getReqStatus() != ApplyState.THROUGH_HAS_VIEW.getValue()){
                AppBaseResult res = getShowMessage(currentUser.getInfo().getAcctUsername());
                modelAndView.addObject("msg",res.getRetMsg());
                modelAndView.addObject("status", res.getRetCode());
                modelAndView.setViewName(MobileApplyDefault.URL_FINISH);
            }
        }
        return modelAndView;
    }
}
