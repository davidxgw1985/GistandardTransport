package com.gistandard.transport.quote.system.common.define;


import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.tools.util.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shenzhijun on 2016/5/19.
 */
public enum AccountType {
    NORMAL("普通用户",1, Arrays.asList(SysAccountRole.NORMAL_PERSONAL.getValue(),
                        SysAccountRole.NORMAL_COMPANY.getValue())) ,
    OPERATOR("经营者",2,Arrays.asList(SysAccountRole.OPERATOR_CAR_OWNER.getValue(),
                        SysAccountRole.OPERATOR_COMPANY_FLEET.getValue(),
                        SysAccountRole.OPERATOR_COMPANY_STATION.getValue(),
                        SysAccountRole.OPERATOR_STATION_WORKER.getValue(),
                        SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue(),
                        SysAccountRole.OPERATOR_EXPRESS_STATION.getValue()));

     AccountType(String name,Integer type, List<Integer> value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    // 成员变量
    private String name;
    private Integer type;
    private List<Integer> value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getValue() {
        return value;
    }

    public void setValue(List<Integer> value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return StringUtil.listConvertToString(value);
    }
}
