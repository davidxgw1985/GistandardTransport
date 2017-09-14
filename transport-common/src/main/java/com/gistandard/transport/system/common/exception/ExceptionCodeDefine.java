package com.gistandard.transport.system.common.exception;

/**
 * @author yujie
 * @ClassName ExceptionCodeDefine
 * @Description
 * @Version 1.0
 * @Date 2016-01-18
 */
public class ExceptionCodeDefine {

    // 系统公共异常码,个人业务异常可以单独定义方便精确定位,公共目前4位长度

    /**规范     0结尾公共异常，1空，2长度超限，3参数不合法，4不在可选范围，5冲突*/


    /**
     * 参数错误.
     */
    public static final int PARAM_ERROR = 1000;

    /**
     * 参数为空.
     */
    public static final int PARM_ISEMPTY = 1001;

    /**
     * 参数超过长度.
     */
    public static final int PARM_OVERLENGTH = 1002;

    /**
     * 参数不合法.
     */
    public static final int PARM_INVALID = 1003;

    /**
     * 参数不在可选范围中.
     */
    public static final int PARM_OPTION_INVALID = 1004;

    /**
     * 无此权限.
     */
    public static final int PERMISSION = 1200;

    /**
     * 登录超时.
     */
    public static final int LOGIN_TIMEOUT = 1300;

    /**
     * 登录失败.
     */
    public static final int LOGIN_FAILED = 1301;

    /**
     * 无效操作.
     */
    public static final int OPERATE_FAILED = 1400;

    /**
     * 系统内部错误.
     */
    public static final int INTERNAL_SERVER_ERROR = 1500;

    /**
     * 数据库访问异常.
     */
    public static final int INTERNAL_DB_ACCESS_ERROR = 1501;

    /**
     * 下游请求超时.
     */
    public static final int GATEWAY_TIMEOUT = 1504;

    /**
     * 数据权限不合法.
     */
    public static final int DATA_PARM_NOT_EXIST = 2001;
    /**
     * 数据权限不合法.
     */
    public static final int DATA_PARM_INVALID = 2003;
    
    /**
     * 系统加密/解密校验错误
     */
    public static final int SYS_USER_PWD_AES = 2004;
    
    /**
     * 转码错误
     */
    public static final int TRANSCODING_ERROR = 2005;


    /**
     * 未登录
     */
    public static final int UNLOGIN_ERROR = 2006;

}