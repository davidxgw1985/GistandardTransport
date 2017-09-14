package com.gistandard.transport.order.module.mobilestation.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.order.module.mobilestation.bean.want.QueryWantListReq;
import com.gistandard.transport.order.module.mobilestation.bean.want.Station;
import com.gistandard.transport.order.module.mobilestation.bean.want.WantInfoReq;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantOrderBean;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantOrderSjBean;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantReq;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantSjReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
@MyBatisRepository
public interface MobileTransportDao {

    /**
     * 我要接单和我要运输查询
     * @param req
     * @return
     */
    List<WantInfoReq> getListByLineStartAndDest(QueryWantListReq req);

    /**
     * 我要接单webservice查询，数量
     * @param mobileWantReq
     * @return
     */
    int getCountByLineStartAndLineDestAndUserNameAndStationName(MobileWantReq mobileWantReq);


    /**
     * 我要接单webservice查询，数据
     * @param mobileWantReq
     * @return
     */
    List<MobileWantOrderBean> getDataByLineStartAndLineDestAndUserNameAndStationName(MobileWantReq mobileWantReq);

    /**
     * 根据地区id，输出城市+区域汉字名称
     * @param id
     * @return
     */
    String getAddressById(@Param("id") String id);

    /**
     * 根据wantId获取多站点信息
     * @param id
     * @return
     */
    List<Station> getStationByWantId(@Param("id") int id);

    /**
     * 我要运输webservice查询，数量
     * @param mobileWantSjReq
     * @return
     */
    int getSjCount(MobileWantSjReq mobileWantSjReq);

    /**
     * 我要运输webservice查询，数据
     * @param mobileWantSjReq
     * @return
     */
    List<MobileWantOrderSjBean> getSjData(MobileWantSjReq mobileWantSjReq);

    /**
     * 我要接单和我要运输，查询数据数量
     * @param req
     * @return
     */
    int getCountByReq(QueryWantListReq req);

    /**
     * 我要接单和我要运输，删除订单
     * @param req
     * @return
     */
    int updateStatus(WantInfoReq req);
}
