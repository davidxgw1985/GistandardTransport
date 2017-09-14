package com.gistandard.transport.base.define;

import java.util.HashMap;

/**
 * @author yujie
 * @ClassName SystemDefine
 * @Description 静态参数定义类
 * @Version 1.0
 * @Date 2015-06-09
 */
public class SystemDefine {

    // 应用名称
    public static final String APPLICATION_NAME = "MSAPP";

    // 用户信息在session存储的属性名称
    public static final String SESSION_ATTR_USER = APPLICATION_NAME + "_" + "sessionUserInfo";

    public static final String SESSION_ATTR_VALIDATE_CODE = APPLICATION_NAME + "_validateCode";

    // 拦截器跳转到登录页标志
    public static final String SESSION_ATTR_LOGIN_CHECK = APPLICATION_NAME + "_" + "loginCheck";

    // token属性在session中的名称
    public static final String SESSION_ATTR_TOKEN_MAP = APPLICATION_NAME + "_" + "token_in_session";

    // token在request中参数名称
    public static final String REQUEST_TOKEN_NAME = "token";

    // 用户是否系统管理员角色session属性
    public static final String USER_SESSION_ADMIN_ROLE = APPLICATION_NAME + "_" + "userAdminRole";

    // 用户拥有的权限列表存入session中的属性名
    public static final String USER_SESSION_AUTHS = APPLICATION_NAME + "_" + "userAuthList";

    // 所有系统共用的sysID
    public static final Integer PROJECT_SYSTEM_ID = -99;

    // 弹出窗口
    public static final String POPUP_WINDOW_URL = "/popup";

    // 所有系统共用的sysName
    public static final String PROJECT_SYSTEM_NAME = "所有系统共用";

    // 系统管理员用户的角色
    public static final Integer ADMIN_USER_ROLE_ID = 1;

    // 客户下单（通用运输、通用快递）系统标志
    public static final String CUSTOMER_SYS_FLAG = "7bf04d26d48e42e6b5f7c49cdb6a9b89";
    //通用账户系统标志
    public static final String ACCOUNT_SYS_FLAG = "d18f90e6aec54bae92e843798bb61df3";

    public static final String VALIDATE_CODE_CHECK_URL = "checkValidateCode";

    // 登录模块
    public static final String LOGIN_MODULE_URL = "/login";

    // ftp文件操作
    public static final String FTP_OPERATE_URL = "/fileOperate";

    // token操作请求
    public static final String TOKEN_MODULE_URL = "/token";

    // 系统异常错误
    public static final String SYS_ERROR = "/error";

    // PSC用户加密字符串
    public static final String PWD_SYS_USER = "gistandardPscUser";

    // js语言包
    public static final HashMap<String, String> LOCALE_MAP = new HashMap<String, String>();

    static {
        LOCALE_MAP.put("zh_CN", "zh_CN");
        LOCALE_MAP.put("zh_HK", "zh_HK");
        LOCALE_MAP.put("zh_TW", "zh_TW");
    }

    // 登录后的主界面
    public static final String FRAME_URL = "frame";

    // 首页
    public static final String HOME_URL = "home";

    // 接入系统管理
    public static final String ACCESS_SYSTEM_URL = "accessSystem";

    // 接入系统模块管理
    public static final String ACCESS_SYSTEM_MODULE_URL = "accessSysMod";

    // 区域备案
    public static final String AREA_URL = "area";

    // 黑名单管理
    public static final String BLACK_LIST_URL = "blackList";

    // 字典管理
    public static final String DICTIONARY_URL = "dictionary";

    // 字典数值管理
    public static final String DICTIONARY_VALUE_URL = "dictionaryValue";

    // 国际化管理
    public static final String INTERNATIONAL_URL = "international";

    // 菜单管理
    public static final String MENU_MANAGE_URL = "menu";

    // 参数配置管理
    public static final String PARAM_CONFIG_URL = "paramConfig";

    // PSC用户管理
    public static final String PSC_USER_URL = "pscUser";

    // 角色管理
    public static final String ROLE_MANAGE_URL = "role";

    // 定时任务管理
    public static final String TIMED_TASK_URL = "task";

    // 用户日志查看管理
    public static final String USER_LOG_URL = "userLog";

    // 缓存管理
    public static final String CACHE_MANAGE_URL = "cacheManage";

    // 服务授权信息配置
    public static final String SERVICE_AUTH_CONFIG_URL = "serviceAuthConfig";

    // 对象参数配置路径
    public static final String OBJECT_PARAM_CONFIG_URL = "objParamConfig";

    // 权限信息管理
    public static final String AUTHORITY_URL = "authority";

    // 备案属性管理
    public static final String PROPERTY_URL = "property";

    // 帐户类型管理
    public static final String ACCOUNT_TYPE_URL = "accountType";

    // 部门管理
    public static final String DEPT_MANAGE_URL = "department";

    // 审核中心
    public static final String AUDIT_CENTER = "auditCenter";

    // 审核中心--物流开发平台审核中心
    public static final String AUDIT_CENTER_TRANSPORT = AUDIT_CENTER + "/transport";

    // 发布中心
    public static final String RELEASE = "release";

    // 发布中心--物流开放平台
    public static final String RELEASE_TRANSPORT_URL = RELEASE + "/transport";

    // 备案中心
    public static final String RECORD_CENTER = "recordCenter";

    public static final String REGISTER_STATISTICS = "registerStatistics";

    // 系统异常监控
    public static final String SYS_EXCEPTION_LOG = "/exceptionLog";

    // 征信中心
    public static final String CREDIT_CENTER_URL = "creditCenter";

    // 统计监控
    public static final String REPORT_URL = "report";

    // 统计监控
    public static final String DDWORD_REPORT_URL = REPORT_URL + "/ddword";

    // 运输平台统计监控
    public static final String TRANSPORT_REPORT_URL = REPORT_URL + "/transport";

    // 帐号异常管理
    public static final String ACCOUNT_EXCEPTION_URL = "accountException";

    // 在线统计监控
    public static final String ONLINE_STATISTIC_URL = "/onlineStatistic";

    // 接入系统操作类型
    public static final String OPERATE_TYPE = "operateType";

    // 系统管理员角色
    public static final String SYS_ROLE = "super_admin";

    // 系统超级用户
    public static final String PSC_USER = "admin";

    // 使用开关
    public static final String USE_DUBBO_AUTH = "1";

    // 不使用开关
    public static final String NOT_USE_DUBBO_AUTH = "0";

    // 对外提供接口文档地址
    public static final String DOC_URL = "doc";

    // ddword系统标志
    public static final String DDWORD_APP_SYS_FLAG = "44af8899947b4243809dd4cb6654fcd3";

    // 运输平台系统标志
    public static final String TRANSPORT_SYS_FLAG = "a17b37944c144eb18f1438ccceba55e1";

    //NJKD 为平台报价结算（平台报价）
    public static final String CALC_MS_SYSTEM_FLAG = "NJKD";

    /**
     * 运输平台在PSC系统中授权的用户名
     */
    public static final String TRANSPORT_AUTH_USER = "test";

    /**
     * 客户下单在PSC系统中授权的用户名
     */
    public static final String CUSTOMER_AUTH_USER = "test";

    /**
     * 请求返回状态选型 retCode
     */

    public static final int FAILURE = 0;// 失败
    public static final int SUCCESS = 1;// 成功

    /**
     * 短信网关 ApplicationId
     */
    public static final String SMS_APPLICATION_ID = "P000000000000038";

    /**
     * 短信网关 应用填写的内部扩展号码。MAS服务器需自动补充为此业务分配的长服务号码
     */
    public static final String SMS_EXTEND_CODE = "0100";
    /**
     * 消息编码类型 ASCII ASCII字符。 UCS2 USC2格式的UniCode字符。 GB18030 GB18030格式的中文字符。
     * GB2312 GB2312格式的中文字符。 Binary 二进制短信，用十六进制字符串。
     */
    public static final String SMS_MESSAGE_FORMAT = "GB2312";

    /**
     * 发送消息选项 Normal 普通短信 Instant 普通短信立即显示 Long 长短信 Structured
     * 长度小于160字节，但UDHI需置为1
     */
    public static final String SMS_SEND_METHOD = "Long";

    // MobileStation系统标志
    public static final String MOBILE_STATION_SYS_FLAG = "1b4d92f79abb44c297850f6f1da2c959";

    /**
     * 运输平台在PSC系统中授权的密码
     */
    public static final String TRANSPORT_AUTH_PWD = "test";
    /**
     * 快递下单在PSC系统中的授权密码
     */
    public static final String CUSTOMER_AUTH_PWD = "test";

    //访问token有效期 24 小时 1 * 24 * 60 * 60
    public static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 24 * 60 * 60;

    //刷新token有效期 7 天 7 * 24 * 60 * 60
    public static final Integer REFRESH_TOKEN_VALIDITY_SECONDS = 7 * 24 * 60 * 60;

}
