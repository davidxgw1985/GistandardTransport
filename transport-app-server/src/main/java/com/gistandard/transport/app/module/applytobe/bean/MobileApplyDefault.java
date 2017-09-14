package com.gistandard.transport.app.module.applytobe.bean;

/**
 * @Author zxnui
 * @Date 8/16/16.
 */
public class MobileApplyDefault {
    public static final String URL_HEAD = "";

    //申请页面，选择升级为快递员还是司机
    public static final String URL_BASE_HTTP = URL_HEAD+"/mobileStation/apply/infoByChose";
    public static final String URL_SHOW_HTTP = URL_HEAD+"/mobileStation/apply/show";

    public static final String URL_BASE = "module/applytobe/index";
    //申请完成，等待审核页面
    public static final String URL_APPLY_FINISH = "module/applytobe/upgrade-apply-success";

    public static final String URL_FINISH = "module/applytobe/upgrade-success";

    public static final String URL_KD_INFO_1 = "module/applytobe/upgrade-courier-desc";
    public static final String URL_KD_INFO_2 = "module/applytobe/upgrade-courier-fill";

    public static final String URL_SJ_INFO_1 = "module/applytobe/upgrade-driver-desc";
    public static final String URL_SJ_INFO = "module/applytobe/upgrade-driver-fill";

    public static final String URL_MZ_INFO_1 = "module/applytobe/upgrade-mz-desc";
    public static final String URL_MZ_INFO_2 = "module/applytobe/upgrade-mz-fill";

    public static final String URL_EXPLAIN = "module/applytobe/upgrade-explain";

    public static final String MSG_ERROR = "程序员开小差...稍后请重试...";


    public static final String MSG_FINISH = "您已经是快递员、司机和咪站，无需再次升级";

    // 申请成功
    public static final String MSG_APPLY = "您已成功提交申请{0}资料,请耐心等待我们的审核,一般1-4个工作日左右,审核结果会通过您注册时填写的手机号发送给您,敬请留意";

    // 等待审核
    public static final String MSG_APPLY_WAIT = "您成为{0}的申请已经提交,正在审核中请耐心等待";

    // 审核通过
    public static final String MSG_APPLY_SUCCESS = "恭喜，您成为{0}的申请已审核通过";

    // 审核拒绝
    public static final String MSG_APPLY_FAIL ="您申请成为{0}的要求被拒绝，原因是：";
}
