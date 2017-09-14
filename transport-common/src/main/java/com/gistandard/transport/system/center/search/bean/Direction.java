package com.gistandard.transport.system.center.search.bean;

import java.io.Serializable;

/**
 * Created by m on 2016/7/4.
 */
public class Direction implements Serializable {
    private static final long serialVersionUID = -4487164714391524105L;
    private Integer status;
    private String message;
    private Integer type;
    private MapResult result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public MapResult getResult() {
        return result;
    }

    public void setResult(MapResult result) {
        this.result = result;
    }
}
