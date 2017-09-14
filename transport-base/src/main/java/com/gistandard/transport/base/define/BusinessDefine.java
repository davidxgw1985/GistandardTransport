package com.gistandard.transport.base.define;

/**
 * Created by m on 2016/9/30.
 */
public class BusinessDefine {

    //国内卡车运输
    public static final String TRAN_COUNTRY_INTERNAL_TRUCK = "OCTPH";

    //国内转关运输
    public static final String TRAN_COUNTRY_INTERNAL_CLEARANCE = "OCTYS";

    //中港两地运输
    public static final String TRAN_CHINA_HK = "OZG";

    //中澳两地运输
    public static final String TRAN_CHINA_MACAO = "OZGKJ";

    //报关报检服务
    public static final String TRAN_OCC = "OCC";

    //站点服务
    public static final String TRAN_OHUB = "OHUB";

    //同城运输
    public static final String TRAN_SAME_CITY = "OBDTP";


    //物品类型的类型

    //车辆类型
    public static final String COM_T_TYPE_VEHICLE ="VEHICLE";
    //货物类型
    public static final String COM_T_TYPE_GOODS ="GOODS";
    //报价类型
    public static final String COM_T_TYPE_PRICETYPE ="PRICETYPE";
    //时效性
    public static final String COM_T_TYPE_LIMITATIONTIME ="LIMITATIONTIME";

    public static final String COM_T_TYPE_TRANSPORT ="TRANSPORT";
    public static final String COM_T_TYPE_SEX ="SEX";
    //运输类型
    public static final String COM_T_TYPE_TRANSPORTTYPE ="TRANSPORTTYPE";

    //运输类型(卡车、航空、海运、快递、仓储、CKD、其他)
    public static final String COM_T_TYPEBUSIKIND = "BUSIKIND";

    //订单类型： 运输业务、入库运输业务、出库运输业务、仓储运输业务、仓储业务、入库操作、出库操作
    public static final String COM_T_TYPEFORMKIND ="FORMKIND";

    //提货（送货）车型:Blue Cargo,3T,5T,8T,10T,20C,40C,40H,45C,PCS,Other,53C
    public static final String COM_T_TYPECARRIAGE="CARRIAGETYPE";

    //业务类型
    public static final String COM_T_TYPEBUSITYPT="BUSITYPT";

    //交易条款
    public static final String COM_T_TYPETRADETERM="TRADETERM";

    //货物状态
    public static final String COM_T_TYPEGOODSTATUS="GOODSTATUS";

    //提货方式
    public static final String COM_T_TYPECARRI_AGERECEI="CARRIAGERECEI";

    //送货方式
    public static final String COM_T_TYPECARRIAGE_DEIV="CARRIAGEDEIV";

    //体积
    public static final String UNIT_VOLUME = "164";

    //公斤
    public static final String UNIT_KILOGRAMME = "035";

    //运输类型  整车
    public static final String CARGO_LOADER_CI = "CI";

    //运输类型  零担
    public static final String CARGO_LOADER_CA = "CA";

    //0物流 1运输 2快递
    public static final String INTERFLOW_OF_GOODS ="0";

    public static final String TRANSPORT = "1";

    public static final String EXPRESS_DELIVERY ="2";

    public static final String GPS = "getDataInRange";

    public static final long NORMAL_FILE_SIZE = 2 * 1024 * 1024;

    public static final String MARK = "!";

    public static final String VSZ02G ="VSZ02G";

    public static final int GRADE = 2;

    public static final int DEPARTURE = 12;

    public static final int ARRIVALS = 13;

    public static final String POD = "POD";

    public static final String VECHILE = "车辆";

    public static final String DEPARTURE_STR = "已发车，即将到达目的地";

    public static final String ARRIVALS_STR = "车辆已到达目的地";

    public static final String POD_DEPARTURE_STR = "已发出，正在进行派件";

    public static final String POD_ARRIVALS_STR = "已签收";

    public static final int PRODUCT = 1;

    public static final int SERVICE = 2;

    public static final String ACCEPT_STR = "站点接收了订单";

    public static final String RE_ACCEPT_STR = "站点取消接单";

    public static final String ORDER_BROADCAST_CACEL = "取消订单";

    public static final int ACCEPT = 1;

    public static final int REACCEPT = 2;

    public static final int GRADE_PUB = 1;

    public static final String NOT_FOUND_ORDER = "未找到订单";

    public static final String ORDER_STATUS_CHANGED = "订单状态已改变";

    public static final String ORDER_STATUS_FAILL = "订单状态修改失败";

    public static final String ORDER_PRE_CALC_FAILL = "记录预结算数据失败";

    public static final String ORDER_PRE_CALC_CHANGED = "订单结算状态已改变";

    public static final String ORDER_SIGN_IN_PLS = "请先登录";

    public static final String ORDER = "下单成功";

    public static final String BIND = "/Wallet/bindToGeneralAccount.do";

    public static final String UNBIND = "/Wallet/unbindGeneralAccount.do";

    public static final String BIND_ACCOUNT = "/Wallet/getBindGeneralAccountInformation.do";
    //获取所有币种余额
    public static final String GET_ALL_ACCOUNT = "/Wallet/queryAllGaBalance.do";
    //余额变动明细查询列表
    public static final String GET_ACCOUNT_DETAIL_LIST = "/Wallet/queryWalletList.do";
    //提现
    public static final String WITHDRAW_CASH_ = "/Wallet/withdrawFromGaBalance.do";
    //查询银行信息
    public static final String GET_BANKINFO = "/Wallet/findAllBankNoCanWithDraw.do";
    //通用账户所提现过的银行卡号信息
    public static final String GET_USED_BANKINFO = "/Wallet/findAllBaAuthedByCode.do";
    //账单信息查询
    public static final String GET_BILL = "/Wallet/queryDealDetailList.do";

    public static final String REG_ACCOUNT = "/GeneralAccount/otherwebRegister.do";

    public static final String CHANGE_PWD = "/GeneralAcctManager/changePayPassIndex.do";

    public static final int ONESELF = 2;

    public static final int AGENT = 1;

    public static final String NANJING = "3195";

    public static final String SHENGZHENG = "3063";

    public static final String GUANGZHOU = "3047";

    public static final String TAIWANG = "2028";

    public static final String XIANGGUANG = "2030";

    public static final String NANJING_NAME = "江苏省-南京市";

    public static final String SHENGZHENG_NAME = "广东省-深圳市";

    public static final String GUANGZHOU_NAME = "广东省-广州市";

    public static final String TAIWANG_NAME = "台湾省-台北市";

    public static final String XIANGGUANG_NAME = "香港特别行政区-香港岛";


}
