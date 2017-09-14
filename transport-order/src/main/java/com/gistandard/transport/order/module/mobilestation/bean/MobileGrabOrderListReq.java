package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileGrabOrderListReq
 * @Date: 2016/6/14
 * @Description: 接单列表查询请求bean
 */
@ApiModel(description = "抢单列表查询请求bean", parent = AppBasePagerRequest.class)
public class MobileGrabOrderListReq extends AppBasePagerRequest implements ValidTokenMark {
    private static final long serialVersionUID = 6739891536733192983L;

	@ApiModelProperty(value = "产品类型的Code",required = true, position = 1)
	private String productType;
	@ApiModelProperty(value = "当前快递员或司机、咪站所在市",required = true, position = 2)
	private String provinceName;
	@ApiModelProperty(value = "当前快递员或司机、咪站所在市",required = true, position = 3)
	private String cityName;
	@ApiModelProperty(value = "当前快递员或司机、咪站所在区",required = true, position = 4)
	private String countyName;
    private List<String> productTypeList;//产品类型的Code
	@ApiModelProperty(value = "3 司机、7 快递员、23咪站",required = true, position = 5)
	private Integer roleId;
	@ApiModelProperty(value = "当前经度",required = true, position = 6)
	private BigDecimal latitude;
	@ApiModelProperty(value = "当前纬度",required = true, position = 7)
	private BigDecimal longitude;

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

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
}
