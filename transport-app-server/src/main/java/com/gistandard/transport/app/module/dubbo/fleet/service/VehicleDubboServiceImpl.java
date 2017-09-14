package com.gistandard.transport.app.module.dubbo.fleet.service;

import com.gistandard.transport.app.dubbo.fleet.bean.*;
import com.gistandard.transport.app.dubbo.fleet.service.VehicleDubboService;
import com.gistandard.transport.app.dubbo.pojo.res.TableResultDubboBean;
import com.gistandard.transport.app.dubbo.register.bean.RegResultBean;
import com.gistandard.transport.app.module.dubbo.fleet.dao.FleetDubboDao;
import com.gistandard.transport.base.entity.bean.BizAttachment;
import com.gistandard.transport.base.entity.bean.ComAccessoryRelation;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComVehicleInfo;
import com.gistandard.transport.base.entity.dao.BizAttachmentDao;
import com.gistandard.transport.base.entity.dao.ComAccessoryRelationDao;
import com.gistandard.transport.base.entity.dao.ComVehicleInfoDao;
import com.gistandard.transport.base.entity.dao.ex.BizAttachmentDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComDriverInfoDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComVehicleInfoDaoEx;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuming on 2017/6/19 0019.
 */
public class VehicleDubboServiceImpl implements VehicleDubboService {

    private final static Logger logger = LoggerFactory.getLogger(VehicleDubboServiceImpl.class);

    @Autowired
    private ComVehicleInfoDao comVehicleInfoDao;

    @Autowired
    private ComVehicleInfoDaoEx comVehicleInfoDaoEx;

    @Autowired
    private BizAttachmentDao bizAttachmentDao;

    @Autowired
    private ComDriverInfoDaoEx comDriverInfoDaoEx;

    @Autowired
    private FleetDubboDao fleetDubboDao;

    @Autowired
    private ComAccessoryRelationDao comAccessoryRelationDao;

    @Autowired
    private BizAttachmentDaoEx bizAttachmentDaoEx;

    @Override
    public RegResultBean addVehicle(VehicleInfoBean vehicleInfoBean) throws Exception {
        RegResultBean resultBean = new RegResultBean();

        if(vehicleInfoBean == null ||  StringUtils.isEmpty(vehicleInfoBean.getTruckcode()) ||
                StringUtils.isEmpty(vehicleInfoBean.getDrivingCode())||
                StringUtil.isEmpty(vehicleInfoBean.getCompanyAccountId())||
                CollectionUtils.isEmpty(vehicleInfoBean.getBizAttachmentList()) ||
                vehicleInfoBean.getBizAttachmentList().size()==0){
            resultBean.setMessage("参数错误");
            return resultBean;
        }

        // 车辆信息备案表
        ComVehicleInfo comVehicleInfo = new ComVehicleInfo();

        PropertyUtils.copyProperties(comVehicleInfo, vehicleInfoBean);
        comVehicleInfo.setId(null);
        comVehicleInfo.setHasgps(vehicleInfoBean.isHasgps());
        // 设置车辆与帐号的绑定关系
        comVehicleInfoDao.insert(comVehicleInfo);

        List<ComAccessoryRelation> accessoryRelationList = new ArrayList<ComAccessoryRelation>();
        List<Integer> attachmentList = new ArrayList<Integer>();
        for(BizAttachmentPar bizAttachmentPar : vehicleInfoBean.getBizAttachmentList()){
            BizAttachment bizAttachment = new BizAttachment();
            PropertyUtils.copyProperties(bizAttachment, bizAttachmentPar);
            bizAttachment.setAttachId(null);
            bizAttachment.setUploadTime(new Date());
            bizAttachment.setAttachState(1);
            bizAttachment.setUploadUser(vehicleInfoBean.getCompanyAccountId());
            bizAttachmentDao.insert(bizAttachment);
            attachmentList.add(bizAttachment.getAttachId());
        }
        bindRelIdAndAttachRelation(accessoryRelationList, attachmentList, comVehicleInfo.getId(), "COM_VEHICLE_INFO");
        if (accessoryRelationList.size() > 0) {
            // 插入数据到附件关联表
            comAccessoryRelationDao.batchInsert(accessoryRelationList);
        }

        if(!StringUtil.isEmpty(vehicleInfoBean.getDriverAccountId())){
            comDriverInfoDaoEx.updateByAccountId(comVehicleInfo.getId(), vehicleInfoBean.getDriverAccountId());
        }
        resultBean.setState(true);
        return resultBean;
    }

    @Override
    public RegResultBean updateVehicle(VehicleInfoBean vehicleInfoBean) throws Exception{
        RegResultBean resultBean = new RegResultBean();

        if(vehicleInfoBean == null ||  StringUtil.isEmpty(vehicleInfoBean.getId())||
                StringUtil.isEmpty(vehicleInfoBean.getCompanyAccountId())){
            resultBean.setMessage("参数错误");
            return resultBean;
        }

        // 车辆信息备案表
        ComVehicleInfo comVehicleInfo = new ComVehicleInfo();

        PropertyUtils.copyProperties(comVehicleInfo, vehicleInfoBean);
        comVehicleInfo.setHasgps(vehicleInfoBean.isHasgps());
        // 设置车辆与帐号的绑定关系
        comVehicleInfoDao.updateByPrimaryKey(comVehicleInfo);
        List<ComAccessoryRelation> accessoryRelationList = new ArrayList<ComAccessoryRelation>();
        List<Integer> attachmentList = new ArrayList<Integer>();
        if (CollectionUtils.isNotEmpty(vehicleInfoBean.getBizAttachmentList()) && vehicleInfoBean.getBizAttachmentList().size()>0){
            for (BizAttachmentPar bizAttachmentPar : vehicleInfoBean.getBizAttachmentList()){
                BizAttachment bizAttachment = new BizAttachment();
                PropertyUtils.copyProperties(bizAttachment, bizAttachmentPar);
                if (StringUtil.isEmpty(bizAttachment.getAttachId())){
                    bizAttachmentDao.insert(bizAttachment);
                    attachmentList.add(bizAttachment.getAttachId());
                }else {
                    bizAttachmentDao.updateByPrimaryKeySelective(bizAttachment);
                }
            }
            if (attachmentList.size()>0){
                bindRelIdAndAttachRelation(accessoryRelationList, attachmentList, comVehicleInfo.getId(), "COM_VEHICLE_INFO");
                if (accessoryRelationList.size() > 0) {
                    // 插入数据到附件关联表
                    comAccessoryRelationDao.batchInsert(accessoryRelationList);
                }
            }
        }
        resultBean.setState(true);
        return resultBean;
    }

    @Override
    public TableResultDubboBean queryVehicle(QueryVehiclePar queryVehiclePar) {
        TableResultDubboBean resultDubboBean = new TableResultDubboBean();
        resultDubboBean.setRows(fleetDubboDao.queryVehicle(queryVehiclePar));
        resultDubboBean.setTotal(fleetDubboDao.queryVehicleCount(queryVehiclePar));
        return resultDubboBean;
    }

    @Override
    public RegResultBean delVehicle(VehicleParBean vehicleParBean) {
        RegResultBean resultBean = new RegResultBean();
        if(StringUtil.isEmpty(vehicleParBean.getCompanyAccountId()) || StringUtil.isEmpty(vehicleParBean.getVehicleId())){
            resultBean.setMessage("参数错误");
            return resultBean;
        }
        int result = comVehicleInfoDaoEx.deleteByVehicleId(vehicleParBean.getVehicleId(), vehicleParBean.getCompanyAccountId());
        if (result>0){
            if(!StringUtil.isEmpty(vehicleParBean.getDriverAccountId())){
                comDriverInfoDaoEx.updateSetNullByAccountId(vehicleParBean.getVehicleId(), vehicleParBean.getDriverAccountId());
            }
            resultBean.setState(true);
        }else {
            resultBean.setMessage("删除失败");
        }
        return resultBean;
    }

    @Override
    public RegResultBean bindVehicleToDriver(VehicleParBean vehicleParBean) {
        RegResultBean resultBean = new RegResultBean();
        if(StringUtil.isEmpty(vehicleParBean.getCompanyAccountId()) || StringUtil.isEmpty(vehicleParBean.getVehicleId())
                || StringUtil.isEmpty(vehicleParBean.getDriverAccountId())){
            resultBean.setMessage("参数错误");
            return resultBean;
        }
        int result = comDriverInfoDaoEx.updateByAccountId(vehicleParBean.getVehicleId(), vehicleParBean.getDriverAccountId());
        if(result>0){
            resultBean.setState(true);
        }else {
            resultBean.setMessage("绑定失败");
        }
        return resultBean;
    }

    @Override
    public RegResultBean unbindVehicleToDriver(VehicleParBean vehicleParBean) {
        RegResultBean resultBean = new RegResultBean();
        if(StringUtil.isEmpty(vehicleParBean.getCompanyAccountId()) || StringUtil.isEmpty(vehicleParBean.getVehicleId())
                || StringUtil.isEmpty(vehicleParBean.getDriverAccountId())){
            resultBean.setMessage("参数错误");
            return resultBean;
        }
        int result = comDriverInfoDaoEx.updateSetNullByAccountId(vehicleParBean.getVehicleId(), vehicleParBean.getDriverAccountId());
        if(result>0){
            resultBean.setState(true);
        }else {
            resultBean.setMessage("解除绑定失败");
        }
        return resultBean;
    }

    @Override
    public TableResultDubboBean queryunbindDriver(QueryunbindDriverPar queryunbindDriverPar) throws Exception {
        TableResultDubboBean resultDubboBean = new TableResultDubboBean();
        resultDubboBean.setRows(fleetDubboDao.queryUnbindDriver(queryunbindDriverPar));
        resultDubboBean.setTotal(fleetDubboDao.queryUnbindDriverCount(queryunbindDriverPar));
        return resultDubboBean;
    }

    @Override
    public RegResultBean queryVehicleDetails(Integer id) throws Exception {
        RegResultBean resultBean = new RegResultBean();
        ComVehicleInfo comVehicleInfo = comVehicleInfoDao.selectByPrimaryKey(id);
        if (comVehicleInfo != null && !StringUtil.isEmpty(comVehicleInfo.getId())){
            VehicleInfoBean vehicleInfoBean = new VehicleInfoBean();
            PropertyUtils.copyProperties(vehicleInfoBean, comVehicleInfo);
            List<BizAttachment> list = bizAttachmentDaoEx.getbyRelCond(id, "COM_VEHICLE_INFO");
            List<BizAttachmentPar> bizAttachmentList = new ArrayList<BizAttachmentPar>();
            if(CollectionUtils.isNotEmpty(list) && list.size()>0){
                for (BizAttachment bizAttachment :list){
                    BizAttachmentPar bizAttachmentPar = new BizAttachmentPar();
                    PropertyUtils.copyProperties(bizAttachmentPar, bizAttachment);
                    bizAttachmentList.add(bizAttachmentPar);
                }
                vehicleInfoBean.setBizAttachmentList(bizAttachmentList);
            }
            resultBean.setData(vehicleInfoBean);
            resultBean.setState(true);
        }else {
            resultBean.setMessage("查询失败");
        }
        return resultBean;
    }

    @Override
    public RegResultBean queryAccountByPar(QueryAccountPar queryAccountPar) {
        RegResultBean resultBean = new RegResultBean();
        if (queryAccountPar ==null || StringUtil.isEmpty(queryAccountPar.getRoleId())){
            resultBean.setMessage("roleId不能为空");
            return resultBean;
        }
        try {
            List<QueryAccountPar> list = fleetDubboDao.queryAccountByPar(queryAccountPar);
            if (CollectionUtils.isNotEmpty(list) && list.size()>0){
                resultBean.setData(list);
                resultBean.setState(true);
            }
        }catch (Exception E){
            E.getMessage();
        }

        return  resultBean;
    }


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
}
