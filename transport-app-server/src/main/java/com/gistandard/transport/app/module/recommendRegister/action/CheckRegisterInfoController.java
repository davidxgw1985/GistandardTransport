package com.gistandard.transport.app.module.recommendRegister.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.register.bean.AccountRegisterBean;
import com.gistandard.transport.register.define.RegisterDefine;
import com.gistandard.transport.register.service.RegisterNewService;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeResult;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeReq;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeRes;
import com.gistandard.transport.app.dubbo.sms.service.SmsDubboService;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.system.common.controller.BaseController;
import com.valueplus.psc.dubbo.service.common.bean.RegisterTelInfoBean;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import com.valueplus.psc.dubbo.service.login.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

/**
 * Created by yujie on 2016/3/18.
 */
@Controller
@RequestMapping(value = AppServerDefine.CHECK_REGISTER_URL)
public class CheckRegisterInfoController extends BaseController {

	private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(CheckRegisterInfoController.class);


	private static final String REGISTER_STEP1_SESSION = "REGISTER_STEP1_SESSION";

	private static final String O2ID_LIST = "O2ID_LIST";

	@Autowired
	private RegisterNewService registerNewService;

	@Autowired
	private SmsDubboService smsService;

	@Autowired
	private AccountService accountService;

	/**
	 * 文件上传
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/fileUpload", method = { RequestMethod.POST })
	public @ResponseBody
	void registerFileUpload(MultipartHttpServletRequest request) throws Exception {
		MultipartFile file = null;
		String fileId = "";
		Map<String, MultipartFile> fileMap = request.getFileMap();
		if (fileMap != null && fileMap.size() > 0) {
			for (String key : fileMap.keySet()) {
				fileId = key;
				file = fileMap.get(key);
			}
		}
		ResultBean resultBean = checkFileInfo(file, fileId);
		if (!resultBean.isState()) {
			writeJson(resultBean);
		} else {
			resultBean = registerNewService.registerFileUpload(file, fileId);
			writeJson(resultBean);
		}
	}

	/**
	 * 上传文件校验
	 * @param file
	 * @param fileId
	 * @return
	 */
	private ResultBean checkFileInfo(MultipartFile file, String fileId) {
		ResultBean resultBean = new ResultBean();
		if (file == null || file.getSize() == 0) {
			resultBean.setMessage("文件为空");
			return resultBean;
		}
		String fileName = file.getOriginalFilename();
		if (!StringUtils.isEmpty(fileName)) {
			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
			if (!"jpg".equals(fileType) && !"jpeg".equals(fileType) && !"png".equals(fileType)) {
				resultBean.setMessage("文件格式不正确");
				return resultBean;
			}
		}
		if (fileId.equals(RegisterDefine.PORTRAIT_FILE_ID)) {
			if (file.getSize() > RegisterDefine.PORTRAIT_FILE_SIZE) {
				resultBean.setMessage("文件超过指定大小");
				return resultBean;
			}
		} else {
			if (file.getSize() > RegisterDefine.NORMAL_FILE_SIZE) {
				resultBean.setMessage("文件超过指定大小");
				return resultBean;
			}
		}
		resultBean.setState(true);
		return resultBean;
	}

	/**
	 * 身份证唯一性检测
	 * @param identityNo
	 */
	@RequestMapping(value = "/checkIdentityNo", method = { RequestMethod.POST })
	@ResponseBody
	public void checkIdentityNo(String identityNo) {
		if (StringUtils.isNotEmpty(identityNo)) {
			ServiceResult<String> result = accountService.verifyIdentityNoUnique(AppServerDefine.buildTransportAuthInfo(),
					identityNo);
			writeJson(result.isResult());
		}
	}

	/**
	 * 手机号唯一性检测
	 * @param telephone
	 */
	@RequestMapping(value = "/checkTelephone", method = { RequestMethod.POST })
	@ResponseBody
	public void checkTelephone(String telephone,String acctType) {
		if (StringUtils.isNotEmpty(telephone)) {
			RegisterTelInfoBean registerTelInfoBean = new RegisterTelInfoBean();
			registerTelInfoBean.setTelphone(telephone);
			registerTelInfoBean.setRegisterType(acctType);
			ServiceResult<String> result = accountService.verifyTelephoneUniqueClassify(AppServerDefine.buildTransportAuthInfo(),
					registerTelInfoBean);
			writeJson(result.isResult());
		}
	}

	/**
	 * 点击发送手机验证码
	 * @param telephone
	 */
	@RequestMapping(value = "/sendTelMessage", method = { RequestMethod.POST })
	@ResponseBody
	public void sendTelMessage(String telephone) {
		if (StringUtils.isNotEmpty(telephone)) {
			SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
			sendSmsVerifyCodeReq.setSystem("TransportReg");
			sendSmsVerifyCodeReq.setModel(0);
			sendSmsVerifyCodeReq.setReceiveNo(telephone);
			SendSmsVerifyCodeResult sendSmsVerifyCodeResult = smsService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
			SendSmsVerifyCodeRes sendSmsVerifyCodeRes = (SendSmsVerifyCodeRes) sendSmsVerifyCodeResult.getData();
			Logger.info("发送短信结果：{}", JSON.toJSONString(sendSmsVerifyCodeRes));
			if(sendSmsVerifyCodeRes != null){
				Logger.info("获取短信tokenId：{}", sendSmsVerifyCodeRes.getTokenId());
				getSession().setAttribute(AppServerDefine.SESSION_ATTR_TELMSG_TOKEN_ID, sendSmsVerifyCodeRes.getTokenId());
				Logger.info("session里短信tokenId：{}", getSession().getAttribute(AppServerDefine.SESSION_ATTR_TELMSG_TOKEN_ID));
			}
			writeJson(sendSmsVerifyCodeResult);
		}
	}

	/**
	 * 校验身份证正面照片
	 * @param identityPositiveFileId
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkIdentityPositiveFile", method = { RequestMethod.POST })
	@ResponseBody
	public void checkIdentityPositiveFile(Integer identityPositiveFileId,String area) throws Exception {
		writeJson(registerNewService.ocrRetriveIdentityInfo(identityPositiveFileId, area));
	}

	/**
	 * 校验身份证反面照片
	 * @param identityNegativeFileId
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkIdentityNegativeFile", method = { RequestMethod.POST })
	@ResponseBody
	public void checkIdentityNegativeFile(Integer identityNegativeFileId,String area) throws Exception {
		writeJson(registerNewService.ocrVerifyIdentityBack(identityNegativeFileId, area));
	}

	/**
	 * 10条帐号
	 * @throws Exception
	 */
	@RequestMapping(value = "/o2IdLayer", method = { RequestMethod.POST })
	@ResponseBody
	public void o2IdLayer() throws Exception {
		if(getSession().getAttribute(O2ID_LIST) != null){
			writeJson(getSession().getAttribute(O2ID_LIST));
		}else{
			AccountRegisterBean accountRegisterBean = (AccountRegisterBean)getSession().getAttribute(REGISTER_STEP1_SESSION);
			ServiceResult serviceResult = accountService.generateAccountList(AppServerDefine.buildTransportAuthInfo(),
					NumberUtils.toInt(accountRegisterBean.getProvinceAndCity_sel()));
			getSession().setAttribute(O2ID_LIST, serviceResult.getData());
			writeJson(serviceResult.getData());
		}
	}
}
