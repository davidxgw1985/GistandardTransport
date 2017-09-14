package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/10/26.
 */
public class QueryReasonParam extends AppBaseRequest {

    @ApiModelProperty(value = "字典类型（1放弃订单理由;2订单失败理由;3派送失败理由）",required = true, position = 1)
    private int dicType;

    @ApiModelProperty(value = "返回结果字体类型（1简体；2繁体）", position = 2)
    private int characterType;//1简体；2繁体

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
