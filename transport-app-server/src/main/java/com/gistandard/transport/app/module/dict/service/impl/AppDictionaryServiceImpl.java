package com.gistandard.transport.app.module.dict.service.impl;

import com.gistandard.transport.app.module.dict.bean.*;
import com.gistandard.transport.app.module.dict.service.AppDictionaryService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.ComCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yujie on 2016/9/29.
 */
@Service
public class AppDictionaryServiceImpl implements AppDictionaryService {

    @Autowired
    private ComReasonDaoEx comReasonDaoEx;

    @Autowired
    private ComCurrencyService comCurrencyService;

    @Autowired
    private ComTackoutGoodsTypeDaoEx comTackoutGoodsTypeDaoEx;

    @Autowired
    private ComGoodsTypeDaoEx comGoodsTypeDaoEx;

    @Autowired
    private ComAllTypeDaoEx comAllTypeDaoEx;

    @Autowired
    private ComUnitDaoEx comUnitDaoEx;

    @Override
    public QueryDictionaryResult queryDictionary(QueryDictionaryParam queryDictionaryReq) {
        QueryDictionaryResult baseResBean = new QueryDictionaryResult();
        switch (queryDictionaryReq.getDicType()) {
            //1放弃订单理由;2订单失败理由;3;货物类型（快递）;4货物数量单位;5价格单位;6货物类型（运输）
            case 1: {
                //1放弃订单理由
                List<ComReason> comReasonList = comReasonDaoEx.getReasonByCondtion(queryDictionaryReq.getDicType(), queryDictionaryReq.getCharacterType());
                baseResBean.setData(comReasonList);
                break;
            }
            case 2: {
                //2订单失败理由
                List<ComReason> comReasonList = comReasonDaoEx.getReasonByCondtion(queryDictionaryReq.getDicType(), queryDictionaryReq.getCharacterType());
                baseResBean.setData(comReasonList);
                break;
            }
            case 3: {
                //;3;货物类型
                List<ComGoodsType> goodsTypeList = comGoodsTypeDaoEx.selectByDicType(queryDictionaryReq.getDicType());
                baseResBean.setData(goodsTypeList);
                break;
            }
            case 4: {
                //4货物数量单位
                if (queryDictionaryReq.getUnitType() == -1) {
                    baseResBean.setData(comUnitDaoEx.queryAllComUnitList());
                } else if (queryDictionaryReq.getUnitType() == 1) {
                    baseResBean.setData(comUnitDaoEx.queryComUnitforTaiBao());
                } else {
                    baseResBean.setData(comUnitDaoEx.queryComUnitByUnitType(queryDictionaryReq.getUnitType()));
                }
                break;
            }
            case 5: {
                //5货币单位
                List<ComCurrency> comCurrencyList = comCurrencyService.queryAllComCurrencyList();
                baseResBean.setData(comCurrencyList);
                break;
            }
            case 6: {
                //;6;货物类型（运输
                List<ComGoodsType> goodsTypeList = comGoodsTypeDaoEx.selectByDicType(queryDictionaryReq.getDicType());
                baseResBean.setData(goodsTypeList);
                break;
            }
            case 7: {
                //7车型
                List<ComAllType> comAllTypes = comAllTypeDaoEx.queryAll(AppServerDefine.COM_T_TYPE_VEHICLE);
                baseResBean.setData(comAllTypes);
                break;
            }
            case 8: {
                //8车量类别
                List<ComAllType> comAllTypes = comAllTypeDaoEx.queryAllTFlag0(AppServerDefine.COM_T_TYPE_VEHICLE);
                baseResBean.setData(comAllTypes);
                break;
            }
            case 9: {
                //9报价类型
                List<ComAllType> comAllTypes = comAllTypeDaoEx.queryAll(AppServerDefine.COM_T_TYPE_PRICETYPE);
                baseResBean.setData(comAllTypes);
                break;
            }
            case 10: {
                //10时效性
                List<ComAllType> comAllTypes = comAllTypeDaoEx.queryAll(AppServerDefine.COM_T_TYPE_LIMITATIONTIME);
                baseResBean.setData(comAllTypes);
                break;
            }
            case 11: {
                //;11;同城外卖货物类型
                List<ComTackoutGoodsType> comTackoutGoodsTypes = comTackoutGoodsTypeDaoEx.selectAll();
                baseResBean.setData(comTackoutGoodsTypes);
                break;
            }
            default:
                break;
        }
        return baseResBean;
    }

    @Override
    public QueryReasonResult queryReason(QueryReasonParam queryReasonParam) {
        QueryReasonResult queryReasonResult = new QueryReasonResult();
        queryReasonResult.setReqId(queryReasonParam.getReqId());
        List<ComReason> comReasonList = comReasonDaoEx.getReasonByCondtion(queryReasonParam.getDicType(), queryReasonParam.getCharacterType());
        queryReasonResult.setData(comReasonList);
        return queryReasonResult;
    }

    @Override
    public QueryGoodsTypeResult queryGoodsType(QueryGoodsTypeParam queryGoodsTypeParam) {
        QueryGoodsTypeResult queryGoodsTypeResult = new QueryGoodsTypeResult();
        queryGoodsTypeResult.setReqId(queryGoodsTypeParam.getReqId());
        List<ComGoodsType> goodsTypeList = comGoodsTypeDaoEx.selectByDicType(queryGoodsTypeParam.getDicType());
        queryGoodsTypeResult.setData(goodsTypeList);
        return queryGoodsTypeResult;
    }

    @Override
    public QueryUnitResult queryUnit(QueryUnitParam queryUnitParam) {
        QueryUnitResult queryUnitResult = new QueryUnitResult();
        queryUnitResult.setReqId(queryUnitParam.getReqId());
        if (queryUnitParam.getUnitType() == -1) {
            queryUnitResult.setData(comUnitDaoEx.queryAllComUnitList());
        } else if (queryUnitParam.getUnitType() == 1) {
            queryUnitResult.setData(comUnitDaoEx.queryComUnitforTaiBao());
        } else {
            queryUnitResult.setData(comUnitDaoEx.queryComUnitByUnitType(queryUnitParam.getUnitType()));
        }
        return queryUnitResult;
    }

    @Override
    public QueryCurrencyResult queryCurrency(AppBaseRequest appBaseRequest) {
        QueryCurrencyResult queryCurrencyResult = new QueryCurrencyResult();
        queryCurrencyResult.setReqId(appBaseRequest.getReqId());
        List<ComCurrency> comCurrencyList = comCurrencyService.queryAllComCurrencyList();
        queryCurrencyResult.setData(comCurrencyList);
        return queryCurrencyResult;
    }

    @Override
    public QueryTackoutGoodsTypeResult queryTackoutGoodsType(AppBaseRequest appBaseRequest) {
        QueryTackoutGoodsTypeResult queryTackoutGoodsTypeResult = new QueryTackoutGoodsTypeResult();
        queryTackoutGoodsTypeResult.setReqId(appBaseRequest.getReqId());
        List<ComTackoutGoodsType> comTackoutGoodsTypes = comTackoutGoodsTypeDaoEx.selectAll();
        queryTackoutGoodsTypeResult.setData(comTackoutGoodsTypes);
        return queryTackoutGoodsTypeResult;
    }

    @Override
    public QueryAllTypeResult queryAllType(QueryAllTypeParam queryAllTypeParam) {
        QueryAllTypeResult queryAllTypeResult = new QueryAllTypeResult();
        queryAllTypeResult.setReqId(queryAllTypeParam.getReqId());
        List<ComAllType> comAllTypeList = comAllTypeDaoEx.queryAll(queryAllTypeParam.getTypeCode());
        queryAllTypeResult.setData(comAllTypeList);
        return queryAllTypeResult;
    }
}
