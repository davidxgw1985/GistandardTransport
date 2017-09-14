package com.gistandard.transport.app.module.im.service;

import com.gistandard.transport.app.module.im.bean.QueryContactsDetailParam;
import com.gistandard.transport.app.module.im.bean.QueryContactsDetailResult;
import com.gistandard.transport.app.module.im.bean.QueryContactsListParam;
import com.gistandard.transport.app.module.im.bean.QueryContactsListResult;

/**
 * Created by yujie on 2016/9/30.
 */
public interface InstantMessagingService {


    /**
     * 查询联系人详情
     *
     * @param queryContactsDetailParam
     * @return
     */
    QueryContactsDetailResult queryContactsDetail(QueryContactsDetailParam queryContactsDetailParam);


    /**
     * 根据账号、昵称、手机号查找账号列表
     *
     * @param queryContactsListParam
     * @return
     */
    QueryContactsListResult queryContactsList(QueryContactsListParam queryContactsListParam);
}
