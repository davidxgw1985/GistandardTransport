package com.gistandard.transport.app.module.im.dao;

import com.gistandard.transport.app.module.im.bean.QueryContactsDetailData;
import com.gistandard.transport.app.module.im.bean.QueryContactsListData;
import com.gistandard.transport.app.module.im.bean.QueryContactsListParam;
import com.gistandard.transport.base.annotation.MyBatisRepository;

import java.util.List;

/**
 * Created by yujie on 2016/9/30.
 */
@MyBatisRepository
public interface InstantMessagingDao {

    /**
     * 查询联系人详情
     *
     * @param accountId
     * @return
     */
    QueryContactsDetailData queryContactsDetail(Integer accountId);

    /**
     * 根据账号、昵称查找账号个数
     *
     * @param queryContactsListParam
     * @return
     */
    int queryContactsListCount(QueryContactsListParam queryContactsListParam);

    /**
     * 根据账号、昵称、手机号查找账号列表
     *
     * @param queryContactsListParam
     * @return
     */
    List<QueryContactsListData> queryContactsList(QueryContactsListParam queryContactsListParam);
}
