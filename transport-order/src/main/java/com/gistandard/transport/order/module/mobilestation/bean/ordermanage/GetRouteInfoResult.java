package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import java.util.HashMap;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: GetRouteInfoResult
 * @Date: 2017/3/27
 * @Description: 解析获取路由信息接口返回对象
 */
public class GetRouteInfoResult {
    private int Status;
    private String Message;
    private HashMap<Long, List<RouteInfo>> data;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public HashMap<Long, List<RouteInfo>> getData() {
        return data;
    }

    public void setData(HashMap<Long, List<RouteInfo>> data) {
        this.data = data;
    }
}
