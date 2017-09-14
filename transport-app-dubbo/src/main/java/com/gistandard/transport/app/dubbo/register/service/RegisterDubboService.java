package com.gistandard.transport.app.dubbo.register.service;

import com.gistandard.transport.app.dubbo.register.bean.*;

/**
 * Created by zhuming on 2017/4/6.
 */
public interface RegisterDubboService {

    /**
     * 个人注册文件上传
     * @param regBizAttachmentRecord
     * @return
     * @throws Exception
     */
    RegResultBean registerFileUpload(RegBizAttachmentRecord regBizAttachmentRecord) throws Exception;

    /**
     * 个人注册
     * @param accountRegisterBean
     * @param personalRegister
     * @param o2IdRegisterBean
     * @throws Exception
     */
    RegResultBean normalPersonalSave(RegAccountRegisterBean accountRegisterBean,
                                     RegNormalPersonalRegisterBean personalRegister, RegO2IdRegisterBean o2IdRegisterBean) throws Exception;

    /**
     * 身份证验证正面
     * @param identityPositiveFileId
     * @param area
     * @return
     */
    RegResultBean ocrRetriveIdentityInfo(Integer identityPositiveFileId, String area);

    /**
     * 身份证验证反面
     * @param identityNegativeFileId
     * @param area
     * @return
     */
    RegResultBean ocrVerifyIdentityBack(Integer identityNegativeFileId, String area);

    /**
     * 企业注册
     * @param accountRegisterBean
     * @param companyRegister
     * @param o2IdRegisterBean
     * @throws Exception
     */
    RegResultBean normalCompanySave(RegAccountRegisterBean accountRegisterBean,
                                    RegNormalCompanyRegisterBean companyRegister,RegO2IdRegisterBean o2IdRegisterBean) throws Exception;

    RegResultBean getTelCode(String selectCityId);

    RegResultBean applytoWechatOrOnlineBus(RegComAccountRequest regComAccountRequest);

    RegResultBean queryApplytoRequest(Integer accountId);

    RegResultBean queryMangerO2ID(Integer accountId);
}
