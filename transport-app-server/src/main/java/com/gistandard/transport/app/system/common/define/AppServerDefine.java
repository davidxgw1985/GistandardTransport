package com.gistandard.transport.app.system.common.define;

import com.valueplus.psc.dubbo.service.common.bean.ServiceAuthBean;

/**
 * Created by yujie on 2016/9/29.
 */
public class AppServerDefine {

	// 应用名称
	public static final String APPLICATION_NAME = "MobileStation";

	public static final String APP_ROOT = "/msApp";

	// 失败
	public static final int FAILURE = 0;

	// 成功
	public static final int SUCCESS = 1;

	public static final String API_URL = APP_ROOT + "/api";

	public static final String LOGIN_URL = APP_ROOT + "/mobileStation";

	public static final String VERSION_URL = APP_ROOT + "/appVersion";

	public static final String DICTIONARY_URL = APP_ROOT + "/dictionary";

	public static final String IM_URL = APP_ROOT + "/instantMessaging";

	public static final String SMS_URL = APP_ROOT + "/sms";

	public static final String GPS_URL = APP_ROOT + "/gpsToken";

	public static final String REGISTER_URL = APP_ROOT +"/mobileRegister";

	public static final String RECOMMEND_REGISTER_URL = APP_ROOT +"/recommendRegister";

	public static final String CHECK_REGISTER_URL = APP_ROOT +"/checkRegister";

	public static final String APPLYTOBE_URL = APP_ROOT +"/mobileStation/apply";

	public static final String KPP_URL = APP_ROOT + "/mobileStation/MoudleRel";

	public static final String KPP_DESC = "KPP管理模块";

	// 附件地址
	public static final String ATTACHMENT_URL = APP_ROOT + "/attachment";

	// 获取我的消息地址
	public static final String MY_MESSAGE_URL = APP_ROOT + "/mobileStation/message";

	// 获取我的消息地址
	public static final String MY_CALL_URL = APP_ROOT + "/mobileStation/call";

	// 反馈信息地址
	public static final String FEED_BACK_URL = APP_ROOT + "/feedBack";

	// 客户地址薄URL
	public static final String ADDRESS_URL = APP_ROOT + "/customer/address";

	// 订单批量生成排货派车单URL
	public static final String BATCH_SCHEDU_CAR_ORDER = APP_ROOT + "/scheduOrder";


	// 客户URL
	public static final String ACCOUNT_URL = APP_ROOT + "/account";

	
	//客户下单
	public static final String ORDER_URL = APP_ROOT + "/customer/order";


	//同城运输下单
	public static final String TRANSPORT_ORDER_URL = APP_ROOT + "/transport/order";

	//查询订单修改的上传图片
	public static final String QUERY_ORDER_UPLOAD = APP_ROOT + "/order/upload";

	//咪站,快递员,司机生成二维码签到URL
	public static final String SIGN_IN_URL = APP_ROOT + "/signInQRCode";

	// 平台参考价
	public static final String EXPRESS_QUOTE_URL = APP_ROOT + "/customer/getQuote";

	// 接单模块
	public static final String QUOTE_URL = APP_ROOT + "/mobileStation/quote";

	//外卖
	public static final String TAKEOUT_URL = APP_ROOT + "/mobileStation/takeout";

	//我要接单、我要运输
	public static final String WANT_URL = APP_ROOT + "/mobileStation/want";

	//库存
	public static final String STOCK_URL = APP_ROOT + "/mobileStation/stock";

	//M站库存
	public static final String M_STOCK_URL = APP_ROOT + "/mobileStation/Mistock";

	//用户订单
	public static final String USER_ORDER_URL = APP_ROOT + "/mobileStation/userOrder";

	//我的订单
	public static final String MY_ORDER_URL = APP_ROOT + "/mobileStation/myOrder";

	//订单
	public static final String MS_ORDER_URL = APP_ROOT + "/mobileStation/order";

	//我的订单
	public static final String ORDER_MANAGE_URL = APP_ROOT + "/mobileStation/orderManage";

	//工作中心
	public static final String ORDER_WORK_CENTER_URL = APP_ROOT + "/mobileStation/workCenter";

	//接单
	public static final String ACCEPT_ORDER_URL = APP_ROOT + "/mobileStation/orderNew";

	//客户下单站点
	public static final String STATION_URL = APP_ROOT + "/customer/station";

	//客户下单快递员
	public static final String COURIER_URL = APP_ROOT + "/customer/courier";

	//客户基本信息
	public static final String BASEINFO_URL = APP_ROOT + "/customer/baseInfo";

	//客户基本信息
	public static final String CUSTOMER_TRACK_URL = APP_ROOT + "/customer/track";

	//车队信息
	public static final String FLEET_URL = APP_ROOT + "/fleet";

	//日志跟踪信息
	public static final String TRACK_URL = APP_ROOT + "/track";

	//代理下单
	public static final String AGENCY_ORDER_URL = APP_ROOT + "/mobileStation/agencyOrder";

	//紧急指派
	public static final String EMERGENCY_URL = APP_ROOT + "/mobileStation/emergencyAssign";

	//结算
	public static final String CALC_URL = APP_ROOT + "/calc";

	//街道查询
	public static final String COMMON_TOWN = APP_ROOT + "/common/town";

	// 用户信息在session存储的属性名称
	public static final String SESSION_ATTR_USER = APPLICATION_NAME + "_" + "sessionUserInfo";

	// 手机短信注册验证码
	public static final String SESSION_ATTR_TELMSG_TOKEN_ID = APPLICATION_NAME + "_telMsgTokenId";

	// APP请求中，APP名称参数
	public static final String APP_REQ_NAME_PARAM = "appName";

	// MobileStation系统标志
	public static final String MOBILE_STATION_SYS_FLAG = "1b4d92f79abb44c297850f6f1da2c959";

	// 车辆类型
	public static final String COM_T_TYPE_VEHICLE = "VEHICLE";

	public static final String COM_T_TYPE_VEHICLE_CATEGORY = "VEHICLE_CATEGORY";

	// 货物类型
	public static final String COM_T_TYPE_GOODS = "GOODS";

	// 报价类型
	public static final String COM_T_TYPE_PRICETYPE = "PRICETYPE";

	// 时效性
	public static final String COM_T_TYPE_LIMITATIONTIME = "LIMITATIONTIME";

	/**
	 * 运输平台在PSC系统中授权的密码
	 */
	public static final String TRANSPORT_AUTH_PWD = "test";

	/**
	 * 运输平台在PSC系统中授权的用户名
	 */
	public static final String TRANSPORT_AUTH_USER = "test";

	// 运输平台系统标志
	public static final String TRANSPORT_SYS_FLAG = "a17b37944c144eb18f1438ccceba55e1";

	// 咪站签派
	public static final String DISPATCH_ORDER_URL = APP_ROOT + "/dispatch";

	// 咪站订单执行
	public static final String OPERATE_ORDER_URL = APP_ROOT + "/operate";

	//咪站接单
	public static final String MI_ACCEPT_ORDER_URL = APP_ROOT + "/miStation/accept";

	//快递员和咪站援助
	public static final String ORDER_ASSISTANCE_URL = APP_ROOT + "/share/assistance";

	/**
	 * 组装运输平台的系统认证信息
	 */
	public static ServiceAuthBean buildTransportAuthInfo() {
		ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
		serviceAuthBean.setAuthPwd(AppServerDefine.TRANSPORT_AUTH_PWD);
		serviceAuthBean.setAuthUser(AppServerDefine.TRANSPORT_AUTH_USER);
		serviceAuthBean.setSysFlag(AppServerDefine.TRANSPORT_SYS_FLAG);
		return serviceAuthBean;
	}
}
