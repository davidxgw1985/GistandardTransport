package com.gistandard.transport.system.common.exception;

import org.apache.shiro.session.SessionException;

/**
 * Created by shenzhijun on 2016/2/4.
 */
public class ShiroSessionException extends SessionException {


    public ShiroSessionException() {
        super();
    }


    public ShiroSessionException(String message) {
        super(message);
    }


    public ShiroSessionException(Throwable cause) {
        super(cause);
    }


    public ShiroSessionException(String message, Throwable cause) {
        super(message, cause);
    }

}
