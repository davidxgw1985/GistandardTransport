package com.gistandard.transport.app.module.calc.action;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.calculate.bean.calc.*;
import com.gistandard.transport.quote.module.calc.bean.CalcForPriceReq;
import com.gistandard.transport.system.webservice.client.calcWebService.CancelPurchase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.calculate.service.CalcService;
import com.gistandard.transport.system.common.controller.BaseController;

/**
 * @author: xgw
 * @ClassName: CalcController
 * @Date: 2016/3/15
 * @Description: 结算模块
 */
@Controller
@RequestMapping(value = AppServerDefine.CALC_URL)
@Api(value = "结算模块", tags = "结算模块")
public class CalcController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CalcController.class);
    @Autowired
    private CalcService calcService;

    /**
     * O单同步结算接口
     *
     * @param calcForPriceReq
     */
    @ApiOperation(value = "结算接口-V1.0.1", httpMethod = "POST", response = CalcForPlatPriceResult.class,
            produces = "application/json", notes = "O单同步结算接口")
    @RequestMapping(value = "/calcForPriceForOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public CalcForPlatPriceResult calcForPriceForOut(@RequestBody CalcForPriceReq calcForPriceReq) throws Exception {
        logger.info("结算接口 calcForPriceForOut param:{}", JSON.toJSONString(calcForPriceReq));
        CalcForPlatPriceResult calcForPlatPriceResult = new CalcForPlatPriceResult(calcForPriceReq);
        if (calcForPriceReq != null) {
            calcForPlatPriceResult = calcService.calcForPriceForOut(calcForPriceReq);
        }
        return calcForPlatPriceResult;
    }

    /**
     * 估价接口
     *
     * @param calcForPriceReq
     */
    @ApiOperation(value = "估价接口-V1.0.1", httpMethod = "POST", response = CalcForPlatPriceResult.class,
            produces = "application/json", notes = "估价接口")
    @RequestMapping(value = "/calcForTempPrice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public CalcForPlatPriceResult calcForTempPrice(@RequestBody CalcForPriceReq calcForPriceReq) throws Exception {
        logger.info("估价接口 calcForTempPrice param:{}", JSON.toJSONString(calcForPriceReq));
        CalcForPlatPriceResult calcForPlatPriceResult = new CalcForPlatPriceResult(calcForPriceReq);
        if (calcForPriceReq != null) {
            calcForPlatPriceResult = calcService.calcForTempPrice(calcForPriceReq);
        }
        return calcForPlatPriceResult;
    }

    /**
     * 待支付列表查询接口
     *
     * @param appBasePagerRequest
     */
    @ApiOperation(value = "待支付列表查询接口-V1.0.1", httpMethod = "POST", response = BaseResBean.class,
            produces = "application/json", notes = "待支付列表查询接口")
    @RequestMapping(value = "/findAllValidBillMst", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public FindAllValidBillMstResult findAllValidBillMst(@RequestBody AppBasePagerRequest appBasePagerRequest) throws Exception {
        logger.info("待支付列表查询接口 findAllValidBillMst param:{}", JSON.toJSONString(appBasePagerRequest));
        FindAllValidBillMstResult findAllValidBillMstResult = new FindAllValidBillMstResult(appBasePagerRequest);
        if (appBasePagerRequest != null) {
            findAllValidBillMstResult = calcService.findAllValidBillMst(appBasePagerRequest);
        }
        return findAllValidBillMstResult;
    }

    /**
     * 订单是否已支付
     *
     * @param calcPricePayStatus
     * @throws Exception
     */
    @ApiOperation(value = "订单支付状态接口-V1.0.1", httpMethod = "POST", response = BaseResBean.class,
            produces = "application/json", notes = "订单支付状态接口")
    @RequestMapping(value = "/getCalcStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void getCalcStatus(@RequestBody CalcPricePayStatus calcPricePayStatus) throws Exception {
        logger.info("订单是否已支付 param:{}", JSON.toJSONString(calcPricePayStatus));
        BaseResBean baseResBean = new BaseResBean();
        if (calcPricePayStatus != null) {
            baseResBean = calcService.getCalcStatus(calcPricePayStatus);
        }
        writeJson(baseResBean);
    }

    /**
     * 根据待支付列表，支付前，查询订单是否可以解冻
     *
     * @param orderThawStatusReq
     * @throws Exception
     */
    @ApiOperation(value = "查询订单是否可以解冻-V1.0.1", httpMethod = "POST", response = BaseResBean.class,
            produces = "application/json", notes = "查询订单是否可以解冻")
    @RequestMapping(value = "/getOrderThawStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void getOrderThawStatus(@RequestBody OrderThawStatusReq orderThawStatusReq) throws Exception {
        logger.info("查询订单是否可以解冻 param:{}", JSON.toJSONString(orderThawStatusReq));
        BaseResBean baseResBean = new BaseResBean();
        if (orderThawStatusReq != null) {
            baseResBean = calcService.getOrderThawStatus(orderThawStatusReq);
        }
        writeJson(baseResBean);
    }

    /**
     * O单同城快递 平台批量结算接口
     *
     * @param batchCalcForPlatReq
     */
    @ApiOperation(value = "O单同城快递 平台批量结算接口-V1.0.1", httpMethod = "POST", response = BatchCalcForPlatPriceResult.class,
            produces = "application/json", notes = "O单同城快递 平台结算接口")
    @RequestMapping(value = "/batchCalcForPrice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BatchCalcForPlatPriceResult batchCalcForPrice(@RequestBody BatchCalcForPlatReq batchCalcForPlatReq) throws Exception {
        logger.info("O单同城快递 平台批量结算接口 batchCalcForPrice param:{}", JSON.toJSONString(batchCalcForPlatReq));
        BatchCalcForPlatPriceResult batchCalcForPlatPriceResult = new BatchCalcForPlatPriceResult(batchCalcForPlatReq);
        if (batchCalcForPlatReq != null) {
            batchCalcForPlatPriceResult = calcService.batchCalcForPrice(batchCalcForPlatReq);
        }
        logger.info("O单同城快递 平台批量结算接口返回:{}", JSON.toJSONString(batchCalcForPlatPriceResult));
        return batchCalcForPlatPriceResult;
    }

    /**
     * O单同城运输、I单同城运输 竞价的用户、咪站、蛙站和平台之间的结算
     * 司机收货时发起结算接口
     *
     * @param calcOtcysReq
     */
    @ApiOperation(value = "O单同城运输、I单同城运输 竞价的用户、咪站、蛙站和平台之间的结算接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "O单同城快递、I单同城运输 竞价的用户、咪站、蛙站和平台之间的结算")
    @RequestMapping(value = "/calcForPriceTcys", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult calcForPriceTcys(@RequestBody CalcOtcysReq calcOtcysReq) throws Exception {
        logger.info("O单同城运输、I单同城运输 竞价的用户、咪站、蛙站和平台之间的结算接口 calcForPriceTcys param:{}", JSON.toJSONString(calcOtcysReq));
        AppBaseResult appBaseResult = calcService.calcForPriceTcys(calcOtcysReq);
        logger.info("O单同城运输、I单同城运输 竞价的用户、咪站、蛙站和平台之间的结算接口返回:{}", JSON.toJSONString(appBaseResult));
        return appBaseResult;
    }

    /**
     * 第三方代付接口
     *
     * @param thirdPartPayReq
     */
    @ApiOperation(value = "第三方代付接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "第三方代付接口")
    @RequestMapping(value = "/thirdPartyPay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult thirdPartyPay(@RequestBody ThirdPartPayReq thirdPartPayReq) throws Exception {
        logger.info("第三方代付接口 thirdPartyPay param:{}", thirdPartPayReq.toString());
        AppBaseResult appBaseResult = calcService.thirdPartyPay(thirdPartPayReq);
        logger.info("第三方代付接口 thirdPartyPay 返回:{}", JSON.toJSONString(appBaseResult));
        return appBaseResult;
    }

    /**
     * 打赏获取结算对账单
     *
     * @param rewardReq
     */
    @ApiOperation(value = "打赏获取结算对账单-V1.0.1", httpMethod = "POST", response = RewardRes.class,
            produces = "application/json", notes = "打赏获取结算对账单接口")
    @RequestMapping(value = "/reward", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public RewardRes reward(@RequestBody RewardReq rewardReq) throws Exception {
        logger.info("打赏获取结算对账单 reward param:{}", JSON.toJSONString(rewardReq));
        RewardRes rewardRes = calcService.reward(rewardReq);
        logger.info("打赏获取结算对账单 reward 返回:{}", JSON.toJSONString(rewardRes));
        return rewardRes;
    }

    /**
     * 购买嗨付券结算
     *
     * @param purchaseReq
     */
    @ApiOperation(value = "购买嗨付券结算-V1.0.1", httpMethod = "POST", response = PurchaseRes.class,
            produces = "application/json", notes = "购买嗨付券结算接口")
    @RequestMapping(value = "/purchase", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PurchaseRes purchase(@RequestBody PurchaseReq purchaseReq) throws Exception {
        logger.info("购买嗨付券结算 purchase param:{}", JSON.toJSONString(purchaseReq));
        PurchaseRes purchaseRes = calcService.purchase(purchaseReq);
        logger.info("购买嗨付券结算 purchase 返回:{}", JSON.toJSONString(purchaseRes));
        return purchaseRes;
    }
}
