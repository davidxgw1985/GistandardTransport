package com.gistandard.transport.order.module.mobilestation.bean.want;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class QueryWantListReq extends AppBasePagerRequest {

    private Integer wantType;//1：我要接单 2：我要运输
    private String lineStart;//接单地点
    private String lineDest;//抵达地点

    private List<Integer> countTyId;

    public String getLineStart() {
        return lineStart;
    }

    public void setLineStart(String lineStart) {
        this.lineStart = lineStart;
    }

    public String getLineDest() {
        return lineDest;
    }

    public void setLineDest(String lineDest) {
        this.lineDest = lineDest;
    }

    public List<Integer> getCountTyId() {
        return countTyId;
    }

    public void setCountTyId(List<Integer> countTyId) {
        this.countTyId = countTyId;
    }

    public Integer getWantType() {
        return wantType;
    }

    public void setWantType(Integer wantType) {
        this.wantType = wantType;
    }
}
