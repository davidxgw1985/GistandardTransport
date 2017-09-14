package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.BizAttachment;
import com.gistandard.transport.base.entity.dao.BizAttachmentDao;
import com.gistandard.transport.base.entity.dao.ex.BizAttachmentDaoEx;
import com.gistandard.transport.base.entity.service.BizAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yujie
 * @ClassName BizAttachmentServiceImpl
 * @Description
 * @Version 1.0
 * @Date 2015-07-21
 */
@Service
public class BizAttachmentServiceImpl implements BizAttachmentService {

    @Autowired
    private BizAttachmentDao bizAttachmentDao;

    @Autowired
    private BizAttachmentDaoEx bizAttachmentDaoEx;

    @Override
    public BizAttachment queryAttachById(Integer attachId) {
        return bizAttachmentDao.selectByPrimaryKey(attachId);
    }

    @Override
    public List<BizAttachment> queryAttachByIds(List<Integer> idList) {
        return bizAttachmentDaoEx.queryAttachByIds(idList);
    }

	@Override
	public List<BizAttachment> getbyRelCond(Integer relaId, String tTab) {
		return bizAttachmentDaoEx.getbyRelCond(relaId, tTab);
	}

    /**
     * 根据账户Id查询附件
     * @param acctId
     * @return
     */
    @Override
    public List<BizAttachment> queryAttachByAcctId(Integer acctId){
        return bizAttachmentDaoEx.queryAttachByAcctId(acctId);
    }
}
