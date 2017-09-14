package com.gistandard.transport.base.define;

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

    NORMAL_PERSONAL("个人", 1, "NORMAL_PERSONAL"),
    NORMAL_COMPANY("企业", 2, "NORMAL_COMPANY"),
    OPERATOR_CAR_OWNER("司机", 3, "OPERATOR_CAR_OWNER"),
    OPERATOR_COMPANY_FLEET("车队", 4, "OPERATOR_FLEET"),
    OPERATOR_COMPANY_STATION("站点", 5, "OPERATOR_STATION"),
    OPERATOR_STATION_WORKER("站点工作人员", 6, "OPERATOR_STATION_WORKER"),
    OPERATOR_DELIVERY_OWNER("快递员", 7, "OPERATOR_COURIER"),
    WORK_TASK_SPACE_NORMAL_USER("工作空间-用户", 8, "WORK_TASK_SPACE_NORMAL_USER"),
    DDWORD_NORMAL_USER("嘀讯-用户", 9, "DDWORD_NORMAL_USER"),
    OPERATOR_EXPRESS_STATION("快递站点", 14, "OPERATOR_EXPRESS_STATION"),
    PERSONAL_BUSINESS_CENTER("业务中心", 19, "PERSONAL_BUSINESS_CENTER"),
    COMPANY_BUSINESS_CENTER("业务中心", 21, "COMPANY_BUSINESS_CENTER"),
    OPERATOR_MSTATION("咪站", 23, "OPERATOR_MSTATION"),
    WECHAT_BUSINESS("微商", 24, "WECHAT_BUSINESS"),
    ONLINE_BUSINESS("电商", 25, "ONLINE_BUSINESS");

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
