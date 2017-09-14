package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccessoryRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ComAccessoryRelationDaoEx {

    /**
     * 根据ID更新关联的附件ID
     * @param id
     * @return
     */
    int updateAttachIdById(@Param(value = "id") Integer id, @Param(value = "attachId") Integer attachId);

    /**
     * 根据ID列表，批量删除附件关联关系
     * @param list
     * @return
     */
    int batchDelByRelationIdList(List<Integer> list);

    /**
     *  根据附件ID,删除附件关联关系
     * @param attachId
     * @return
     */
    int deleteAttachID(Integer attachId);

    int batchInsert(List<ComAccessoryRelation> list);

    
    List<ComAccessoryRelation> getAccessoryRelation(@Param(value = "relaId") Integer relaId, @Param(value = "tTab") String tTab);
    
    int deleteByCond(@Param(value = "relaId") Integer relaId, @Param(value = "tTab") String tTab, @Param(value = "attachId") Integer attachId);
}