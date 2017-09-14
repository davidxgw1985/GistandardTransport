package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileScheduSubOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 子订单数据持久化
* @className  MobileScheduSubOrderDaoEx 
* @describe  TODO
* @author  M.simple 
* @datetime  2016年6月14日 上午11:02:57
 */
@MyBatisRepository
public interface MobileScheduSubOrderDaoEx {

	/**
	 * 查询子订单接口
	 * @return
	 * @author M.simple  
	 * @version 3.0
	 */
    MobileScheduSubOrder selectMobiScheduSubOrderByBusibookno(@Param("busiBookNo") String busiBookNo);

	/**
	 * 查询子订单接口
	 * @return
	 * @author M.simple
	 * @version 3.0
	 */
    MobileScheduSubOrder selectMobiScheduSubOrderByBusibookno2(@Param("busiBookNo") String busiBookNo,
                                                               @Param("roleId") Integer roleId,
                                                               @Param("busiCtrl") Integer busiCtrl);

	/**
	 * 查询子订单接口
	 * @return
	 * @author M.simple
	 * @version 3.0
	 */
    MobileScheduSubOrder selectMobiScheduSubOrderByBusibookno3(@Param("busiBookNo") String busiBookNo,
                                                               @Param("busiCtrl") Integer busiCtrl, @Param("destLocus") String destLocus);



    int updateByMoblieBookingFormId(MobileScheduSubOrder record);

    /**
     * 批量插入
     *
     * @param recordList
     * @return
     */
    int batchInsert(List<MobileScheduSubOrder> recordList);

    /**
     * 更新子单状态
     */
    int updateStatusByMobileBookingFormId(@Param("orderId") Integer orderId, @Param("bfStatus") Integer bfStatus, @Param("afStatus") Integer afStatus);

    List<MobileScheduSubOrder> selectByScheducarno(@Param("scheducarno") String scheducarno,
                                                   @Param("revUser") String revUser,
                                                   @Param("roleId") Integer roleId);

    /**
     * 根据主派车单ID查询子派车单
     * <p>Title: selectMobileSubOrderByMobileId</p>
     * <p>Description: </p>
     * @param orderId
     * @return
     */
    List<MobileScheduSubOrder> selectMobileSubOrderByMobileId(Integer orderId);

    /**
     * 查询子订单接口
     * @return
     * @author M.simple
     * @version 3.0
     */
    MobileScheduSubOrder selectWaSubOrderByBusibookno(@Param("busiBookNo") String busiBookNo,
                                                               @Param("roleId") Integer roleId,
                                                               @Param("busiCtrl") Integer busiCtrl);

}