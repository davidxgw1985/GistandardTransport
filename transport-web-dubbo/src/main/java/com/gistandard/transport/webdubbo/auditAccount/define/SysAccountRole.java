package com.gistandard.transport.webdubbo.auditAccount.define;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author yujie
 * @ClassName SysAccountType
 * @Description
 * @Version 1.0
 * @Date 2015-09-10
 */
public enum SysAccountRole {

    NORMAL_PERSONAL("普通用户-个人", 1, "NORMAL_PERSONAL"),
    NORMAL_COMPANY("普通用户-企业", 2, "NORMAL_COMPANY"),
    OPERATOR_CAR_OWNER("经营者-个人-车主", 3, "OPERATOR_CAR_OWNER"),
    OPERATOR_COMPANY_FLEET("经营者-企业-车队", 4, "OPERATOR_FLEET"),
    OPERATOR_COMPANY_STATION("经营者-企业-网点", 5, "OPERATOR_STATION"),
    OPERATOR_STATION_WORKER("经营者-个人-站点工作人员", 6, "OPERATOR_STATION_WORKER"),
    OPERATOR_DELIVERY_OWNER("经营者-个人-递送员", 7, "OPERATOR_COURIER"),
    OPERATOR_EXPRESS_STATION("快递站点", 14, "OPERATOR_EXPRESS_STATION"),
    PERSONAL_BUSINESS_CENTER("个人业务中心", 19, "PERSONAL_BUSINESS_CENTER"),
    COMPANY_BUSINESS_CENTER("企业业务中心", 21, "COMPANY_BUSINESS_CENTER"),
    OPERATOR_MSTATION("咪站", 23, "OPERATOR_MSTATION");

    public static final HashMap<Integer, Object> ACCOUNT_ROLE_MAP = new LinkedHashMap<Integer, Object>();

    public static final HashMap<String, Integer> ROLE_CODE_MAP = new HashMap<String, Integer>();

    static {
        for (SysAccountRole sysAccountRole : SysAccountRole.values()) {
            ACCOUNT_ROLE_MAP.put(sysAccountRole.getValue(), sysAccountRole.getName());
            ROLE_CODE_MAP.put(sysAccountRole.getRoleCode(), sysAccountRole.getValue());
        }
    }

    private String name;

    private int value;

    private String roleCode;

    SysAccountRole(String name, int value, String roleCode) {
        this.name = name;
        this.value = value;
        this.roleCode = roleCode;
    }

    // 普通方法
    public static String getName(int value) {
        for (SysAccountRole c : SysAccountRole.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }

        return null;
    }

    public static String getRoleCode(int value) {
        for (SysAccountRole c : SysAccountRole.values()) {
            if (c.getValue() == value) {
                return c.roleCode;
            }
        }

        return null;
    }

    public static int getRoleIdByCode(String roleCode) {
        for (SysAccountRole c : SysAccountRole.values()) {
            if (c.getRoleCode().equals(roleCode)) {
                return c.getValue();
            }
        }
        return -1;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
