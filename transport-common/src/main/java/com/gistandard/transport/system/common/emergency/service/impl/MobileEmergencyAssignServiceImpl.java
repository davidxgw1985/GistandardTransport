package com.gistandard.transport.system.common.emergency.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComUserinfo;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComUserinfoDaoEx;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.emergency.bean.GetNearMsBean;
import com.gistandard.transport.system.common.emergency.bean.GetNearMsDataResult;
import com.gistandard.transport.system.common.emergency.bean.GetNearMsReq;
import com.gistandard.transport.system.common.emergency.bean.GiPositionUser;
import com.gistandard.transport.system.common.emergency.service.MobileEmergencyAssignService;
import com.gistandard.transport.system.gps.util.GeoUtil;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xgw
 * @ClassName MobileEmergencyAssignServiceImpl
 * @Description 紧急指派
 * @Version 3.0
 * @Date 2016-06-17
 */
@Service
public class MobileEmergencyAssignServiceImpl implements MobileEmergencyAssignService {

    private static final Logger logger = LoggerFactory.getLogger(MobileEmergencyAssignServiceImpl.class);
    @Autowired
    private ComAccountDaoEx comAccountDao;


    @Autowired
    private PnWebService pnWebService;

    @Autowired
    private ComUserinfoDaoEx comUserinfoDao;


    /**
     * 获取距离范围内附近的MS接口
     *
     * @param getNearMsReq
     * @throws Exception
     */
    @Override
    public GetNearMsDataResult queryUsersByLocation(GetNearMsReq getNearMsReq) throws MobileStationBizException {
        GetNearMsDataResult baseResBean = new GetNearMsDataResult();
        List<GetNearMsBean> nearMsBeanList = new ArrayList<>();
        try {
            String roleCode;
            if (getNearMsReq.getScope() == 0) {//获取外卖附近ms
                roleCode = SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode();
                getNearMsReq.setProductType(MobileStationDefine.PRODUCT_TYPE_0TCWM);
            } else if (getNearMsReq.getScope() == 2) {//获取附近司机ms
                roleCode = SysAccountRole.OPERATOR_CAR_OWNER.getRoleCode();
                getNearMsReq.setProductType(MobileStationDefine.PRODUCT_TYPE_TCYS);
            } else {//获取附近快递员
                roleCode = SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode();
                getNearMsReq.setProductType(MobileStationDefine.PRODUCT_TYPE_TCKD);
            }
            String jsonStr = pnWebService.getAllGiPositionUserByLanLatRadApptagModulesRoles(getNearMsReq.getLongitude(), getNearMsReq.getLatitude(), getNearMsReq.getRadius(), getNearMsReq.getAppTag(), getNearMsReq.getProductType(), roleCode);

            logger.info("获取距离范围内附近的MS接口返回:" + jsonStr);
            List<GiPositionUser> giPositionUserList = JSON.parseArray(jsonStr, GiPositionUser.class);
            ComAccount comAccount;
            for (GiPositionUser giPositionUser : giPositionUserList) {
                comAccount = comAccountDao.queryByAccount(giPositionUser.getUserCode());
                if (comAccount == null) {
                    continue;
                }
                GetNearMsBean getNearMsBean = new GetNearMsBean();
                getNearMsBean.setLatitude(giPositionUser.getGiPoint().getLatitude());
                getNearMsBean.setLongitude(giPositionUser.getGiPoint().getLongitude());
                getNearMsBean.setAddress(giPositionUser.getAddress());
                getNearMsBean.setUserCode(giPositionUser.getUserCode());
                getNearMsBean.setUserName(giPositionUser.getUserName());
                getNearMsBean.setTelephone(giPositionUser.getTelephone());
                getNearMsBean.setAccountId(comAccount.getId());
                getNearMsBean.setUserImg(comAccount.getUserImg());
                getNearMsBean.setScope(getNearMsReq.getScope());
                getNearMsBean.setDistance(GeoUtil.calcDistance(giPositionUser.getGiPoint().getLongitude(), giPositionUser.getGiPoint().getLatitude(), getNearMsReq.getLongitude(), getNearMsReq.getLatitude()));


                ComUserinfo comUserinfo = comUserinfoDao.queryByAcctId(comAccount.getId());
                if (comUserinfo == null) {
                    continue;
                }
                getNearMsBean.setId(comUserinfo.getId());
                nearMsBeanList.add(getNearMsBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MobileStationBizException("查询附近的MS失败！");
        }
        baseResBean.setData(nearMsBeanList);
        return baseResBean;
    }

}
