package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

/**
 * Created by m on 2016/5/17.
 */
public class ReceiveCustomerOrderReq extends AppBaseRequest implements ValidTokenMark{
    /** 
	* @fieldName serialVersionUID
	* @describe TODO
	* @fieldType long
	*/ 
	private static final long serialVersionUID = 2310875743282860190L;
    private String bookbusino;
    private String userImg;//头像
    private String realName;//真实姓名
    private String registerType;//类型
    private String tel;//手机
    private String orderForm;//订单来源1 app， 2 BS
    private Integer roleId; // 接单形式  3 司机 、7 快递员
    private Integer revCompanyId;
    /**
     * 是否是广播单
     */
    private boolean isBrocast;
    
    /**
     * mobile_book_form ID
     */
    private Integer mobileBookFormId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMobileBookFormId() {
		return mobileBookFormId;
	}

	public void setMobileBookFormId(Integer mobileBookFormId) {
		this.mobileBookFormId = mobileBookFormId;
	}

	public boolean isBrocast() {
		return isBrocast;
	}

	public void setBrocast(boolean isBrocast) {
		this.isBrocast = isBrocast;
	}

    public String getBookbusino() {
        return bookbusino;
    }

    public void setBookbusino(String bookbusino) {
        this.bookbusino = bookbusino;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(String orderForm) {
        this.orderForm = orderForm;
    }

    public Integer getRevCompanyId() {
        return revCompanyId;
    }

    public void setRevCompanyId(Integer revCompanyId) {
        this.revCompanyId = revCompanyId;
    }
}
