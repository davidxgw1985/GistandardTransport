package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BizAttachment;
@MyBatisRepository
public interface BizAttachmentDao {
    int deleteByPrimaryKey(Integer attachId);

    int insert(BizAttachment record);

    int insertSelective(BizAttachment record);

    BizAttachment selectByPrimaryKey(Integer attachId);

    int updateByPrimaryKeySelective(BizAttachment record);

    int updateByPrimaryKey(BizAttachment record);
}