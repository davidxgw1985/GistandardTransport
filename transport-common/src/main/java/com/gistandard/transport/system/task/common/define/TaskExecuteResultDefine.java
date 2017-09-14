package com.gistandard.transport.system.task.common.define;

/**
 * @author yujie
 * @ClassName SysAccountType
 * @Description
 * @Version 1.0
 * @Date 2015-09-10
 */
public enum TaskExecuteResultDefine {

    EXECUTE_RESULT_SUCCESS("taskExecuteSuccess", 1),
    EXECUTE_RESULT_FAIL("taskExecuteFail", 2),
    EXECUTE_RESULT_NULL("taskExecuteNull", 3);

    private String name;

    private int value;

    private TaskExecuteResultDefine(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (TaskExecuteResultDefine c : TaskExecuteResultDefine.values()) {
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
