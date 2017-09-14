package com.gistandard.transport.app.dubbo.base.bean;


import java.io.Serializable;
import java.util.Date;
/**
 * @author 员伟
 */
public class MobileValueAdd implements Serializable {

    private static final long serialVersionUID = -2282875859608298630L;
    private Integer id;

    private String valueAddCode;

    private String valueAddName;

    private Date createDate;

    private Integer createUserId;

    private String createUser;

    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValueAddCode() {
        return valueAddCode;
    }

    public void setValueAddCode(String valueAddCode) {
        this.valueAddCode = valueAddCode;
    }

    public String getValueAddName() {
        return valueAddName;
    }

    public void setValueAddName(String valueAddName) {
        this.valueAddName = valueAddName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
