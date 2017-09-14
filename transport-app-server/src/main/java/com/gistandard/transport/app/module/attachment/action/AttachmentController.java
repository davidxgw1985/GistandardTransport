package com.gistandard.transport.app.module.attachment.action;

import java.util.List;

import com.gistandard.transport.app.module.attachment.bean.QueryPicFileListReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gistandard.transport.app.module.attachment.bean.PicFileAddReq;
import com.gistandard.transport.app.module.attachment.bean.PicFileDeleteReq;
import com.gistandard.transport.app.module.attachment.bean.QueryResultBean;
import com.gistandard.transport.app.module.attachment.service.AttachmentService;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.BizAttachment;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.upload.service.UploadService;
import com.gistandard.transport.tools.util.StringUtil;

/**
 * @author xgw
 * @ClassName AttachmentController
 * @Description 附件操作请求
 * @Version 1.0
 * @Date 2016-03-06
 */
@Controller
@RequestMapping(value = AppServerDefine.ATTACHMENT_URL)
@Api(value = "附件上传接口", tags = "附件上传接口")
public class AttachmentController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AttachmentController.class);

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private UploadService uploadService;

	@ApiOperation(value = "送达确认附件上传-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
			produces = "application/json;charset=UTF-8",
			notes = "上传附件，其中reqId安卓必填、账户ID、要保存附件关联的 tableNameType表的id、关联表的值、文件传的类型以及文件必填！")
	@RequestMapping(value = "/picFileUpload", method = { RequestMethod.POST })
	@ResponseBody
	public AppBaseResult picFileUpload(@RequestBody PicFileAddReq picFileAddReq, MultipartHttpServletRequest request) throws Exception {
		AppBaseResult res = new AppBaseResult();
		if (picFileAddReq == null || StringUtil.isEmpty(picFileAddReq.getId())
				|| StringUtil.isEmpty(picFileAddReq.getAccountId())
				|| StringUtil.isEmpty(picFileAddReq.getTableNameType())
				|| StringUtil.isEmpty(picFileAddReq.getUploadFileType())) {
			logger.info("送达确认上传附件的参数为空!");
			res.setRetCode(SystemDefine.FAILURE);
			res.setRetMsg("送达确认上传附件的参数为空!");
		}

		logger.info("送达确认附件上传 picFileUpload id = {},appAccountId={},tableNameType={},uploadFileType={}",
				picFileAddReq.getId(), picFileAddReq.getAccountId(), picFileAddReq.getTableNameType(),
				picFileAddReq.getUploadFileType());
		MultipartFile file = request.getFile("file");
		// 获取文件为空，返回false
		if (file == null) {
			res.setRetCode(SystemDefine.FAILURE);
			res.setRetMsg("送达确认上传附件不能为空！");
			return res;
		}

		// 上传附件，并上传记录插入到数据库中
		BizAttachment bizAttachment = uploadService.uploadFile(file, picFileAddReq.getUploadFileType(),
				picFileAddReq.getAccountId());
		if (bizAttachment == null) {
			res.setRetCode(SystemDefine.FAILURE);
			res.setRetMsg("送达确认上传附件失败！");
		} else {
			bizAttachment.setAttachRelId(picFileAddReq.getId());
			BaseResBean addResultBean = attachmentService.addPicFile(bizAttachment, picFileAddReq);
			res.setReqId(addResultBean.getReqId());
			res.setRetCode(addResultBean.getRetCode());
			res.setRetMsg(addResultBean.getRetMsg());
		}
		return res;
	}

	/**
	 * 送达确认附件删除
	 * 
	  附件ID
	 */
	@ApiOperation(value = "附件删除-V1.0.1", httpMethod = "POST", response = AppBaseResult.class, produces = "application/json;charset=UTF-8", notes = "删除附件，其中请求reqId安卓必填、账户ID、图片文件编号必填！")
	@RequestMapping(value = "/deletePicFile", method = RequestMethod.POST)
	@ResponseBody
	public AppBaseResult deletePicFile(@RequestBody PicFileDeleteReq picFileDeleteReq) throws Exception {
		AppBaseResult res = new AppBaseResult();
		// 如果上传图片ID为空，返回false
		if (picFileDeleteReq != null && !StringUtil.isEmpty(picFileDeleteReq.getPicFileId())) {
			logger.info("送达确认附件删除 picFileDelete picFileId = {}", picFileDeleteReq.getPicFileId());
			BaseResBean delResultBean = attachmentService.deletePicFile(picFileDeleteReq);
			res.setReqId(delResultBean.getReqId());
			res.setRetCode(delResultBean.getRetCode());
			res.setRetMsg(delResultBean.getRetMsg());
		} else {
			logger.info("送达确认附件删除picFileId为空!");
			res.setRetCode(SystemDefine.FAILURE);
			res.setRetMsg("请求参数不能为空！");
		}
		return res;
	}

	/**
	 * 获取送达确认上传附件列表
	 * 
	 *   包含订单ID和页数信息
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "附件删除-V1.0.1", httpMethod = "POST", response = QueryResultBean.class, produces = "application/json;charset=UTF-8", notes = "删除附件，其中请求reqId安卓必填、账户ID、订单编号、表名必填！")
	@RequestMapping(value = "/queryPicFileList", method = RequestMethod.POST)
	@ResponseBody
	public AppBaseResult queryPicFileList(@RequestBody QueryPicFileListReq queryPicFileListReq) throws Exception {
		QueryResultBean res = new QueryResultBean();
		// 如果订单ID为空，返回false
		if (queryPicFileListReq == null || StringUtil.isEmpty(queryPicFileListReq.getOrderId())) {
			logger.info("送达确认上传附件列表的订单ID为空!");
			res.setRetCode(SystemDefine.FAILURE);
			res.setRetMsg("送达确认上传附件列表的订单ID为空!");
		} else {
			logger.info("送达确认上传附件列表订单ID orderId = {}", queryPicFileListReq.getOrderId());
			BaseResBean queryResultBean = attachmentService.queryPicFileList(queryPicFileListReq);
			res.setReqId(queryResultBean.getReqId());
			res.setRetCode(queryResultBean.getRetCode());
			res.setRetMsg(queryResultBean.getRetMsg());
			res.setData((List<BizAttachment>)queryResultBean.getData());
			res.setRecordCount(queryResultBean.getRetCode());
		}
		return res;
	}

}
