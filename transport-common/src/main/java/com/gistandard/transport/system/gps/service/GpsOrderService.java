package com.gistandard.transport.system.gps.service;

import com.gistandard.transport.system.gps.bean.DataResult;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import com.gistandard.transport.system.gps.bean.GiPositionAcct;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: GpsOrderService
 * @Date: 2017/1/17
 * @Description: 广播单发送、取消队列
 */
public interface GpsOrderService {

    /**
     * 发送、取消广播单队列消息
     *
     * @param giBizOrderList
     */
    void sendBroadCastOrderMessage(final List<GiBizOrder> giBizOrderList);

    /**
     * 判断是否上传GPS坐标
     * 查询该用户是否GPS在线, 即是否向GPS上传位置
     * 有3种可以不在线
     * 1) GPS没有该账号记录
     * 2) 最后一次上传位置的时间已超过5分钟
     * 3) 与要求的角色不匹配
     *
     * @param acctUserName
     * @param roleId
     */
    DataResult getGpsOnlineStatus(String acctUserName, Integer roleId);

    /**
     * 发送用户企业登录队列消息
     * @param giPositionAcct
     */
    void sendUserLoginCompanyMessage(final GiPositionAcct giPositionAcct);
}
