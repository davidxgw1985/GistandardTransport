package com.gistandard.transport.order.module.mobilestation.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.HubInOrdermst;
import com.gistandard.transport.base.entity.bean.ScheduCar;
import com.gistandard.transport.order.module.mobilestation.bean.ScheduCarUpdateBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileScheduOrderDao
 * @Date: 2016/3/25
 * @Description: 运输指派单操作
 */
@MyBatisRepository
public interface MobileScheduOrderDao {
    /**
     * 修改派车单表状态
     *
     * @param scheduCarUpdateBean
     * @return
     */
    int updateScheduCarStatus(ScheduCarUpdateBean scheduCarUpdateBean);


    ScheduCar selectScheduCarByNo(String scheducarno);

    /**
     * 修改HUB入库表状态
     *
     * @param scheduCarUpdateBean
     * @return
     */
    int updateHubInStatus(ScheduCarUpdateBean scheduCarUpdateBean);

    /**
     * 查询HUB入库表状态
     *
     * @param busiBookNo
     * @return
     */
    List<HubInOrdermst> selectHubInOrdermstByBusiBookNo(@Param("busiBookNo") String busiBookNo);
}
