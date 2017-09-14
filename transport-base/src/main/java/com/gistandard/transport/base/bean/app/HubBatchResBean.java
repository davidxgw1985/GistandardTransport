package com.gistandard.transport.base.bean.app;

import java.util.List;

/**
 * Created by m on 2016/12/21.
 */
public class HubBatchResBean {
    /**
     * data : [{"data":"S_131232","retCode":"-1","retMsg":"哈哈"}]
     * retCode : 0
     * retMsg : 成功
     */

    private String retCode;
    private String retMsg;
    /**
     * data : S_131232
     * retCode : -1
     * retMsg : 哈哈
     */

    private List<DataBean> data;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String data;
        private String retCode;
        private String retMsg;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getRetCode() {
            return retCode;
        }

        public void setRetCode(String retCode) {
            this.retCode = retCode;
        }

        public String getRetMsg() {
            return retMsg;
        }

        public void setRetMsg(String retMsg) {
            this.retMsg = retMsg;
        }
    }
}
