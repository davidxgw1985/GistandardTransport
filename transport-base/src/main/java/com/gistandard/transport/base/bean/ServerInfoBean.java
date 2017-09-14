package com.gistandard.transport.base.bean;


import com.gistandard.transport.tools.util.StringUtil;
import org.springframework.stereotype.Component;

/**
 * @author yujie
 * @ClassName ServerInfoBean
 * @Description
 * @Version 1.0
 * @Date 2015-07-17
 */
@Component
public class ServerInfoBean {

    /**
     * 服务器编号
     */
    private String serverNo;

    private String serverIp;

    public String getServerNo() {
        return serverNo;
    }

    public void setServerNo(String serverNo) {
        this.serverNo = serverNo;
    }

    public String getServerIp() {
        if(serverIp != null){
            return serverIp;
        }
        else {
            serverIp = StringUtil.getLocalServerIp();
        }
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

}
