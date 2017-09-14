package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 员伟
 */
public class BatchUpMBF implements Serializable{

    private static final long serialVersionUID = -1324635424683007487L;
    private List<Integer> idList;

    private Date signInTime;


    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }
}
