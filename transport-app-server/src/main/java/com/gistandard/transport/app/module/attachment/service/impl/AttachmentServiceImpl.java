package com.gistandard.transport.app.module.attachment.service.impl;

import java.util.List;

import com.gistandard.transport.app.module.attachment.bean.QueryPicFileListReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gistandard.transport.app.module.attachment.bean.PicFileAddReq;
import com.gistandard.transport.app.module.attachment.bean.PicFileDeleteReq;
import com.gistandard.transport.app.module.attachment.dao.AttachmentDao;
import com.gistandard.transport.app.module.attachment.service.AttachmentService;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.app.BaseResPageBean;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.BizAttachment;
import com.gistandard.transport.base.entity.bean.ComAccessoryRelation;
import com.gistandard.transport.base.entity.dao.BizAttachmentDao;
import com.gistandard.transport.base.entity.dao.ComAccessoryRelationDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccessoryRelationDaoEx;
import com.gistandard.transport.system.common.define.TableNameDefine;
import com.gistandard.transport.system.upload.service.UploadService;

/**
 * @author: xgw
 * @ClassName: AttachmentServiceImpl
 * @Date: 2016/3/5
 * @Description: 附件请求服务Impl
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;

	@Autowired
	private BizAttachmentDao bizAttachmentDao;

	@Autowired
	private ComAccessoryRelationDao comAccessoryRelationDao;

	@Autowired
	private ComAccessoryRelationDaoEx comAccessoryRelationDaoEx;

	@Autowired
	private UploadService uploadService;

	/**
	 * 新增附件信息
	 * 
	 * @param bizAttachment
	 * @param picFileAddReq
	 * @return
	 */
	@Override
	public BaseResBean addPicFile(BizAttachment bizAttachment, PicFileAddReq picFileAddReq) {
		BaseResBean baseResBean = new BaseResBean();
		baseResBean.setReqId(picFileAddReq.getReqId());
		baseResBean.setRetCode(SystemDefine.SUCCESS);
		try {
			int result = bizAttachmentDao.updateByPrimaryKey(bizAttachment);

			if (result > 0) {
				ComAccessoryRelation comAccessoryRelation = new ComAccessoryRelation();
				comAccessoryRelation.setRelaId(bizAttachment.getAttachRelId());
				comAccessoryRelation.setAttachId(bizAttachment.getAttachId());
				comAccessoryRelation.settTab(TableNameDefine.getName(picFileAddReq.getTableNameType()));
				result = comAccessoryRelationDao.insert(comAccessoryRelation);
				if (result <= 0) {
					baseResBean.setRetCode(SystemDefine.FAILURE);
					baseResBean.setRetMsg("插入附件关联关系表失败！");
				}
			} else {
				baseResBean.setRetCode(SystemDefine.FAILURE);
				baseResBean.setRetMsg("更新附件表失败！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			baseResBean.setRetCode(SystemDefine.FAILURE);
		}
		return baseResBean;
	}

	/**
	 * 删除上传附件
	 * 
	 * @param picFileDeleteReq
	 * @return
	 */
	@Override
	@Transactional
	public BaseResBean deletePicFile(PicFileDeleteReq picFileDeleteReq) {
		BaseResBean baseResBean = new BaseResBean();
		baseResBean.setReqId(picFileDeleteReq.getReqId());
		BizAttachment picFile = bizAttachmentDao.selectByPrimaryKey(Integer.valueOf(picFileDeleteReq.getPicFileId()));
		if (picFile == null) {
			// 图片没查到返回失败
			baseResBean.setRetCode(SystemDefine.FAILURE);
			baseResBean.setRetMsg("未查询到附件！");
		} else {
			try {
				if (!uploadService.deleteFile(picFile)) {
					baseResBean.setRetCode(SystemDefine.FAILURE);
					baseResBean.setRetMsg("删除附件失败！");
				} else {
					// 删除附件关联表信息
					comAccessoryRelationDaoEx.deleteAttachID(picFile.getAttachId());
				}
			} catch (Exception e) {
				baseResBean.setRetCode(SystemDefine.FAILURE);
			}
		}
		return baseResBean;
	}

	/**
	 * 查询附件列表信息
	 * 
	 * @param queryPicFileListReq
	 * @return
	 */
	@Override
	public BaseResBean queryPicFileList(QueryPicFileListReq queryPicFileListReq) {
		BaseResPageBean baseResPageBean = new BaseResPageBean();
		baseResPageBean.setReqId(queryPicFileListReq.getReqId());
		queryPicFileListReq.setTableName("MOBILE_BOOKING_FORM");
		int recordCount = attachmentDao.queryPicFileListCount(queryPicFileListReq);
		if (recordCount > 0) {
			List<BizAttachment> picFileList = attachmentDao.queryPicFileList(queryPicFileListReq);
			baseResPageBean.setRecordCount(recordCount);
			baseResPageBean.setData(picFileList);
		}
		return baseResPageBean;
	}
}
