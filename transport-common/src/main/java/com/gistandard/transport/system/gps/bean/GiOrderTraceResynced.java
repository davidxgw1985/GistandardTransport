package com.gistandard.transport.system.gps.bean;

import com.gistandard.transport.system.webservice.client.gps.GiPoint;

import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: GiOrderTraceResynced
 * @Date: 2016/11/7
 * @Description:
 */
public class GiOrderTraceResynced {
    private List<String> allBusiNo;     //如下单，接单，揽件，应该只有一条；如果是运输，则有可能是多条
    private String productType;         //OTCKD(同城快递), OTCZS(同城专送), OTCYSEX(同城运输,用户下单)--订单数据在运输的BookingForm中; ITCYS(同城运输, 咪站下单)--订单在运输的MobileBookingForm中; IGXYS(内部干线运输)--订单为HUB的派车单
    private String action;              //业务动作，请参考GiOrderTraceDefine
    private Date tsAct;                 //发生时间
    private String userCode;            //账号, 格式如CN0002500160001, XCN0002500160001。 下单--快递员；接单--快递员，咪站；运输--司机
    private String userName;
    private String userTel;

    private String roleCode;

    private String loginCode;                   //登录人员账号: userCode是公司账号, 则loginCode为员工账号;  userCode是HUB, 则loginCode为员工; userCode是车队, 则loginCode为司机;
    private String loginName;                   //登录人员姓名
    private String loginTel;                    //登录人员电话

    private String userCodeFrom;        //运输时，开始账号；
    private String provinceFrom;        //运输开始，下单，接单，揽件
    private String cityFrom;
    private String districtFrom;
    private String addressFrom;
    private GiPoint pointFrom;

    private String userCodeTo;          //运输时，到达账号；
    private String provinceTo;          //运输到达，POD确认
    private String cityTo;
    private String districtTo;
    private String addressTo;
    private GiPoint pointTo;
    private Integer typeBizAssign;//指派类型 0商管指派 1咪站指派 2蛙站指派 3系统指派 4 业助指派
    private Integer typeCancelOrdered;      //取消订单--取消时使用, 0: 用户取消; 1: 报价后用户取消, 2: 无人报价取消, 3: 车队5分钟后取消,

    private Date tsSigned;
    private boolean isToAccept;                 //是否用来接单, 即PopOrdered的消息, 并不能判断该单是用于接单, 只有广播单是

    private Integer typeFirstAid; //快递员咪站申请援助类型   0: 无, 1: 请重新指派, 2: 请修改电话, 3: 取消
    private String remarkFirstAid;//快递员咪站申请援助备注   如电话号码等

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public boolean getIsToAccept() {
        return isToAccept;
    }

    public void setIsToAccept(boolean isToAccept) {
        this.isToAccept = isToAccept;
    }

    public Integer getTypeBizAssign() {
        return typeBizAssign;
    }

    public void setTypeBizAssign(Integer typeBizAssign) {
        this.typeBizAssign = typeBizAssign;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginTel() {
        return loginTel;
    }

    public void setLoginTel(String loginTel) {
        this.loginTel = loginTel;
    }

    public List<String> getAllBusiNo() {
        return allBusiNo;
    }

    public void setAllBusiNo(List<String> allBusiNo) {
        this.allBusiNo = allBusiNo;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getTsAct() {
        return tsAct;
    }

    public void setTsAct(Date tsAct) {
        this.tsAct = tsAct;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCodeFrom() {
        return userCodeFrom;
    }

    public void setUserCodeFrom(String userCodeFrom) {
        this.userCodeFrom = userCodeFrom;
    }

    public String getProvinceFrom() {
        return provinceFrom;
    }

    public void setProvinceFrom(String provinceFrom) {
        this.provinceFrom = provinceFrom;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getDistrictFrom() {
        return districtFrom;
    }

    public void setDistrictFrom(String districtFrom) {
        this.districtFrom = districtFrom;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public GiPoint getPointFrom() {
        return pointFrom;
    }

    public void setPointFrom(GiPoint pointFrom) {
        this.pointFrom = pointFrom;
    }

    public String getUserCodeTo() {
        return userCodeTo;
    }

    public void setUserCodeTo(String userCodeTo) {
        this.userCodeTo = userCodeTo;
    }

    public String getProvinceTo() {
        return provinceTo;
    }

    public void setProvinceTo(String provinceTo) {
        this.provinceTo = provinceTo;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getDistrictTo() {
        return districtTo;
    }

    public void setDistrictTo(String districtTo) {
        this.districtTo = districtTo;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public GiPoint getPointTo() {
        return pointTo;
    }

    public void setPointTo(GiPoint pointTo) {
        this.pointTo = pointTo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Integer getTypeCancelOrdered() {
        return typeCancelOrdered;
    }

    public void setTypeCancelOrdered(Integer typeCancelOrdered) {
        this.typeCancelOrdered = typeCancelOrdered;
    }

    public Date getTsSigned() {
        return tsSigned;
    }

    public void setTsSigned(Date tsSigned) {
        this.tsSigned = tsSigned;
    }

    public Integer getTypeFirstAid() {
        return typeFirstAid;
    }

    public void setTypeFirstAid(Integer typeFirstAid) {
        this.typeFirstAid = typeFirstAid;
    }

    public String getRemarkFirstAid() {
        return remarkFirstAid;
    }

    public void setRemarkFirstAid(String remarkFirstAid) {
        this.remarkFirstAid = remarkFirstAid;
    }
}
