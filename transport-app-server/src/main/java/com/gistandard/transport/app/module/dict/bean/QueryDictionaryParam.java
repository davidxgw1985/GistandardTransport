package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/9/29.
 */
@ApiModel(description = "数据字典查询接口参数对象", parent = AppBaseRequest.class)
public class QueryDictionaryParam extends AppBaseRequest{

    @ApiModelProperty(value = "字典类型（1放弃订单理由;2订单失败理由;3;货物类型;4货物数量单位;5价格单位;6银行;7车型;8车量类别;9报价类型;10时效性）",required = true, position = 1)
    private int dicType;//字典类型 1放弃订单理由;2订单失败理由;3;货物类型;4货物数量单位;5价格单位;6银行;//7车型;//8车量类别;//9报价类型;10//时效性

    @ApiModelProperty(value = "返回结果字体类型（1简体；2繁体）", position = 2)
    private int characterType;//1简体；2繁体

    /**
     * 下单单位  1:数量单位  2：重量单位 3：体积单位   -1:全部
     */
    @ApiModelProperty(value = "单位类型（1、数量单位，2、重量单位，3、体积单位，-1、全部）",required = true, position = 3)
    private int unitType;

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public int getDicType() {
        return dicType;
    }

    public void setDicType(int dicType) {
        this.dicType = dicType;
    }

    public int getCharacterType() {
        return characterType;
    }

    public void setCharacterType(int characterType) {
        this.characterType = characterType;
    }
}
