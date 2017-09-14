package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileAcceptOrderListReq
 * @Date: 2016/6/14
 * @Description: 接单列表查询请求bean
 */
public class MobileAcceptOrderListReq extends AppBasePagerRequest implements ValidTokenMark {
    private static final long serialVersionUID = 6739891536733192983L;

    private String productType;//产品类型的Code
    private String acctUsername;//登录账号
    private List<String> productTypeList;//产品类型的Code
    private Integer roleId; //接单形式  3 司机、7 快递员

    public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<String> getProductTypeList() {
		return productTypeList;
	}

	public void setProductTypeList(List<String> productTypeList) {
		this.productTypeList = productTypeList;
	}

	public String getAcctUsername() {
		return acctUsername;
	}

	public void setAcctUsername(String acctUsername) {
		this.acctUsername = acctUsername;
	}

	public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
