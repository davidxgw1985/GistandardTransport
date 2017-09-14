package com.gistandard.transport.webdubbo.auditAccount.define;

/**
 * 调用服务结果代码
 */
public enum ServiceResultCode {

    SERVICE_SUCCESS("执行成功", 0),
    PARAM_INFO_NULL("参数信息为空", 1),
    PARAM_INFO_ERROR("参数信息错误", 2),
    ACCOUNT_NOT_EXIST("帐户不存在", 3),
    ACCOUNT_ROLE_EXIST("帐户角色已存在", 4),
    REQUEST_NOT_EXIST("申请不存在", 5),
    REQUEST_STATE_ERROR("申请状态不正确", 6),
    REQUEST_STATE_EXIST_ERROR("存在未审核的申请", 7),
    DATA_ERROR("数据错误", 8),
    UPDATE_DATA_ERROR("更新数据失败", 9),
    UPLOAD_FILE_ERROR("文件上传失败", 10),
    UPLOAD_FILE_NULL("文件为空", 11),
    UPLOAD_FILENAME_ERROR("文件格式不正确", 12),
    UPLOAD_FILE_SIZE_ERROR("文件超过指定大小", 13),
    REQUEST_PERSON_ERROR("申请角色错误，个人只能升级成快递员、司机、咪站和业务中心", 14),
    REQUEST_COMPANY_ERROR("申请角色错误，企业只能升级成站点、业务中心、商业中心", 15);

    private String name;
    private int value;

    private ServiceResultCode(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getName(int value) {
        ServiceResultCode[] arr = values();
        int len = arr.length;

        for(int i = 0; i < len; ++i) {
            ServiceResultCode c = arr[i];
            if(c.getValue() == value) {
                return c.name;
            }
        }

        return null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
