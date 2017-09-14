package com.gistandard.transport.base.entity.service;


import com.gistandard.transport.base.entity.bean.BizAttachment;

import java.util.List;

/**
 * @author yujie
 * @ClassName BizAttachmentService
 * @Description
 * @Version 1.0
 * @Date 2015-07-21
 */
public interface BizAttachmentService {

    BizAttachment queryAttachById(Integer attachId);

    /**
     * 根据ID批量查询附件
     * @param idList
     * @return
     */
    List<BizAttachment> queryAttachByIds(List<Integer> idList);
    
    /**
     * 根据附件关联表数据查询附件
    * <p>Title: getbyRelCond</p>
    * <p>Description: </p>
    * @param relaId
    * @param tTab
    * @return
     */
    List<BizAttachment> getbyRelCond(Integer relaId, String tTab);

    /**
     * 根据账户Id查询附件
     * @param acctId
     * @return
     */
    List<BizAttachment> queryAttachByAcctId(Integer acctId);
}
