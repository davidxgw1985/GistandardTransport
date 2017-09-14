package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: BatchCalcForPlatReq
 * @Date: 2016/12/2
 * @Description: 平台批量结算接口请求对象
 */
@ApiModel(value = "平台批量结算接口请求对象", parent = AppBaseRequest.class)
public class BatchCalcForPlatReq extends AppBaseRequest{
    @ApiModelProperty(value = "平台结算请求对象列表",required = true, position = 1)
    private List<CalcForPlatPriceBean> calcList ;

    @ApiModelProperty(value = "当前用户操作角色",required = true, position = 2)
    private Integer roleId;

    public List<CalcForPlatPriceBean> getCalcList() {
        return calcList;
    }

    public void setCalcList(List<CalcForPlatPriceBean> calcList) {
        this.calcList = calcList;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
