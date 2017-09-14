package com.gistandard.transport.app.module.attachment.service;

import com.gistandard.transport.app.module.attachment.bean.PicFileAddReq;
import com.gistandard.transport.app.module.attachment.bean.PicFileDeleteReq;
import com.gistandard.transport.app.module.attachment.bean.QueryPicFileListReq;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.entity.bean.BizAttachment;

/**
 * @author: xgw
 * @ClassName: AttachmentService
 * @Date: 2016/3/5
 * @Description: 附件请求服务
 */
public interface AttachmentService {

	/**
	 * 删除上传附件信息
	 * 
	 * @param picFileDeleteReq
	 * @return
	 */
	BaseResBean deletePicFile(PicFileDeleteReq picFileDeleteReq);

	/**
	 * 查询附件列表信息
	 * 
	 * @param queryPicFileListReq
	 * @return
	 */
	BaseResBean queryPicFileList(QueryPicFileListReq queryPicFileListReq);

	/**
	 * 新增上传附件
	 * 
	 * @param bizAttachment
	 * @param picFileAddReq
	 * @return
	 */
	BaseResBean addPicFile(BizAttachment bizAttachment, PicFileAddReq picFileAddReq);
}
