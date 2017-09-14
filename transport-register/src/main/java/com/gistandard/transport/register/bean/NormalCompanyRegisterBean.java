package com.gistandard.transport.register.bean;

import com.valueplus.psc.dubbo.service.record.bean.ManagerInfoRelativeBean;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 企业资料填写bean
 */
public class NormalCompanyRegisterBean implements Serializable{

    // 真实姓名，企业名称
    @NotBlank(message = "{field.notNull}")
    @Length(min = 0,max = 200, message = "{AccountRegisterBean.realName.length}")
    private String realName;

    // 统一社会信用代码
    @NotBlank(message = "{field.notNull}")
    @Length(min = 0,max = 24, message = "{CompanyRegisterBean.custComno.length}")
    private String custComno;

    // 新版企业营业执照
    @NotNull(message = "{field.notNull}")
    private Integer licenseFileId;

    // 真实头像照片ID
    private Integer portraitFileId;

    // 新版企业营业执照url
    private String licenseUrl;

    // 真实头像照片
    private String userImg;

    // 真实头像照片url
    private String portraitUrl;

    // 管理员
    @NotNull(message = "{field.notNull}")
    private List<ManagerInfoRelativeBean> managers;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCustComno() {
        return custComno;
    }

    public void setCustComno(String custComno) {
        this.custComno = custComno;
    }

    public Integer getLicenseFileId() {
        return licenseFileId;
    }

    public void setLicenseFileId(Integer licenseFileId) {
        this.licenseFileId = licenseFileId;
    }

    public Integer getPortraitFileId() {
        return portraitFileId;
    }

    public void setPortraitFileId(Integer portraitFileId) {
        this.portraitFileId = portraitFileId;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public List<ManagerInfoRelativeBean> getManagers() {
        return managers;
    }

    public void setManagers(List<ManagerInfoRelativeBean> managers) {
        this.managers = managers;
    }
}
