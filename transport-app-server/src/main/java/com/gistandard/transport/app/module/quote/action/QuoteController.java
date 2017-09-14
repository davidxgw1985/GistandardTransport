package com.gistandard.transport.app.module.quote.action;

import com.gistandard.transport.app.module.quote.bean.*;
import com.gistandard.transport.app.module.quote.service.QuoteService;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.quote.system.common.bean.QuoteInfoReq;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.tools.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xgw
 * @ClassName MobileStationQuoteController
 * @Description 我的服务、我的产品 发布报价
 * @Version 1.0
 * @Date 2016-2-25
 */
@Controller
@RequestMapping(value = AppServerDefine.QUOTE_URL)
public class QuoteController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);
    @Autowired
    private QuoteService quoteService;

    /**
     * 查询报价列表
     * @param queryQuoteListReq
     * @throws Exception
     */
    @RequestMapping(value = "/queryQuoteList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询报价列表-V1.0.1", httpMethod = "POST", response = AppQuoteListResult.class,
            produces = "application/json", notes = "根据科目（itemId）、起始地址(startRoute,endRoute)等条件查询")
    public AppQuoteListResult queryQuoteList(@RequestBody QueryQuoteListReq queryQuoteListReq) throws Exception {
        return  quoteService.queryQuoteList(queryQuoteListReq);
     }

    /**
     * 根据根据类别、类型查询条目
     * @param queryQuoteItemReq
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryQuoteItemList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询报价科目列表-V1.0.1", httpMethod = "POST", response = AppQuoteItemListResult.class,
            produces = "application/json", notes = "根据类别（itemCategory）、科目类型（itemType）查询")
    public AppQuoteItemListResult queryQuoteItemList(@RequestBody QueryQuoteItemReq queryQuoteItemReq) throws Exception {
       return quoteService.queryQuoteItemList(queryQuoteItemReq);

    }

    /**
     * 新增或者修改报价  服务、产品
     * @param quoteInfoReq
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveQuoteInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存报价-V1.0.1", httpMethod = "POST", response = AppQuoteSaveResult.class,
            produces = "application/json", notes = "科目、起始线路、帐号必填，其他根据报价类型选填")
    public AppQuoteSaveResult saveQuoteInfo(@RequestBody QuoteInfoReq quoteInfoReq) throws Exception {
           return quoteService.saveQuoteInfo(quoteInfoReq);
    }

    /**
     * 启用、禁用或者删除报价  服务、产品
     * @param quoteInfoOperateReq
     * @throws Exception
     */
    @RequestMapping(value = "/quoteInfoOperate", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改报价状态-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "报价ID必填，状态必填（QuoteStatus）")
    public AppBaseResult quoteInfoOperate(@RequestBody QuoteInfoOperateReq quoteInfoOperateReq) throws Exception {
          return quoteService.quoteInfoOperate(quoteInfoOperateReq);
    }

    /**
     * 根据城市（省，城市，区县）查站点
     * @param queryQuoteListReq
     * @throws Exception
     */
    @RequestMapping(value = "/queryStationByArea", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询站点-V1.0.1", httpMethod = "POST", response = AppStationListResult.class,
            produces = "application/json", notes = "根据省市区areaCode必填，查询站点")
    public AppStationListResult queryStationList(@RequestBody QueryStationListReq queryQuoteListReq) throws Exception {

        AppStationListResult res;
        if (queryQuoteListReq != null && !StringUtil.isEmpty(queryQuoteListReq.getAreaCode())) {
            res = quoteService.queryStationList(queryQuoteListReq);
        } else {
            res = new AppStationListResult();
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("请求参数不能为空！");
        }
       return  res;
    }

    /**
     * 查询商户列表
     * itemCategory;//1、产品 查询所有用户 2、服务 查询商户 3、4、5、7
     * @param queryMerchantListReq
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryMerchantList", method = RequestMethod.POST)
    @ResponseBody
    public AppMerchantListResult queryMerchantList(@RequestBody QueryMerchantListReq queryMerchantListReq) throws Exception {
        AppMerchantListResult res;
        if (queryMerchantListReq != null && !StringUtil.isEmpty(queryMerchantListReq.getItemCategory()) && !StringUtils.isBlank(queryMerchantListReq.getCustName())) {
            res = quoteService.queryMerchantList(queryMerchantListReq);
        } else {
            res = new AppMerchantListResult();
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(StringUtils.isBlank(queryMerchantListReq.getCustName()) ? "请输入查询关键字" : "请求参数不能为空！");
        }
        return  res;
    }
}
