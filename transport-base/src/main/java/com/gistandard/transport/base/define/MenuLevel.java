package com.gistandard.transport.base.define;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sjl
 * @ClassName MenuLevel
 * @Description
 * @Version 1.0
 * @Date 2016-01-27
 */
public enum MenuLevel {

    ROOT_MENU("根菜单", -1),
    MENU_LEVEL_1("1级菜单", 1),
    MENU_LEVEL_2("2级菜单", 2),
    MENU_LEVEL_3("3级菜单", 3);

    public static final List<MenuLevel> MENU_LEVEL_LIST = new ArrayList<MenuLevel>();

    // 成员变量
    private String levelName;
    private int value;

    static {
        MENU_LEVEL_LIST.add(MENU_LEVEL_1);
        MENU_LEVEL_LIST.add(MENU_LEVEL_2);
        MENU_LEVEL_LIST.add(MENU_LEVEL_3);
    }

    // 构造方法
    private MenuLevel(String levelName, int value) {
        this.levelName = levelName;
        this.value = value;
    }

    // 普通方法
    public static MenuLevel getLevel(int value) {
        for (MenuLevel c : MenuLevel.values()) {
            if (c.getValue() == value) {
                return c;
            }
        }
        return null;
    }

    // 普通方法
    public static String getLevelName(int value) {
        for (MenuLevel c : MenuLevel.values()) {
            if (c.getValue() == value) {
                return c.levelName;
            }
        }
        return null;
    }


    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
