package com.gistandard.transport.app.module.attachment.dao;

import java.util.List;

import com.gistandard.transport.app.module.attachment.bean.QueryPicFileListReq;
import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BizAttachment;

/**
 * @author: xgw
 * @ClassName: AttachmentDao
 * @Date: 2016/3/7
 * @Description: 附件请求服务Dao
 */
@MyBatisRepository
public interface AttachmentDao {

	/**
	 * 查询附件个数
	 * 
	 * @param queryPicFileListReq
	 * @return
	 */
	int queryPicFileListCount(QueryPicFileListReq queryPicFileListReq);

	/**
	 * 查询附件列表
	 * 
	 * @param queryPicFileListReq
	 * @return
	 */
	List<BizAttachment> queryPicFileList(QueryPicFileListReq queryPicFileListReq);

}
