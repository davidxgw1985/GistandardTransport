package com.gistandard.transport.quote.system.common.define;


import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.ComAccountRoleRel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shenzhijun on 2016/5/19.
 */
public enum AccountRoleType {

    NORMAL_PERSONAL("普通用户-个人",1, Arrays.asList(SysAccountRole.NORMAL_PERSONAL.getValue())) ,
    NORMAL_COMPANY("普通用户-企业",2,Arrays.asList(SysAccountRole.NORMAL_COMPANY.getValue())),
    OPERATOR_PERSONAL("经营者-个人",3,Arrays.asList(SysAccountRole.OPERATOR_CAR_OWNER.getValue(),
                                                SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue())),
    OPERATOR_COMPANY("经营者-企业",4,Arrays.asList(SysAccountRole.OPERATOR_COMPANY_FLEET.getValue(),
            SysAccountRole.OPERATOR_COMPANY_STATION.getValue(),
            SysAccountRole.OPERATOR_STATION_WORKER.getValue(),
            SysAccountRole.OPERATOR_EXPRESS_STATION.getValue()));

     AccountRoleType(String name,Integer type, List<Integer> value) {
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

    public static Integer getTypeByRoleId(Integer roleId) {

        if(roleId.equals(SysAccountRole.OPERATOR_CAR_OWNER.getValue())
                || roleId.equals(SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue())){
            return  AccountRoleType.OPERATOR_PERSONAL.getType();
        }else if(roleId.equals(SysAccountRole.OPERATOR_COMPANY_FLEET.getValue())
                || roleId.equals(SysAccountRole.OPERATOR_COMPANY_STATION.getValue())
                || roleId.equals(SysAccountRole.OPERATOR_STATION_WORKER.getValue())
                || roleId.equals(SysAccountRole.OPERATOR_EXPRESS_STATION.getValue())){
            return  AccountRoleType.OPERATOR_COMPANY.getType();
        }else if(roleId.equals(SysAccountRole.NORMAL_PERSONAL.getValue())){
            return  AccountRoleType.NORMAL_PERSONAL.getType();
        }else if(roleId.equals(SysAccountRole.NORMAL_COMPANY.getValue())){
            return  AccountRoleType.NORMAL_COMPANY.getType();
        }
            return -1;
    }

    public static Integer getTypeByRoleIds(List<ComAccountRoleRel> roleIds) {

        for (ComAccountRoleRel comAccountRoleRel : roleIds) {
            Integer roleId = comAccountRoleRel.getRoleId();
           if (roleId.equals(SysAccountRole.OPERATOR_CAR_OWNER.getValue())
                    || roleId.equals(SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue())) {
                return AccountRoleType.OPERATOR_PERSONAL.getType();
            } else if (roleId.equals(SysAccountRole.OPERATOR_COMPANY_FLEET.getValue())
                    || roleId.equals(SysAccountRole.OPERATOR_COMPANY_STATION.getValue())
                    || roleId.equals(SysAccountRole.OPERATOR_STATION_WORKER.getValue())
                    || roleId.equals(SysAccountRole.OPERATOR_EXPRESS_STATION.getValue())) {
                return AccountRoleType.OPERATOR_COMPANY.getType();
            }else  if (roleId.equals(SysAccountRole.NORMAL_PERSONAL.getValue())) {
               return AccountRoleType.NORMAL_PERSONAL.getType();
           } else if (roleId.equals(SysAccountRole.NORMAL_COMPANY.getValue())) {
               return AccountRoleType.NORMAL_COMPANY.getType();
           }

        }
        return -1;
    }

    public static Integer getTypeByOperatorRoleIds(List<ComAccountRoleRel> roleIds) {

        for (ComAccountRoleRel comAccountRoleRel : roleIds) {
            Integer roleId = comAccountRoleRel.getRoleId();
            if (roleId.equals(SysAccountRole.OPERATOR_CAR_OWNER.getValue())
                    || roleId.equals(SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue())) {
                return AccountRoleType.OPERATOR_PERSONAL.getType();
            } else if (roleId.equals(SysAccountRole.OPERATOR_COMPANY_FLEET.getValue())
                    || roleId.equals(SysAccountRole.OPERATOR_COMPANY_STATION.getValue())
                    || roleId.equals(SysAccountRole.OPERATOR_STATION_WORKER.getValue())
                    || roleId.equals(SysAccountRole.OPERATOR_EXPRESS_STATION.getValue())) {
                return AccountRoleType.OPERATOR_COMPANY.getType();
            }

        }
        return -1;
    }


    public static boolean hasRoles(List<ComAccountRoleRel> comAccountRoleRelList,Integer roleId){
        for(ComAccountRoleRel comAccountRoleRel:comAccountRoleRelList){
            if(comAccountRoleRel.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }


    public void setType(Integer type) {
        this.type = type;
    }
}
