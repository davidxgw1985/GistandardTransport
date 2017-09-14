package com.gistandard.transport.system.logToPsc.bean;

import java.io.Serializable;

/**
 * 司机APP发车参数
 */
public class DriverAppDepartPara implements Serializable{

    private String scheducarno;//派车单号

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }
}
