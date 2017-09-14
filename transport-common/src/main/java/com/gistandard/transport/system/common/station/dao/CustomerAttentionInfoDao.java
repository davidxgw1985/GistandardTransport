package com.gistandard.transport.system.common.station.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.AttentionInfo;

/**
 * 服务商关注Dao
 */
@MyBatisRepository
public interface CustomerAttentionInfoDao {
    void updateStatus(AttentionInfo attentionInfo);

    int count(AttentionInfo attentionInfo);

    AttentionInfo queryByAttionInfo(AttentionInfo attentionInfo);
}