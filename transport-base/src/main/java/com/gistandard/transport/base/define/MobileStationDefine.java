package com.gistandard.transport.base.define;

/**
 * @author: xgw
 * @ClassName: MobileStationDefine
 * @Date: 2016/2/29
 * @Description:
 */
public class MobileStationDefine {
	// 订单状态定义
	public static final int MOBILE_ORDER_STATUS_NOORDER = 0;// 未接单
	public static final int MOBILE_ORDER_STATUS_TO_QUOTE = 12;// 待报价
	public static final int MOBILE_ORDER_STATUS_QUOTE = 13;// 已报价，待确认
	public static final int MOBILE_ORDER_STATUS_DOC_FAIL = -12;// 已失效(订单)同城运输
	public static final int MOBILE_ORDER_STATUS_QUO_FAIL = -13;// 已失效(报价)同城运输
	public static final int MOBILE_ORDER_STATUS_HAVEORDER = 1;// 已接单
	public static final int MOBILE_ORDER_STATUS_TAKE_SUCESS = 40;// 取件成功
	public static final int MOBILE_ORDER_STATUS_SENDIN = 20;// 派件中、发车、待签派单
	public static final int MOBILE_ORDER_STATUS_BROADCAST = 22;// 同城快递广播中,同城运输广播单 待报价
	public static final int MOBILE_ORDER_STATUS_SINGLE_PENDING = 25;// 已签派，待接单
	public static final int MOBILE_ORDER_STATUS_SINGLE = 26;// 已签派，已接单
	public static final int MOBILE_ORDER_STATUS_SINGLE_DONE = 27;// 已签派，签派出去的单，对方已完成
	public static final int MOBILE_ORDER_STATUS_AGENCY_PENDING = 23;// 已紧急指派，待接单
	public static final int MOBILE_ORDER_STATUS_AGENCY = 24;// 已紧急指派，已接单
	public static final int MOBILE_ORDER_STATUS_EXCEPTION_RECE = 4;// 异常收件
	public static final int MOBILE_ORDER_STATUS_FINISH = 5;// 已完成、送达确认
	public static final int MOBILE_ORDER_STATUS_ASSIGN_CANCEL = -1;// 取消指派
	public static final int MOBILE_ORDER_STATUS_REFUSE = -2;// 拒绝
	public static final int MOBILE_ORDER_STATUS_GIVEUP = -3;// 取消订单、放弃
	public static final int MOBILE_ORDER_STATUS_FAILURE = -4;// 派件失败、取件失败
	public static final int MOBILE_ORDER_STATUS_RETURN = -5;// 已退回
	public static final int MOBILE_ORDER_STATUS_TO_DO = 11;// 咪站待执行单M-POD，等POP-M发车后触发为已接单
	public static final int MOBILE_ORDER_STATUS_ASSIST = 50;//咪站、快递员申请援助,援助中状态


	// 订单数据来源
	public static final int MOBILE_ORDER_FROM_DISPATCH_BROADCAST = 1;// 签派广播单
	public static final int MOBILE_ORDER_FROM_SCHEDU_BROADCAST = 5;// 运输广播单
	public static final int MOBILE_ORDER_FROM_PERSONAL_BROADCAST = 6;// 市场广播单
	public static final int MOBILE_ORDER_FROM_SCHEDU = 2;// 运输指派单
	public static final int MOBILE_ORDER_FROM_DISPATCH = 3;// 签派指派单
	public static final int MOBILE_ORDER_FROM_PERSONAL = 4;// 市场指派单
	public static final int MOBILE_ORDER_FROM_MS = 7;// MS指派单
	public static final int MOBILE_ORDER_FROM_MS_BROADCAST = 8; // MS广播单
	public static final int MOBILE_ORDER_FROM_M = 9; // 咪站自己生成的派车单

	// 签派状态
	public static final String DISPATCH_STATUS_ACCEPT_ORDER = "0_23";// mobile
																		// station接单
	public static final String DISPATCH_STATUS_REFUSE_ORDER = "0_-22";// mobile
																		// station拒绝接单
	public static final String DISPATCH_STATUS_GIVEUP_ORDER = "0_-23";// mobile
																		// station接单后再放弃接单
	public static final String DISPATCH_STATUS_FAILURE_ORDER = "0_-24";// mobile
																		// station接单后再订单失败
	public static final String DISPATCH_STATUS_DEPART_ORDER = "0_24";// mobile
																		// station确认发车
	public static final String DISPATCH_STATUS_DELIVERY_ORDER = "0_25";// mobile
																		// station送达确认

	// 派车单状态
	public static final int SCHEDU_CAR_STATUS_ACCEPT_ORDER = 14;// mobile
																// station接单
	public static final int SCHEDU_CAR_STATUS_REFUSE_ORDER = 13;// mobile
																// station拒绝接单
	public static final int SCHEDU_CAR_STATUS_GIVEUP_ORDER = 15;// mobile
																// station接单后再放弃接单
	public static final int SCHEDU_CAR_STATUS_FAILURE_ORDER = 16;// mobile
																	// station接单后再订单失败
	public static final int SCHEDU_CAR_STATUS_LOADING = 21;// HUB端完成装车扫描
	public static final int SCHEDU_CAR_STATUS_TAKE_SUCCESS = 22;// mobile
																// station取件完成
	public static final int SCHEDU_CAR_STATUS_DEPART_ORDER = 17;// mobile
																// station确认发车
	public static final int SCHEDU_CAR_STATUS_DELIVERY_ORDER = 18;// mobile
																	// station送达确认

	// 修改Hub出入库状态
	public static final int HUB_STATUS_PK_SUCESS = 102;// HUB PK通过
	public static final int HUB_STATUS_SEND_SUCESS = 104;// mobile station确认收货

	// 运输类型
	public static final String TRANSPORT_TYPE_LOGISTICS = "0";// 物流
	public static final String TRANSPORT_TYPE_TRANSPORT = "1";// 运输
	public static final String TRANSPORT_TYPE_EXPRESS = "2";// 快递

	// 报价类型
	public static final int QUOTE_TYPE_ZC = 1;// 整车
	public static final int QUOTE_TYPE_LD = 2;// 零担
	public static final int QUOTE_TYPE_ZLFD = 3;// 重量分段
	public static final int QUOTE_TYPE_GL = 4;// 公里
	public static final int QUOTE_TYPE_GLFD = 5;// 公里分段
	public static final int QUOTE_TYPE_MY = 6;// 自定议
	public static final int QUOTE_TYPE_EXPRESS_PER = 7;// 快递按票
	public static final int QUOTE_TYPE_EXPRESS = 8;// 快递首重续重（同重量分段）
	public static final int QUOTE_TYPE_SPECIAL_DELIVERY = 9;// 同城专送
	public static final int QUOTE_TYPE_TRANSPORT = 10;// 运输车（类似快递重量分段）
	public static final int QUOTE_TYPE_TAXI = 101;// 出租车（类似快递重量分段）

	// 常用单位
	public static final String COM_UNIT_MI = "030";// 米
	public static final String COM_UNIT_XIANG = "120";// 箱

	// 出入库类型
	public static final int STOCK_IN = 0;// 入库
	public static final int STOCK_OUT = 1;// 出库

	// 客户类型类型
	public static final String POP = "POP";
	public static final String POD = "POD";
	public static final String POM = "POM";
	public static final String M = "M";// 咪站
	public static final String W = "W";// 洼站

	// ItemCategory类型
	public static final int ITEM_CATEGORY_PRODUCT = 1;
	public static final int ITEM_CATEGORY_SERVICE = 2;

	// 转单中心状态
	public static final int SINGLE_CENTER_TOSINGLE = 0;
	public static final int SINGLE_CENTER_TOACCEPT = 1;
	public static final int SINGLE_CENTER_ACCEPT = 2;
	public static final int SINGLE_CENTER_REFUSE = -1;

	// 用户角色类型
	public static final String OPERATOR_CAR_OWNER = "司机";
	public static final String OPERATOR_COURIER = "快递员";
	public static final String OPERATOR_MSTATION = "咪站";
	public static final String OPERATOR_WSTATION = "蛙站";

	// 报价单规则类型（1、小于，2、小于等于，3、大于，4、大于等于，5、等于）
	public static final int QUOTE_RULE_TYPE_XYDY = 2;// 小于等于
	public static final int QUOTE_RULE_TYPE_DY = 3;// 大于

	// 产品类型
	public static final String PRODUCT_TYPE_GNKD = "OGNEXP";
	public static final String PRODUCT_TYPE_GJKD = "OGJEXP";
	public static final String PRODUCT_TYPE_TCKD = "OTCKD";
	public static final String PRODUCT_TYPE_TCYS = "OTCYSEX";
	public static final String PRODUCT_TYPE_BDTP = "BDTP";
	public static final String PRODUCT_TYPE_EXPHUB = "EXPHUB";
	public static final String PRODUCT_TYPE_ITCYS = "ITCYS";
	public static final String PRODUCT_TYPE_ITCKD = "ITCKD";
	// public static final String PRODUCT_TYPE_IPJ = "IPJ";
	// public static final String PRODUCT_TYPE_IQJ = "IQJ";
	public static final String PRODUCT_TYPE_0TCWM = "OTCWM";
	public static final String PRODUCT_TYPE_OTCKDM = "OTCKDM"; // 咪站
	public static final String PRODUCT_TYPE_TCZS = "OTCZS";
	public static final String PRODUCT_TYPE_OTCYS = "OTCYS";

	// 紧急指派类型
	public static final int ISEMERGENCY_NO = 0;// 不是紧急指派
	public static final int ISEMERGENCY_YES = 1;// 是紧急指派

	// 用户、商户类型（产品、服务）
	public static final Integer PRODUCT = 1;// 用户、产品
	public static final Integer SERVICE = 2;// 商户、服务

	// 支付方式
	public static final int PAYTYPE_COLLECT = 1;// 到付
	public static final int PAYTYPE_GENERALACCT = 2;// GeneralAcct为通用账户支付
	public static final int PAYTYPE_CASH = 3;// CASH为现金
	public static final String PAYMENT_CASH = "CASH";// CASH为现金
	public static final String PAYMENT_GENERALACCT = "GeneralAcct";// GeneralAcct为通用账户支付

	// 订单类型
	// 1-取件单
	public static final Integer SEND_ORDER_TYPE = 1;
	// 2-派件单
	public static final Integer GET_ORDER_TYPE = 2;

	// 结算类型
	// 已结算
	public static final Integer HAD_JS = 1;

	//GPS日志常量
	public static final String Action_FleetAccept = "FleetAccept";              //车队接单, 即报完价以后, 10分钟后推给用户, 用户已确认
	public static final String Action_BidFailured = "BidFailured";              //车队接单, 即报完价以后, 10分钟后推给用户, 用取消该标, 或用户收到推送, 10分钟后也没有做确认--流标
	public static final String Action_PopOrdered = "PopOrdered";                //POP用户下单
	public static final String Action_CancelOrdered = "CancelOrdered";          //用户取消下单
	public static final String Action_AcceptOrder = "AcceptOrder";              //接单 接客户O单
	public static final String Action_BizAssignFleet = "BizAssignFleet";        //商管指派车队
	public static final String Action_CancelAccept = "CancelAccept";            //取消接单
	public static final String Action_PickupOrder = "PickupOrder";              //揽件, 可以快递员览件，或直接送到咪站 POP取件或咪站直接收货
	public static final String Action_DriverPickup = "DriverPickup";            //司机收货
	public static final String Action_StationReceived = "StationReceived";    	//站点收货
	public static final String Action_TransportStart = "TransportStart";        //运输开始 递送员司机发车
	public static final String Action_TransportArrival = "TransportArrival";    //运输到达 递送员司机
	public static final String Action_DeliveryToPod = "DeliveryToPod";          //POD派件 递送员
	public static final String Action_PodConfirmed = "PodConfirmed";            //POD确认收货
	public static final String Action_PodFailured = "PodFailured";              //POD派送失败
	public static final String Action_PodReturn = "PodDeliveryReturned";        //POD派送失账后退回 -- 派送失败后, 快递员把包裹退回给上一家(咪站, 或蛙站
	public static final String Action_ErrorPosition = "ErrorPosition";          //客户下单问题单，地址坐标不准确
    public static final String Action_FirstAid = "FirstAid";                    //快递员或者咪站申请救援
	public static final String Action_FleetToDriver = "FleetToDriver";          //车队分发给司机

	//批量操作标志
	public final static int BATCH_PICK_UP = 1;//标识取件
	public final static int BATCH_SENDING = 1 << 1;//标识发车
	public final static int BATCH_ASSIGNMENT_COURIER = 1 << 2;//标识指派咪站、蛙站（快递员指派/广播）
	public final static int BATCH_ASSIGNMENT_STATION = 1 << 3;//标识咪站派车快递员（咪站指派/广播）
	public final static int BATCH_TRANSFER = 1 << 4;//标识咪站中转
	public final static int BATCH_ASSIGNMENT = 1 << 5;//标识咪站排货派车(中转后指派/广播)
	public final static int BATCH_STOCK_OUT = 1 << 6;//咪站分拣出库
	public final static int BATCH_DELIVERY_CONFIRM = 1 << 7;//快递员、司机批量送达确认
	public final static int BATCH_ASSIGNMENT_DRIVER = 1 << 8;//标识咪站派车司机（咪站指派/广播）

	//2M 普通文件大小
	public static final long NORMAL_FILE_SIZE = 100 * 1024 * 1024;

	//GPS 发送广播单接口操作参数
	public static final String GPS_ACTION_RESYNC_DATA = "ResyncData";            //同步数据
	public static final String GPS_ACTION_DELETE = "Delete";                    //删除数据

	public static final String  WEIXINORDERCODE = "WX00001";//GPS约定

	public static final String SYS_COMPANY_NAME = "通用供应链技术有限公司(南京)";
}
