package com.gistandard.transport.order.wechat.dao;

import com.gistandard.transport.app.dubbo.wechat.bean.AddressInfo;
import com.gistandard.transport.app.dubbo.wechat.bean.WeChatAddressQueryReq;
import com.gistandard.transport.base.annotation.MyBatisRepository;

import java.util.List;

/**
 * Created by m on 2017/2/7.
 */
@MyBatisRepository
public interface WeChatAddressDao {
    /**
     * 查询地址类型查询地址
     * @param weChatAddressQueryReq
     * @return
     */
    List<AddressInfo> queryAddressByType(WeChatAddressQueryReq weChatAddressQueryReq);
}
