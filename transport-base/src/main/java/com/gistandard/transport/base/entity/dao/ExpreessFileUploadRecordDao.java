package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ExpreessFileUploadRecord;

@MyBatisRepository
public interface ExpreessFileUploadRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ExpreessFileUploadRecord record);

    int insertSelective(ExpreessFileUploadRecord record);

    ExpreessFileUploadRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExpreessFileUploadRecord record);

    int updateByPrimaryKey(ExpreessFileUploadRecord record);
}