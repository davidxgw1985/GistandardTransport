package com.gistandard.transport.calculate.service;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.calculate.bean.calc.*;
import com.gistandard.transport.quote.module.calc.bean.CalcForPriceReq;

/**
 * @author xgw
 * @ClassName CalcService
 * @Description 结算模块
 * @Version 1.0
 * @Date 2016/2/23
 */
public interface CalcService {

    /**
     * O单结算 同步接口
     * @param calcForPriceReq
     * @return
     */
    CalcForPlatPriceResult calcForPriceForOut(CalcForPriceReq calcForPriceReq);

    CalcForPlatPriceResult calcForTempPrice(CalcForPriceReq calcForPriceReq);

    FindAllValidBillMstResult findAllValidBillMst(AppBasePagerRequest appBasePagerRequest);

    BaseResBean getCalcStatus(CalcPricePayStatus calcPricePayStatus);

    BaseResBean getOrderThawStatus(OrderThawStatusReq orderThawStatusReq);

    /**
     * O单同城快递 平台批量结算接口
     * @param batchCalcForPlatReq
     * @return
     */
    BatchCalcForPlatPriceResult batchCalcForPrice(BatchCalcForPlatReq batchCalcForPlatReq);

    /**
     * O单同城运输、I单同城运输 竞价的用户、咪站、蛙站和平台之间的结算
     * 司机收货时发起结算接口
     *
     * @param calcOtcysReq
     */
    AppBaseResult calcForPriceTcys(CalcOtcysReq calcOtcysReq);

    /**
     * 第三方代付接口
     * @param thirdPartPayReq
     * @return
     */
    AppBaseResult thirdPartyPay(ThirdPartPayReq thirdPartPayReq);

    /**
     * 打赏获取结算对账单
     * @param rewardReq
     * @return
     */
    RewardRes reward(RewardReq rewardReq);

    /**
     * 购买嗨付券结算
     * @param purchaseReq
     * @return
     */
    PurchaseRes purchase(PurchaseReq purchaseReq);

}
