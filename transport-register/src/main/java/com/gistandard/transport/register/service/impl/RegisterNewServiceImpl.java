package com.gistandard.transport.register.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.register.bean.*;
import com.gistandard.transport.register.define.RegisterDefine;
import com.gistandard.transport.register.service.RegisterNewService;
import com.gistandard.transport.register.util.UploadToPsc;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.system.upload.service.UploadService;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.common.bean.*;
import com.valueplus.psc.dubbo.service.common.define.RecordOperatorTypeEnum;
import com.valueplus.psc.dubbo.service.common.define.UploadFileType;
import com.valueplus.psc.dubbo.service.ocr.OcrService;
import com.valueplus.psc.dubbo.service.ocr.bean.IdentityBean;
import com.valueplus.psc.dubbo.service.ocr.bean.OcrParamBean;
import com.valueplus.psc.dubbo.service.ocr.define.OcrType;
import com.valueplus.psc.dubbo.service.record.PscRecordService;
import com.valueplus.psc.dubbo.service.record.bean.ManagerInfoRelativeBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by yujie on 2016/3/18.
 */
@Service
public class RegisterNewServiceImpl implements RegisterNewService {

	private final static Logger LOG = LoggerFactory.getLogger(RegisterNewServiceImpl.class);

	@Autowired
	private ComAccountDao comAccountDao;

	@Autowired
	private ComAccountDaoEx comAccountDaoEx;

	@Autowired
	private PscRecordService pscRecordService;

	@Autowired
	private ComUserinfoDao comUserinfoDao;

	@Autowired
	private ComUserinfoDaoEx comUserinfoDaoEx;

	@Autowired
	private ComAccountRoleRelDao comAccountRoleRelDao;

	@Autowired
	private ComAccessoryRelationDaoEx comAccessoryRelationDaoEx;

	@Autowired
	private BizAttachmentDao bizAttachmentDao;

	@Autowired
	private BizAttachmentDaoEx bizAttachmentDaoEx;

	@Autowired
	private ComCustomerDao comCustomerDao;

	@Autowired
	private ComCityService comCityService;

	@Autowired
	private ComCountyService comCountyService;

	@Autowired
	private ComCustomerRelationDao comCustomerRelationDao;

	@Autowired
	private UploadService uploadService;

	@Autowired
	private ComCountryService countryService;

	@Autowired
	private ComProvinceService provinceService;

	@Autowired
	private ComCityService cityService;

	@Autowired
	private ComCountyService countyService;

	@Autowired
	private BizAttachmentService bizAttachmentService;

	@Autowired
	private OcrService ocrService;

	@Autowired
	private BizAttachmentRecordDao bizAttachmentRecordDao;

	/**
	 * 运输平台地址
	 */
	@Value("#{spring.msAppUrl}")
	private String msAppUrl;

	@Value("#{spring.fastDFSUrl}")
	private String fastDFSUrl;

	/**
	 * 上传文件
	 * @param multipartFile
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean registerFileUpload(MultipartFile multipartFile, String fileId) throws Exception {
		ResultBean resultBean = new ResultBean();
		int fileType = -1;
		if (RegisterDefine.IDENTITY_POSITIVE_FILE_ID.equals(fileId)) {
			fileType = UploadFileType.IDENTITY_POSITIVE.getValue();
		} else if (RegisterDefine.IDENTITY_NEGATIVE_FILE_ID.equals(fileId)) {
			fileType = UploadFileType.IDENTITY_NEGATIVE.getValue();
		} else if (RegisterDefine.PORTRAIT_FILE_ID.equals(fileId)) {
			fileType = UploadFileType.PORTRAIT.getValue();
		} else if (RegisterDefine.LICENSE_FILE_ID.equals(fileId)) {
			fileType = UploadFileType.OPERATE_LICENSE.getValue();
		} else if (RegisterDefine.TAX_REGISTER_FILE_ID.equals(fileId)) {
			fileType = UploadFileType.TAX_REGISTER.getValue();
		} else if (RegisterDefine.ORG_CODE_LILCENSE_FILE_ID.equals(fileId)) {
			fileType = UploadFileType.ORG_CODE_LILCENSE.getValue();
		} else if (RegisterDefine.IDENTITY_HALF_FILE_ID.equals(fileId)) {
			fileType = UploadFileType.IDENTITY_HALF.getValue();
		}
		LOG.info("文件上传fileType："+fileType);
		if (fileType == -1) {
			resultBean.setMessage("文件上传异常");
			return resultBean;
		}
		BizAttachment bizAttachment = uploadService.uploadDfsFile(multipartFile, fileType);
		if (bizAttachment == null) {
			resultBean.setMessage("文件上传失败");
			return resultBean;
		}
		resultBean.setState(bizAttachment.getAttachId() != null);
		resultBean.setData(bizAttachment.getAttachId());
		resultBean.setMessage(fastDFSUrl + bizAttachment.getAttachPath());
		return resultBean;
	}

	/**
	 * 保存头像
	 * @param portraitCutBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean portraitSave(PortraitCutBean portraitCutBean) throws Exception {
		ResultBean resultBean = new ResultBean();
		BizAttachment bizAttachment = bizAttachmentDao.selectByPrimaryKey(portraitCutBean.getFileId());
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String projectRootPath = request.getSession().getServletContext().getRealPath("/");
		String fileRelativePath = bizAttachment.getAttachPath();
		String fileFullPath = projectRootPath + fileRelativePath;
		if (projectRootPath.endsWith("/") && fileRelativePath.startsWith("/")) {
			fileFullPath = projectRootPath.substring(0, projectRootPath.length() - 1) + fileRelativePath;
		}
		String fileName = bizAttachment.getAttachName();
		// 获取文件后缀
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		File imgFile = new File(fileFullPath);
		if("png".equalsIgnoreCase(suffix)){
			//如果是png图片，就转换为jpeg，否则图片裁剪报错
			BufferedImage bi = ImageIO.read(new FileInputStream(imgFile));
			//ImageIO.write((RenderedImage)bufferedImage, "jpg", file);
			ImageIO.write((RenderedImage) bi, "JPEG", imgFile);
			suffix = "JPEG";
		}
		Iterator iterator = ImageIO.getImageReadersByFormatName(suffix);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(imgFile);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		// 原文件的宽度
		int sourcePicWidth = reader.getWidth(0);
		// 源文件的高度
		int sourcePicHeight = reader.getHeight(0);
		double widthRatio = (double) sourcePicWidth / (double) portraitCutBean.getShowPicWidth();
		double heightRatio = (double) sourcePicHeight / (double) portraitCutBean.getShowPicHeight();
		int cutRealWidth = (int) (portraitCutBean.getCutPicWidth() * widthRatio);
		int cutRealHeight = (int) (portraitCutBean.getCutPicHeight() * heightRatio);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle((int) (portraitCutBean.getStartX() * widthRatio),
				(int) (portraitCutBean.getStartY() * heightRatio), cutRealWidth, cutRealHeight);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		String portraitFilePath = fileRelativePath.substring(0, fileRelativePath.lastIndexOf(".")) + "_100_100"
				+ fileRelativePath.substring(fileRelativePath.lastIndexOf("."));
		String dest = projectRootPath + portraitFilePath;
		boolean result = ImageIO.write(bi, suffix, new File(dest));
		resultBean.setState(result);

		resultBean.setMessage(msAppUrl + portraitFilePath);

		String newName = bizAttachment.getAttachName().substring(0, bizAttachment.getAttachName().lastIndexOf("."))
				+ "_100_100" + bizAttachment.getAttachName().substring(bizAttachment.getAttachName().lastIndexOf("."));
		bizAttachment.setAttachPath(portraitFilePath);
		bizAttachment.setAttachName(newName);
		bizAttachmentDao.updateByPrimaryKey(bizAttachment);
		return resultBean;
	}

	/**
	 * 个人注册
	 * @param accountRegisterBean
	 * @param personalRegister
	 * @param o2IdRegisterBean
	 * @throws Exception
	 */
	@Override
	@Transactional
	public ResultBean normalPersonalSave(AccountRegisterBean accountRegisterBean,NormalPersonalRegisterBean personalRegister,
										 O2IdRegisterBean o2IdRegisterBean) throws Exception {
		// 注册相关数据
		RecordInfoBean recordInfoBean = new RecordInfoBean();
		recordInfoBean.setRecordType(RecordOperatorTypeEnum.ADD.getType());
		recordInfoBean.setAcctType(SysAccountRole.NORMAL_PERSONAL.getRoleCode());

		// 用于生成帐号的数据
		GistandardNoBean gistandardNoBean = new GistandardNoBean();
		gistandardNoBean.setCountryCode(accountRegisterBean.getArea());
		gistandardNoBean.setTelCode(accountRegisterBean.getTelCode());
		gistandardNoBean.setCityId(NumberUtils.toInt(accountRegisterBean.getProvinceAndCity_sel()));
		gistandardNoBean.setUserNo(o2IdRegisterBean.getUserNo());
		gistandardNoBean.setAccountVerifyUUID(o2IdRegisterBean.getAccountId());
		recordInfoBean.setGistandardNoBean(gistandardNoBean);

		// 帐号相关信息
		AccountInfo accountInfo = new AccountInfo();
		// 转换省市区县信息
		transAddress(accountRegisterBean, accountInfo);
		accountInfo.setAcctType(accountRegisterBean.getAcctType());
		accountInfo.setTelPrefix(accountRegisterBean.getTelPrefix());
		accountInfo.setTelephone(accountRegisterBean.getTelephone().trim());
		accountInfo.setAccountTypes(SysAccountRole.NORMAL_PERSONAL.getValue()+"");
		accountInfo.setRegisterType(RegisterDefine.REGISTER_USER_TYPE);
		accountInfo.setAcctPassword(o2IdRegisterBean.getAcctPassword());
		accountInfo.setShareStatus(true);
		if(accountRegisterBean.getRecommendAccountId() != null){
			ComAccount recommendAccount = comAccountDao.selectByPrimaryKey(accountRegisterBean.getRecommendAccountId());
			if(recommendAccount!=null && !StringUtil.isEmpty(recommendAccount.getId())){
				accountInfo.setRecommendAccountId(recommendAccount.getId());
			}
		}
		accountInfo.setRecommendAccountId(accountRegisterBean.getRecommendAccountId());
		accountInfo.setRecommendFrom(accountRegisterBean.getRecommendFrom());
		recordInfoBean.setAccountInfo(accountInfo);

		// 用户信息数据
		recordInfoBean.setUserPropertyList(buildRecordUserInfo(accountRegisterBean, personalRegister));

		ServiceResult result = pscRecordService.recordOnce(RegisterDefine.buildTransportAuthInfo(), recordInfoBean);
		LOG.info("个人注册返回结果：{}", JSON.toJSONString(result));
		if(result.isResult()){
			ComAccount comAccount = new ComAccount();
			AccountInfo accountInfoResult = (AccountInfo)result.getData();
			PropertyUtils.copyProperties(comAccount, result.getData());
			comAccount.setId(accountInfoResult.getAccountId());
			comAccount.setId(accountInfoResult.getAccountId());
			comAccount.setCountryId(Integer.valueOf(accountRegisterBean.getCountry()));
			comAccount.setProvinceId(Integer.valueOf(accountRegisterBean.getProvince()));
			comAccount.setCityId(Integer.valueOf(accountRegisterBean.getCity()));
			comAccount.setCountyId(Integer.valueOf(accountRegisterBean.getCounty()));
			comAccount.setShareStatus(true);
			comAccountDao.insert(comAccount);

			ComUserinfo comUserinfo = new ComUserinfo();
			// 复制请求对象中属性到人员信息对象中
			comUserinfo.setAccountId(comAccount.getId());
			comUserinfo.setTelephone(accountRegisterBean.getTelephone().trim());
			comUserinfoDao.insert(comUserinfo);
			LOG.info("运输平台数据库普通人员记录：{}", JSON.toJSONString(comUserinfo));

			// 账号角色关联表
			ComAccountRoleRel comAccountRoleRel = new ComAccountRoleRel();
			comAccountRoleRel.setAccountId(comAccount.getId());
			comAccountRoleRel.setRoleId(SysAccountRole.NORMAL_PERSONAL.getValue());
			comAccountRoleRel.setRelId(comUserinfo.getId());
			comAccountRoleRelDao.insert(comAccountRoleRel);
		}

		ResultBean resultBean = new ResultBean();
		resultBean.setState(result.isResult());
		resultBean.setMessage(result.getMsg());
		resultBean.setData(result.getData());
		return resultBean;
	}

	/**
	 * 企业注册
	 * @param accountRegisterBean
	 * @param companyRegister
	 * @param o2IdRegisterBean
	 * @throws Exception
	 */
	@Override
	@Transactional
	public ResultBean normalCompanySave(AccountRegisterBean accountRegisterBean, NormalCompanyRegisterBean companyRegister,
										O2IdRegisterBean o2IdRegisterBean) throws Exception {
		// 注册相关数据
		RecordInfoBean recordInfoBean = new RecordInfoBean();
		recordInfoBean.setRecordType(RecordOperatorTypeEnum.ADD.getType());
		recordInfoBean.setAcctType(SysAccountRole.NORMAL_COMPANY.getRoleCode());
		recordInfoBean.setCompanyBizNo(companyRegister.getCustComno().trim());

		// 用于生成帐号的数据
		GistandardNoBean gistandardNoBean = new GistandardNoBean();
		gistandardNoBean.setCountryCode(accountRegisterBean.getArea());
		gistandardNoBean.setTelCode(accountRegisterBean.getTelCode());
		gistandardNoBean.setCityId(NumberUtils.toInt(accountRegisterBean.getProvinceAndCity_sel()));
		gistandardNoBean.setUserNo(o2IdRegisterBean.getUserNo());
		gistandardNoBean.setAccountVerifyUUID(o2IdRegisterBean.getAccountId());
		recordInfoBean.setGistandardNoBean(gistandardNoBean);

		// 帐号相关信息
		AccountInfo accountInfo = new AccountInfo();
		// 转换省市区县信息
		transAddress(accountRegisterBean, accountInfo);
		accountInfo.setAcctType(accountRegisterBean.getAcctType());
		accountInfo.setUserImg(companyRegister.getUserImg());
		accountInfo.setRealName(companyRegister.getRealName().trim());
		accountInfo.setTelPrefix(accountRegisterBean.getTelPrefix());
		accountInfo.setTelephone(accountRegisterBean.getTelephone().trim());
		accountInfo.setAccountTypes(SysAccountRole.NORMAL_COMPANY.getRoleCode());
		accountInfo.setRegisterType(accountRegisterBean.getRegisterType());
		accountInfo.setAcctPassword(o2IdRegisterBean.getAcctPassword());
		recordInfoBean.setAccountInfo(accountInfo);

		// 企业信息数据
		recordInfoBean.setCompanyPropertyList(buildRecordCompanyInfo(accountRegisterBean, companyRegister));

		// 附件
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(companyRegister.getLicenseFileId());
		idList.add(companyRegister.getPortraitFileId());
		recordInfoBean.setAttachmentList(buildAttachmentInfo(idList));

		recordInfoBean.setManagerInfoRelativeBeanList(companyRegister.getManagers());

		ServiceResult result = pscRecordService.recordOnce(RegisterDefine.buildTransportAuthInfo(),recordInfoBean);
		if(RegisterDefine.REGISTER_SERVICE_CENTER_TYPE.equals(accountRegisterBean.getRegisterType())){
			LOG.info("业务中心注册返回结果：{}", JSON.toJSONString(result));
		}else{
			LOG.info("企业注册返回结果：{}", JSON.toJSONString(result));
		}
		if(result.isResult()) {
			ComAccount comAccount = new ComAccount();
			AccountInfo accountInfoResult = (AccountInfo)result.getData();
			PropertyUtils.copyProperties(comAccount, result.getData());
			comAccount.setId(accountInfoResult.getAccountId());
			comAccountDao.insert(comAccount);

			ComCustomer comCustomer = new ComCustomer();
			PropertyUtils.copyProperties(comCustomer, accountRegisterBean);
			comCustomer.setAccountId(comAccount.getId());
			comCustomer.setCustComno(companyRegister.getCustComno().trim());
			comCustomer.setCustName(companyRegister.getRealName().trim());
			comCustomerDao.insert(comCustomer);
			if(RegisterDefine.REGISTER_SERVICE_CENTER_TYPE.equals(accountRegisterBean.getRegisterType())){
				LOG.info("运输平台数据库业务中心企业记录：{}", JSON.toJSONString(comCustomer));
			}else{
				LOG.info("运输平台数据库企业记录：{}", JSON.toJSONString(comCustomer));
			}

			// 账号角色关联表
			ComAccountRoleRel comAccountRoleRel = new ComAccountRoleRel();
			comAccountRoleRel.setAccountId(comAccount.getId());
			comAccountRoleRel.setRoleId(SysAccountRole.NORMAL_COMPANY.getValue());
			comAccountRoleRel.setRelId(comCustomer.getId());
			comAccountRoleRelDao.insert(comAccountRoleRel);

			// 企业父子关系关联表
			ComCustomerRelation comCustomerRelation = new ComCustomerRelation();
			comCustomerRelation.setCustomId(comCustomer.getId());
			comCustomerRelation.setParentCustomId(RegisterDefine.CUSTOMER_PARENT_ROOT_ID);
			comCustomerRelation.setParentPath(StringUtil.getObjStr(comCustomer.getId()));
			comCustomerRelationDao.insert(comCustomerRelation);

			List<ComAccessoryRelation> relationList = new ArrayList<ComAccessoryRelation>();
			List<Integer> attachIdList = new ArrayList<Integer>();
			if (companyRegister.getLicenseFileId() != null) {
				attachIdList.add(companyRegister.getLicenseFileId());
			}
			if (companyRegister.getPortraitFileId() != null) {
				attachIdList.add(companyRegister.getPortraitFileId());
			}
			// 附件表关联的表是企业信息表
			if (attachIdList.size() > 0) {
				comAccessoryRelationDaoEx.deleteByCond(comAccount.getId(),"COM_ACCOUNT",null);
				bindRelIdAndAttachRelation(relationList, attachIdList, comAccount.getId(), "COM_ACCOUNT");
				comAccessoryRelationDaoEx.batchInsert(relationList);
				bizAttachmentDaoEx.batchUpdateUploadUser(comAccount.getId(), attachIdList);
			}

			if(CollectionUtils.isNotEmpty(companyRegister.getManagers())){
				for(ManagerInfoRelativeBean manager : companyRegister.getManagers()){
					ComAccount comAccountManager = comAccountDaoEx.queryByAccount(manager.getManagerO2Id());
					if(comAccountManager != null){
						ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(comAccountManager.getId());
						comUserinfo.setCustomerId(comCustomer.getId());
						comUserinfoDao.updateByPrimaryKey(comUserinfo);
					}
				}
			}
		}

		ResultBean resultBean = new ResultBean();
		resultBean.setState(result.isResult());
		resultBean.setMessage(result.getMsg());
		resultBean.setData(result.getData());
		return resultBean;
	}

	/**
	 * 身份证验证正面
	 * @param identityPositiveFileId
	 * @param area
	 */
	public ResultBean ocrRetriveIdentityInfo(Integer identityPositiveFileId,String area) {
		BizAttachmentRecord bizAttachmentRecord = bizAttachmentRecordDao.selectByPrimaryKey(identityPositiveFileId);
		ResultBean resultBean = new ResultBean();
		try{
			URL url = new URL(bizAttachmentRecord.getAttachDomain() + bizAttachmentRecord.getAttachPath());
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			//创建一个Buffer字符串
			byte[] buffer = new byte[1024];
			int len = 0;
			while( (len=inStream.read(buffer)) != -1 ){
				outStream.write(buffer, 0, len);
			}
			inStream.close();
			OcrParamBean ocrParamBean = new OcrParamBean();
			ocrParamBean.setPicArr(outStream.toByteArray());
			if (area.contains("CN")){
				ocrParamBean.setTypeId(OcrType.SECOND_IDENTITY_CARD_POSITIVE.getValue());
			} else if (area.contains("TW")) {
				ocrParamBean.setTypeId(OcrType.TW_IDENTITY_CARD_POSITIVE.getValue());
			} else if(area.contains("HK")){
				ocrParamBean.setTypeId(OcrType.HK_IDENTITY_CARD.getValue());
			}
			ocrParamBean.setExtensionType(bizAttachmentRecord.getAttachName().substring(bizAttachmentRecord.getAttachName().indexOf(".")));

			ServiceResult<IdentityBean> result = ocrService.ocrRetriveIdentityInfo(RegisterDefine.buildTransportAuthInfo(), ocrParamBean);
			LOG.info("身份证正面识别结果：{}",result.isResult());
			if(result.isResult()){
				resultBean.setState(true);
				OcrResultBean ocrResultBean = new OcrResultBean();
				IdentityBean identityBean = (IdentityBean)result.getData();
				ocrResultBean.setIdentno(identityBean.getIdentityNo());
				ocrResultBean.setRealName(identityBean.getUserName());
				LOG.info("身份证正面识别结果：身份证号码{}，姓名{}", identityBean.getIdentityNo(),identityBean.getUserName());
				resultBean.setData(ocrResultBean);
			}else{
				resultBean.setState(false);
			}
		}catch(Exception e){
			resultBean.setState(false);
		}
		return resultBean;
	}

	/**
	 * 身份证验证反面
	 * @param identityNegativeFileId
	 * @param area
	 */
	public ResultBean ocrVerifyIdentityBack(Integer identityNegativeFileId,String area) {
		BizAttachmentRecord bizAttachmentRecord = bizAttachmentRecordDao.selectByPrimaryKey(identityNegativeFileId);
		ResultBean resultBean = new ResultBean();
		try{
			URL url = new URL(bizAttachmentRecord.getAttachDomain() + bizAttachmentRecord.getAttachPath());
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			//创建一个Buffer字符串
			byte[] buffer = new byte[1024];
			int len = 0;
			while( (len=inStream.read(buffer)) != -1 ){
				outStream.write(buffer, 0, len);
			}
			inStream.close();

			OcrParamBean ocrParamBean = new OcrParamBean();
			ocrParamBean.setPicArr(outStream.toByteArray());
			ocrParamBean.setExtensionType(bizAttachmentRecord.getAttachName().substring(bizAttachmentRecord.getAttachName().indexOf(".")));
			if (area.contains("CN")){
				ocrParamBean.setTypeId(OcrType.SECOND_IDENTITY_CARD_NEGATIVE.getValue());
				ServiceResult<IdentityBean> result = ocrService.ocrVerifyIdentityBack(RegisterDefine.buildTransportAuthInfo(), ocrParamBean);
				LOG.info("身份证反面识别结果：{}",JSON.toJSONString(result));
				resultBean.setState(result.isResult());
			} else if (area.contains("TW")) {
				ocrParamBean.setTypeId(OcrType.TW_IDENTITY_CARD_NEGATIVE.getValue());
				ServiceResult<IdentityBean> result = ocrService.ocrVerifyIdentityBack(RegisterDefine.buildTransportAuthInfo(), ocrParamBean);
				LOG.info("身份证反面识别结果：{}",JSON.toJSONString(result));
				resultBean.setState(result.isResult());
			} else if(area.contains("HK")){
				resultBean.setState(true);
			}
		}catch(Exception e){
			resultBean.setState(false);
		}
		return resultBean;
	}

	/**
	 * 设置附件关联表数据
	 * @param relationList
	 * @param attachList
	 * @param relId
	 * @param tableName
	 */
	private void bindRelIdAndAttachRelation(List<ComAccessoryRelation> relationList, List<Integer> attachList,
											Integer relId, String tableName) {
		if (attachList != null && attachList.size() > 0) {
			for (Integer attachId : attachList) {
				ComAccessoryRelation comAccessoryRelation = new ComAccessoryRelation();
				comAccessoryRelation.setAttachId(attachId);
				comAccessoryRelation.settTab(tableName);
				comAccessoryRelation.setRelaId(relId);
				relationList.add(comAccessoryRelation);
			}
		}
	}

	/**
	 * 解析省市区
	 * @param accountRegisterBean
	 */
	private void transAddress(AccountRegisterBean accountRegisterBean,AccountInfo accountInfo) {
		// 设置默认国家
		accountRegisterBean.setCountry(RegisterDefine.REGISTER_DEFAULT_COUNTRY);
		accountInfo.setCountryId(RegisterDefine.REGISTER_DEFAULT_COUNTRY_INT);
		// 设置省市区
		if (StringUtils.isEmpty(accountRegisterBean.getProvinceAndCity())) {
			return;
		}
		String[] tempArray = accountRegisterBean.getProvinceAndCity().split("-");
		if (tempArray != null && tempArray.length > 0) {
			// 只选择到城市
			if (tempArray.length == 2) {
				Map<String, ComCity> cityMap = comCityService.queryForMap();
				if (cityMap != null && cityMap.get(accountRegisterBean.getProvinceAndCity_sel()) != null) {
					ComCity comCity = cityMap.get(accountRegisterBean.getProvinceAndCity_sel());
					accountRegisterBean.setCity(accountRegisterBean.getProvinceAndCity_sel());
					accountRegisterBean.setProvince(comCity.getProvinceId().toString());

					accountInfo.setCityId(comCity.getId());
					accountInfo.setProvinceId(comCity.getProvinceId());
				}
			} else if (tempArray.length == 3) {
				// 选择到区县
				Map<String, ComCounty> countyMap = comCountyService.queryForMap();
				if (countyMap != null && countyMap.get(accountRegisterBean.getProvinceAndCity_sel()) != null) {
					ComCounty comCounty = countyMap.get(accountRegisterBean.getProvinceAndCity_sel());
					accountRegisterBean.setCounty(comCounty.getId().toString());
					accountRegisterBean.setCity(comCounty.getCityId().toString());
					accountRegisterBean.setProvince(comCounty.getProvinceId().toString());

					accountInfo.setCountyId(comCounty.getId());
					accountInfo.setCityId(comCounty.getCityId());
					accountInfo.setProvinceId(comCounty.getProvinceId());
				}
			}
		}
	}

	/**
	 * 绑定用户数据
	 * @param accountRegisterBean
	 * @param personalRegister
	 * @return
	 */
	private List<RecordProperty> buildRecordUserInfo(AccountRegisterBean accountRegisterBean, NormalPersonalRegisterBean personalRegister){
		// 人员备案信息
		List<RecordProperty> userPropertyList = new ArrayList<RecordProperty>();
		userPropertyList.add(buildRecordProperty("realName", personalRegister != null ?
				StringUtil.getNotNullStr(personalRegister.getRealName(),"") : ""));
		userPropertyList.add(buildRecordProperty("identno", personalRegister != null ?
				StringUtil.getNotNullStr(personalRegister.getIdentno(),"") : ""));
		userPropertyList.add(buildRecordProperty("telephone", accountRegisterBean != null ?
				StringUtil.getNotNullStr(accountRegisterBean.getTelephone(),"") : ""));
		buildAreaProperty(accountRegisterBean, userPropertyList);
		return userPropertyList;
	}

	/**
	 * 绑定企业数据
	 * @param accountRegisterBean
	 * @param companyRegister
	 * @return
	 */
	private List<RecordProperty> buildRecordCompanyInfo(AccountRegisterBean accountRegisterBean, NormalCompanyRegisterBean companyRegister){
		// 人员备案信息
		List<RecordProperty> companyPropertyList = new ArrayList<RecordProperty>();
		companyPropertyList.add(buildRecordProperty("companyName", companyRegister.getRealName()));
		companyPropertyList.add(buildRecordProperty("companyBizNo", companyRegister.getCustComno()));
		buildAreaProperty(accountRegisterBean, companyPropertyList);
		return companyPropertyList;
	}

	/**
	 * 绑定附件
	 * @param idList
	 * @return
	 */
	private List<AttachmentInfo> buildAttachmentInfo(List<Integer> idList) throws Exception{
		List<AttachmentInfo> attachmentList = new ArrayList<AttachmentInfo>();
		List<BizAttachment> bizAttachmentList = bizAttachmentService.queryAttachByIds(idList);
		for(BizAttachment bizAttachment : bizAttachmentList){
			AttachmentInfo attachmentInfo = UploadToPsc.uploadToPsc(bizAttachment);
			attachmentInfo.setId(null);
			attachmentInfo.setUploadDomain(msAppUrl);
			attachmentList.add(attachmentInfo);
		}
		return attachmentList;
	}

	/**
	 * 绑定参数
	 * @param propertyCode
	 * @param value
	 * @return
	 */
	private RecordProperty buildRecordProperty(String propertyCode, String value) {
		RecordProperty recordProperty = new RecordProperty();
		recordProperty.setPropertyCode(propertyCode);
		recordProperty.setPropertyValue(value);
		return recordProperty;
	}

	/**
	 * 组装国家省市区县信息
	 * @param accountRegisterBean
	 */
	private void buildAreaProperty(AccountRegisterBean accountRegisterBean,List<RecordProperty> propertyList) {
		// 将国家代码标志转成中文显示
		Map<String, ComCountry> countryMap = countryService.queryForCountryCodeMap();
		if (countryMap != null && countryMap.size() > 0 && countryMap.containsKey(accountRegisterBean.getCountry())) {
			propertyList.add(buildRecordProperty("country",countryMap.get(accountRegisterBean.getCountry()).getCountryNameCh()));
		}

		// 转换省份
		if (StringUtils.isNotEmpty(accountRegisterBean.getProvince())) {
			ComProvince province = provinceService.queryForMap().get(accountRegisterBean.getProvince());
			if (province != null) {
				propertyList.add(buildRecordProperty("province", province.getProvinceName()));
			}
		}

		// 转换城市
		if (StringUtils.isNotEmpty(accountRegisterBean.getCity())) {
			ComCity city = cityService.queryForMap().get(accountRegisterBean.getCity());
			if (city != null) {
				propertyList.add(buildRecordProperty("city", city.getName()));
			}
		}

		// 转换县
		if (StringUtils.isNotEmpty(accountRegisterBean.getCounty())) {
			ComCounty county = countyService.queryForMap().get(accountRegisterBean.getCounty());
			if (county != null) {
				propertyList.add(buildRecordProperty("county", county.getAreaName()));
			}
		}
	}
}
