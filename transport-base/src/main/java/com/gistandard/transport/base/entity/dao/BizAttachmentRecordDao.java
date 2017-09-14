package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BizAttachmentRecord;

@MyBatisRepository
public interface BizAttachmentRecordDao {
    int deleteByPrimaryKey(Integer attachId);

    int insert(BizAttachmentRecord record);

    int insertSelective(BizAttachmentRecord record);

    BizAttachmentRecord selectByPrimaryKey(Integer attachId);

    int updateByPrimaryKeySelective(BizAttachmentRecord record);

    int updateByPrimaryKey(BizAttachmentRecord record);
}