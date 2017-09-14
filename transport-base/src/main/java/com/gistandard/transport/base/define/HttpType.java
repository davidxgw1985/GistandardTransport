package com.gistandard.transport.base.define;

import java.util.ArrayList;
import java.util.List;

public enum HttpType {
    GET("GET", 1),
    POST("POST", 2),
    HEAD("HEAD", 3),
    OPTIONS("OPTIONS", 4),
    PUT("PUT", 5),
    DELETE("DELETE", 6),
    TARCE("TARCE", 7);

    public static final List<HttpType> HTTP_TYPE_LIST= new ArrayList<HttpType>();

    // 成员变量
    private String typeName;
    private int value;

    static {
        HTTP_TYPE_LIST.add(GET);
        HTTP_TYPE_LIST.add(POST);
        HTTP_TYPE_LIST.add(HEAD);
        HTTP_TYPE_LIST.add(OPTIONS);
        HTTP_TYPE_LIST.add(PUT);
        HTTP_TYPE_LIST.add(DELETE);
        HTTP_TYPE_LIST.add(TARCE);
    }

    // 构造方法
    private HttpType(String typeName, int value) {
        this.typeName = typeName;
        this.value = value;
    }

    // 普通方法
    public static String getTypeName(int value) {
        for (HttpType c : HttpType.values()) {
            if (c.getValue() == value) {
                return c.typeName;
            }
        }

        return null;
    }

    // get set 方法
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
