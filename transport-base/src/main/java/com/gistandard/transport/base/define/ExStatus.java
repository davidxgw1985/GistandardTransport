package com.gistandard.transport.base.define;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yujie
 * @ClassName State
 * @Description
 * @Version 1.0
 * @Date 2015-06-30
 */
public enum ExStatus {
	UNDONE("未解决", 1), DONE("已解决", 2), UNKNOW("未知异常", 3);

    public static final List<HashMap<String, Object>> SYS_STATE_LIST = new ArrayList<HashMap<String, Object>>();

    // 成员变量
    private String name;
    private int value;

    // 构造方法
    private ExStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (ExStatus c : ExStatus.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }

        return null;
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

}
