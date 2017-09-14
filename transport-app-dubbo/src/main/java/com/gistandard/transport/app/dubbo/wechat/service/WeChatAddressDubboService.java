package com.gistandard.transport.app.dubbo.wechat.service;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.app.dubbo.wechat.bean.*;

import java.util.List;

/**
 * Created by yjf on 2017/2/6.
 */
public interface WeChatAddressDubboService {

    /**
     * 查询地址类型查询地址
     * @param weChatAddressQueryReq
     * @return
     */
    List<AddressInfo> queryAddressByType(WeChatAddressQueryReq weChatAddressQueryReq);

    /**
     * 新增地址
     * 返回地址簿ID
     * @param address
     * @return
     */
    WebAddressInfoRes addAddressInfo(AddressInfo address);

    /**
     * 删除，修改状态,并不是物理删除
     *
     * @param addrIds
     *            地址数组
     * @weChatId  微信iD  （选填）
     * @accountId 绑定o2id的AccountID （选填）
     * @return 删除的条数
     */
    MsDubboResBean delete(Integer[] addrIds,String weChatId,Integer accountId);

    /**
     * 修改地址
     * @param address
     * @return
     */
    MsDubboResBean updateAddressInfo(AddressInfo address);

    /**
     * 区县查询街道
     * @param countyId
     * @return
     */
    List<WeChatComTown> getTownListByConutyId(Integer countyId);


    /**
     * 获取订单坐标信息
     * @param weChatOrderCdntReq 请求
     * @return 获取的订单坐标信息
     */
    WeChatOrderCdntRes getOrderCoordinateInfo(WeChatOrderCdntReq weChatOrderCdntReq);


}
