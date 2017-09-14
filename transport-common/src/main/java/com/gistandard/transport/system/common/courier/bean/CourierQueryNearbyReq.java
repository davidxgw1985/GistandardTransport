package com.gistandard.transport.system.common.courier.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

/**
 * @author kongxm
 * @ClassName StationQueryNearbyReq
 * @Description 查询附近快递员请求
 * @Version 1.0
 * @Date 2016/1/28
 */
public class CourierQueryNearbyReq extends AppBasePagerRequest {

    private Double longitude;   //精度

    private Double latitude;    //纬度

    private Double distance;    //距离
    
    private Integer itemId;//条目类型ID
    
    public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    private String acctUsername;


    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
