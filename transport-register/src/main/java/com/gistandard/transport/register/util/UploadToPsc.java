package com.gistandard.transport.register.util;

import com.gistandard.transport.base.entity.bean.BizAttachment;
import com.gistandard.transport.base.entity.bean.BizAttachmentRecord;
import com.gistandard.transport.system.upload.define.UploadFileType;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.common.bean.AttachmentInfo;

/**
 * Created by m on 2016/2/3.
 */
public class UploadToPsc {

	private static final String SEPARATOR = "/";

	/**
	 * 将上传文件数据转化成PSC系统所需数据
	 * 
	 * @param bizAttachment
	 *            附件类型ID（存储在PSC系统数据库中）
	 */
	public static AttachmentInfo uploadToPscByRecord(BizAttachmentRecord bizAttachment) {
		AttachmentInfo comAttachment = new AttachmentInfo();
		// 设置上传用户
		comAttachment.setAccount(StringUtil.getObjStr(bizAttachment.getUploadUser()));
		// 设置文件名称
		comAttachment.setAttachmentName(bizAttachment.getAttachName());
		// 设置文件原名称
		comAttachment.setAttachmentOldName(bizAttachment.getAttachOldName());
		// 设置文件类型
		comAttachment.setAttachmentMime(bizAttachment.getAttachType());
		// 设置文件大小
		comAttachment.setAttachmentSize(bizAttachment.getAttachSize());
		// 设置文件状态
		comAttachment.setAttachmentStatus(bizAttachment.getAttachState());
		// 设置项目路径
		comAttachment.setProjectPath(bizAttachment.getProjectPath());
		// 设置文件保存路径（相对路径，不包括文件名）
		String attachPath = bizAttachment.getAttachPath();
		if (!StringUtil.isEmpty(attachPath)) {
			if (attachPath.indexOf(bizAttachment.getAttachName()) != -1) {
				int pos = attachPath.lastIndexOf(SEPARATOR);
				comAttachment.setSavePath(attachPath.substring(0, pos));
			}
		}

		// 设置文件上传时间
		comAttachment.setAttachmentCreateTime(bizAttachment.getUploadTime());
		// 设置文件类型
		comAttachment.setAttachmentTypeId(bizAttachment.getFileType());
		// 设置文件上传服务器
		comAttachment.setServerIp(bizAttachment.getAttachServerIp());
		comAttachment.setAttachmentTypeName(UploadFileType.getName(bizAttachment.getFileType()));

		return comAttachment;
	}

	public static AttachmentInfo uploadToPsc(BizAttachment bizAttachment) {
		AttachmentInfo comAttachment = new AttachmentInfo();
		// 设置上传用户
		comAttachment.setAccount(StringUtil.getObjStr(bizAttachment.getUploadUser()));
		// 设置文件名称
		comAttachment.setAttachmentName(bizAttachment.getAttachName());
		// 设置文件原名称
		comAttachment.setAttachmentOldName(bizAttachment.getAttachOldName());
		// 设置文件类型
		comAttachment.setAttachmentMime(bizAttachment.getAttachType());
		// 设置文件大小
		comAttachment.setAttachmentSize(bizAttachment.getAttachSize());
		// 设置文件状态
		comAttachment.setAttachmentStatus(bizAttachment.getAttachState());
		// 设置项目路径
		comAttachment.setProjectPath(bizAttachment.getProjectPath());
		// 设置文件保存路径（相对路径，不包括文件名）
		String attachPath = bizAttachment.getAttachPath();
		if (!StringUtil.isEmpty(attachPath)) {
			if (attachPath.indexOf(bizAttachment.getAttachName()) != -1) {
				int pos = attachPath.lastIndexOf(SEPARATOR);
				comAttachment.setSavePath(attachPath.substring(0, pos));
			}
		}

		// 设置文件上传时间
		comAttachment.setAttachmentCreateTime(bizAttachment.getUploadTime());
		// 设置文件类型
		comAttachment.setAttachmentTypeId(bizAttachment.getFileType());
		// 设置文件上传服务器
		comAttachment.setServerIp(bizAttachment.getAttachServerIp());
		comAttachment.setAttachmentTypeName(UploadFileType.getName(bizAttachment.getFileType()));
		comAttachment.setUploadDomain(bizAttachment.getAttachDomain());
		return comAttachment;
	}

}
