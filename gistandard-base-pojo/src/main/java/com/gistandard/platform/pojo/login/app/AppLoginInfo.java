package com.gistandard.platform.pojo.login.app;

import com.gistandard.platform.pojo.login.AbstractLoginInfo;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComCustomer;

import java.io.Serializable;

/**
 * Created by shenzhijun on 2016/9/29.
 */
public class AppLoginInfo extends AbstractLoginInfo implements Serializable{

    private static final long serialVersionUID = 857217522985608117L;

    private ComCustomer currentComCustomer;

    private ComAccount currentComCustomerAccount;


    public ComCustomer getCurrentComCustomer() {
        return currentComCustomer;
    }

    public void setCurrentComCustomer(ComCustomer currentComCustomer) {
        this.currentComCustomer = currentComCustomer;
    }

    public ComAccount getCurrentComCustomerAccount() {
        return currentComCustomerAccount;
    }

    public void setCurrentComCustomerAccount(ComAccount currentComCustomerAccount) {
        this.currentComCustomerAccount = currentComCustomerAccount;
    }
}
