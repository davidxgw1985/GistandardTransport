package com.gistandard.transport.order.module.mobilestation.service.impl;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.bean.app.BaseResPageBean;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.BizAttachmentDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComUserinfoDaoEx;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.StationReq;
import com.gistandard.transport.order.module.mobilestation.bean.userorder.ComStationBean;
import com.gistandard.transport.order.module.mobilestation.bean.want.*;
import com.gistandard.transport.order.module.mobilestation.dao.MobileTransportDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileUserOrderDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileWantService;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantOrderBean;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantOrderSjBean;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantReq;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantSjReq;
import com.gistandard.transport.system.gps.util.GeoUtil;
import com.gistandard.transport.system.upload.define.UploadFileType;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
@Service
public class MobileWantServiceImpl implements MobileWantService {

    @Autowired
    private MobileTransportDao mobileTransportDao;

    @Autowired
    private MobileTransportRelDao mobileTransportRelDao;

    @Autowired
    private MobileWantTransportDao mobileWantTransportDao;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private ComCountyService comCountyService;

    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;

    @Autowired
    private MobileUserOrderDao mobileUserOrderDao;

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private BizAttachmentDaoEx bizAttachmentDaoEx;

    /**
     * 我要接单、我要运输新增接口
     *
     * @param wantInfoReq
     * @throws Exception
     */
    @Override
    @Transactional
    public AppBaseResult saveWantInfo(WantInfoReq wantInfoReq) throws MobileStationBizException {
        AppBaseResult res = new AppBaseResult(wantInfoReq);
        if (wantInfoReq==null){
            throw new MobileStationBizException("参数错误");
        }

        MobileTransportRel mobileTransportRel;
        MobileWantTransport mobileWantTransport;
        Line line;

        //多线路，重复插入
        for (int i = 0;i<wantInfoReq.getLineList().size();i++){
            mobileWantTransport = new MobileWantTransport();
            line = wantInfoReq.getLineList().get(i);

            //我要运输，才有运输时间
            if(wantInfoReq.getWantType()==2) {
                mobileWantTransport.setLineStart(line.getLineStart());
                mobileWantTransport.setLineDest(line.getLineDest());
                mobileWantTransport.setArriveTime(line.getArriveTime());
                mobileWantTransport.setDepartTime(line.getDepartTime());
                mobileWantTransport.setStatus(wantInfoReq.STATUS_YES);
                mobileWantTransport.setCurrency(wantInfoReq.getCurrency());
                mobileWantTransport.setRespondentUser(wantInfoReq.getRespondentUser());
                mobileWantTransport.setRestLoad(wantInfoReq.getRestLoad());
                mobileWantTransport.setRestSpace(wantInfoReq.getRestSpace());
                mobileWantTransport.setWantType(wantInfoReq.getWantType());
                mobileWantTransport.setHeavyPrice(wantInfoReq.getHeavyPrice());
                mobileWantTransport.setLightPrice(wantInfoReq.getLightPrice());
                mobileWantTransport.setLowestVote(wantInfoReq.getLowestVote());

                mobileWantTransport.setCreateUserId(wantInfoReq.getAccountId());
                mobileWantTransport.setCreateUser(wantInfoReq.getAcctUsername());
                mobileWantTransport.setCreateTime(new Date());
            }else{
                mobileWantTransport.setLineStart(line.getLineStart());
                mobileWantTransport.setLineDest(line.getLineDest());
                mobileWantTransport.setStatus(WantInfoReq.STATUS_YES);
                mobileWantTransport.setCurrency(wantInfoReq.getCurrency());
                mobileWantTransport.setRespondentUser(wantInfoReq.getRespondentUser());
                mobileWantTransport.setWantType(wantInfoReq.getWantType());
                mobileWantTransport.setPerTicket(wantInfoReq.getPerTicket());

                mobileWantTransport.setCreateUserId(wantInfoReq.getAccountId());
                mobileWantTransport.setCreateUser(wantInfoReq.getAcctUsername());
                mobileWantTransport.setCreateTime(new Date());
            }

            mobileWantTransportDao.insertSelective(mobileWantTransport);
            if (wantInfoReq.getRespondentUser()==1) {
                //插入站点
                for (Station station : wantInfoReq.getRespondentUserList()){
                    mobileTransportRel = new MobileTransportRel();
                    mobileTransportRel.setStationId(station.getStationId());
                    mobileTransportRel.setStationTtl(station.getStationTtl());
                    mobileTransportRel.setWantId(mobileWantTransport.getId());
                    mobileTransportRelDao.insert(mobileTransportRel);
                }
            }
        }

        return res;
    }

    @Override
    public AppBaseResult deleteWantInfo(WantInfoReq wantInfoReq) throws MobileStationBizException{
        AppBaseResult res = new AppBaseResult(wantInfoReq);
        if (wantInfoReq==null||wantInfoReq.getId()==null||wantInfoReq.getId()==0){
            throw new MobileStationBizException("上传需要删除的订单id");
        }

        wantInfoReq.setStatus(WantInfoReq.STATUS_NO);
        mobileTransportDao.updateStatus(wantInfoReq);
        return res;
    }


    /**
     * 我要接单、我要运输列表查询
     *
     * @param queryWantListReq
     * @throws Exception
     */
    @Override
    public QueryWantListResult queryWantList(QueryWantListReq queryWantListReq) throws MobileStationBizException {
        QueryWantListResult res = new QueryWantListResult(queryWantListReq);

        List<WantInfoReq> list = new ArrayList<>();
        if(queryWantListReq.getLineStart()!=null&&queryWantListReq.getLineStart().trim().length()==0){
            queryWantListReq.setLineStart(null);
        }
        if(queryWantListReq.getLineDest()!=null&&queryWantListReq.getLineDest().trim().length()==0){
            queryWantListReq.setLineDest(null);
        }

        int count = mobileTransportDao.getCountByReq(queryWantListReq);
        if (count>0) {
            list = mobileTransportDao.getListByLineStartAndDest(queryWantListReq);
            for (int i=0;i<list.size();i++){
                //出发站点和抵达站点，通过id，查询中文名字，显示
                String start = list.get(i).getLineStart();
                String end = list.get(i).getLineDest();

                list.get(i).setLineStart(mobileTransportDao.getAddressById(start));
                list.get(i).setLineDest(mobileTransportDao.getAddressById(end));

                //我要运输，需要站点列表显示
                if (queryWantListReq.getWantType()==2) {
                    if (list.get(i).getRespondentUser() == 0) {
                        list.get(i).setRespondentUserList(new ArrayList<Station>());
                    } else {
                        list.get(i).setRespondentUserList(mobileTransportDao.getStationByWantId(list.get(i).getId()));
                    }
                }
            }

        }

        res.setRecordCount(count);
        res.setData(list);
        return res;
    }

    @Override
    public GetByLineStartAndLineDestAndUserNameAndStationNameResult getByLineStartAndLineDestAndUserNameAndStationName(MobileWantReq mobileWantReq) throws MobileStationBizException {
        GetByLineStartAndLineDestAndUserNameAndStationNameResult res = new GetByLineStartAndLineDestAndUserNameAndStationNameResult(mobileWantReq);
        if (mobileWantReq==null){
            throw new MobileStationBizException("参数错误");
        }
        List<MobileWantOrderBean> list = new ArrayList<>();

        if(mobileWantReq.getLineStart()!=null&&mobileWantReq.getLineStart()==0){
            mobileWantReq.setLineStart(null);
        }

        if(mobileWantReq.getLineDest()!=null&&mobileWantReq.getLineDest()==0){
            mobileWantReq.setLineDest(null);
        }

        if(mobileWantReq.getStationName()==null||mobileWantReq.getStationName().length()==0||mobileWantReq.getStationName().equals("null")){
            mobileWantReq.setStationName("");
        }

        if(mobileWantReq.getUserName()==null||mobileWantReq.getUserName().length()==0||mobileWantReq.getUserName().equals("null")){
            mobileWantReq.setUserName("");
        }

        int count = mobileTransportDao.getCountByLineStartAndLineDestAndUserNameAndStationName(mobileWantReq);

        if (count>0){
            list = mobileTransportDao.getDataByLineStartAndLineDestAndUserNameAndStationName(mobileWantReq);
            for (int i=0;i<list.size();i++){
                String start = list.get(i).getLineStart();
                String end = list.get(i).getLineDest();

                list.get(i).setLineStart(mobileTransportDao.getAddressById(start));
                list.get(i).setLineDest(mobileTransportDao.getAddressById(end));

            }
        }

        res.setRecordCount(count);
        res.setData(list);

        return res;
    }

    @Override
    public GetByLineStartAndLineDestAndUserNameAndStationNameRes getByLineStartAndLineDestAndUserNameAndStationName(MobileWantSjReq mobileWantSjReq) throws MobileStationBizException {
        GetByLineStartAndLineDestAndUserNameAndStationNameRes res = new GetByLineStartAndLineDestAndUserNameAndStationNameRes(mobileWantSjReq);

        if (mobileWantSjReq==null){
            throw new MobileStationBizException("参数错误");
        }
        List<MobileWantOrderSjBean> list = new ArrayList<>();

        if(mobileWantSjReq.getLineStart()!=null&&mobileWantSjReq.getLineStart()==0){
            mobileWantSjReq.setLineStart(null);
        }

        if(mobileWantSjReq.getLineDest()!=null&&mobileWantSjReq.getLineDest()==0){
            mobileWantSjReq.setLineDest(null);
        }

        if(mobileWantSjReq.getCarNo()==null||mobileWantSjReq.getCarNo().length()==0||mobileWantSjReq.getCarNo().equals("null")){
            mobileWantSjReq.setCarNo("");
        }

        if(mobileWantSjReq.getUserName()==null||mobileWantSjReq.getUserName().length()==0||mobileWantSjReq.getUserName().equals("null")){
            mobileWantSjReq.setUserName("");
        }

        if(!(mobileWantSjReq.getRestLoad()!=null&&mobileWantSjReq.getRestLoad()>0)){
            mobileWantSjReq.setRestLoad(null);
        }

        if(!(mobileWantSjReq.getRestSpace()!=null&&mobileWantSjReq.getRestSpace()>0)){
            mobileWantSjReq.setRestSpace(null);
        }

        if(mobileWantSjReq.getDepartTime()!=null&&mobileWantSjReq.getDepartTime().length()<8){
            mobileWantSjReq.setDepartTime(null);
        }

        if(mobileWantSjReq.getArriveTime()!=null&&mobileWantSjReq.getArriveTime().length()<8){
            mobileWantSjReq.setArriveTime(null);
        }

        int count = mobileTransportDao.getSjCount(mobileWantSjReq);

        if (count>0){
            list = mobileTransportDao.getSjData(mobileWantSjReq);
            for (int i=0;i<list.size();i++){
                String start = list.get(i).getLineStart();
                String end = list.get(i).getLineDest();

                list.get(i).setLineStart(mobileTransportDao.getAddressById(start));
                list.get(i).setLineDest(mobileTransportDao.getAddressById(end));

            }
        }

        res.setRecordCount(count);
        res.setData(list);

        return res;
    }

    @Override
    public BaseResPageBean getStation(StationReq stationReq) throws MobileStationBizException{
        BaseResPageBean res = new BaseResPageBean();
        res.setReqId(stationReq.getReqId());
        if (stationReq==null){
            throw new MobileStationBizException("参数错误");
        }

        List<ComCustomer> stationList;
        List<ComStationBean> comStationBeanList = new ArrayList<>();
        int count = mobileUserOrderDao.getCountStationByStationName(stationReq);
        if (count>0){
            stationList = mobileUserOrderDao.getStationByCityAndCountyNameAndStationName(stationReq);
            try {
                ComAccount comAccount;
                List<BizAttachment> bizAttachmentList;
                BizAttachment bizAttachment;
                for (ComCustomer station : stationList) {
                    ComStationBean comStationBean = new ComStationBean();
                    PropertyUtils.copyProperties(comStationBean, station);
                    //设置距离
                    double distance = GeoUtil.calcDistance(stationReq.getLongitude(), stationReq.getLatitude(), station.getStaLongitude().doubleValue(), station.getStaLatitude().doubleValue());
                    comStationBean.setDistance(distance);
                    //设置头像
                    comAccount = comAccountDao.selectByPrimaryKey(station.getAccountId());
                    if (comAccount==null||comAccount.getUserImg()==null||comAccount.getUserImg().length()==0){
                        comStationBean.setHeadImage("");
                    }else{
                        comStationBean.setHeadImage(comAccount.getUserImg());
                    }

                    //设置营业执照
                    bizAttachment = new BizAttachment();
                    bizAttachment.setUploadUser(station.getAccountId());
                    bizAttachment.setFileType(UploadFileType.OPERATE_LICENSE.getValue());
                    bizAttachmentList = bizAttachmentDaoEx.querybyConditions(bizAttachment);
                    if (bizAttachmentList != null && bizAttachmentList.size() > 0) {
                        comStationBean.setYyzzImage(bizAttachmentList.get(0).getAttachPath());
                    }

                    comStationBean.setImAccount(comAccount.getAcctUsername());
                    ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(comAccount.getId());
                    if (comUserinfo==null){
                        comStationBean.setTelephone(null);
                    }else{
                        comStationBean.setTelephone(comUserinfo.getTelephone());
                    }

                    comStationBeanList.add(comStationBean);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        res.setData(comStationBeanList);
        res.setRecordCount(count);
        return res;
    }
}
