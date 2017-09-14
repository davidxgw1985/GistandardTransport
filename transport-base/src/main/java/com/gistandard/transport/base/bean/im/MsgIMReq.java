package com.gistandard.transport.base.bean.im;

import com.gistandard.transport.base.bean.app.BaseReqBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.Bidi;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 广播单查询信息
 * 
 * @author ShengHao
 * 
 */
public class MsgIMReq extends BaseReqBean implements Serializable{
	private static final long serialVersionUID = -2855216992970154492L;
	private String busiBookNo;//订单号
	private String scheducarno;//实派车单号
	private String remindCode;//业务标识,定义的消息类别
	private Integer orderId;//订单ID
	private Integer orderFrom;//2运输指派单，4个人指派单，5运输广播单6个人广播单，7MS指派单，8MS广播单
	private Integer msgTo;//1发给用户；2发给MS；3发给Hub
	private int operateFrom;//1新的指派单下发给MS
	private String createUser;//接收方账号
	private Map<String, String> mapObject = new HashMap<String, String>();//消息体
	private Integer roleId; //接单形式  3司机、7快递员
	private BigDecimal bidValue;//新增属性 车队竞价价格
	private String bidCurr;//新增属性 车队竞价币制
	private String fleetName;//新增属性 车队名称
	
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public int getMsgTo() {
		return msgTo;
	}
	public void setMsgTo(int msgTo) {
		this.msgTo = msgTo;
	}
	public int getOperateFrom() {
		return operateFrom;
	}
	public void setOperateFrom(int operateFrom) {
		this.operateFrom = operateFrom;
	}
	public Integer getOrderFrom() {
		return orderFrom;
	}
	public void setOrderFrom(Integer orderFrom) {
		this.orderFrom = orderFrom;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Map<String, String> getMapObject() {
		return mapObject;
	}
	public void setMapObject(Map<String, String> mapObject) {
		this.mapObject = mapObject;
	}
	public String getRemindCode() {
		return remindCode;
	}
	public void setRemindCode(String remindCode) {
		this.remindCode = remindCode;
	}
	public String getBusiBookNo() {
		return busiBookNo;
	}
	public void setBusiBookNo(String busiBookNo) {
		this.busiBookNo = busiBookNo;
	}
	public String getScheducarno() {
		return scheducarno;
	}
	public void setScheducarno(String scheducarno) {
		this.scheducarno = scheducarno;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public BigDecimal getBidValue() {
		return bidValue;
	}

	public void setBidValue(BigDecimal bidValue) {
		this.bidValue = bidValue;
	}

	public String getBidCurr() {
		return bidCurr;
	}

	public void setBidCurr(String bidCurr) {
		this.bidCurr = bidCurr;
	}

	public String getFleetName() {
		return fleetName;
	}

	public void setFleetName(String fleetName) {
		this.fleetName = fleetName;
	}
}
