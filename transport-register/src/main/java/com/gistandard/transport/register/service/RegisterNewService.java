package com.gistandard.transport.register.service;

import com.gistandard.transport.register.bean.*;
import com.gistandard.transport.system.common.bean.ResultBean;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by yujie on 2016/3/18.
 */
public interface RegisterNewService {

	/**
	 * 根据尺寸裁剪用户头像
	 * @param portraitCutBean
	 * @return
	 * @throws Exception
	 */
	ResultBean portraitSave(PortraitCutBean portraitCutBean) throws Exception;

	/**
	 * 个人注册文件上传
	 * @param multipartFile
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	ResultBean registerFileUpload(MultipartFile multipartFile, String fileId) throws Exception;

	/**
	 * 个人注册
	 * @param accountRegisterBean
	 * @param personalRegister
	 * @param o2IdRegisterBean
	 * @throws Exception
	 */
	ResultBean normalPersonalSave(AccountRegisterBean accountRegisterBean,
								  NormalPersonalRegisterBean personalRegister, O2IdRegisterBean o2IdRegisterBean) throws Exception;

	/**
	 * 企业注册
	 * @param accountRegisterBean
	 * @param companyRegister
	 * @param o2IdRegisterBean
	 * @throws Exception
	 */
	ResultBean normalCompanySave(AccountRegisterBean accountRegisterBean,
								 NormalCompanyRegisterBean companyRegister, O2IdRegisterBean o2IdRegisterBean) throws Exception;

	/**
	 * 身份证验证正面
	 * @param identityPositiveFileId
	 * @param area
	 * @return
	 */
	ResultBean ocrRetriveIdentityInfo(Integer identityPositiveFileId, String area);

	/**
	 * 身份证验证反面
	 * @param identityNegativeFileId
	 * @param area
	 * @return
	 */
	ResultBean ocrVerifyIdentityBack(Integer identityNegativeFileId, String area);
}
