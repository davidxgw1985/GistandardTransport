package com.gistandard.transport.system.frame.batch;

/**
 * 默认的异常处理组件
 * Created by m on 2016/12/28.
 */
public class DefaultError implements Error {
    private static DefaultError instance = new DefaultError();

    public static DefaultError getInstance(){
        return instance;
    }

    private DefaultError(){

    }

    @Override
    public void error() {

    }
}
