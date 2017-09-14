package com.gistandard.transport.base.define;

/**
 * Created by m on 2016/9/30.
 */
public class CustomerDefine {

    public static final long FIVE_MINUTE = 300000L;

    public static final int DocType_Pickup = 1;	//取件单
    public static final int DocType_Send = 2;		//派件单

    public static final int BizType_Transport	= 1;	//运输
    public static final int BizType_Express =	2;	//快递

    public static final String IM_MS_DEFINE = "0001";
    public static final String IM_WA_DEFINE = "0009";
    public static final String IM_DEFINE = "0010";//ddword
    public static final String IM_BS_DEFINE = "0003";//bs客户下单
    public static final String IM_BUSI_TYPE = "NOTIFY_0001";
    public static final String IM_SYS_DEFINE = "SYS_1003";
    public static final String IM_TRANSPORT_DEFINE = "SYS_1005";//运输平台
    public static final String IM_MOBILE_STATION_DEFINE = "SYS_1006";//MS
    public static final String IM_MERCHANT_CENTER_DEFINE = "SYS_1007";//商户中心系统-通知flag

    public static final String IM_REMAINCODE_ACCEPT_ORDER= "PUSH_CS_ORDER_000001";//接单
    public static final String IM_REMAINCODE_REFUSE_ORDER= "PUSH_ORDER_000003";//接单-拒绝订单
    public static final String IM_REMAINCODE_DELIVERY_CONFIRM= "PUSH_ORDER_000002";//确认送达
    public static final String IM_REMAINCODE_GIVEUP_ORDER= "PUSH_ORDER_000004";//放弃订单
    public static final String IM_REMAINCODE_NONE_FLEET = "PUSH_ORDER_000005";//没有车队竞价
    public static final String IM_REMAINCODE_FLEET_ACCEPT = "PUSH_ORDER_000006";//车队已竞价完成,通知用户确认
    public static final String IM_REMAINCODE_FLEET_DISPATCH = "PUSH_ORDER_000007";//车队已分发司机/已经派车


    public static final String IM_ORDERFORM_APP = "1";
    public static final String IM_ORDERFORM_BS = "2";
    public static final String IM_ORDERFORM_WECHAT = "3";

    //订单状态
    public static final int ORDER_STATUS_NOORDER = 0;//未接单&&下单
    public static final int ORDER_STATUS_FROZEN = -4;//冻结
    public static final int ORDER_STATUS_CANCLE = -1;//取消订单
    public static final int ORDER_STATUS_DELETE = -9;//删除订单
    public static final int ORDER_STATUS_HAVEORDER = 1;//已接单
    public static final int ORDER_STATUS_SENDIN = 2;//运送中
    public static final int ORDER_STATUS_FINISH = 5;//已签收
    public static final int ORDER_STATUS_GIVEUP = -999;//已失败

    public static final String CUSTOMER_APP = "CustomerApp";
    //到付验证短信模版
    public static final int SMS_MODLE = 6;


    public static final int WAYBILL_TRACE_HUB_HAVE_ORDER = 27;//Hub接单
    public static final int WAYBILL_TRACE_HUB_RECEIVE_GOODS = 4;//Hub收货
    public static final int WAYBILL_TRACE_HUB_IN = 7;//Hub入库
    public static final int WAYBILL_TRACE_HUB_OUT = 8;//Hub出库
    public static final int WAYBILL_TRACE_HUB_GOODS = 9;//Hub排货
    public static final int WAYBILL_TRACE_HUB_CAR = 10;//Hub派车



    public static final String ORDER_ROLE_SENDERE = "1";//寄件人
    public static final String ORDER_ROLE_CENN = "2";//收件人
    public static final String ORDER_DELIVERY_OWNER = "3";//快递员
    //    public static final String ORDER_COMPANY_STATION = "4";//站点
    public static final String ORDER_CAR_OWNER = "4";//司机
    public static final String ORDER_ROLE_MS = "5";//咪站
    public static final String ORDER_ROLE_HUB = "6";//HUB

    public static final int ORDER_NOTIFY_FINSH = -1;//结束




    public static final int FOLLOW = 1;//关注
    public static final int UNFOLLOW = 0;//未关注
    public static final int FOLLOW_FOR_SURE = 2;//关注待确认

    public static final String IM_FOLLOW_CODE = "1";//关注确认消息

    public static final String IM_FOLLOW_NOTIFY_CODE = "2";//关注确认通知消息

    public static final String IM_STATUS_CHANGE_CODE = "3";//状态类消息

    public static final String IM_STATUS_HAVE_ORDER_CODE = "4";//接单应答通知

    public static final String APP_TAG = "0001";

    public static String SMS_MODEL = "2505791980006";

    public static final int SAVE_ADDRES = 1;//保存地址

    public static String CLASSESTYPE = "1";//险类：国内货运险

    public static String CLASSTYPE = "11040200";//险种：公路货运险

    public static String HIGHWAY = "4";//国内货运险运输方式 公路

    public static String PARCEL = "6";//国内货运险运输方式 邮包

    public static String HIGHWAY_TOOL = "卡车";// 公路运输工具 卡车

    public static String PARCEL_TOOL = "飞机";//邮包运输工具 待定

    public static String CURRENCYCODE = "01";//人民币

    public static String NOTNEEDBILL = "1";//不需要发票

    public static String PRINTTYPE = "2";//电子保单

    public static String PRICECOND = "1";// C&F

    public static String MAINITEMCODE_KD = "04PALL";//邮包一切险

    public static String MAINITEMCODE_YS = "C090017M";//公路货物运输保险条款

    public static String RATE = "0.0005";//费率

    public static final int PREMIUM_STATUS_TAKE_EFFECT = 10;//保单生效

    public static String ENDORSE_RESON = "订单取消";//订单取消

    public static String WITHDRAW_INSURE= "002";//退保批改

    public static final double  SEARCH_DISTANCE = 5000;

    public static final int OTCKD = 38;

    //太保已支付
    public static final int HAVE_PAY= 10;

    //太保待支付
    public static final int NEED_PAY = 36;
}
