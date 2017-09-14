package com.gistandard.transport.app.module.applytobe.action;

import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.app.dubbo.security.service.TokenManageService;
import com.gistandard.transport.app.module.applytobe.bean.*;
import com.gistandard.transport.applytobe.bean.MerchantPersonalBean;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComUserinfo;
import com.gistandard.transport.base.entity.service.ComAccountService;
import com.gistandard.transport.base.entity.service.ComUserinfoService;
import com.gistandard.transport.oauth2.SecurityUser;
import com.gistandard.transport.system.common.annotation.TokenAnnotation;
import com.gistandard.transport.tools.util.IdCardUtil;
import com.valueplus.psc.dubbo.service.common.bean.IdVerifyBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.applytobe.service.MobileApplyToBeService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.applytobe.bean.MerchantCarOwnerBean;
import com.gistandard.transport.applytobe.bean.MerchantCourierBean;
import com.gistandard.transport.applytobe.bean.MerchantMstationBean;
import com.gistandard.transport.applytobe.service.ApplyToBeService;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SysResult;
import com.gistandard.transport.base.entity.service.ComAllTypeService;
import com.gistandard.transport.register.define.RegisterDefine;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.token.bean.Token;
import com.gistandard.transport.system.token.service.TokenService;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import com.valueplus.psc.dubbo.service.login.AccountService;

/**
 * 升级快递员或司机
 * @Author zxnui
 * @Date 8/16/16.
 */
@Controller
@RequestMapping(value = AppServerDefine.APPLYTOBE_URL)
@Api(value = "注册模块", tags = "注册模块" )
public class MobileApplyToBeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(MobileApplyToBeController.class);

    @Autowired
    private MobileApplyToBeService mobileApplyToBeService;

    @Autowired
    private ApplyToBeService applyToBeService;

    @Autowired
    private ComAllTypeService comAllTypeService;

    @Autowired
    private AccountService accountService;

    @Autowired
	private TokenService tokenService;

    @Autowired
    private TokenManageService tokenManageService;

    @Autowired
    private ComAccountService comAccountService;

    @Autowired
    private ComUserinfoService comUserinfoService;

    /**
     * 检查用户跳转页面
     * @param appBaseRequest
     * @throws Exception
     */
    @ApiOperation(value = "检测用户目前的升级状态-V1.0.1", httpMethod = "POST", notes = "检测用户目前的升级状态，处于审核状态，返回审核结果页面，否则返回升级页面")
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    @ResponseBody
    public CheckRoleResult check(@RequestBody AppBaseRequest appBaseRequest) throws Exception {
        return mobileApplyToBeService.check(appBaseRequest);
    }

    /**
     * 升级结果展示
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "升级审核结果显示页面-V1.0.1", httpMethod = "GET", notes = "升级审核结果显示页面")
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public ModelAndView show(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        String account = request.getParameter("account");
        AppBaseResult res = mobileApplyToBeService.getShowMessage(account);
        modelAndView.addObject("msg",res.getRetMsg());
        modelAndView.addObject("status",res.getRetCode());
        modelAndView.setViewName(MobileApplyDefault.URL_FINISH);
        return modelAndView;
    }

    /**
     * 升级选择页面，选择升级为何种角色
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "升级选择页面-V1.0.1", httpMethod = "GET", notes = "升级选择页面，用户可升级成快递员、司机和咪站，已升级过的角色不能再升级")
    @RequestMapping(value = "/infoByChose",method = RequestMethod.GET)
    public ModelAndView infoByChose(@AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        ModelAndView modelAndView =  mobileApplyToBeService.checkPage(currentUser);
        if (StringUtils.isNotEmpty(modelAndView.getViewName())) {
            return modelAndView;
        }
        MobileRoleBean mobileRoleBean = mobileApplyToBeService.getRoleName(currentUser.getInfo().getAccountId());
        modelAndView.addObject("mobileRoleBean", mobileRoleBean);
        modelAndView.setViewName(MobileApplyDefault.URL_BASE);

        return modelAndView;
    }

    /**
     * 升级说明页面
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "用户升级协议页面-V1.0.1", httpMethod = "GET", notes = "用户升级协议页面")
    @RequestMapping(value = "/explain",method = RequestMethod.GET)
    public ModelAndView explain(HttpServletRequest request,@AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        ModelAndView modelAndView =  mobileApplyToBeService.checkPage(currentUser);
        if (StringUtils.isNotEmpty(modelAndView.getViewName())) {
            return modelAndView;
        }
        modelAndView.addObject("type", request.getParameter("type"));
        modelAndView.setViewName(MobileApplyDefault.URL_EXPLAIN);

        return modelAndView;
    }

    /**
     * 快递员升级说明
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "升级快递员说明页面1-V1.0.1", httpMethod = "GET", notes = "升级快递员说明页面1")
    @RequestMapping(value = "/kdInfo1",method = RequestMethod.GET)
    public ModelAndView kdInfo1(@AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        ModelAndView modelAndView =  mobileApplyToBeService.checkPage(currentUser);
        if (StringUtils.isNotEmpty(modelAndView.getViewName())) {
            return modelAndView;
        }
        modelAndView.setViewName(MobileApplyDefault.URL_KD_INFO_1);
        return modelAndView;
    }

    /**
     * 快递员升级填写信息
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "升级快递员说明页面2-V1.0.1", httpMethod = "GET", notes = "升级快递员说明页面2")
    @RequestMapping(value = "/kdInfo2",method = RequestMethod.GET)
    public ModelAndView kdInfo2(HttpServletRequest request,@AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        logger.info("用户升级快递员，填写信息");
        ModelAndView modelAndView =  mobileApplyToBeService.checkPage(currentUser);
        if (StringUtils.isNotEmpty(modelAndView.getViewName())) {
            return modelAndView;
        }
        modelAndView.addObject("merchantPersonalBean",getMerchantPersonalBean(currentUser.getInfo().getAccountId()));
        modelAndView.setViewName(MobileApplyDefault.URL_KD_INFO_2);
        Token token =  tokenService.newToken(request);
        modelAndView.addObject("token", token.getToken());
        if (currentUser.getInfo()!=null&&currentUser.getInfo().getComUserinfo()!=null){

            MerchantCourierBean merchantCourierBean = new MerchantCourierBean();
            merchantCourierBean.setUrgentLinkTel(currentUser.getInfo().getComUserinfo().getUrgentLinkTel());
            merchantCourierBean.setUrgentLinkUser(currentUser.getInfo().getComUserinfo().getUrgentLinkUser());
            modelAndView.addObject("merchantCourierBean", merchantCourierBean);
        }
        return modelAndView;
    }

    /**
     * 司机升级说明
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "升级司机说明页面1-V1.0.1", httpMethod = "GET", notes = "升级司机说明页面1")
    @RequestMapping(value = "/sjInfo1")
    public ModelAndView sjInfo1(@AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        logger.info("用户升级司机，说明");
        ModelAndView modelAndView =  mobileApplyToBeService.checkPage(currentUser);
        if (StringUtils.isNotEmpty(modelAndView.getViewName())) {
            return modelAndView;
        }
        modelAndView.setViewName(MobileApplyDefault.URL_SJ_INFO_1);
        return modelAndView;
    }

    /**
     * 司机填写道路运输许可证
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "升级司机说明页面2-V1.0.1", httpMethod = "GET", notes = "升级司机说明页面2")
    @RequestMapping(value = "/sjInfo2",method = RequestMethod.GET)
    public ModelAndView sjInfo2(HttpServletRequest request,@AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        ModelAndView modelAndView =  mobileApplyToBeService.checkPage(currentUser);
        if (StringUtils.isNotEmpty(modelAndView.getViewName())) {
            return modelAndView;
        }
        modelAndView.addObject("merchantPersonalBean",getMerchantPersonalBean(currentUser.getInfo().getAccountId()));
        modelAndView.setViewName(MobileApplyDefault.URL_SJ_INFO);
        modelAndView.addObject("carTypeList",
                comAllTypeService.queryComAllTypeList(RegisterDefine.CAR_TYPE_CATEGORY_CODE));
        modelAndView.addObject("boxTypeList", comAllTypeService.queryComAllTypeList(RegisterDefine.CAR_TYPE_BOX_TYPE_CODE));
        modelAndView.addObject("emptyWeightFlagList", comAllTypeService.queryComAllTypeList(RegisterDefine.CAR_TYPE_BOX_EMPTY_WEIGHT_FLAG));
        modelAndView.addObject("sourceCodeList", comAllTypeService.queryComAllTypeList(RegisterDefine.CAR_TYPE_BOX_SOURCE_CODE));
        modelAndView.addObject("trayTypeList", comAllTypeService.queryComAllTypeList(RegisterDefine.CAR_TYPE_TRAY_TYPE_CODE));
        Token token =  tokenService.newToken(request);
        modelAndView.addObject("token", token.getToken());
        if (currentUser.getInfo()!=null&&currentUser.getInfo().getComUserinfo()!=null) {
            MerchantCourierBean carOwnerBean = new MerchantCourierBean();
            carOwnerBean.setUrgentLinkTel(currentUser.getInfo().getComUserinfo().getUrgentLinkTel());
            carOwnerBean.setUrgentLinkUser(currentUser.getInfo().getComUserinfo().getUrgentLinkUser());
            modelAndView.addObject("carOwnerBean", carOwnerBean);
        }
        return modelAndView;
    }

    /**
     * 咪站升级说明
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "升级咪站说明页面1-V1.0.1", httpMethod = "GET", notes = "升级咪站说明页面1")
    @RequestMapping(value = "/mzInfo1",method = RequestMethod.GET)
    public ModelAndView mzInfo1(@AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        logger.info("用户升级咪站，说明");
        ModelAndView modelAndView =  mobileApplyToBeService.checkPage(currentUser);
        if (StringUtils.isNotEmpty(modelAndView.getViewName())) {
            return modelAndView;
        }
        modelAndView.setViewName(MobileApplyDefault.URL_MZ_INFO_1);
        return modelAndView;
    }

    /**
     * 咪站填写
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "升级咪站说明页面2-V1.0.1", httpMethod = "GET", notes = "升级咪站说明页面2")
    @RequestMapping(value = "/mzInfo2",method = RequestMethod.GET)
    public ModelAndView mzInfo2(MerchantMstationBean mstationBean,HttpServletRequest request,@AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        ModelAndView modelAndView =  mobileApplyToBeService.checkPage(currentUser);
        if (StringUtils.isNotEmpty(modelAndView.getViewName())) {
            return modelAndView;
        }
        modelAndView.addObject("merchantPersonalBean",getMerchantPersonalBean(currentUser.getInfo().getAccountId()));
        if(!StringUtil.isEmpty(mstationBean.getAddress())){
            mstationBean.setAddress(URLDecoder.decode(mstationBean.getAddress(), "utf8"));
        }
        modelAndView.setViewName(MobileApplyDefault.URL_MZ_INFO_2);
        Token token =  tokenService.newToken(request);
            modelAndView.addObject("token", token.getToken());
        if (currentUser.getInfo()!=null&&currentUser.getInfo().getComUserinfo()!=null) {
            mstationBean.setUrgentLinkTel(currentUser.getInfo().getComUserinfo().getUrgentLinkTel());
            mstationBean.setUrgentLinkUser(currentUser.getInfo().getComUserinfo().getUrgentLinkUser());
        }
        modelAndView.addObject("mstationBean", mstationBean);

        return modelAndView;
    }

    /**
     * 申请成为快递员
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "申请成为快递员-V1.0.1", httpMethod = "GET", notes = "申请成为快递员，对提交数据进行验证")
    @RequestMapping(value = "/courier", method = {RequestMethod.POST })
    @TokenAnnotation(remove = true)
    public ModelAndView courier(@Valid MerchantCourierBean merchantCourierBean,BindingResult bindingResult,
                                HttpServletRequest request,@AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        ModelAndView modelAndView =  mobileApplyToBeService.checkPage(currentUser);
        if (StringUtils.isNotEmpty(modelAndView.getViewName())) {
            return modelAndView;
        }
        AppLoginInfo appLoginInfo = currentUser.getInfo();
        merchantCourierBean.setUserInfoStatus(2);
        merchantCourierBean.setAccountId(appLoginInfo.getAccountId());
        HashMap<String, String> errorInfoMap = checkPersonalBeanNull(merchantCourierBean);
        if (bindingResult.hasErrors()) {
            errorInfoMap = tranErrorListToMap(bindingResult.getFieldErrors());
        }

        if (errorInfoMap.size() == 0){
            ResultBean resultBean = idVerify(merchantCourierBean,appLoginInfo.getAcctUsername());
            if (!resultBean.isState()){
                errorInfoMap.put("Identno",resultBean.getMessage());
            }
        }

        if (errorInfoMap.size() > 0) {
        	Token token =  tokenService.newToken(request);
            modelAndView.addObject("token", token.getToken());
            modelAndView.setViewName(MobileApplyDefault.URL_KD_INFO_2);
            modelAndView.addObject("merchantCourierBean", merchantCourierBean);
            modelAndView.addObject("errorInfo", JSON.toJSONString(errorInfoMap));
            modelAndView.addObject("merchantPersonalBean",getMerchantPersonalBean(currentUser.getInfo().getAccountId()));
            return modelAndView;
        }

        ResultBean resultBean = applyToBeService.courier(merchantCourierBean, currentUser.getInfo().getAccountId(), null);
        if (resultBean.isState()) {
            request.setAttribute("msg",
                    MobileApplyDefault.MSG_APPLY.replace("{0}", SysAccountRole.OPERATOR_DELIVERY_OWNER.getName()));
        } else {
            request.setAttribute("msg", MobileApplyDefault.MSG_ERROR);
        }
        modelAndView.setViewName(MobileApplyDefault.URL_APPLY_FINISH);
        return modelAndView;
    }

    /**
     * 申请成为司机
     * @param carOwnerBean
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "申请成为司机-V1.0.1", httpMethod = "POST",  notes = "申请成为司机，对提交数据进行验证")
    @RequestMapping(value = "/carOwner", method = { RequestMethod.POST })
    @TokenAnnotation(remove = true)
    public ModelAndView carOwner(@Valid MerchantCarOwnerBean carOwnerBean,BindingResult bindingResult, HttpServletRequest request,
                                 @AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        ModelAndView modelAndView =  mobileApplyToBeService.checkPage(currentUser);
        if (StringUtils.isNotEmpty(modelAndView.getViewName())) {
            return modelAndView;
        }
        AppLoginInfo appLoginInfo = currentUser.getInfo();
        carOwnerBean.setUserInfoStatus(2);
        carOwnerBean.setAccountId(appLoginInfo.getAccountId());
        HashMap<String, String> errorInfoMap = checkPersonalBeanNull(carOwnerBean);
        if (bindingResult.hasErrors()) {
            errorInfoMap = tranErrorListToMap(bindingResult.getFieldErrors());
        }

        if (errorInfoMap.size() == 0){
            ResultBean resultBean = idVerify(carOwnerBean,appLoginInfo.getAcctUsername());
            if (!resultBean.isState()){
                errorInfoMap.put("Identno",resultBean.getMessage());
            }
        }

        if (errorInfoMap.size() > 0) {
        	Token token =  tokenService.newToken(request);
            modelAndView.addObject("token", token.getToken());
            modelAndView.setViewName(MobileApplyDefault.URL_SJ_INFO);
            modelAndView.addObject("accountType", SysAccountRole.OPERATOR_CAR_OWNER.getName());
            modelAndView.addObject("carOwnerBean", carOwnerBean);
            modelAndView.addObject("errorInfo", JSON.toJSONString(errorInfoMap));
            modelAndView.addObject("carTypeList",
                    comAllTypeService.queryComAllTypeList(RegisterDefine.CAR_TYPE_CATEGORY_CODE));
            modelAndView.addObject("boxTypeList", comAllTypeService.queryComAllTypeList(RegisterDefine.CAR_TYPE_BOX_TYPE_CODE));
            modelAndView.addObject("emptyWeightFlagList", comAllTypeService.queryComAllTypeList(RegisterDefine.CAR_TYPE_BOX_EMPTY_WEIGHT_FLAG));
            modelAndView.addObject("sourceCodeList", comAllTypeService.queryComAllTypeList(RegisterDefine.CAR_TYPE_BOX_SOURCE_CODE));
            modelAndView.addObject("trayTypeList", comAllTypeService.queryComAllTypeList(RegisterDefine.CAR_TYPE_TRAY_TYPE_CODE));
            modelAndView.addObject("merchantPersonalBean",getMerchantPersonalBean(currentUser.getInfo().getAccountId()));
            return modelAndView;
        }
        
        ResultBean resultBean = applyToBeService.carOwner(carOwnerBean, currentUser.getInfo().getAccountId(), null);
        if(resultBean.isState()){
            request.setAttribute("msg",
                    MobileApplyDefault.MSG_APPLY.replace("{0}", SysAccountRole.OPERATOR_CAR_OWNER.getName()));
        }else{
            request.setAttribute("msg", MobileApplyDefault.MSG_ERROR);
        }

        modelAndView.setViewName(MobileApplyDefault.URL_APPLY_FINISH);
        return modelAndView;
    }

    /**
     * 申请成为咪站
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "申请成为咪站-V1.0.1", httpMethod = "GET", notes = "申请成为咪站，对提交数据进行验证")
    @RequestMapping(value = "/mstation", method = {RequestMethod.POST })
    @TokenAnnotation(remove = true)
    public ModelAndView mstation(@Valid MerchantMstationBean mstationBean,BindingResult bindingResult,HttpServletRequest request,
                                 @AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        ModelAndView modelAndView =  mobileApplyToBeService.checkPage(currentUser);
        if (StringUtils.isNotEmpty(modelAndView.getViewName())) {
            return modelAndView;
        }
        AppLoginInfo appLoginInfo = currentUser.getInfo();
        mstationBean.setUserInfoStatus(2);
        mstationBean.setAccountId(appLoginInfo.getAccountId());
        HashMap<String, String> errorInfoMap = checkPersonalBeanNull(mstationBean);
        // 校验参数
        if (bindingResult.hasErrors()) {
            errorInfoMap = tranErrorListToMap(bindingResult.getFieldErrors());
        }

        if (errorInfoMap.size() == 0){
            ResultBean resultBean = idVerify(mstationBean,appLoginInfo.getAcctUsername());
            if (!resultBean.isState()){
                errorInfoMap.put("Identno",resultBean.getMessage());
            }
        }

        if (errorInfoMap.size() > 0) {
        	Token token =  tokenService.newToken(request);
            modelAndView.addObject("token", token.getToken());
            modelAndView.addObject("mstationBean", mstationBean);
            modelAndView.setViewName(MobileApplyDefault.URL_MZ_INFO_2);
            modelAndView.addObject("errorInfo", JSON.toJSONString(errorInfoMap));
            modelAndView.addObject("merchantPersonalBean",getMerchantPersonalBean(currentUser.getInfo().getAccountId()));
            return modelAndView;
        }

        ResultBean resultBean = applyToBeService.mstation(mstationBean, appLoginInfo.getAccountId(), null);
        if(resultBean.isState()){
            request.setAttribute("msg", MobileApplyDefault.MSG_APPLY.replace("{0}", SysAccountRole.OPERATOR_MSTATION.getName()));
        }else{
            request.setAttribute("msg", MobileApplyDefault.MSG_ERROR);
        }

        modelAndView.setViewName(MobileApplyDefault.URL_APPLY_FINISH);
        return modelAndView;
    }

    /**
     * 文件上传
     * @param request
     * @throws Exception
     */
    @ApiOperation(value = "升级文件上传-V1.0.1", httpMethod = "GET", response = FileUploadResult.class, notes = "升级文件上传")
    @RequestMapping(value = "/fileUpload" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public FileUploadResult registerFileUpload(MultipartHttpServletRequest request,
                                               @AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        FileUploadResult fileUploadResult = new FileUploadResult();
        logger.info("文件上传");
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
        if (resultBean.isState()) {
            resultBean = applyToBeService.registerFileUpload(file, fileId,currentUser.getInfo().getAccountId());
        }
        fileUploadResult.setRetCode(resultBean.isState() ? SysResult.SUCCESS.getValue() : SysResult.FAILED.getValue());
        fileUploadResult.setData(resultBean);
        return fileUploadResult;
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
     * 车牌号是否唯一
     * @param checkTruckCodeReq
     * @throws Exception
     */
    @ApiOperation(value = "检测车牌号是否唯一-V1.0.1", httpMethod = "POST", response = CheckTruckCodeResult.class, notes = "检测车牌号是否唯一，车牌号不能为空")
    @RequestMapping(value = "/checkTruckCode", method = { RequestMethod.POST })
    @ResponseBody
    public CheckTruckCodeResult checkTruckCode(CheckTruckCodeReq checkTruckCodeReq) throws Exception {
        CheckTruckCodeResult checkTruckCodeResult = new CheckTruckCodeResult(checkTruckCodeReq);
        if (StringUtils.isNotEmpty(checkTruckCodeReq.getTruckcode())) {
            boolean unique = applyToBeService.checkRecordTruckCodeUnique(checkTruckCodeReq.getTruckcode());
            if (!unique) {
                checkTruckCodeResult.setData(false);
            }else{
                ServiceResult result = accountService.verifyTruckCodeUnique(AppServerDefine.buildTransportAuthInfo(),
                        checkTruckCodeReq.getTruckcode());
                checkTruckCodeResult.setData(result.isResult());
            }
        }
        return checkTruckCodeResult;
    }

    /**
     * 司机升级说明
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "地图页面-V1.0.1", httpMethod = "GET", response = ModelAndView.class, notes = "跳转到地图页面，用户选择位置，返回经纬度和地址")
    @RequestMapping(value = "/map")
    public ModelAndView map(MapReq mapReq) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mapReq",mapReq);
        modelAndView.setViewName("module/applytobe/upgrade-map");
        return modelAndView;
    }

    /**
     * 校验身份证和姓名匹配
     * @param merchantPersonalBean
     * @throws Exception
     */
    @RequestMapping(value = "/checkIdVerify", method = {RequestMethod.POST })
    @ResponseBody
    public void checkIdVerify(MerchantPersonalBean merchantPersonalBean,
                              @AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser) throws Exception {
        ResultBean resultBean = new ResultBean();
        AppLoginInfo appLoginInfo = currentUser.getInfo();
        if (StringUtils.isNotEmpty(merchantPersonalBean.getIdentno()) && StringUtils.isNotEmpty(merchantPersonalBean.getRealName())){
            resultBean = idVerify(merchantPersonalBean,appLoginInfo.getAcctUsername());
        }
        writeJson(resultBean);
    }


    /**
     * 用户实名认证
     * @param merchantPersonalBean
     * @throws Exception
     */
    @ApiOperation(value = "用户实名认证-V1.0.1", httpMethod = "POST", response = AppBaseResult.class, notes = "检查用户是否实名认证，没有的话进行实名认证")
    @RequestMapping(value = "/realNameAuthentication", method = { RequestMethod.POST })
    @ResponseBody
    public AppBaseResult realNameAuthentication(@AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser,
                                                @RequestBody MerchantPersonalBean merchantPersonalBean) throws Exception {
        AppBaseResultRes appBaseResult = new AppBaseResultRes();
        AppLoginInfo appLoginInfo = currentUser.getInfo();
        ResultBean data = new ResultBean();
        data.setState(false);
        if(StringUtils.isEmpty(currentUser.getInfo().getComUserinfo().getIdentno())){
            merchantPersonalBean.setUserInfoStatus(1);
            merchantPersonalBean.setAccountId(appLoginInfo.getAccountId());
            HashMap<String, String> errorInfoMap = checkPersonalBeanNull(merchantPersonalBean);
            if (errorInfoMap.size() > 0) {
                logger.info("实名注册失败，失败信息：{},{}",appLoginInfo.getAcctUsername(),JSON.toJSONString(errorInfoMap));
                if (errorInfoMap.containsKey("Identno")){
                    data.setMessage(errorInfoMap.get("Identno"));
                }else if (errorInfoMap.containsKey("RealName")){
                    data.setMessage(errorInfoMap.get("RealName"));
                }
            }else{
                ResultBean resultBean = idVerify(merchantPersonalBean,appLoginInfo.getAcctUsername());
                if (resultBean.isState()){
                    ResultBean result = applyToBeService.userAuthentication(merchantPersonalBean,appLoginInfo);
                    if (!result.isState()){
                        data.setMessage(result.getMessage());
                    }else{
                        data.setState(true);
                    }
                }else{
                    data.setMessage(resultBean.getMessage());
                }
            }
        }else{
            data.setMessage("用户已实名认证");
        }
        appBaseResult.setData(data);
        return appBaseResult;
    }

    /**
     * 判断MerchantPersonalBean是否存在空字段
     * @param obj
     * @return errorInfoMap
     */
    private HashMap<String, String> checkPersonalBeanNull(Object obj){
        HashMap<String, String> errorInfoMap = new HashMap<>();
        MerchantPersonalBean merchantPersonalBean = (MerchantPersonalBean) obj;
        Integer userInfoStatus = merchantPersonalBean.getUserInfoStatus();
        ComAccount comAccount = comAccountService.queryAccountById(merchantPersonalBean.getAccountId());
        if (comAccount != null){
            Integer authStatus = comAccount.getAuthStatus();
            if (StringUtil.isEmpty(authStatus)){
                if(!StringUtil.isEmpty(userInfoStatus)&& 2==userInfoStatus){
                    if(merchantPersonalBean.getIdentityPositiveFileId()==null){
                        errorInfoMap.put("IdentityPositiveUrl","不能为空");
                    }if(merchantPersonalBean.getIdentityNegativeFileId()==null){
                        errorInfoMap.put("IdentityNegativeUrl","不能为空");
                    }if(merchantPersonalBean.getIdentityHalfFileId()==null){
                        errorInfoMap.put("IdentityHalfUrl","不能为空");
                    }
                }
                if(StringUtils.isEmpty(merchantPersonalBean.getIdentno())){
                    errorInfoMap.put("Identno","身份证号不能为空");
                }
                if (!IdCardUtil.validateCard(merchantPersonalBean.getIdentno())) {
                    errorInfoMap.put("Identno","身份证号不合法");
                }
                if(StringUtils.isEmpty(merchantPersonalBean.getRealName())){
                    errorInfoMap.put("RealName","姓名不能为空");
                }
                if(StringUtils.isNotEmpty(merchantPersonalBean.getIdentno())){
                    ServiceResult<String> serviceResult = accountService.verifyIdentityNoUnique(AppServerDefine.buildTransportAuthInfo(),
                            merchantPersonalBean.getIdentno());
                    if (!serviceResult.isResult()){
                        errorInfoMap.put("Identno","身份证已被注册");
                    }
                }
            }else if(authStatus == 1){
                if (!StringUtil.isEmpty(userInfoStatus)&& 1==userInfoStatus){
                    errorInfoMap.put("error","用户已实名注册");
                }
                ComUserinfo comUserinfo = comUserinfoService.queryUserInfoById(merchantPersonalBean.getAccountId());
                if (null != comUserinfo && StringUtils.isNotEmpty(comUserinfo.getIdentno()) &&
                        !comUserinfo.getIdentno().equals(merchantPersonalBean.getIdentno())){
                    errorInfoMap.put("error","身份证号码和未认证号码不符");
                }
                if(merchantPersonalBean.getIdentityPositiveFileId()==null){
                    errorInfoMap.put("IdentityPositiveUrl","不能为空");
                }if(merchantPersonalBean.getIdentityNegativeFileId()==null){
                    errorInfoMap.put("IdentityNegativeUrl","不能为空");
                }if(merchantPersonalBean.getIdentityHalfFileId()==null){
                    errorInfoMap.put("IdentityHalfUrl","不能为空");
                }
            }
        }else{
            errorInfoMap.put("error","帐号不存在");
        }
        return  errorInfoMap;
    }

    /**
     * 身份证唯一性检测
     * @param appBaseRequest
     */
    @RequestMapping(value = "/checkIdentityNo", method = { RequestMethod.POST })
    @ResponseBody
    public AppBaseResult checkIdentityNo(@RequestBody AppBaseRequest appBaseRequest) {
        AppBaseResultRes appBaseResult = new AppBaseResultRes();
        ResultBean data = new ResultBean();
        if (appBaseRequest != null &&StringUtils.isNotEmpty(appBaseRequest.getIdentityNo())) {
            ServiceResult<String> result = accountService.verifyIdentityNoUnique(AppServerDefine.buildTransportAuthInfo(),
                    appBaseRequest.getIdentityNo());
            logger.info("身份证唯一性检测,参数：{},{}",JSON.toJSONString(appBaseRequest),JSON.toJSONString(result));
            appBaseResult.setRetCode(1);
            if (result.isResult()){
                data.setState(true);
                data.setMessage("身份证唯一，未必注册");
            }else {
                data.setState(false);
            }
        }else {
            appBaseResult.setRetCode(0);
        }
        appBaseResult.setData(data);
        return appBaseResult;
    }

    private MerchantPersonalBean getMerchantPersonalBean(Integer accountId){
        MerchantPersonalBean bean = new MerchantPersonalBean();
        ComUserinfo comUserinfo = comUserinfoService.queryUserInfoById(accountId);
        if(StringUtils.isEmpty(comUserinfo.getIdentno())){
            bean.setUserInfoStatus(0);
        }else {
            ComAccount comAccount = comAccountService.queryAccountById(accountId);
            if (null != comAccount && !StringUtil.isEmpty(comAccount.getAuthStatus())){
                bean.setUserInfoStatus(comAccount.getAuthStatus());
            }
            bean.setIdentno(comUserinfo.getIdentno());
            bean.setRealName(comAccount.getRealName());
        }
        return bean;
    }

    private ResultBean idVerify(MerchantPersonalBean merchantPersonalBean,String acctUsername){
        ResultBean data = new ResultBean();
        ComAccount comAccount = comAccountService.queryAccountByAcctUsername(acctUsername);
        if (StringUtil.isEmpty(comAccount.getAuthStatus())){
            IdVerifyBean idVerifyBean = new IdVerifyBean();
            idVerifyBean.setRealName(merchantPersonalBean.getRealName());
            idVerifyBean.setIdentno(merchantPersonalBean.getIdentno());
            idVerifyBean.setAcctUsername(acctUsername);
            ServiceResult serviceResult = accountService.idVerify(AppServerDefine.buildTransportAuthInfo(), idVerifyBean);
            if (serviceResult.isResult()){
                data.setState(true);
            }else {
                data.setMessage(serviceResult.getMsg());
            }
        }else{
            data.setState(true);
        }
        return data;
    }
}
