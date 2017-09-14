package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: WaybillTraceOperateBean
 * @Date: 2016/5/17
 * @Description: 插入流程跟踪日志Bean
 */
public class WaybillTraceOperateBean implements Serializable{
    private static final long serialVersionUID = 9133654961101559936L;

    private String acctUsername;//登陆账号名
    private String busiBookNo;//订单号
    private String startLocus;//开始站点
    private String destnLocus;//结束站点
    private Integer grade;
    private String remark;//备注
    private Integer execCode;
    private String scheducarno;//派车单号
    private Integer dispatchId;//签派号：也用做MOBILE_BOOKING_FORM的ID传参
    private Integer roleId;
    private String realName;


    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getStartLocus() {
        return startLocus;
    }

    public void setStartLocus(String startLocus) {
        this.startLocus = startLocus;
    }

    public String getDestnLocus() {
        return destnLocus;
    }

    public void setDestnLocus(String destnLocus) {
        this.destnLocus = destnLocus;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getExecCode() {
        return execCode;
    }

    public void setExecCode(Integer execCode) {
        this.execCode = execCode;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
