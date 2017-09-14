package com.gistandard.transport.app.module.im.service.impl;

import com.gistandard.transport.app.module.im.bean.*;
import com.gistandard.transport.app.module.im.dao.InstantMessagingDao;
import com.gistandard.transport.app.module.im.service.InstantMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yujie on 2016/9/30.
 */
@Service
public class InstantMessagingServiceImpl implements InstantMessagingService{

    @Autowired
    private InstantMessagingDao instantMessagingDao;

    @Override
    public QueryContactsDetailResult queryContactsDetail(QueryContactsDetailParam queryContactsDetailParam) {
        QueryContactsDetailResult baseResBean = new QueryContactsDetailResult();
        QueryContactsDetailData contactsDetailRes = instantMessagingDao.queryContactsDetail(queryContactsDetailParam.getContactAccountId());
        baseResBean.setData(contactsDetailRes);
        return baseResBean;
    }

    /**
     * 根据账号、昵称、手机号查找账号列表
     *
     * @param queryContactsListParam
     * @return
     */
    @Override
    public QueryContactsListResult queryContactsList(QueryContactsListParam queryContactsListParam) {
        QueryContactsListResult baseResPageBean = new QueryContactsListResult();
        int recordCount = instantMessagingDao.queryContactsListCount(queryContactsListParam);
        if (recordCount > 0) {
            List<QueryContactsListData> listRes = instantMessagingDao.queryContactsList(queryContactsListParam);
            baseResPageBean.setData(listRes);
        } else {
            baseResPageBean.setData(new ArrayList<>());
        }
        baseResPageBean.setReqId(queryContactsListParam.getReqId());
        baseResPageBean.setRecordCount(recordCount);
        return baseResPageBean;
    }
}
