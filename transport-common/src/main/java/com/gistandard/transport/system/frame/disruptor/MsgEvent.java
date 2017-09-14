package com.gistandard.transport.system.frame.disruptor;

import java.util.List;

/**
 * Created by m on 2016/12/5.
 */
public class MsgEvent<T> {
    private List<T> value;

    public List<T> get() {
        return value;
    }

    public void set(List<T> value) {
        this.value = value;
    }
}
