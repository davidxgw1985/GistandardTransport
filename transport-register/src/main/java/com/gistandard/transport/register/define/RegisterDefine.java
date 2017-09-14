package com.gistandard.transport.register.define;

import com.valueplus.psc.dubbo.service.common.bean.ServiceAuthBean;

import java.util.HashMap;

/**
 * @author yujie
 * @ClassName RegisterDefine
 * @Description
 * @Version 1.0
 * @Date 2015-09-02
 */
public class RegisterDefine {

    //2M 普通文件大小
    public static final long NORMAL_FILE_SIZE = 10 * 1024 * 1024;

    //头像文件大小 2M
    public static final long PORTRAIT_FILE_SIZE = 10 * 1024 * 1024;

    //身份证正面照片，附件ID
    public static final String IDENTITY_POSITIVE_FILE_ID = "identityPositiveFile";

    //司机身份证正面照片，附件ID
    public static final String DRIVER_IDENTITY_POSITIVE_FILE_ID = "driverIdentityPositiveFile";

    //身份证反面照片，附件ID
    public static final String IDENTITY_NEGATIVE_FILE_ID = "identityNegativeFile";

    //司机身份证反面照片，附件ID
    public static final String DRIVER_IDENTITY_NEGATIVE_FILE_ID = "driverIdentityNegativeFile";

    //头像照片，附件ID
    public static final String PORTRAIT_FILE_ID = "portraitFile";

    //身份证正面照片，附件ID
    public static final String IDENTITY_HALF_FILE_ID = "identityHalfFile";

    //经营执照
    public static final String LICENSE_FILE_ID = "licenseFile";

    //驾驶证
    public static final String DRIVER_LICENSE_FILE_ID = "driverLicenseFile";

    //第二司机驾驶证
    public static final String SECOND_DRIVER_LICENSE_FILE_ID = "secondDriverLicenseFile";

    //车辆照片一
    public static final String FIRST_CAR_IMAGE_FILE_ID = "firstCarNoFile";

    //车辆照片二
    public static final String SECOND_CAR_IMAGE_FILE_ID = "secondCarNoFile";

    //行驶证
    public static final String DRIVING_LICENSE_FILE_ID = "drivingLicenseFile";

    //税务登记证
    public static final String TAX_REGISTER_FILE_ID = "taxRegisterFile";

    //道路运输许可证
    public static final String TRANSPORT_AGREE_FILE_ID = "transportAgreeFile";

    //道路运输营运证
    public static final String TRANSPORT_BUSINESS_FILE_ID = "transportBusinessFile";
    
    //组织结构代码证
    public static final String ORG_CODE_LILCENSE_FILE_ID = "orgCodeLilcenseFile";

    //加密的key
    public static final String PASSWORD_ENCRIPT_KEY = "valuePlus";

    //用户注册默认国家名称
    public static final String REGISTER_DEFAULT_COUNTRY = "142";

    //用户注册默认国家名称
    public static final int REGISTER_DEFAULT_COUNTRY_INT = 142;

    //帐号类型--个人
    public static final String ACCOUNT_TYPE_PERSONAL = "1";

    //帐号类型--企业
    public static final String ACCOUNT_TYPE_COMPANY = "2";

    public static final HashMap<String, String> ACCOUNT_TYPE_MAP = new HashMap<String, String>();

    static {
        ACCOUNT_TYPE_MAP.put(ACCOUNT_TYPE_PERSONAL, "个人账号");
        ACCOUNT_TYPE_MAP.put(ACCOUNT_TYPE_COMPANY, "企业账号");
    }

    //企业性质的备案编码
    public static final String COMPANY_TYPE_CATEGORY_CODE = "CUSTCLAS";

    //车辆类型的备案
    public static final String CAR_TYPE_CATEGORY_CODE = "VEHICLE";

    //车辆集装箱类型的备案
    public static final String CAR_TYPE_BOX_TYPE_CODE = "BOX_TYPE";

    //车辆集装箱空重标识的备案
    public static final String CAR_TYPE_BOX_EMPTY_WEIGHT_FLAG = "BOX_FLAG";

    //车辆集装箱来源代码的备案
    public static final String CAR_TYPE_BOX_SOURCE_CODE = "BOX_SOURCE_CODE";

    //车辆托架或拖挂车类型代码的备案
    public static final String CAR_TYPE_TRAY_TYPE_CODE = "TRAY_TYPE_CODE";

    //注册账号校验的正则表达式
    public static final String ACCT_PASSWORD_REGEXP = "^(?=\\w*[0-9]\\w*)(?=\\w*[A-Z]\\w*)(?=\\w*[a-z]\\w*).{8,20}$";

    //企业关系信息表中,如果企业是根,则定义根企业的父ID为-1
    public static final int  CUSTOMER_PARENT_ROOT_ID = -1;

    //账号业务类型的备案
    public static final String ACCOUNT_BUSI_TYPE = "BUSITYPE";

    //账号授权系统的备案
    public static final String ACCOUNT_ACCR_SYS = "ACCRSYS";

    // 员工人数的备案
    public static final String FLEET_EMPLOYEES_NUMBER_CODE = "EMPLOYEES_NUMBER_CODE";

    // 服务范围的备案
    public static final String FLEET_SERVICE_CATEGORY = "SERVICE_CATEGORY_CODE";

    // 服务类型的备案
    public static final String FLEET_SERVICE_TYPE = "SERVICE_TYPE_CODE";

    // 自有车辆的备案
    public static final String FLEET_SELF_OWNER_CAR_NUM_CODE="SELF_OWNER_CAR_NUM";

    // 所属地区
    public static final String REGISTER_AREA_CODE = "REGISTER_AREA";

    // 用户注册
    public static final String REGISTER_USER_TYPE = "1";

    // 商户注册
    public static final String REGISTER_MERCHANT_TYPE = "2";

    // 业务中心注册
    public static final String REGISTER_SERVICE_CENTER_TYPE = "3";

    // 商业中心注册
    public static final String REGISTER_BUSINESS_CENTER_TYPE = "4";

    // 个人
    public static final String REGISTER_PERSON = "个人";

    // 企业
    public static final String REGISTER_COMPANY = "企业";

    // 邮件已被激活
    public static final String REGISTER_MAIL_HAS_ACTIVE = "1";

    /**
     * 运输平台在PSC系统中授权的密码
     */
    public static final String TRANSPORT_AUTH_PWD = "test";

    /**
     * 运输平台在PSC系统中授权的用户名
     */
    public static final String TRANSPORT_AUTH_USER = "test";

    // MSAPP平台系统标志
    public static final String TRANSPORT_SYS_FLAG = "1b4d92f79abb44c297850f6f1da2c959";

    /**
     * 组装运输平台的系统认证信息
     */
    public static ServiceAuthBean buildTransportAuthInfo() {
        ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
        serviceAuthBean.setAuthPwd(RegisterDefine.TRANSPORT_AUTH_PWD);
        serviceAuthBean.setAuthUser(RegisterDefine.TRANSPORT_AUTH_USER);
        serviceAuthBean.setSysFlag(RegisterDefine.TRANSPORT_SYS_FLAG);
        return serviceAuthBean;
    }
}
