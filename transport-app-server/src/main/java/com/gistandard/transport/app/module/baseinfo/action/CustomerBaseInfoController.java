package com.gistandard.transport.app.module.baseinfo.action;


import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.baseinfo.bean.ChangeAvatarResult;
import com.gistandard.transport.app.module.baseinfo.bean.QueryResult;
import com.gistandard.transport.app.module.baseinfo.service.CustomerBaseInfoService;
import com.gistandard.transport.app.module.gps.bean.GpsTrackDataResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.BizAttachment;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.baseinfo.bean.*;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.upload.define.UploadFileType;
import com.gistandard.transport.system.upload.service.UploadService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author kongxm
 * @ClassName MobileBaseInfoController
 * @Description 基本信息管理
 * @Version 1.0
 * @Date 2016/1/26
 */
@Controller
@RequestMapping(value = AppServerDefine.BASEINFO_URL)
public class CustomerBaseInfoController extends BaseController {
    private static final String TAGS_DESC = "密码管理模块";
    @Autowired
    private CustomerBaseInfoService mobileBaseInfoService;
    @Autowired
    private UploadService uploadService;

    @ApiOperation(value = "客户下单APP用户基本信息查询接口-V1.0.1", httpMethod = "POST", response = QueryResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP用户基本信息查询")
    @RequestMapping(value = "/queryByAccountId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryResult query(@RequestBody String jsonStr) {
        AppBaseRequest baseReqBean = JSON.parseObject(jsonStr, AppBaseRequest.class);
        QueryResult res = new QueryResult();
        try {
            BaseInfoRes baseInfoRes = mobileBaseInfoService.queryByAccountId(baseReqBean.getAccountId());
            res.setData(baseInfoRes);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }

    @ApiOperation(value = "客户下单APP找回密码接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP找回密码")
    @RequestMapping(value = "/retrievePassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult retrievePassword(@RequestBody String jsonStr) {
        RetrievePasswordReq req = JSON.parseObject(jsonStr, RetrievePasswordReq.class);
        AppBaseResult res = new AppBaseResult(req);
        try {
            mobileBaseInfoService.retrievePassword(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }

    @ApiOperation(value = "客户下单APP密码修改接口-V1.0.1", httpMethod = "POST", response = GpsTrackDataResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP密码修改")
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void changePassword(@RequestBody String jsonStr) {
        ChangePasswordReq changePasswordReq = JSON.parseObject(jsonStr, ChangePasswordReq.class);
        AppBaseResult res = new AppBaseResult(changePasswordReq);
        try {
            mobileBaseInfoService.changePassword(changePasswordReq);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        writeJson(res);
    }

    @ApiOperation(value = "客户下单APP绑定手机-V1.0.1", httpMethod = "POST", response = GpsTrackDataResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP绑定手机")
    @RequestMapping(value = "/bindPhone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void bindPhone(@RequestBody String jsonStr) {
        BindPhoneReq bindPhoneReq = JSON.parseObject(jsonStr, BindPhoneReq.class);
        AppBaseResult res = new AppBaseResult(bindPhoneReq);
        try {
            mobileBaseInfoService.bindPhone(bindPhoneReq);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        writeJson(res);
    }

    @ApiOperation(value = "客户下单APP修改昵称接口-V1.0.1", httpMethod = "POST", response = GpsTrackDataResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP修改昵称")
    @RequestMapping(value = "/changeNickName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void changeNickName(@RequestBody String jsonStr) {
        ChangeNickNameReq changeNickNameReq = JSON.parseObject(jsonStr, ChangeNickNameReq.class);
        AppBaseResult res = new AppBaseResult(changeNickNameReq);
        try {
            mobileBaseInfoService.changeNickName(changeNickNameReq);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        writeJson(res);
    }


    @ApiOperation(value = "客户下单APP修改昵称接口-V1.0.1", httpMethod = "POST", response = ChangeAvatarResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP修改昵称")
    @RequestMapping(value = "/changeAvatar", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ChangeAvatarResult changeAvatar(Integer accountId, MultipartHttpServletRequest request) {
        ChangeAvatarReq req = new ChangeAvatarReq();
        req.setAccountId(accountId);
        ChangeAvatarResult res = new ChangeAvatarResult();
        if(accountId == null){
            res.setRetCode(-1);
            res.setRetMsg("accountId不能为空");
            return res;
        }
        MultipartFile file = request.getFile("avatar");
        // 获取文件为空，返回false
        if (file == null) {
            res.setRetCode(-1);
            res.setRetMsg("请上传图片文件");
            return res;
        }
        String contentType = file.getContentType();
        if(!contentType.startsWith("image/")){
            res.setRetCode(-1);
            res.setRetMsg("请上传图片文件");
            return res;
        }

        // 上传附件，并上传记录插入到数据库中
        try {
            BizAttachment bizAttachment = uploadService.uploadDfsFile(file, UploadFileType.PORTRAIT.getValue(), accountId);
            //组装完整的路径
            String avatarUrl = bizAttachment.getAttachDomain() +bizAttachment.getAttachPath();
            req.setUrl(avatarUrl);
            req.setBizAttachment(bizAttachment);
            mobileBaseInfoService.changeAvatar(req);
            res.setData(avatarUrl);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            res.setRetCode(-1);
            res.setRetMsg("修改图像失败：" + e.getMessage());
        }

        return res;
    }








}
